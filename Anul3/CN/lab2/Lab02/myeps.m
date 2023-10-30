function x=myeps(myone)
  %>>myeps(single(1))
  %>>myeps(double(1))
  x=myone;
  while 1+x/2>1
    x=x/2;
  end
