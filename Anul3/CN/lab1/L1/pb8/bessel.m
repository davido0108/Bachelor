aux=@(x)integral(@(t) cos(2*x*sin(t)),0,pi)/pi

r1=@(x)pade_sym(aux(x),2,2,x)
r2=@(x)pade_sym(aux(x),4,3,x)
r3=@(x)pade_sym(aux(x),2,4,x)


r1(0)