/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package assignmentoneproduct;

import java.io.File;
import java.time.LocalDate;
import javafx.scene.control.Alert;

/**
 *
 * @author albert
 */
public class Product {
    private String productName;
    private double marketPrice;
    private int quantity;
    private LocalDate productAge;
    
    
    public Product(){
    
 }
 public Product(String productName, double marketPrice, int quantity, LocalDate productAge){
     
     this.productName = productName;
     this.marketPrice = marketPrice;
     this.quantity = quantity;
     this.productAge = productAge;     
 }
 
 public String getProductName(){
     return productName;
 }
 
 public void setProductName(String productName){
    this.productName = productName; 
 }
 
 public double getMarketPrice(){
     return marketPrice;
 }
 
 public void setMarketPrice(double marketPrice){
     this.marketPrice = marketPrice;
 }
 
 public int getQuantity(){
     return quantity;
 }
 
 public void setQuantity(int quantity){
     this.quantity = quantity;
 }
 
 public LocalDate getProductAge(){
     return productAge;
 }
 
 public void setProductAge(LocalDate productAge){
     this.productAge = productAge;
 }

    
}
