unit application;

    interface 
        uses list_ptr,arbre;

        function insererEntierArbre(val: T; a: pArbre ): pArbre;
        function construireArbre(liste: List): pArbre;
        procedure parcoursInfixe(a: pArbre; var L: List);

    implementation
        function insererEntierArbre(val: T; a: pArbre ): pArbre ;
            begin
                if a = nil then
                begin
                    new(a);
                    a^.racine := val;
                    a^.sgauche := nil;
                    a^.sdroite := nil;
                end
                else if val < a^.racine then
                    a^.sgauche := insererEntierArbre(val, a^.sgauche)
                else
                    a^.sdroite := insererEntierArbre(val, a^.sdroite);
                insererEntierArbre := a;
            end;

    // Construire un arbre trié à partir d'une liste
        function construireArbre(liste: List): pArbre ;
            var
                i, n: integer;
                val: T;
                a: pArbre;
            begin
                a := nil;
                n := list_ptr.longueur(liste);
                for i := 1 to n do
                begin
                    val := list_ptr.ieme(liste, i);
                    a := insererEntierArbre(val, a);
                end;
                construireArbre := a;
            end;

    // Parcours infixé (gauche - racine - droite) pour obtenir une liste triée
        // procedure parcoursInfixe(a: pArbre ; var L: List);
        //     begin
        //         if a <> nil then
        //         begin
        //             parcoursInfixe(a^.sgauche, L);
        //             inserer(L, longueur(L) + 1, a^.racine);
        //             parcoursInfixe(a^.sdroite, L);
        //         end;
        //     end;

        procedure parcoursInfixe(a: pArbre; var L: List);
            begin
                if a <> nil then
                begin
                    parcoursInfixe(a^.sgauche, L);
                    inserer(L, longueur(L) + 1, a^.racine);
                    parcoursInfixe(a^.sdroite, L);
                end;
            end;



    end.