function x=mymax(Nrbiti)
%cel mai mare nr. normal
if nargin < 1
        Nrbiti = 64;
end
if Nrbiti==32
    x=single(1);    % il reprezint pe 32
else
    x=1;            % by default el e pe 64
end
y=0;
while x*2 ~= Inf   % se merge pana la dreapta axei
  x=x*2; 
  y=y+1;    % dreapta axei este inf = 1*(2^128)
end 
% ma duc pana aproape de inf

                   % pana aici am facut 2^127 * 1.000...000
                   % 2^127 e cea mai mare putere a lui doi 
                   % pe care o putem reprezenta in calculator
ep=myeps(Nrbiti);
x=x*(2-ep);       

% y 127 pt 32
% y 1023 pt 64

end