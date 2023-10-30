syms x
r1=pade_sym(exp(x),1,1,x)
r2=pade_sym(exp(x),2,2,x)
r3=exp(x)
fplot(r1,[-1,1],'r')
hold on
fplot(r2,[-1,1],'--or')
hold on 
fplot(r3,[-1,1],'g')
hold off
grid on
%Restul descreste mult mai rapid
%aproximarea este mai buna
%parametrii optimi sunt usor de gasit