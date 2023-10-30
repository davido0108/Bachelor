function x=eliminareGauss(A, b)
  % A = matricea triunghiulara superior
  % b = rezultatul matricei/ termenii liberi
  % x = vom calcula x, coloana necunoscutelor
  
  % toate operatiile de scadere de linii, se aplica
  % si b-urilor, ca sa ne fie mai usor vom concatena
  % b-ul cu A-ul si vom obtine matricea extinsa
  
  % asta este o concatenare simpla
  % care rezulta matricea extinsa
  A = [A, b];
  
  % parcurgem fiecare linie de pe care vom face 0 
  % sub ea
  for k = 1:length(b)-1
    
    % cautam pivotul
    % ca sa gasim linia cu cel mai mare pivotul
    % trebuie sa calculam un maxim
    % explicatia lui [val, ind] e in README
    [~, ind] = max(abs(A(k:end, k)));
    % pana aici s-a gasit pozitia pivotului
   
    % facem asta deoarece ind = pozitia din
    % intervalul k:end
    ind = ind + k - 1;
    
    if k ~= ind   
      % acum vom face o interschimbare de linii
      % ma intereseaza liniile k si ind
      % ma intereseaza coloanele k:end
      A([k, ind], k:end) = A([ind, k], k:end); 
    end
        
    % parcurgem liniile urmatoare ca sa facem 0 
    % pe ele
    % linile cu indicele i, reprezinta liniile 
    % albastre
    for i=k+1:length(b)
      
      % m reprezinta multiplicatorul
      m = A(i,k)/A(k,k);
      
      % de pe linia i, scadem linia cu pivotul
      % linai cu pivotul o inmultim cu m
      A(i, k:end) = A(i, k:end) - A(k, k:end)*m;
      
    end
  end
  
  % acum ca avem o matrice triunhiulara superioara
  % trebuie sa folosim backwardsSubs pentru a
  % o calcula
  
  % modalitatea simpla de a accesa ultima coloana
  b = A(:,end);
  
  % eliminam ultima coloana
  A(:,end) = [];
  
  x = backwardsSubs(A, b);
  
end