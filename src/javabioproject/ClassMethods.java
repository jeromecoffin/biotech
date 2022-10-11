/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package javabioproject;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.Scanner;
import java.util.HashMap;
import java.util.Set;
import org.biojava.nbio.structure.StructureException;
import org.biojava.nbio.structure.Structure;
import org.biojava.nbio.structure.StructureIO;
//import org.biojava.nbio.structure.io.PDBFileReader;
//import org.slf4j.LoggerFactory;
//import javax.vecmath.Tuple3d;
//import org.biojava.nbio.alignment.template.GapPenalty;
//import org.biojava.nbio.structure.align.util.AtomCache;
//import java.lang.Math;
//import java.util.Collections;
//import java.util.Map;


/**
 *
 * @author jeromecoffin
 */

//On cree une classe contenant toutes les actions possibles
public class ClassMethods {

    
    //Introduire le programme a l'utilisateur
    public void IntroductionProgramme(){ 
        boolean continuer;
        continuer = Boolean.TRUE;
        
        while (continuer){
		System.out.println("Bonjour, voici la programme d interaction proteique");
		System.out.println("**************************************************");
		continuer= Boolean.valueOf("");
		if (!Objects.equals(continuer, Boolean.valueOf(""))){
                    continuer = Boolean.TRUE;
                }
        }
	System.out.println("Pour utiliser le programme, vous avez besoin d une connection internet si votre structure PDB n est pas telechargee");
        Scanner scanner = new Scanner(System.in);
        scanner.nextLine();
    }
    
    //Telecharger les structure depuis le serveur
    public void TelechargerStructuresPdbs(String nom) throws IOException, StructureException {
        
        
        //pour telecharger le pdb
        //ne fonctionne pas chez moi
        //Structure s1 = StructureIO.getStructure(nom);    
        
 }
    
    //Demander la structure pdb a l'utilisateur
    public String TelechargeStructure() throws IOException, StructureException{
        
        boolean continuer = Boolean.TRUE;
        String nomStructure = new String();
        
        ArrayList<String> PDBList1 = new ArrayList<>();
        PDBList1.add("4b97");
        PDBList1.add("5fuc");
        PDBList1.add("3040");
        PDBList1.add("2b5i");
        PDBList1.add("6df3");
        PDBList1.add("3w04");
        PDBList1.add("5m5e");
        PDBList1.add("4d0h");
        PDBList1.add("5vi4");
        PDBList1.add("3jvf");
        PDBList1.add("4kc3");
        
        while (continuer) {  
            
            Scanner sc = new Scanner(System.in);
            System.out.println("Entrez le code de la structure en MINUSCULE: ");
            nomStructure = sc.nextLine();  
        
            if(PDBList1.contains(nomStructure)){
                TelechargerStructuresPdbs(nomStructure);
                nomStructure = "pdb"+nomStructure.toLowerCase()+".ent";
                continuer = Boolean.FALSE;
                return nomStructure;
            }
        
            else{
            
                System.out.println("Cette structure n'existe pas");
            
            }
        }
        
        return "hello";
    }
    
    
    //Regarder si la structure comporte le complexe
    public boolean VerificationStructure(String nom) throws FileNotFoundException, InterruptedException{
        
        boolean complexe = Boolean.FALSE;
        int nb = 0;
        
        String filename = "/Users/jeromecoffin/home/anna/cnam/licence_3/projet_personnel/JAVA/PDB/";
        filename += nom;
        
        File file = new File(filename);
        
        Scanner sc = new Scanner(file); 
        
        ArrayList<String> list = new ArrayList<>();
        
        while (sc.hasNextLine()){
            list.add(sc.nextLine());
            
        }
        
        int i, j, k;
        
        for( i = 0; i <list.size(); i++){
            
            String[] lol = list.get(i).split(" ");
            for(j=0; j<lol.length; j++){
                if(lol[j].equalsIgnoreCase("")){
                    for(k = j; k < lol.length - 1; k++){
                        lol[k] = lol[k+1];
                    }
                }
            }
            
            for(j=0; j<lol.length; j++){
                if(lol[j].equalsIgnoreCase("")){
                    for(k = j; k < lol.length - 1; k++){
                        lol[k] = lol[k+1];
                    }
                }
            }
            
            for(j=0; j<lol.length; j++){
                if(lol[j].equalsIgnoreCase("")){
                    for(k = j; k < lol.length - 1; k++){
                        lol[k] = lol[k+1];
                    }
                }
            }
            
            if(lol[0].equals("COMPND") && lol[2].equalsIgnoreCase("MOLECULE:")){
                
                nb+=1;
                
            }
        }
        
        if(nb>1){
            complexe = Boolean.TRUE;
        }
        
        return complexe;
        
    }
    
