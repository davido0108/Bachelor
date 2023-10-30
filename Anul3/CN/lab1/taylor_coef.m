% coeficientul monomului de grad n
% din formula Taylor a functiei f 
% in jurul punctului a
function c=taylor_coef(f,n,a=0)
  if n<0
    c=sym(0);
    return;
  endif
  c=subs(diff(f,n),a)/factorial(n);
endfunction
