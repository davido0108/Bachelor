function T=taylor_draw(f,a,b,n)
  clf; hold on; grid on; 
  axis([-2*pi 2*pi -3 3]); axis equal;
  %fh=function_handle(f);
  %fplot(fh,[a,b]);
  L={'f'};
  for i=1:n
    T=taylor(f,'order',i);
    Th=function_handle(T);
    fplot(Th,[a,b]);
    pause(1)
    L{end+1}=['T' num2str(i)];
  endfor
  legend(L,'location','northeastoutside');
endfunction