    //donner la methode d'acquisiton du complexe
    public String Acquisition(String nom) throws FileNotFoundException{
        
        
        String filename = "/Users/jeromecoffin/home/anna/cnam/licence_3/projet_personnel/JAVA/PDB/";
        filename += nom;
        
        String phrase = new String();
        
        File file = new File(filename);
        
        Scanner sc = new Scanner(file); 
        
        ArrayList<String> list = new ArrayList<>();
        
        while (sc.hasNextLine()){
            list.add(sc.nextLine());
            
        }
        
        for (String list1 : list) {
            if (list1.substring(0, 6).equalsIgnoreCase("EXPDTA")) {
                phrase = list1.substring(6, list1.length()); 
            }
        }
        
        phrase = phrase.trim();
        
        return phrase;
    } 
    
    
    //Cette methode est appelee pour demander a l utilisateur si il souhaitera fair la partie 2 du projet ou non
    public String Choix1(){
        
        Scanner sc = new Scanner(System.in);
        System.out.println("Souhaitez vous calculer la distance entre deux residus du complexe? (o/n): ");
        String faireCalcul = sc.nextLine();
        if(faireCalcul.equalsIgnoreCase("o")){
            return "o";
        }
        else if(faireCalcul.equalsIgnoreCase("n")){
            return "n";
        }
        else{
            Choix1();
        }

        return "n";
    }
    
    //cette methode recoit un nom de chaine et un numero. Elle verifie que a chaine et le numero existent dans le fichier
    public boolean VerifierSiPresent(String chaine, String num, String nom) throws FileNotFoundException{

        boolean present = Boolean.FALSE;
        
        String filename = "/Users/jeromecoffin/home/anna/cnam/licence_3/projet_personnel/JAVA/PDB/";
        filename += nom;
        
        int i, j, k;
        
        File file = new File(filename);
        
        Scanner sc = new Scanner(file); 
        
        ArrayList<String> list = new ArrayList<>();
        
        while (sc.hasNextLine()){
            list.add(sc.nextLine());
            
        }
        
        
        for(i = 0; i <list.size(); i++){
            
            if(list.get(i).substring(0, 4).equalsIgnoreCase("ATOM")){
                
                String[] lol = list.get(i).split(" ");
                
                for(j=0; j<lol.length; j++){
                    if(lol[j].equalsIgnoreCase("")){
                        for(k = j; k < lol.length - 1; k++){
                            lol[k] = lol[k+1];
                        }
                    }
                }
                for(j=0; j<lol.length; j++){
                    if(lol[j].equalsIgnoreCase("")){
                        for(k = j; k < lol.length - 1; k++){
                            lol[k] = lol[k+1];
                        }
                    }
                }
                
                for(j=0; j<lol.length; j++){
                    if(lol[j].equalsIgnoreCase("")){
                        for(k = j; k < lol.length - 1; k++){
                            lol[k] = lol[k+1];
                        }
                    }
                }
                
                if(lol[4].equals(chaine) && lol[5].equals(num)){
                    present = Boolean.TRUE;
                }
            }
        }
        
        return present;
    }
    
    
    //Cette methode demande a l utilisateur de choisir un residu, en donnant le nom de sa chaine et son numero
    public String ChoixResidu(String nom) throws FileNotFoundException{
        String num = new String();
        boolean present = Boolean.FALSE;
        String chaine = new String();
        while (present == Boolean.FALSE) {
            
            Scanner sc = new Scanner(System.in);
            System.out.println("Entrez le nom de la chaine : ");
            chaine = sc.nextLine();
            System.out.println("Entrez le nom du numero : ");
            num = sc.nextLine();
            present = VerifierSiPresent(chaine, num, nom);
            if(present == Boolean.FALSE){
                System.out.println("Ce residu n'existe pas...");
            }
            
        }
        return chaine+num;
    }
    
