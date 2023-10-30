function x=forwardsSubs(A, b)
  % A = matricea triunghiulara superior
  % b = rezultatul matricei/ termenii liberi
  % length(b) = marimea lui b
  
  % initializam pe x cu b, ne intereseaza marimea lui
  % nu valorile din el deoarece totul se va suprascrie

  x=b;
  
  for i=1:1:length(b)
  
    A(i, i+1:end);    

    A(i, i+1:end)*x(i+1:end);

    x(i)=(b(i)-A(i, 1:i-1)*x(1:i-1)) / A(i,i);
    
  end
  
end