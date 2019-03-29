/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package projetdysgraphie;

/**
 *
 * @author Utilisateur
 */
public class Point implements Comparable {
    private int x;
    private int y;
    private int num;
    private int inter;
    
    public Point(int x, int y, int num, int inter){
        this.x = x;
        this.y = y;
        this.inter = inter;
        this.num = num;
    }
    
    /**
     * Calcule la distance entre 2 points
     * @param p
     * @return 
     */
    public double distanceAvec(Point p){
        return Math.sqrt(Math.pow(p.x-this.x,2)+Math.pow(p.y-this.y, 2));
    }
    
    public double vitesseEntre(Point p){
        /*if(this.num+1!=p.num && this.num-1!=p.num){
            throw new PointException("calcul de vitesse entre deux points non consécutifs");
        }*/
        int interval;
        if(this.num>p.num){
            interval = p.inter;
        }
        else{
            interval = this.inter;
        }
        return p.distanceAvec(this)/((double)interval);
    }
    
    public int getInterval(){
        return inter;
    }
    
    @Override
    public int compareTo(Object o) {
        Point p = (Point)o;
        return this.num-p.num;
    }
    
    @Override
    public String toString(){
        return "N°"+num+"\tx:"+x+"\ty:"+y+"\tinter:"+inter;
    }
    
    public int getX(){
	return this.x;
    }
 
    public int getY(){
	return this.y;
    }
}