    //Cette methode recupere les coordonnes du carbone alpha du residu d un num donne et d une chaine donnee. elle renvoie une liste qui comporte le x, y et le z
    public ArrayList<String> CoordonnesCa(String chaine, String num, String nom) throws FileNotFoundException{
        
        
        String filename = "/Users/jeromecoffin/home/anna/cnam/licence_3/projet_personnel/JAVA/PDB/";
        filename += nom;
        
        int i, j, k;
        
        File file = new File(filename);
        
        Scanner sc = new Scanner(file);
        
        ArrayList<String> list = new ArrayList<>();
        
        while (sc.hasNextLine()){
            list.add(sc.nextLine());
            
        }
        
        ArrayList<String> trinome = new ArrayList();
        
        for (String list1 : list) {
            if (list1.substring(0, 4).equalsIgnoreCase("ATOM")) {
                String[] lol = list1.split(" ");
                for(j=0; j<lol.length; j++){
                    if(lol[j].equalsIgnoreCase("")){
                        for(k = j; k < lol.length - 1; k++){
                            lol[k] = lol[k+1];
                        }
                    }
                }
                for(j=0; j<lol.length; j++){
                    if(lol[j].equalsIgnoreCase("")){
                        for(k = j; k < lol.length - 1; k++){
                            lol[k] = lol[k+1];
                        }
                    }
                }
                for(j=0; j<lol.length; j++){
                    if(lol[j].equalsIgnoreCase("")){
                        for(k = j; k < lol.length - 1; k++){
                            lol[k] = lol[k+1];
                        }
                    }
                }
                if(lol[2].equalsIgnoreCase("CA") && lol[4].equalsIgnoreCase(chaine) && lol[5].equalsIgnoreCase(num)){
                    trinome.add(lol[6]);
                    trinome.add(lol[7]);
                    trinome.add(lol[8]);
                }
            }
        }

        return trinome;
    }
    
    
    ///cette methode recoit trois liste, chacune comportant le x,y et z d un des 3 acides amines. elle calcule le barycentre de ces 3 points, et renvoie une liste avec les coordonnes x y et z du barycentre
    public ArrayList<String> CalculBarycentre(ArrayList<String> lc, ArrayList<String> lo, ArrayList<String> ln){
        
        
        float coordX;
        coordX = ((12*Float.parseFloat(lc.get(0)))+(16*Float.parseFloat(lo.get(0)))+(14*Float.parseFloat(ln.get(0))))/(12+16+14);
	float coordY;
        coordY = ((12*Float.parseFloat(lc.get(1)))+(16*Float.parseFloat(lo.get(1)))+(14*Float.parseFloat(ln.get(1))))/(12+16+14);
	float coordZ;
        coordZ = ((12*Float.parseFloat(lc.get(2)))+(16*Float.parseFloat(lo.get(2)))+(14*Float.parseFloat(ln.get(2))))/(12+16+14);
        
        ArrayList<String> trinome = new ArrayList();
        
        trinome.add(Float.toString(coordX));
        trinome.add(Float.toString(coordY));
        trinome.add(Float.toString(coordZ));
        
        return trinome;
    }
    
    
    //cette methode recupere les coordonnes du c, n et o de la chaine donnee et du numero donne. elle appelle ensuite la methode calculbarycentre qui va calculer le barycentre de ces trois points. Elle renvoie la liste retournee par la methode calculbarycentre.
    public ArrayList<String> CoordonneesBarycentre(String chaine, String num, String nom) throws FileNotFoundException{
        
        String filename = "/Users/jeromecoffin/home/anna/cnam/licence_3/projet_personnel/JAVA/PDB/";
        filename += nom;
        
        File file = new File(filename);
        
        Scanner sc = new Scanner(file);
        
        ArrayList<String> list = new ArrayList<>();
        
        while (sc.hasNextLine()){
            list.add(sc.nextLine());
            
        }
        
        int i, j, k;
        
        ArrayList<String> trinomeC = new ArrayList();
        ArrayList<String> trinomeO = new ArrayList();
        ArrayList<String> trinomeN = new ArrayList();
              
        for (String list1 : list) {
            if (list1.substring(0, 4).equalsIgnoreCase("ATOM")) {
                String[] lol = list1.split(" ");
                for(j=0; j<lol.length; j++){
                    if(lol[j].equalsIgnoreCase("")){
                        for(k = j; k < lol.length - 1; k++){
                            lol[k] = lol[k+1];
                        }
                    }
                }
                for(j=0; j<lol.length; j++){
                    if(lol[j].equalsIgnoreCase("")){
                        for(k = j; k < lol.length - 1; k++){
                            lol[k] = lol[k+1];
                        }
                    }
                }
                for(j=0; j<lol.length; j++){
                    if(lol[j].equalsIgnoreCase("")){
                        for(k = j; k < lol.length - 1; k++){
                            lol[k] = lol[k+1];
                        }
                    }
                }
                if(lol[2].equalsIgnoreCase("C") && lol[4].equalsIgnoreCase(chaine) && lol[5].equalsIgnoreCase(num)){
                    
                    
                    trinomeC.add(lol[6]);
                    trinomeC.add(lol[7]);
                    trinomeC.add(lol[8]);

                }
                if(lol[2].equalsIgnoreCase("O") && lol[4].equalsIgnoreCase(chaine) && lol[5].equalsIgnoreCase(num)){
                    
                    trinomeO.add(lol[6]);
                    trinomeO.add(lol[7]);
                    trinomeO.add(lol[8]);

                }
                if(lol[2].equalsIgnoreCase("N") && lol[4].equalsIgnoreCase(chaine) && lol[5].equalsIgnoreCase(num)){
                    
                    trinomeN.add(lol[6]);
                    trinomeN.add(lol[7]);
                    trinomeN.add(lol[8]);

                }
            }
        }
        

        ArrayList<String> trinome;
        trinome = CalculBarycentre(trinomeC, trinomeO, trinomeN);
        
        return trinome;

    }
    
    
    //methode appellee par une methode qui permet de calculer la distance entre 2 acides amines
    public float CalculDistance(ArrayList<String> liste1, ArrayList<String> liste2){
        
        
        float x1;
        x1 = Float.parseFloat(liste1.get(0));
        float x2;
        x2 = Float.parseFloat(liste2.get(0));
        float y1;
        y1 = Float.parseFloat(liste1.get(1));
        float y2;
        y2 = Float.parseFloat(liste2.get(1));
        float z1;
        z1 = Float.parseFloat(liste1.get(2));
        float z2;
        z2 = Float.parseFloat(liste2.get(2));
        
        float d = (float) Math.sqrt(Math.pow((x2-x1), 2)+Math.pow((y2-y1),2)+Math.pow((z2-z1),2));
                                
        return d;
    }
    
