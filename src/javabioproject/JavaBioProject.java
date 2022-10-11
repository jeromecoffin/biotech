/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package javabioproject;

import java.io.FileNotFoundException;
import java.io.IOException;
import org.biojava.nbio.structure.StructureException;
import org.biojava.nbio.structure.align.util.UserConfiguration;


/**
 *
 * @author jeromecoffin
 */
public class JavaBioProject {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) throws FileNotFoundException, InterruptedException, IOException, StructureException {
        
        //Definir l'emplacement des dossiers pour le telechargement des pdb
        System.setProperty("java.io.tmpdir", "/Users/jeromecoffin/home/anna/cnam/licence_3/projet_personnel/JAVA/PDB/");
        System.setProperty("PDB_DIR", "/Users/jeromecoffin/home/anna/cnam/licence_3/projet_personnel/JAVA/PDB/");
        System.setProperty("PDB_CACHE_DIR", "/Users/jeromecoffin/home/anna/cnam/licence_3/projet_personnel/JAVA/PDB/");
        
        System.out.println(Math.sqrt(4));
        
        //creation de l'objet
        ClassMethods myObject = new ClassMethods();
        myObject.Principale();

    }
    
}
