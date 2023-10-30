
#include "cuda_runtime.h"
#include "device_launch_parameters.h"

#include <stdlib.h>
#include <stdio.h>
#include <fstream>


const int n=3,m=3;

// Matrices are stored in row-major order:
// M(row, col) = *(M.elements + row * M.width + col)
typedef struct {
    int width;
    int height;
    float* elements;
} Matrix;


__constant__ const float W[3][3] = {
    1.0 / 9 , 1.0 / 9 , 1.0 / 9 ,
    1.0 / 9 , 1.0 / 9 , 1.0 / 9 ,
    1.0 / 9 , 1.0 / 9 , 1.0 / 9 
};


cudaError_t boxFilterCuda(Matrix& IMG_IN, Matrix& IMG_OUT);

__global__ void boxFilter(Matrix IMG_IN, Matrix IMG_OUT)
{
    int i = blockIdx.x * blockDim.x + threadIdx.x; //row
    int j = blockIdx.y * blockDim.y + threadIdx.y; //col

    int N = IMG_IN.width, M = IMG_IN.height;


    for (int k = 0; k < n; ++k)
        for (int l = 0; l < m; ++l)
            if (i - n / 2 + k >= 0 && i - n / 2 + k < N && j - m / 2 + l >= 0 && j - m / 2 + l < M)
            {
                IMG_OUT.elements[i * N + j] += (IMG_IN.elements[(i - n / 2 + k)*N + j - m / 2 + l] * W[k][l]);
               // printf("%f", IMG_OUT.elements[(i - n / 2 + k) * N + j - m / 2 + l]);
            }



}
void readImage(const std::string& fName, Matrix& IMG_IN)
{
    std::ifstream fin(fName);
    int height, width;
    fin >> height >> width;
    for (int i = 0; i < height; ++i)
        for (int j = 0; j < width; ++j)
            fin >> IMG_IN.elements[i * width + j];

    
}

// Thread block size
#define BLOCK_SIZE 10

int main()
{
    const int N = 10;
    const int M = 10;
    Matrix IMG_IN;
    Matrix IMG_OUT;

    IMG_IN.elements = new float[N * M];
    IMG_IN.height = N;
    IMG_IN.width = M;

    IMG_OUT.elements = new float[N * M];
    IMG_OUT.height = N;
    IMG_OUT.width = M;

    for (int i = 0; i < 10; ++i)
        for (int j = 0; j < 10; ++j)
            IMG_IN.elements[i*N + j] = i * 10 + j;

    readImage("in.txt", IMG_IN);

    cudaError_t cudaStatus = boxFilterCuda(IMG_IN, IMG_OUT);
    if (cudaStatus != cudaSuccess) {
        fprintf(stderr, "BOX FILTER FAILED!");
        return 1;
    }
    

    for (int i = 0; i < 10; ++i) {
        for (int j = 0; j < 10; ++j) {
            printf("%.6f ", IMG_OUT.elements[i * N + j]);
        }
        printf("\n");
    }


    return 0;
}

cudaError_t boxFilterCuda(Matrix& h_in, Matrix& h_out)
{
    Matrix dev_in;
    dev_in.height = h_in.height;
    dev_in.width = h_in.width;
    size_t size = dev_in.width * dev_in.height * sizeof(int);
    cudaMalloc(&dev_in.elements, size);
    cudaMemcpy(dev_in.elements, h_in.elements, size, cudaMemcpyHostToDevice);


    Matrix dev_out;
    dev_out.height = h_in.height;
    dev_out.width = h_in.width;
    cudaMalloc(&dev_out.elements, size);

    cudaError_t cudaStatus;

    // blocksize
    dim3 dimBlock(BLOCK_SIZE, BLOCK_SIZE);
    // gridsize
    dim3 dimGrid(h_in.width / dimBlock.x, h_in.height / dimBlock.y);
    
    // Launch a kernel on the GPU with one thread for each element.
    boxFilter <<< dimGrid, dimBlock >>> (dev_in, dev_out);
   
    // Read C from device memory
    cudaStatus = cudaMemcpy(h_out.elements, dev_out.elements, size,
        cudaMemcpyDeviceToHost);


    
    cudaDeviceSynchronize();
    if (cudaStatus != cudaSuccess) {
        fprintf(stderr, "cudaMemCpy failed!");
        goto Error;
    }



Error:
    cudaFree(dev_in.elements);
    cudaFree(dev_out.elements);

    return cudaStatus;
}
