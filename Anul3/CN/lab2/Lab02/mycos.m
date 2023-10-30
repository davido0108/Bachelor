function y=mycos(x)
x=reducereper(x); 
semn=1;
%daca se afla in cadranul IV, fie x=2*pi-a. Aplicam cos (2*pi-a) = cos a
if x>3*pi/2
    x=2*pi-x;
%daca se afla in cadranul III, fie x=pi+a. Aplicam cos (pi+a) = -cos a
elseif x>pi
    x=x-pi; semn=-1;
%daca se afla in cadranul II, fie x=pi-a. Aplicam cos (pi-a) = -cos a
elseif x>pi/2
    x=pi-x; semn=-1;
end
if x<=pi/4
    y=cosred(x);
else
    y=sinred(pi/2-x);
end
y=semn*y;