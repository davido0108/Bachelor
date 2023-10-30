function ccos=cosred(x)
 ccos=0;
 u=1;
 n=0;
 while abs(u)
   ccos=ccos+u;
   n++;
   u=power(-1,n)*power(x,2*n)/factorial(2*n);
 endwhile
