/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package projetdysgraphie;


import java.io.File;
import java.io.IOException;
import java.io.Reader;
import java.nio.charset.Charset;
import java.util.ArrayList;
import java.util.List;
import org.apache.commons.csv.*;
/**
 *
 * @author Utilisateur
 */
public class Lecteur {
    private CSVParser parser;
    
    public Lecteur(File source) throws IOException{
        parser = CSVParser.parse(source, Charset.forName("ISO-8859-1"), CSVFormat.TDF.withHeader());
    }
    
    public Lecteur(Reader source) throws IOException{
        parser = new CSVParser(source, CSVFormat.TDF.withHeader());
    } 
    
    /**
     * Lit le fichier CSV et extrait les informations nécéssaires à la création d'un objet Trace
     * @return 
     */
    public List<Point> lire(){
        ArrayList lesPoints = new ArrayList();
        int i=0;
        for(CSVRecord c:parser){
            if(c.get("Tip").equals("1")){
                lesPoints.add(new Point(Integer.parseInt(c.get("X /100 mm")),Integer.parseInt(c.get("Y /100 mm")), i, Integer.parseInt(c.get("Interv ms"))));
                i++;
            }
        }
        return lesPoints;
    }
}
