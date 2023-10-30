function x=choleskySolve(A, b)
  
  % R ~ U
  R=Cholesky(A);

  L=R';

  % matricea identitate
  P=eye(length(A));

  y = forwardsSubs(L, P*b);

  x = backwardsSubs(R, y);

end