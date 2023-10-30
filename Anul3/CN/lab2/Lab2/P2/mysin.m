function y=mysin(x)
x=reducereper(x);       % asta ti-l reduce la [0,360]
semn=1;
        
if x>3*pi/2             % reducere la primul cadran
    x=2*pi-x; semn=-1;
elseif x>pi
    x=x-pi; semn=-1;
elseif x>pi/2
    x=pi-x;
end

if x<=pi/4              % reducere la primul octan
    y=sinred(x);
else
    y=cosred(pi/2-x);
end
y=semn*y;

end