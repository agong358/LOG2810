//package LOG2810.TP1;

import java.util.LinkedList;
import java.util.Scanner;

public class Commande {
    private int nbObjetsA_, nbObjetsB_, nbObjetsC_;
    private RobotX robotX = new RobotX(this);
//    private RobotX robotY = new RobotY(this);
//    private RobotX robotZ = new RobotZ(this);

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

    public double CalculerTempsTotal(LinkedList<Sommet> chemin) {
        double temps = Double.MAX_VALUE;
        if (robotX.verifierChargeMax()) {
            temps = robotX.calculerTempsTotal(chemin);
        }
        //if robot y et robot z meme chose
        return temps;
    }
}