function R=mysin(y)
    syms x
    sn = pade_sym(sin(x),3, 3,x);
    fc = matlabFunction(sn);
    R=fc(y)
end