    //Cette methode demande a l utilisateur si il souhaite calculer la distance des carbones alpha ou des barycentres. il retourne true ou false
    public String ChoixCalcul(){
        
        boolean continuer = Boolean.TRUE;
        String choix = new String();
        while(continuer == Boolean.TRUE){
            
            Scanner sc = new Scanner(System.in);
            System.out.println("Souhaitez vous faire le calcul du barycentre (b) ou du carbone alpha (ca)?: ");
            choix = sc.nextLine();
            if(choix.equalsIgnoreCase("b") || choix.equalsIgnoreCase("ca")){
                
                continuer =  Boolean.FALSE;
                return choix;

            }
            else{
                System.out.println("Ce choix n existe pas");
            }
            
        }
        
        return choix;
    }
    
    //cette methode va appeler toutes les methodes redigees au dessus dans la partie 2. Elle retourne une distance
    public float PrincipalePartie2(String nom) throws FileNotFoundException{
        
        String temp = new String();

        System.out.println("Choix du premier residu ");
        temp = ChoixResidu(nom);
        String chaine1 = String.valueOf(temp.charAt(0));
        String num1 = String.valueOf(temp.substring(1));

        System.out.println("Choix du deuxieme residu");
        temp = ChoixResidu(nom);
        String chaine2 = String.valueOf(temp.charAt(0));
        String num2 = String.valueOf(temp.substring(1));

        String choix = ChoixCalcul();
        
        ArrayList<String> residu1 = new ArrayList();
        ArrayList<String> residu2 = new ArrayList();
        
        if (choix.equalsIgnoreCase("b")){
		residu1 = CoordonneesBarycentre(chaine1,num1, nom);
		residu2 = CoordonneesBarycentre(chaine2,num2, nom);
        }
        else{
		residu1 = CoordonnesCa(chaine1,num1, nom);
		residu2 = CoordonnesCa(chaine2,num2, nom);
        }
                
	float distance = CalculDistance(residu1, residu2);
        System.out.println("La distance est : " + distance);
	return distance;
    }
    
    
    //cette methode verifie que le nom de la chaine rentree existe bien dans le fichier (qui correspond a nom du fichier)
    public boolean VerifierSiChainePresente(String chaine, String nom) throws FileNotFoundException{
        
	boolean present = Boolean.FALSE;
        
        String filename = "/Users/jeromecoffin/home/anna/cnam/licence_3/projet_personnel/JAVA/PDB/";
        filename += nom;
        
        File file = new File(filename);
        
        Scanner sc = new Scanner(file);
        
        ArrayList<String> list = new ArrayList<>();
        
        while (sc.hasNextLine()){
            list.add(sc.nextLine());
            
        }
        
        ArrayList<String> trinome = new ArrayList();
        
        int i, j, k;
        
        for (String list1 : list) {
            if (list1.substring(0, 4).equalsIgnoreCase("ATOM")) {
                String[] lol = list1.split(" ");
                for(j=0; j<lol.length; j++){
                    if(lol[j].equalsIgnoreCase("")){
                        for(k = j; k < lol.length - 1; k++){
                            lol[k] = lol[k+1];
                        }
                    }
                }
                for(j=0; j<lol.length; j++){
                    if(lol[j].equalsIgnoreCase("")){
                        for(k = j; k < lol.length - 1; k++){
                            lol[k] = lol[k+1];
                        }
                    }
                }
                for(j=0; j<lol.length; j++){
                    if(lol[j].equalsIgnoreCase("")){
                        for(k = j; k < lol.length - 1; k++){
                            lol[k] = lol[k+1];
                        }
                    }
                }
                if(lol[4].equalsIgnoreCase(chaine)){
                    
                    present = Boolean.TRUE;
                }
            }
        }
        
        return present;
    }

    
    //Cette methode propose a l utilisateur de choisir une chaine. Il retourne le nom de la chaine choisie
    public String ChoixChaine(String nom) throws FileNotFoundException {
        
	boolean present = Boolean.FALSE;
        String chaine = new String();

	while (Objects.equals(present, Boolean.FALSE)){
            
            Scanner sc = new Scanner(System.in);
            System.out.println("Entrez le nom de la chaine : ");
            chaine = sc.nextLine();
            present = VerifierSiChainePresente(chaine, nom);
            
            if(Objects.equals(present, Boolean.FALSE)){
                System.out.println("Cette chaine n'existe pas...");
            }
        }
        return chaine;
    }
    
