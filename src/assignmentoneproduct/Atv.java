/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package assignmentoneproduct;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import java.security.SecureRandom;
import java.time.LocalDate;

/**
 *
 * @author albert
 */
public class Atv extends Product{
    
    private String brand, isFourWheel;
    private int engineSize;
    private File imageFile;
    
    public Atv() {
     
    }
    
    public Atv(String productName, double marketPrice, int quantity, LocalDate productAge, String brand, int engineSize, String isFourWheel, File imageFile){
        super(productName, marketPrice, quantity, productAge);
        
        this.brand = brand;
        this.engineSize = engineSize;
        this.isFourWheel = isFourWheel;
        setImageFile(new File("./src/assignmentoneproduct/images/defaultimage" ));
      
    }
    
    public String getBrand(){
        return brand;
    }
    
    public int getEngineSize(){
        return engineSize;
    }
    
    public String getIsFourWheel(){
        return isFourWheel;
    }
    
    public void setBrand(String brand){
        this.brand = brand;
    }
    
    public void setEngineSize(int engineSize){
        this.engineSize = engineSize;
    }
    
    public void setIsFourWheel(String isFourWheel){
        this.isFourWheel = isFourWheel;
    }
    
     public File getImageFile(){
     return imageFile;
    }
 
     public void setImageFile(File imageFile){
     this.imageFile = imageFile;
    }
     
     /*
     This method will copy the file specified to the images directory on this server and give it a unique name
     
     */
     
    public void copyImageFile() throws IOException{
        //Create a new Path to copy the image into a local directary
        Path sourcePath = imageFile.toPath();
        
        String uniqueFileName = getUniqueFileName(imageFile.getName());
        
        Path targetPath = Paths.get("./src/images" + uniqueFileName);
        
        //copy the file to the new directory
        Files.copy(sourcePath, targetPath, StandardCopyOption.REPLACE_EXISTING);
        
        //update the imageFile to the point to the new file
        imageFile = new File(targetPath.toString());
    }
    
    /**
     * This method will receive a String that represents a file name and return a 
     * String with a random, unique set of letters prefixed to it
     */
    
    private String getUniqueFileName(String oldFileName){
        String newName;
        
        //create a Random Number Generator
        SecureRandom rng = new SecureRandom();
        
        //loop until file name is unique
        
        do
        {
            newName = "";
        
        
        //generate 32 random characters
        for(int count =1; count <=32; count++)
        {
            int nextChar;
            
            do
            {
              nextChar = rng.nextInt(123);
            }while(!validCharacterValue(nextChar));
            
            newName = String.format("%s%c", newName, nextChar);
        }
        newName += oldFileName;
        }while(!uniqueFileInDirectory(newName));
        
        return newName;
    }
    
    
     /*
    This method will search the images directory and ensure that the file name 
    if unique.
    */
    public boolean uniqueFileInDirectory(String fileName){
        File directory = new File("./src/images");
        
        File[] dir_contents = directory.listFiles();
        
        for(File file: dir_contents)
        {
            if(file.getName().equals(fileName))
                return false;
        }
        return true;
            
    }
    /*
    
    This method will validate if the integer given corresponds to a valid
    ASCII character that could be used in a file name
    
    */
    
    public Boolean validCharacterValue(int asciiValue){
        
        //0-9 ASCII range 48-57
        if(asciiValue>=48 && asciiValue<=57)
            return true;
        
        //A-Z ASCII range 65-90
         if(asciiValue>=65 && asciiValue<=90)
            return true;
         
         //A-Z ASCII range 97-122
         if(asciiValue>=97 && asciiValue<=122)
            return true;
         
         return false;
    }
}  
    
     
    
