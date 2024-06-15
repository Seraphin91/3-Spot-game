package gestion;

import java.util.Scanner;

public class Jeu {
    private Plateau plateau;//Le plateau du jeu
    private Piece blanche;//La piece blanche

    private int L1 = 56;//Indice de la premiere case dans le tableau affiche (ligne 1)
    private int L2 = 160;//Indice de la troisieme case dans le tableau affiche (ligne 2 colonne 1)
    private int L3 = 264;//Indice de la cinquieme case dans le tableau affiche (ligne 13 colonne 1)

    private Joueur[] liste_joueur;//Liste contenant les deux joueurs

    private int[] liste_indice;//Liste contenant les indices des cases dans le string du plateau

    private final int TROIS= 3;

    private int HUIT = 8;


    public Jeu() {
        this.plateau = new Plateau();//initialise le plateau
        this.blanche = new Piece(Plateau.Couleur.BLANC.getSymbole());//initialise la piece blanche
        this.liste_joueur = new Joueur[2];
        this.liste_indice = new int[this.plateau.get_nbcase()];
        int i = 0;
        for(int j = 0; j < TROIS; ++j)//On ajoute les indices des 3 premieres cases
        {
            this.liste_indice[i++] = L1+HUIT*j;
        }
        for(int k = 0; k < TROIS; ++k){//On ajoute les indices des cases de la ligne 2 du plateau
            this.liste_indice[i++] = L2+HUIT*k;
        }
        for(int l = 0; l < TROIS; ++l){//On ajoute les indices des cases de la ligne 3 du plateau
            this.liste_indice[i++] = L3+HUIT*l;
        }
    }

    public Piece get_piece_blanche(){
        return this.blanche;
    }

    public Plateau get_plateau(){return this.plateau;}

    public void initialise_jeu(Joueur j1, Joueur j2){//initialise le jeu en mettant l'emplacement par défaut des joueurs dans le plateau
        this.plateau.set_case(Plateau.Couleur.ROUGE.getSymbole(), 1);
        this.plateau.set_case(Plateau.Couleur.ROUGE.getSymbole(), 2);
        this.plateau.set_case(Plateau.Couleur.BLEU.getSymbole(), 7);
        this.plateau.set_case(Plateau.Couleur.BLEU.getSymbole(), 8);
        this.plateau.set_case(Plateau.Couleur.BLANC.getSymbole(), 4);
        this.plateau.set_case(Plateau.Couleur.BLANC.getSymbole(),5);
        this.blanche.set_coordonnee(4,5);
        if(j1.piece.get_nom() == Plateau.Couleur.ROUGE.getSymbole()) {
            j1.piece.set_coordonnee(1,2);
            j2.piece.set_coordonnee(7,8);
        }
        else {
            j2.piece.set_coordonnee(1,2);
            j1.piece.set_coordonnee(7,8);
        }
        this.liste_joueur[1] = j1;
        this.liste_joueur[0] = j2;
    }

    public void next(){//Passe au tour du prochain joueur
        Joueur joueurtmp = this.liste_joueur[1];
        this.liste_joueur[1] = this.liste_joueur[0];
        this.liste_joueur[0] = joueurtmp;
    }
    public Joueur Tour(){
        return this.liste_joueur[0];
    }//Renvoi le joueur qui doit jouer

    public Joueur[] get_liste() {//Renvoi la liste de joueur
        return this.liste_joueur;
    }

    public int[] get_liste_indice(){return this.liste_indice;}

    public void donne_points(){//Donne les points aux joueurs en fonction de si leur piece est sur un spot
        if(this.Tour().piece.get_coordonnee_x() == 2 || this.Tour().piece.get_coordonnee_x() == 5 || this.Tour().piece.get_coordonnee_x() == 8)
            this.Tour().set_points(1);
        if(this.Tour().piece.get_coordonnee_y() == 2 || this.Tour().piece.get_coordonnee_y() == 5 || this.Tour().piece.get_coordonnee_y() == 8)
            this.Tour().set_points(1);
    }

    public void affiche_points(){//Affiche les points des joueurs au debut de chaque tour
        for(int i = 0; i < 2; ++i){
            StringBuilder phrase = new StringBuilder("Joueur " + this.liste_joueur[i].get_numero() +" : " + this.liste_joueur[i].get_points());
            if(this.liste_joueur[i].get_points() <= 1)
                phrase.append(" point");
            else
                phrase.append(" points");
            System.out.println(phrase);
        }
    }

