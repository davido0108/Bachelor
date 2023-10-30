% pkg load symbolic;
%pkg load statistics;

n=10;
b=[];
for i=2:-1:-n+2
    b=[b;i];
end
A=zeros(n+1,n+1);
for i=1:n+1
    for j=1:n+1
        if i == j
            A(i,j)=1;
            continue
        end
        if n+1 == j
            A(i,j)=1;
            continue
        end
        if j<i
            A(i,j)=-1;
        end
    end
end
A
b

solQr = QRSolve(A, b);
solLup = LUPSolve(A, b);

solLup
solQr