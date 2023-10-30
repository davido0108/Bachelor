function c=taylor_coef(f,n)
%bessel e greu de calculat in special cu 0 folosim epsilon ca sa nu fie
%departe de rezultat si sa evitam division by zero
a=1e-3
if n<0
    c=sym(0);
    return
end
z=f
c=subs(diff(f,n),a)/factorial(n);
end

