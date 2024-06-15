package gestion;

public class Piece {
    private char nom;//Nom de la pièce
    private int coordonnee_x;//Coordonnee principale de la pièce
    private int coordonnee_y;//Coordonne secondaire de la pièce
    public Piece (char lettre){
        this.nom = lettre;
    }



    public char get_nom (){
        return this.nom;
    }

    public int  get_coordonnee_x() {
        return this.coordonnee_x;
    }

    public int  get_coordonnee_y() {
        return this.coordonnee_y;
    }
    public void set_coordonnee (int nouv_coordonnee_x, int nouv_coordonnee_y) {//modifie les coordonnees de la pièce
        this.coordonnee_x = nouv_coordonnee_x;
        this.coordonnee_y = nouv_coordonnee_y;
    }

}
