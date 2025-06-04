unit application;
   
    interface
        uses list_tab;

        function occurence(L: List; value: integer): integer;
        procedure createListe(var L: List; taille: Integer);
        procedure afficherListe(L: List);
        procedure suppOccurence(var L: List; value: integer);

    implementation
        function occurence(L: List; value: integer): integer;
            var
                i, count, len: integer;
            begin
                len := longueur(L);
                count := 0;

                for i := 1 to len do
                    begin
                        if ieme(L, i) = value then
                        count := count + 1;
                    end;

                occurence := count;
            end;

        procedure createListe(var L: List; taille: Integer);
            var
                i, value: integer;
            begin
                vide(L);
                for i := 1 to taille do
                    begin
                        Write('Entrez l''element ', i, ': ');
                        ReadLn(value);
                        inserer(L, i, value);
                    end;
            end;

        procedure afficherListe(L: List);
            var
                i, len: integer;
            begin
                len := longueur(L);
                for i := 1 to len do
                    Write(ieme(L, i), ' ');
                Writeln;
            end;

        procedure suppOccurence(var L: List; value: integer);
            var
                pos, i, taille: integer;
            begin
                i := 1;
                taille := longueur(L);

                while (i <= taille) do
                    begin
                        if ieme(L, i) = value then
                            begin
                            supprimer(L, i);
                            taille := taille - 1;
                            end
                        else
                            i := i + 1;
                    end;
            end;
    end.


