/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package projetdysgraphie;

import java.io.File;
import java.io.IOException;

/**
 *
 * @author Utilisateur
 */
public class ProjetDysgraphie {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) throws IOException {
        File f = new File("fichier3.txt");
        Trace t = new Trace(f);
        System.out.println(t.getPoint().toString());
    }
    
}
