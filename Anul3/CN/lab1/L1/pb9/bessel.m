syms x t
aux=int(cos(x*sin(t)),t,0,pi)/pi;

r1= pade_sym(aux,2, 2,x)
r2= pade_sym(aux,4,3,x)
r3= pade_sym(aux,2,4,x)
fplot(r1,[-2,2],'r')
hold on
fplot(r2,[-2,2],'--or')
hold on
fplot(r3,[-2,2],'g')
hold off
grid on