    public boolean Est_bon(int indice, int direction,Piece piece){//direction = 0 -> horizontale, direction = 1 -> direction verticale, direction = 2 -> les deux
        if(direction == 0)
            return this.plateau.get_case(indice) != piece.get_nom() || this.plateau.get_case(indice+1) != piece.get_nom();
        else if (direction == 1)
            return this.plateau.get_case(indice) != piece.get_nom() || this.plateau.get_case(indice-TROIS) != piece.get_nom();
        else
            return this.plateau.get_case(indice) != piece.get_nom() || (this.plateau.get_case(indice+1) != piece.get_nom() && this.plateau.get_case(indice-TROIS) != piece.get_nom());
    }

    public String possibilite(Piece piece){//Algorithme de recherche qui va verifier tous les mouvements possibles d'une piece et renvois l'affiche du plateau avec les indices à l'interieur
        String tab = this.plateau.affiche_tableau();
        char nb = '1';
        for(int i = 0; i < 9; ++i){//Parcourt toutes les cases et va en fonction de la case faire des verifications differentes
            if((i == 0 || i == 1) && this.Est_bon(i,0,piece) && this.plateau.est_libre(i,piece) && this.plateau.est_libre(i+1,piece)){//Si on est dans la case 1 et 2, on verifie seulement si la piece peut etre posee horizontalement
                tab = tab.substring(0, this.liste_indice[i]) + nb + tab.substring(this.liste_indice[i] + 1);//On ajoute un numero dans le tableau correspondant a la position possible trouvee
                nb+=1;
            }
            else if((i == 5 || i == 8)&& this.Est_bon(i,1,piece) && this.plateau.est_libre(i,piece) && this.plateau.est_libre(i-TROIS,piece)){//Si on est dans la case 5 et 8, on verifie seulement si la piece peut etre posee verticalement
                tab = tab.substring(0, this.liste_indice[i]) + nb + tab.substring(this.liste_indice[i] + 1);//On ajoute un numéro dans le tableau correspondant a la position possible trouvee
                nb+=1;
            }
            else if ( (i == 3||i == 4 || i == 6 || i == 7)) {//Si on est dans la case 3, 4, 6 ou 7, on verifie si le joueur peut poser sa piece dans les deux directions
                if ( this.Est_bon(i,2,piece) && this.plateau.est_libre(i,piece) && this.plateau.est_libre(i + 1,piece) && this.plateau.est_libre(i - TROIS,piece)) {//Verifie si la piece peut etre posee horizontalement et verticalement
                    tab = tab.substring(0, this.liste_indice[i] - 1) + nb + '-' + tab.substring(this.liste_indice[i] + 1);
                    nb += 1;
                    tab = tab.substring(0, this.liste_indice[i]+1) + nb + tab.substring(this.liste_indice[i] + 2);
                    nb += 1;
                } else if (this.Est_bon(i,0,piece) && this.plateau.est_libre(i,piece) && this.plateau.est_libre(i + 1,piece)) {//Verifie si la piece peut etre posee horizontalement
                    tab = tab.substring(0, this.liste_indice[i]) + nb + tab.substring(this.liste_indice[i] + 1);//On ajoute un numero dans le tableau correspondant a la position possible trouvee
                    nb += 1;
                } else if (this.Est_bon(i,1,piece) && this.plateau.est_libre(i,piece) && this.plateau.est_libre(i - TROIS,piece)) {//Verifie si la piece peut etre posee verticalement
                    tab = tab.substring(0, this.liste_indice[i]) + nb + tab.substring(this.liste_indice[i] + 1);//On ajoute un numero dans le tableau correspondant a la position possible trouvee
                    nb += 1;
                }
            }
        }
        for(int i = 0; i < 9; ++i){
            if((i == 2 || i == 5 ||i == 8) && ((tab.charAt(this.liste_indice[i]) == Plateau.Couleur.VIDE.getSymbole()) || tab.charAt(this.liste_indice[i]) == piece.get_nom())){//Change les cases ou se situent les spot par des 'O' si la piece est dessus
                tab = tab.substring(0, this.liste_indice[i]) + Plateau.Couleur.SPOT.getSymbole() + tab.substring(this.liste_indice[i] + 1);
            }
            else if (tab.charAt(this.liste_indice[i]) == piece.get_nom())//Change les cases ou se situent la piece par un espace
                tab = tab.substring(0, this.liste_indice[i]) + Plateau.Couleur.VIDE.getSymbole() + tab.substring(this.liste_indice[i] + 1);
        }
        return tab;
    }

