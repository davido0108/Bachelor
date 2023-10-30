function x=mymin(Nrbiti)
    %cel mai mic nr. normal
    %daca nu setam Nrbiti se va pune 64
    if nargin < 1
        Nrbiti = 64;
    end
    if Nrbiti==32
        x=single(1);
        exp=126;
    else
        x=1;
        exp=1022;
    end
    
    x = x * power(2, -exp);
end
% x-ul va ajunge la valoarea 1*(2^-126)

% >> num2float(mymin(32))
% ans = 0 00000001 00000000000000000000000 = 000...000 = -127


% ar mai fi o varianta de generare, sa folosesc myminsubnorm si sa inmultesc
% numarul x cu numarul de biti din mantisa ca sa mut
