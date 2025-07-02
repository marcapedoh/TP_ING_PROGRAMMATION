program main;

    uses crt, list_ptr, arbre, application;

    var
        L, LTriee: List;
        A: pArbre;
        choix, val: integer;
       

    procedure afficherListe(L: List);
    var
        i, n: integer;
    begin
        n := longueur(L);
        for i := 1 to n do
            write(ieme(L, i), ' ');
        writeln;
    end;

// Affichage visuel d'un arbre binaire simple en console
    procedure afficherArbreStylise(a: pArbre; prefixe: string; estDernier: boolean);
        begin
            if a <> nil then
            begin
                write(prefixe);

                if estDernier then
                    begin
                        write('+-- ');
                        prefixe := prefixe + '   ';
                    end
                else
                    begin
                        write('|-- ');
                        prefixe := prefixe + '|  ';
                    end;


                writeln(a^.racine);

                // Parcourir le sous-arbre gauche et droit
                if (a^.sgauche <> nil) or (a^.sdroite <> nil) then
                begin
                    if a^.sdroite <> nil then
                        afficherArbreStylise(a^.sdroite, prefixe, a^.sgauche = nil);

                    if a^.sgauche <> nil then
                        afficherArbreStylise(a^.sgauche, prefixe, true);
                end;
            end;
        end;

    procedure afficherMenu;
        begin
        writeln('Menu:');
        writeln('1 - Ajouter un entier à la liste');
        writeln('2 - Afficher l''arbre binaire trié');
        writeln('3 - Afficher la liste triée (parcours infixe)');
        writeln('0 - Quitter');
        write('Votre choix : ');
        end;


    begin
        clrscr;
        vide(L);
        A := nil;
        vide(LTriee);

        repeat
            afficherMenu;
            readln(choix);

            case choix of
                1: begin
                    writeln('Entrez des entiers à ajouter à la liste.');
                    writeln('Tapez -1 pour terminer l insertion.');

                    while True do
                        begin
                            write('> ');
                            readln(val);
                            if val = -1 then
                            begin
                                writeln('Fin de l insertion.');
                                break; // sortir si -1
                            end;
                            inserer(L, longueur(L) + 1, val);
                            writeln('Entier ajouté : ', val);
                        end;
                    end;

                2: begin
                    A := construireArbre(L);
                    writeln;
                    writeln('Arbre binaire trié (affichage stylisé) :');
                    afficherArbreStylise(A, '', true);
                    writeln;
                    writeln('Appuyez sur Entrée pour continuer...');
                    readln;
                    end;

                3: begin
                    if A = nil then
                        A := construireArbre(L);
                    vide(LTriee);
                    parcoursInfixe(A, LTriee);
                    writeln;
                    writeln('Liste triée après parcours infixe :');
                    afficherListe(LTriee);
                    writeln;
                    writeln('Appuyez sur Entrée pour continuer...');
                    readln;
                    end;

                0: writeln('Fin du programme.');

            else
            writeln('Choix invalide.');
            readln;
            end;

            clrscr;
        until choix = 0;
end.

