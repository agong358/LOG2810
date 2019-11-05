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

    public void setNbObjetsA_(int nbObjetsA_) {
        this.nbObjetsA_ = nbObjetsA_;
    }

    public void setNbObjetsB_(int nbObjetsB_) {
        this.nbObjetsB_ = nbObjetsB_;
    }

    public void setNbObjetsC_(int nbObjetsC_) {
        this.nbObjetsC_ = nbObjetsC_;
    }
}