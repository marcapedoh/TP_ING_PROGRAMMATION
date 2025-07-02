unit list_ptr;

interface

type
  T = Integer;
type
    elt= Record
            valeur:T;
            suivant:^elt;
        end;
    List=^elt;

function longueur(L: List):integer;
procedure concatenerList(var L:List;liste:List);
procedure inserer(var L:List;pos:integer;val:T);
procedure supprimer(var L:List;pos:integer);
function ieme(L:List;pos:integer):T;
procedure ieme(L:List;pos:integer;var val:T);
procedure vide(var L:List);

implementation
  
  function longueur(L:List):integer;
    var
      count:integer;
      p:List;
    begin
      P:=L;
      count:=0;
      while p<> nil do
        begin 
          count:=count+1;
          P:=P^.suivant;
        end;
      longueur:=count;
    end;

  procedure vide(var L:List);
    begin
      l:=nil;
    end;


  function ieme(L:List;pos:integer):T;
    var
      p: List;
      i: Integer;
    begin
      p := L;
      i := 1;
      while (p <> nil) and (i < pos) do
        begin
          p := p^.suivant;
          i := i + 1;
        end;

      if (p = nil) then 
        writeln('Position invalide')
      else
        ieme := p^.valeur;
    end;

  procedure ieme(L: List; pos: integer; var val: T);
    var
      p: List;
      i: integer;
    begin
      p := L;
      i := 1;
      while (p <> nil) and (i < pos) do
        begin
          p := p^.suivant;
          i := i + 1;
        end;

      if p = nil then
        writeln('Position invalide')
      else
        val := p^.valeur;
    end;
  procedure supprimer(var L: List; pos: integer);
    var
      courant, precedent: List;
      i: integer;
    begin
      if (L = nil) or (pos <= 0) then
      begin
        writeln('Position invalide');
        exit;
      end;

      courant := L;
      precedent := nil;
      i := 1;

      if pos = 1 then
      begin
        L := L^.suivant; 
        dispose(courant); 
        writeln('Element trouve et supprime');
        exit;
      end;

      while (courant <> nil) and (i < pos) do
      begin
        precedent := courant;
        courant := courant^.suivant;
        i := i + 1;
      end;

      if courant = nil then
      begin
        writeln('Position invalide');
        exit;
      end;

      precedent^.suivant := courant^.suivant;
      dispose(courant);
    end;

  procedure inserer(var L: List; pos: integer; val: T);
    var
      nouveau, courant, precedent: List;
      i: Integer;
    begin
      if pos <= 0 then
      begin
        writeln('Position invalide');
        exit;
      end;

      new(nouveau);
      nouveau^.valeur := val;
      nouveau^.suivant := nil;

      if pos = 1 then
      begin
        nouveau^.suivant := L;
        L := nouveau;
        exit;
      end;

      courant := L;
      precedent := nil;
      i := 1;

      while (courant <> nil) and (i < pos) do
      begin
        precedent := courant;
        courant := courant^.suivant;
        i := i + 1;
      end;

      if (i <> pos) then
      begin
        writeln('Position invalide');
        dispose(nouveau);
        exit;
      end;

      precedent^.suivant := nouveau;
      nouveau^.suivant := courant;
    end;

  procedure concatenerList(var L: List; liste: List);
    var
      p: List;
    begin
      if L = nil then
      begin
        L := liste;
      end
      else
      begin
        p := L;
        while p^.suivant <> nil do
          p := p^.suivant;

        p^.suivant := liste;
      end;
    end;

end.