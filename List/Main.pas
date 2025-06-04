
program main;
    uses list_tab,application;
    
    var
        L1, L2: List;
        choix, pos, val, occ: Integer;


    begin
        vide(L1);
        vide(L2);
        repeat
            Writeln;
            Writeln('======= PROGRAMME TYPE ABSTRAIT DONNEE (LIST) =======');
            Writeln('1. Creer une liste');
            Writeln('2. Inserer un element');
            Writeln('3. Concatener deux listes');
            Writeln('4. Compter les occurrences');
            Writeln('5. Afficher l''element ieme');
            Writeln('6. Supprimer un element a une position');
            Writeln('7. Supprimer toutes les occurrences');
            Writeln('8. Afficher la liste');
            Writeln('0. Quitter');
            Write('Choix : ');
            ReadLn(choix);

            case choix of
                1: begin
                    Write('Nombre d''elements a inserer : ');
                    ReadLn(val);
                    createListe(L1, val);
                    end;

                2: begin
                    Write('Valeur a inserer : ');
                    ReadLn(val);
                    Write('Position : ');
                    ReadLn(pos);
                    inserer(L1, pos, val);
                    end;

                3: begin
                    Write('Combien d''elements pour la 2e liste ? ');
                    ReadLn(val);
                    createListe(L2, val);
                    concatenerList(L1, L2);
                    end;

                4: begin
                    Write('Valeur a chercher : ');
                    ReadLn(val);
                    occ := occurence(L1, val);
                    Writeln('Nombre d''occurrences : ', occ);
                    end;

                5: begin
                    Write('Position : ');
                    ReadLn(pos);
                    val := ieme(L1, pos);
                    Writeln('Element en position ', pos, ' : ', val);
                    end;

                6: begin
                    Write('Position a supprimer : ');
                    ReadLn(pos);
                    supprimer(L1, pos);
                    end;

                7: begin
                    Write('Valeur a supprimer : ');
                    ReadLn(val);
                    suppOccurence(L1, val);
                    end;

                8: begin
                    Write('Liste : ');
                    afficherListe(L1);
                    end;
            end;

        until choix = 0;

        Writeln('Fin du programme.');
    end.