    public void mouvement(int coordonnee,boolean direction, Piece piece ){
        if(!direction){//Si on veut poser une piece verticalement
            this.plateau.set_case(piece.get_nom(), coordonnee);
            this.plateau.set_case(piece.get_nom(), coordonnee-TROIS);
            if(piece.get_coordonnee_x() != coordonnee && piece.get_coordonnee_x() != coordonnee-TROIS)//Si l'ancienne coordonne x de la piece n'est pas egale a la nouvelle coordonnee, on vide la case
                this.plateau.set_case(Plateau.Couleur.VIDE.getSymbole(), piece.get_coordonnee_x());
            if(piece.get_coordonnee_y() != coordonnee && piece.get_coordonnee_y() != coordonnee-TROIS)//Si l'ancienne coordonne y de la piece n'est pas egale a la nouvelle coordonnee, on vide la case
                this.plateau.set_case(Plateau.Couleur.VIDE.getSymbole(), piece.get_coordonnee_y());
            piece.set_coordonnee(coordonnee,coordonnee-TROIS);

        }
        else {//Si on veut poser une piece horizontalement
                this.plateau.set_case(piece.get_nom(), coordonnee);
                this.plateau.set_case(piece.get_nom(), coordonnee+1);
                if(piece.get_coordonnee_x() != coordonnee && piece.get_coordonnee_x() != coordonnee+1)//Si l'ancienne coordonne x de la piece n'est pas egale a la nouvelle coordonnee, on vide la case
                    this.plateau.set_case(Plateau.Couleur.VIDE.getSymbole(), piece.get_coordonnee_x());
                if(piece.get_coordonnee_y() != coordonnee && piece.get_coordonnee_y() != coordonnee+1)//Si l'ancienne coordonne y de la piece n'est pas egale a la nouvelle coordonnee, on vide la case
                    this.plateau.set_case(Plateau.Couleur.VIDE.getSymbole(), piece.get_coordonnee_y());
                piece.set_coordonnee(coordonnee,coordonnee+1);
        }
    }

    public void partie(String tab_possibilite, Piece piece){//Fonction qui gere le tour du joueur qui doit bouger une piece
        System.out.println("-------------------------------------------------------------------------------");
        this.affiche_points();
        System.out.println(this.plateau.affiche_tableau());//Affiche le plateau
        System.out.println("Au tour du Joueur " + this.Tour().get_numero() + " de bouger sa piece (" + piece.get_nom() +"), voici les emplacements disponibles :\n");
        System.out.println(tab_possibilite);//Affiche les possibiles mouvement de la piece
        boolean position = false;
        int indice = -1;
        while(indice == -1) {//Tant que le joueur n'a pas inscrit une des possibilites listees
            Scanner scan = new Scanner(System.in);
            System.out.println("Choisissez un indice parmi ceux disponibles pour effectuer un deplacement :");
            String input = scan.nextLine();
            if(input.length() == 1 && input.charAt(0) != '0'){//Si la taille de l'input est bien egale à 1
                indice = input.charAt(0);
                boolean trouve = true;//pour arreter la boucle
                for (int i = 0; i < 9 && trouve; ++i) {//Parcourt les cases du plateau de possibilite grace au tableau d'indice
                    if (tab_possibilite.charAt(this.liste_indice[i]) == indice) {//Si l'indice inscit est bien trouve
                        trouve = false;
                        indice = i;
                    }
                    else if(tab_possibilite.charAt(this.liste_indice[i]) == '-'){//Si la fonction rencontre une case composee de deux positions possibles
                        if(tab_possibilite.charAt(this.liste_indice[i]-1) == indice ){//Verifie l'indice a gauche du tiret
                            trouve = false;
                            indice = i;
                        }
                        else if(tab_possibilite.charAt(this.liste_indice[i]+1) == indice){//Verifie l'indice a doite du tiret
                            trouve = false;
                            position = true;
                            indice = i;
                        }
                    }
                }
                if(trouve) {
                    System.out.println("Coordonnees incorrecte");
                    indice = -1;//Recommence la boucle
                }
            }
            else
                System.out.println("Coordonnees incorrecte");
        }
        if(tab_possibilite.charAt(this.liste_indice[indice]) == '-'){//Place la piece dans les cas ou il y avait plusieurs positions dans la meme case
            this.mouvement(indice,position,piece);
        }
        else {
            if(indice == 0 || indice == 1){//Si on est dans le cas ou la piece ne pourrait qu'etre placee horizontalement
                this.mouvement(indice,true,piece);
            }
            else if(indice == 5 || indice == 8){//Si on est dans le cas ou la piece ne pourrait qu'etre placee verticalement
                this.mouvement(indice,false,piece);
            }
            else{
                if(this.Est_bon(indice,0,piece) && this.plateau.est_libre(indice,piece) && this.plateau.est_libre(indice+1,piece))//Regarde s'il faut le placer horizontalement
                    this.mouvement(indice,true,piece);
                else
                    this.mouvement(indice,false,piece);
            }
        }



    }

}
