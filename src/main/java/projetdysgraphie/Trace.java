/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package projetdysgraphie;

import java.io.File;
import java.io.IOException;
import java.io.StringReader;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

/**
 *
 * @author Utilisateur
 */
public class Trace {
    private List<Point> myPoints;
    
    public Trace(File source) throws IOException{
        Scanner sc = new Scanner(source);
        Lecteur l;
        if(!sc.next().equals("Identification")){
            l = new Lecteur(source);
        }
        else{
           sc.nextLine();
           sc.nextLine();
           sc.nextLine();
           sc.nextLine();
           String input ="";
           while(sc.hasNextLine()){
               input+="\n"+sc.nextLine();
           }
           StringReader sourceStr = new StringReader(input);
           l = new Lecteur(sourceStr);
        }
        myPoints = l.lire();
        Double averageInterval = getAverageInter();
        for(Point p:myPoints){
            p.setInter((int) Math.round(averageInterval));
        }
    }
    public Trace(List<Point> mP){
        myPoints = mP;
    }
    
    public List<Point> getPoint(){
        return myPoints;
    }
    
    public List<Double> getAcceleration(){
        List<Double> res = new ArrayList();
        Double averageInterval = getAverageInter();
        for(int i=1;i<myPoints.size()-1;i++){
            //if(i%2==0){
                Point pAv = myPoints.get(i-1);
                Point pMi = myPoints.get(i);
                Point pAp = myPoints.get(i+1);
                Double acc = (pAv.vitesseEntre(pMi)-pMi.vitesseEntre(pAp))/((double)2*averageInterval);
                res.add(acc);
            //}
        }
        return res;
    }
    
    public double getAverageInter(){
        double res = 0;
        for(Point p:myPoints){
            res+=p.getInterval();
        }
        res = res/myPoints.size();
        return res;
    }
    
    public List<PointAffichage> getPointsAcceleration(){
        ArrayList<PointAffichage> res = new ArrayList();
        List<Double> accel = getAcceleration();
        Double min = accel.get(0);
        for(int i=0;i<accel.size();i++){
            double y=accel.get(i);
            PointAffichage p = new PointAffichage(i,y);
            res.add(p);
            if(y<min){
                min = y;
            }
        }
        if(min<0){
            for(PointAffichage p:res){
                p.setY(p.getY()-min);
            }
        }
        return res;
    }
    
    public double moyennePositive(List<Double> l){
        double res = 0;
        for(double d:l){
            res+=Math.abs(d);
        }
        res=res/l.size();
        return res;
    }
    
    public int getNbPic(){
        int res = 0;
        int signe = 0;
        List<Double> accel = getAcceleration();
        double average = moyennePositive(accel);
        if(accel.get(0)-accel.get(1)<0){
            signe = 1;
        }
        else{
            signe = -1;
        }
        for(int i=0; i<accel.size()-1;i++){
            double signeAct = accel.get(i)-accel.get(i+1);
            if(((signe<0 && signeAct>0) ||
                (signe>0 && signeAct<0)) &&
                 Math.abs(signeAct) > average*0.5){
                
                signe=-signe;
                res++;
            }
        }
        return res;
    }
    
    public boolean estSimilaireAuModele(Trace essai){
        int nbPicModele = getNbPic();
        int nbPicEssai = essai.getNbPic();
        double margeErreur = 0.20;
        boolean res = Math.abs(nbPicModele-nbPicEssai)<=nbPicModele*margeErreur;
        return res;
    }
}