    //cette methode permet de parcourir le fichier de nom Nom, et recupere l ensemble des numeros des aa qui correspondent a la chaine. Elle retourne une liste avec tous les numeros en chaine de caracteres
    public ArrayList<String> NumResidusChaine(String chaine, String nom) throws FileNotFoundException{
        
        String filename = "/Users/jeromecoffin/home/anna/cnam/licence_3/projet_personnel/JAVA/PDB/";
        filename += nom;
        
        ArrayList<String> listeCoordChaine = new ArrayList();
        
        File file = new File(filename);
        
        Scanner sc = new Scanner(file);
        
        ArrayList<String> list = new ArrayList<>();
        
        int i, j, k;
        
        while (sc.hasNextLine()){
            list.add(sc.nextLine());
            
        }
          
        for (String list1 : list) {
            if (list1.substring(0, 4).equalsIgnoreCase("ATOM")) {
                String[] lol = list1.split(" ");
                for(j=0; j<lol.length; j++){
                    if(lol[j].equalsIgnoreCase("")){
                        for(k = j; k < lol.length - 1; k++){
                            lol[k] = lol[k+1];
                        }
                    }
                }
                for(j=0; j<lol.length; j++){
                    if(lol[j].equalsIgnoreCase("")){
                        for(k = j; k < lol.length - 1; k++){
                            lol[k] = lol[k+1];
                        }
                    }
                }
                for(j=0; j<lol.length; j++){
                    if(lol[j].equalsIgnoreCase("")){
                        for(k = j; k < lol.length - 1; k++){
                            lol[k] = lol[k+1];
                        }
                    }
                }
                if(lol[4].equalsIgnoreCase(chaine)){
                    
                    if(!listeCoordChaine.contains(lol[5])){
                        listeCoordChaine.add(lol[5]);
                    }
                }
            }
        }
        return listeCoordChaine;
    }

    
    //cette methode va generer un dictionaire. celui ci contient chacun des numeros de la liste des num aa d une chaine. 
    //le numero est associe a une liste, comprenant les coords des points x y et z du residu correspondant au numero. 
    //La methode appelle deux autres methodes definies plus haut dans la partie 2
    public HashMap<String, ArrayList> DicoChaines(String nom, String chaine, String choix) throws FileNotFoundException{
        
        HashMap<String, ArrayList> dico = new HashMap<>();
        ArrayList<String> listeCoordChaine = new ArrayList();
        listeCoordChaine = NumResidusChaine(chaine, nom);
        ArrayList<String> trinome = new ArrayList();
        int i;
        
        if(choix.equalsIgnoreCase("ba")){

            for(i = 0; i<listeCoordChaine.size(); i++){
                trinome = CoordonneesBarycentre(chaine, Integer.toString(i), nom);

                if(!trinome.isEmpty()){
                    
                    dico.put(Integer.toString(i), trinome);
                }
            }
        }
        else{
            for(i = 0; i<listeCoordChaine.size(); i++){
                trinome = CoordonnesCa(chaine, Integer.toString(i), nom);

                if(!trinome.isEmpty()){
                    dico.put(Integer.toString(i), trinome);
                }

            }
        }
        
        return dico;
    }
       
