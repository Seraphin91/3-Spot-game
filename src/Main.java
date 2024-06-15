import gestion.Jeu;
import gestion.Joueur;

//TIP To <b>Run</b> code, press <shortcut actionId="Run"/> or
// click the <icon src="AllIcons.Actions.Execute"/> icon in the gutter.
public class Main {
    public static void main(String[] args) {
        Joueur j1 = new Joueur();//Initialise joueur 1
        Joueur j2 = new Joueur();//Initialise joueur 2
        Jeu jeu = new Jeu();//Initialise le jeu
        jeu.initialise_jeu(j1,j2);
        while(!jeu.Tour().a_12_points()){//Tant que un joueur n'a pas 12 points
            jeu.next();//Au prochain joueur de jouer
            jeu.get_plateau().set_spot();//On actualise les spot
            jeu.partie(jeu.possibilite(jeu.Tour().get_piece()),jeu.Tour().get_piece());//Le tour du joueur
            jeu.get_plateau().set_spot();//On actualise les spots
            jeu.donne_points();//On actualise les points du joueur
            if(!jeu.Tour().a_12_points())
                jeu.partie(jeu.possibilite(jeu.get_piece_blanche()),jeu.get_piece_blanche());//Le joueur doit bouger la piece blanche
        }
        System.out.println("-------------------------------------------------------------------------------");
        if(jeu.get_liste()[1].a_moins_6_points()){//Regarde si l'autre joueur a moins de 6 points
            System.out.println("Le joueur " + jeu.get_liste()[1].get_numero() + " a gagne");
        }
        else {
            System.out.println("Le joueur " + jeu.Tour().get_numero() + " a gagne");
        }/*
        test.Test_unitaire test = new test.Test_unitaire();
        test.Test();*/
    }
}