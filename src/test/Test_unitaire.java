package test;

import gestion.Jeu;
import gestion.Joueur;

public class Test_unitaire {
    public void Test() {
        Joueur j1_1 = new Joueur();//Initialise joueur 1
        Joueur j2_2 = new Joueur();//Initialise joueur 2
        Jeu jeu_1 = new Jeu();//Initialise le jeu
        jeu_1.initialise_jeu(j1_1,j2_2);//Insérer manuellement "Bleu" pour j1
        assert(j1_1.get_numero() == 1 && j1_1.get_piece().get_nom() == 'B');
        assert(j2_2.get_numero() == 2 && j2_2.get_piece().get_nom() == 'R');
        assert(j1_1.get_piece().get_coordonnee_x() == 7 && j1_1.get_piece().get_coordonnee_y() == 8);
        assert(j2_2.get_piece().get_coordonnee_x() == 1 && j2_2.get_piece().get_coordonnee_y() == 2);
        assert(jeu_1.get_piece_blanche().get_nom() == 'W' && jeu_1.get_piece_blanche().get_coordonnee_x() == 4 && jeu_1.get_piece_blanche().get_coordonnee_y() == 5);
        Joueur j1 = new Joueur();//Initialise joueur 1
        Joueur j2 = new Joueur();//Initialise joueur 2
        Jeu jeu = new Jeu();//Initialise le jeu
        jeu.initialise_jeu(j1,j2);//Insérer manuellement "Rouge" pour j1
        assert(j1.get_numero() == 1 && j1.get_piece().get_nom() == 'R');
        assert(j2.get_numero() == 2 && j2.get_piece().get_nom() == 'B');
        assert(j1.get_piece().get_coordonnee_x() == 1 && j1.get_piece().get_coordonnee_y() == 2);
        assert(j2.get_piece().get_coordonnee_x() == 7 && j2.get_piece().get_coordonnee_y() == 8);
        assert(j1.get_points() == 0 && j2.get_points() == 0);
        assert(jeu.get_plateau().est_libre(0,j1.get_piece()));
        assert(jeu.get_plateau().get_case(2) == 'R');
        assert(jeu.get_plateau().get_case(5) == 'W');
        assert(jeu.get_plateau().get_case(8) == 'B');
        jeu.get_plateau().set_case('R',0);
        assert(jeu.get_plateau().get_case(0) == 'R');
        jeu.get_plateau().set_case(' ',0);
        assert(jeu.get_plateau().get_case(0) == ' ');
        assert(jeu.get_plateau().est_libre(0,j1.get_piece()));
        assert(!jeu.get_plateau().est_libre(8,j1.get_piece()));
        assert(jeu.get_plateau().est_libre(3,j2.get_piece()));
        assert(!jeu.get_plateau().est_libre(1,j2.get_piece()));
        assert(jeu.get_plateau().est_libre(6,jeu.get_piece_blanche()));
        assert(!jeu.get_plateau().est_libre(2,jeu.get_piece_blanche()));
        jeu.next();
        jeu.donne_points();
        assert(j1.get_points() == 1);
        assert(j2.get_points() == 0);
        String possible = jeu.possibilite(j1.get_piece());
        assert(possible.charAt(jeu.get_liste_indice()[0]) == '1');
        assert(possible.charAt(jeu.get_liste_indice()[1]) == ' ');
        assert(possible.charAt(jeu.get_liste_indice()[2]) == '0');
        assert(possible.charAt(jeu.get_liste_indice()[3]) == '2');
        assert(possible.charAt(jeu.get_liste_indice()[4]) == 'W');
        assert(possible.charAt(jeu.get_liste_indice()[6]) == '3');
        assert(possible.charAt(jeu.get_liste_indice()[7]) == 'B');
        possible = jeu.possibilite((jeu.get_piece_blanche()));
        assert(possible.charAt(jeu.get_liste_indice()[0]) == ' ');
        assert(possible.charAt(jeu.get_liste_indice()[1]) == 'R');
        assert(possible.charAt(jeu.get_liste_indice()[3]) == '-');
        assert(possible.charAt(jeu.get_liste_indice()[3]-1) == '1');
        assert(possible.charAt(jeu.get_liste_indice()[3]+1) == '2');
        assert(possible.charAt(jeu.get_liste_indice()[5]) == '0');
        assert(possible.charAt(jeu.get_liste_indice()[6]) == '3');
        assert(possible.charAt(jeu.get_liste_indice()[7]) == 'B');
        possible = jeu.possibilite((j2.get_piece()));
        assert(possible.charAt(jeu.get_liste_indice()[0]) == ' ');
        assert(possible.charAt(jeu.get_liste_indice()[1]) == 'R');
        assert(possible.charAt(jeu.get_liste_indice()[3]) == '1');
        assert(possible.charAt(jeu.get_liste_indice()[4]) == 'W');
        assert(possible.charAt(jeu.get_liste_indice()[8]) == '0');
        assert(possible.charAt(jeu.get_liste_indice()[6]) == '-');
        assert(possible.charAt(jeu.get_liste_indice()[6]-1) == '2');
        assert(possible.charAt(jeu.get_liste_indice()[6]+1) == '3');
        assert(possible.charAt(jeu.get_liste_indice()[7]) == ' ');
        jeu.mouvement(0,true,j1.get_piece());
        jeu.get_plateau().set_spot();
        assert(jeu.get_plateau().get_case(0) == 'R');
        assert(jeu.get_plateau().get_case(2) == '0');
        assert(j1.get_piece().get_coordonnee_x() == 0 && j1.get_piece().get_coordonnee_y() == 1);
        jeu.mouvement(5,false,jeu.get_piece_blanche());
        jeu.next();
        assert(jeu.Tour().get_numero() == 2);
        possible = jeu.possibilite(jeu.Tour().get_piece());
        assert(possible.charAt(jeu.get_liste_indice()[0]) == 'R');
        assert(possible.charAt(jeu.get_liste_indice()[2]) == 'W');
        assert(possible.charAt(jeu.get_liste_indice()[5]) == 'W');
        assert(possible.charAt(jeu.get_liste_indice()[3]) == '1');
        assert(possible.charAt(jeu.get_liste_indice()[6]) == '-');
        assert(possible.charAt(jeu.get_liste_indice()[6]-1) == '2');
        assert(possible.charAt(jeu.get_liste_indice()[6]+1) == '3');
        assert(possible.charAt(jeu.get_liste_indice()[7]) == '4');
        assert(possible.charAt(jeu.get_liste_indice()[8]) == '0');
    }
}