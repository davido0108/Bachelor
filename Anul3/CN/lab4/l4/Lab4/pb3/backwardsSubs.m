function x=backwardsSubs(A, b)
  % A = matricea triunghiulara superior
  % b = rezultatul matricei/ coloana termenii liberi
  % x = coloana necunoscutelor
  % length(b) = marimea lui b = marimea lui A
  
  % initializam pe x cu b, ne intereseaza marimea lui
  % nu valorile din el deoarece totul se va suprascrie

  x=b;
  
  for i=length(b):-1:1
    % pas = -1, interval: [length(b), 1]  
  
    % sutem pe linia i, si vrem sa ii accesam toti 
    % coeficientii cu rosu 
    % A(liniile interesante, coloanele interesante)
    A(i, i+1:end);    
    
    % pe o linie avem toti coeficientii pe care vrem sa
    % ii inmultim cu o bucata din coloana x-lor
    A(i, i+1:end)*x(i+1:end);
    % lucram destul de compact
    % este inmultire matriciala, A(i, i+1:end) este o 
    % linie si daca vreau sa obtin suma de produse
    % trebuie sa inmultesc cu o coloana nu o linie
    % inmutirea matriciala = linie * coloana
    % x este coloana si accesam valorile care ne intere
    % asta a fost suma de produse 
    
    
    % daca avem suma de produse putem calcula x(i)
    x(i)=(b(i)-A(i, i+1:end)*x(i+1:end))/A(i,i);
    % 
    % 
    
  end
  
end