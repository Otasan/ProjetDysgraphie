/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package projetdysgraphie;

import java.awt.Color;
import java.awt.Graphics;
import java.util.ArrayList;
import java.util.List;
import javax.swing.JPanel;


/**
 *
 * @author cbason
 */
public class Courbe extends JPanel {

    private ArrayList<PointAffichage> listePoints = new ArrayList();
    private double xMin = Double.MAX_VALUE;
    private double yMin = Double.MAX_VALUE;
    private double xMax = Double.MIN_VALUE;
    private double yMax = Double.MIN_VALUE;

    private int largeur = 0;
    private int hauteur = 0;
    private int left = 10;
    private int top = 10;

    public Courbe(Trace t) {
        List<Point> lesPoints = t.getPoint();
        for(Point p:lesPoints){
            ajouterPoint(p);
        }
    }

    /**
     * converti un point en PointAffichage et l'ajoute à listePoints
     * @param p 
     */
    public void ajouterPoint(Point p) {
        if (p.getX() < this.xMin) {
            this.xMin = p.getX();
        } else if (p.getX() > this.xMax) {
            this.xMax = p.getX();
        }

        if (p.getY() < this.yMin) {
            this.yMin = p.getY();
        } else if (p.getY() > this.yMax) {
            this.yMax = p.getY();
        }

        this.listePoints.add(p.toPointAffichage());

        this.repaint();
    }

    @Override
    public void paint(Graphics g) {
        super.paint(g);

        this.largeur = this.getWidth() - 20;
        this.hauteur = this.getHeight() - 20;
        this.left = 10;
        this.top = 10;

        g.setColor(Color.WHITE);
        g.fillRect(this.left, this.top, this.largeur, this.hauteur);

        g.setColor(Color.RED);
        if (this.listePoints.size() == 1) {
            int x = this.left + (this.largeur / 2);
            int y = this.top + (this.hauteur / 2);
            g.drawLine(x - 2, y, x + 2, y);
            g.drawLine(x, y - 2, x, y + 2);
        } else {
            for (int i = 0; i < this.listePoints.size() - 1; i++) {
                PointAffichage p1 = this.convertirPointSurReferenciel(this.listePoints.get(i));
                PointAffichage p2 = this.convertirPointSurReferenciel(this.listePoints.get(i + 1));
                int x1 = (int) p1.getX();
                int y1 = (int) p1.getY();
                int x2 = (int) p2.getX();
                int y2 = (int) p2.getY();

                g.setColor(Color.BLUE);
                g.drawLine(x1, y1, x2, y2);

                g.setColor(Color.RED);
                g.drawLine(x1 - 4, y1, x1 + 4, y1);
                g.drawLine(x1, y1 - 4, x1, y1 + 4);
            }

            PointAffichage p1 = this.convertirPointSurReferenciel(this.listePoints.get(this.listePoints.size() - 1));
            int x1 = (int) p1.getX();
            int y1 = (int) p1.getY();
            g.drawLine(x1 - 4, y1, x1 + 4, y1);
            g.drawLine(x1, y1 - 4, x1, y1 + 4);
        }
    }

    /**
     * Converti un Point pour l'afficher sur le même reférentiel que les autres.
     * @param p
     * @return 
     */
    public PointAffichage convertirPointSurReferenciel(Point p) {
        double amplitudeX = this.xMax - this.xMin;
        double amplitudeY = this.yMax - this.yMin;

        double rapportX = this.largeur / amplitudeX;
        double rapportY = this.hauteur / amplitudeY;

        double x = (p.getX() - this.xMin) * rapportX;
        double y = (p.getY() - this.yMin) * rapportY;

        y = this.hauteur - y;

        x = x + this.left;
        y = y + this.top;

        return new PointAffichage(x, y);
    }

    /**
     * Converti un PointAffichage pour l'afficher sur le même reférentiel que les autres.
     * Si ce point existe déjà dans la liste, il sera supprimé
     * @param p
     * @return 
     */
    public PointAffichage convertirPointSurReferenciel(PointAffichage p) {
        if (listePoints.contains(p)) {
            listePoints.remove(p);
        }
        double amplitudeX = this.xMax - this.xMin;
        double amplitudeY = this.yMax - this.yMin;

        double rapportX = this.largeur / amplitudeX;
        double rapportY = this.hauteur / amplitudeY;

        double x = (p.getX() - this.xMin) * rapportX;
        double y = (p.getY() - this.yMin) * rapportY;

        y = this.hauteur - y;

        x = x + this.left;
        y = y + this.top;

        return new PointAffichage(x, y);
    }

    @Override
    public String toString(){
        return listePoints.toString();
    }
}
