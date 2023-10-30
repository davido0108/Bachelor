function x=QRSolve(A, b)
  [qq,rr] = qr(A);
  x = rr\qq.'*b;
  
end