/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package csvparser;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.PrintWriter;

/**
 *
 * @author Daniel
 */
public class CSVParser {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        String path= "./";        
        String nomFitxer;
        try{
            File folder = new File(path);
            File[] listOfFiles = folder.listFiles();

            //Parsejem els fitxers .csv o .CSV
            for (File fitxer : listOfFiles) {
                if (fitxer.isFile()) {
                    nomFitxer = fitxer.getName();
                    if (nomFitxer.endsWith(".csv") || nomFitxer.endsWith(".CSV")) parseFile(nomFitxer);
                }
            }
        }
        catch(Exception e){
            e.printStackTrace();
        }
    }
    
    private static void parseFile(String fileName){
         File f=null;
         FileReader fr = null;
         BufferedReader br=null;

         try{
             //Obrim el fitxer
             f=new File(fileName);
             fr=new FileReader(f);
             br=new BufferedReader(fr);
             
             String line;
             boolean eof=false;
             while(!eof){
                 //Llegim els registres mentre no és final de fitxer
                 line=br.readLine();
                 if(line!=null){
                     //Parsejem el registre
                     String newline=line.replaceAll(",\"\"", ",\\\\N");
                     newline=newline.replaceAll(",,", ",\\\\N,");
                     newline=newline.replaceAll(",,", ",\\\\N,");
                    //Afegim la linea parsejada a un nou fitxer
                    FileWriter file=null;
                    PrintWriter pw=null;
                    try{
                        file= new FileWriter("PARSED-"+fileName,true);
                        pw=new PrintWriter(file);

                        pw.println(newline);
                    }
                    catch (Exception e){
                        e.printStackTrace();
                    }
                    finally {
                        try{
                            if(null != file) file.close();
                        }
                        catch(Exception e2){
                            e2.printStackTrace();
                        }
                    }
                     //
                   }
                 else eof=true;  
             }
         }
         catch(Exception e){
             e.printStackTrace();
         }
         finally{
             try{
                 if ( null!= fr ){
                     fr.close();
                 }
             }
             catch(Exception e2){
                 e2.printStackTrace();
             }
         }
     }
}
