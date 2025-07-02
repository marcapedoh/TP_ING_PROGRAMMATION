unit arbre;

    interface
        uses list_ptr;
        type T=integer;
        type arbreSpec= Record
                            racine:T;
                            sgauche:^arbreSpec;
                            sdroite:^arbreSpec;
                        end;
        pArbre  =^arbreSpec;

        function racine(a:pArbre ):T;
        procedure gauche(a:pArbre ;var G:pArbre );
        procedure droite(a:pArbre ;var b:pArbre );
        function videA(A:pArbre ):boolean;
        function feuille(a:pArbre ):boolean;
        procedure construireArbre(v:T;G,D:pArbre ;var A:pArbre );

    implementation

        function videA(A:pArbre ):boolean;
            begin   
                videA:=a=nil;
            end;
        function racine(a:pArbre ):T;
            begin
                racine:=a^.racine;
            end;

        procedure construireArbre(v:T;G,D:pArbre ;var A:pArbre );
            begin
                new(A);
                A^.racine:=v;
                A^.sgauche:=G;
                A^.sdroite:=D;
            end;

        function feuille(a:pArbre ):boolean;
            begin
                feuille:=(a<> nil) and (a^.sgauche=nil) and (a^.sdroite=nil);
            end;

        procedure gauche(a:pArbre ;var G:pArbre );
            begin
                G:=a^.sgauche;
            end;

        procedure droite(a:pArbre ;var b:pArbre );
            begin
                b:=a^.sdroite;
            end;
    end.