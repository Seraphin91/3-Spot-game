package gestion;

public class Plateau {
    private final int nb_cases = 9;//Nombre de cases dans le plateau
    private String tableau;//String contenant les attributs dans chacune des cases

    public enum Couleur { //Les noms des états des cases
        BLEU('B'),
        ROUGE('R'),
        BLANC('W'),
        VIDE(' '),
        SPOT('0');

        public char getSymbole(){
            return symbole;
        }
        private final char symbole;
        Couleur(char symbole) {
            this.symbole = symbole;

        }
    }
    public Plateau(){//initialise les cases du tableaux
        this.tableau = "         ";
    }

    public char get_case(int indice){//Retourne l'attribut d'une case
        return this.tableau.charAt(indice);
    }

    public void set_spot(){//Remet les Spots dans le tableau si aucune pièce n'est positionnée dessus
        for(int i = 2; i < nb_cases; i+=3){
            if(this.tableau.charAt(i) == Couleur.VIDE.symbole){
                this.set_case(Couleur.SPOT.symbole,i);
            }
        }
    }

    public int get_nbcase(){
        return this.nb_cases;
    }

    public boolean est_libre(int indice,Piece piece){//Regarde si une piece peut se placer sur une case (La case doit soit être vide, soit avoir un spot, soit avoir le joueur dessus)
        return this.get_case(indice) == Couleur.VIDE.symbole || this.get_case(indice) == Couleur.SPOT.symbole || this.get_case(indice) == piece.get_nom();
    }

    public void set_case (char lettre,int indice){//Modifie la case choisie
        char[] nouveau_tableau = this.tableau.toCharArray();
        nouveau_tableau[indice] = lettre;
        this.tableau = new String(nouveau_tableau);
    }

    public String affiche_tableau(){//Créer l'affichage du tableau pour l'utilisateur
        int indice = 0;
        StringBuilder plateau = new StringBuilder();
        plateau.append("* * * * * * * * * * * * *\n");
        plateau.append("*       *       *       *\n");
        plateau.append("*   ").append(get_case(indice++)).append("   *   ").append(get_case(indice++)).append("   *   ").append(get_case(indice++)).append("   *\n");
        plateau.append("*       *       *       *\n");
        plateau.append("* * * * * * * * * * * * *\n");
        plateau.append("*       *       *       *\n");
        plateau.append("*   ").append(get_case(indice++)).append("   *   ").append(get_case(indice++)).append("   *   ").append(get_case(indice++)).append("   *\n");
        plateau.append("*       *       *       *\n");
        plateau.append("* * * * * * * * * * * * *\n");
        plateau.append("*       *       *       *\n");
        plateau.append("*   ").append(get_case(indice++)).append("   *   ").append(get_case(indice++)).append("   *   ").append(get_case(indice++)).append("   *\n");
        plateau.append("*       *       *       *\n");
        plateau.append("* * * * * * * * * * * * *\n");
        return plateau.toString();
    }


}
