function x=LUPSolve(A, b)
  [L,U,P] = lup(A);
  
  % L*y = P*b
  y = forwardsSubs(L, P*b);
  
  % U*x = y
  x = backwardsSubs(U, y);
  
end