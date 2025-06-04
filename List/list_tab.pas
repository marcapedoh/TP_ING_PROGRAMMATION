unit list_tab;
    
interface
    const MAX_ELEMENT=100;
type
  T = Integer;
type 
    List=Record
            lg:integer;
            valeur:array[1..MAX_ELEMENT] of T;
        end;

function longueur(L: List):integer;
procedure concatenerList(var L:List;liste:List);
procedure inserer(var L:List;pos:integer;val:T);
procedure supprimer(var L:List;pos:integer);
function ieme(L:List;pos:integer):T;
procedure ieme(L:List;pos:integer;var val:T);
procedure vide(var L:List);


implementation

  function longueur(L:List):integer;
    begin
      longueur:=L.lg;
    end;

  procedure vide(var L:List);
    begin
      L.lg:=0;
    end;

  function ieme(L:List;pos:integer):T;
    begin
      ieme:=L.valeur[pos];
    end;
     

  procedure supprimer(var L:List;pos:integer);
    var
      i:integer;
    begin
      if (pos>=1) and (pos<=L.lg) then
        begin
          for i:=pos to L.lg-1 do
            L.valeur[i]:=L.valeur[i+1];
          L.lg:=L.lg-1;
        end;
    end;

  procedure inserer(var L:List;pos:integer;val:T);
    var
      i:integer;
    begin
      if (L.lg < MAX_ELEMENT) and (pos >= 1) and (pos <= L.lg + 1) then
        begin
    
          for i := L.lg downto pos do
            L.valeur[i + 1] := L.valeur[i];
   
          L.valeur[pos] := val;
          L.lg:=L.lg+1;
        end
      else
        writeln('Vous fournissez une position non pris en compte par le tableau');
    end;

  procedure ieme(L:List;pos:integer;var val:T);
    begin
      val:=ieme(L,pos);
    end;

  procedure concatenerList(var L: List; liste: List);
    var
      i: integer;
    begin
      if L.lg + liste.lg > MAX_ELEMENT then
      begin
        writeln('Erreur : dépassement de capacité lors de la concaténation.');
        exit;
      end;

      for i := 1 to liste.lg do
      begin
        L.valeur[L.lg + i] := liste.valeur[i];
      end;

      L.lg := L.lg + liste.lg;
    end;

end.
