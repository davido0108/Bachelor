function x=myeps(Nrbiti)
% calculam ori 32 de biti sau 64 de biti  
if Nrbiti==32
    x=single(1);
else
    x=1;
end
y=0;
while x/2+1>1
   x=x/2;
   y=y+1;
   
end
%y 23 pt 32 ;
%y 52 pt 64 
end
