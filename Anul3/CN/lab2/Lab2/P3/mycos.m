function R=mycos(y)
    syms x
    sn = pade_sym(cos(x),2, 4,x);
    fc = matlabFunction(sn);
    R=fc(y)
end