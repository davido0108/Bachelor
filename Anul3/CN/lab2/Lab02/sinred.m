function ssin=sinred(x)
 ssin=0;
 u=x;
 n=0;
 while abs(u)
   ssin=ssin+u;
   n++;
   u=power(-1,n)*power(x,2*n+1)/factorial(2*n+1);
 endwhile