    //cette methode calcule la distance entre les acides amines de deux dicos. Elle fabrique un dictionnaire comportant chaque association d aa 
    //(ex l aa 42 du dico avec le 21 du dico 2, et la distance entre eux (aa520_aa310 : distance)
    public HashMap<String, String> CreerDicoDistanceSeuil(HashMap<String, ArrayList> dico1, HashMap<String, ArrayList> dico2){
        
        HashMap<String, String> dicoFinal = new HashMap<>(); 

        for (String nomi : dico1.keySet()){
            for(String nomj : dico2.keySet()){
                String nom = nomi+"_"+nomj;
                ArrayList<String> l1 = new ArrayList<>();
                ArrayList<String> l2 = new ArrayList<>();
                l1 = dico1.get(nomi);
                l2 = dico2.get(nomj);

                if(!l1.isEmpty() && !l2.isEmpty()){
                    float d = CalculDistance(l1, l2);
                    dicoFinal.put(nom, Float.toString(d));
                }
                
            }
        }

        return dicoFinal;
    }
    
    //cette fontion utilise appelle toutes les methodes definies precedement. Elle retourne le dictionnaire degenre par creerdicodistanceseuil
    public HashMap<String, String> PrincipalePartie3(String nom) throws FileNotFoundException{
        String Chaine1 = ChoixChaine(nom);
        String Chaine2 = ChoixChaine(nom);
        String choix = ChoixCalcul();
        HashMap<String, ArrayList> dico1 = DicoChaines(nom, Chaine1, choix);
        HashMap<String, ArrayList> dico2 = DicoChaines(nom, Chaine2, choix);
        HashMap<String, String> dicoDistance = CreerDicoDistanceSeuil(dico1, dico2);
        

	return dicoDistance;
    }

