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
        Lecteur l = null;
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
    }
    public Trace(List<Point> mP){
        myPoints = mP;
    }
    
    public List<Point> getPoint(){
        return myPoints;
    }
    
    public List<Double> getAcceleration(){
        List<Double> res = new ArrayList();
        for(int i=1;i<myPoints.size()-1;i++){
            Point pAv = myPoints.get(i-1);
            Point pMi = myPoints.get(i);
            Point pAp = myPoints.get(i+1);
            Double acc = (pAv.vitesseEntre(pMi)-pMi.vitesseEntre(pAp))/((double)(pMi.getInterval()+pAp.getInterval()));
            res.add(acc);
        }
        return res;
    }
}
