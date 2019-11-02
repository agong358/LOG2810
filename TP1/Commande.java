//package LOG2810.TP1;

import java.util.Scanner;

public class Commande {
    private int nbObjetsA_, nbObjetsB_, nbObjetsC_;

    public Commande(){
        nbObjetsA_ = 0;
        nbObjetsB_ = 0;
        nbObjetsC_ = 0;
    }

    public Commande(int a, int b, int c) {
        nbObjetsA_ = a;
        nbObjetsB_ = b;
        nbObjetsC_ = c;
    }

    public int getNbObjetsA_() {
        return nbObjetsA_;
    }

    public int getNbObjetsB_() {
        return nbObjetsB_;
    }

    public int getNbObjetsC_() {
        return nbObjetsC_;
    }


}