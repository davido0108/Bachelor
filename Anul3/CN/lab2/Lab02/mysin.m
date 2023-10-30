function y=mysin(x,digits=1000)
x=reducereper(x,digits); 
semn=1;
%daca se afla in cadranul IV, fie x=2*pi-a. Aplicam sin (2*pi-a) = -sin a
if x>3*pi/2
    x=2*pi-x; semn=-1;
%daca se afla in cadranul III, fie x=pi+a. Aplicam sin (pi+a) = -sin a
elseif x>pi
    x=x-pi; semn=-1;
%daca se afla in cadranul II, fie x=pi-a. Aplicam sin (pi-a) = sin a
elseif x>pi/2
    x=pi-x;
end
if x<=pi/4
    y=sinred(x);
else
    y=cosred(pi/2-x);
end
y=semn*y;