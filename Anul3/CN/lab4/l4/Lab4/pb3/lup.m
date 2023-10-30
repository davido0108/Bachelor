function [L,U,P]=lup(A)
  % ii dai A, iti da L, U, P
  %LUP - LUP decomposition of A
  %permute effectively lines
  [m,n]=size(A);
  P=zeros(m,n);
  piv=(1:m)';
  for i=1:m-1
    %pivoting
    [pm,kp]=max(abs(A(i:m,i)));
    kp=kp+i-1;
    %line interchange
    if i~=kp
      A([i,kp],:)=A([kp,i],:);
      piv([i,kp])=piv([kp,i]);
    end
    %Schur complement
    lin=i+1:m;
    A(lin,i)=A(lin,i)/A(i,i);
    A(lin,lin)= A(lin,lin) - A(lin,i) * A(i,lin);
  end
  for i=1:m
    P(i,piv(i))=1;
  end
  % triunghi superior
  U=triu(A);
  % inferior plus matricea identitate
  L=tril(A,-1)+eye(m);
  L
  U
  P
  ret=[L,U,P];
end