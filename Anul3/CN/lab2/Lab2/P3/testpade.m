syms x
%sn = pade_sym(sin(x),3, 3,x);
cs = pade_sym(cos(x),2,4,x);
fc = matlabFunction(cs);
fc(pi/6)