        // cette methode demande a l utilisateur de choisir un seuil, et le retourne
    public int ChoixSeuil(){
        
        Scanner sc = new Scanner(System.in);
        System.out.println("Entrez le seuil choisi :");
        int s = Integer.valueOf(sc.nextLine());
	return s;
    }
    

    //cette fontion tri le dictionnaire par ordre croissant de distance associee en items
    public ArrayList<String> TriDico(HashMap<String, String> dd){
        
        
        HashMap<String, String> dico1 = new HashMap<>();
        int i = 0;
                
        for (String name1: dd.keySet()){
            
            String key1 = name1;

            if(!dd.get(name1).isEmpty()){    
                String value1 = dd.get(name1);

                dico1.put(key1, value1);
                i+=1;
            }
            
            
        }

        System.out.println("Merci de patienter...");
        
        ArrayList<String> liste1 = new ArrayList<>();

        
        int len = dico1.size();
        while(len>0){
            String minKey = null;
            float minValue = 1000.F;
            for(String key : dico1.keySet()) {
                float value = Float.parseFloat(dico1.get(key));
                if(value < minValue) {
                    minValue = value;
                    minKey = key;
                }
            }
            liste1.add(minKey);
            minValue = 1000.F;
            dico1.remove(minKey);
            len -= 1;
        }
        
        return liste1;
    }

    
    //cette methode recupere les cles du dictionnaire contenant l item correspondant a la distance la plus proche
    public int Choix_Plus_Proches(HashMap<String, String> dico){
        
    	boolean continuer = Boolean.TRUE;
        int nb = 0;
        
	while (continuer){
            Scanner reader = new Scanner(System.in);
            System.out.println("Choisissez le nombre d interactions parmi les plus petites que vous souhaitez: ");
            nb = reader.nextInt();
            if (nb>dico.size()){
                System.out.println("Il y en a trop");
            }
            else if(nb<1){
                System.out.println("Il en faut plus");
            }
            else {
                continuer = Boolean.FALSE;
            }
        }
        return nb;
    }            
    
    //cette methode utilise les trois methodes precedantes.
    public void PrincipalePartie4(HashMap<String, String> dico){
        
        ArrayList<String> l = new ArrayList<>();
        l = TriDico(dico);
        System.out.println("********************");
        System.out.println("vous passez a la derniere etape");
        System.out.println("********************");
        int nb = Choix_Plus_Proches(dico);
        //List<String> sousliste = new ArrayList<>();
        List<String> sousliste = new ArrayList<>(l.subList(0, nb));
        System.out.println("------------");
        System.out.println("Voici les "+nb+" plus proches interactions");
        System.out.println("******");
        
        Set<String> keys = dico.keySet();
        sousliste.stream().map((na) -> na + " : " + dico.get(na)).forEach((phr) -> {
            System.out.println(phr);
        });
    }
 
    //ceci est la methode principale du grogramme, qui fait appel a toutes les autres methodes
    public void Principale() throws FileNotFoundException, InterruptedException, IOException, StructureException{
        
        IntroductionProgramme();
        
        boolean trouver_Complexe = Boolean.FALSE;
        String nomStructure = new String();
        boolean complexe;
        
        while(trouver_Complexe == Boolean.FALSE){
            
            nomStructure = TelechargeStructure();
            complexe = VerificationStructure(nomStructure);
            if (complexe == Boolean.TRUE){
                trouver_Complexe = Boolean.TRUE;
            }  
            
        }
        
        String methode = Acquisition(nomStructure);
        System.out.println("La methode d'acquisition est : " + methode);
        
        
        String fairePartie2 = Choix1();
        if(fairePartie2.equalsIgnoreCase("o")){
            PrincipalePartie2(nomStructure);
        }
        System.out.println("************");
        System.out.println("Le programme va maintenant passer a la partie suivante");
        HashMap<String, String> dicoPrincipal = PrincipalePartie3(nomStructure);
        
        
        PrincipalePartie4(dicoPrincipal);
        
    }

}
