package gestion;

import java.util.Scanner;

public class Joueur {
    private static int nb_joueur = 1;//Nombre de joueurs initialise
    private static char lettre_precedante;//La couleur du premier joueur
    private final int numero;//Numero du joueur
    private int points;//Points du joueur

    Piece piece;//gestion.Piece du joueur

    public Joueur(){
        if(nb_joueur == 3)
            nb_joueur = 1;
        this.numero = nb_joueur;
        this.points = 0;
        if (nb_joueur == 1){//Quand on initialise le premier joueur
            String couleure ="";
            do {
                Scanner scan = new Scanner(System.in);
                System.out.println("Choisir une couleur ('R' pour Rouge et 'B' pour Bleu) :");
                couleure = scan.nextLine();
            }while(!couleure.equals("R") && !couleure.equals("B"));//Tant que le joueur 1 n'a pas choisi sa couleur
            this.piece = new Piece(couleure.charAt(0));
            lettre_precedante = this.piece.get_nom();//On stock la couleur du joueur 1
        }
        else{//Quand on initialise le joueur 2
            if (lettre_precedante == 'R'){//On donne sa couleur en fonction de ce qu'a choisi le joueur 1
                this.piece = new Piece('B');
            }
            else {
                this.piece = new Piece('R');
            }
        }
        nb_joueur+=1;

    }

    public Piece get_piece(){
        return this.piece;
    }

    public int get_numero (){
        return this.numero;
    }

    public int get_points (){
        return this.points;
    }

    public void set_points (int nb_points) {
        this.points+= nb_points;
    }

    public boolean a_12_points (){
        return this.points >= 12;
    }//Regarde si le joueur a 12 points

    public boolean a_moins_6_points (){
        return this.points < 6;
    }//Regarde si le joueur a moins de 6 points

}
