/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package assignmentoneproduct;

import java.awt.Desktop;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.time.LocalDate;
import java.time.Month;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.application.Application;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.embed.swing.SwingFXUtils;
import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.geometry.Insets;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.image.WritableImage;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.stage.FileChooser;
import javafx.stage.FileChooser.ExtensionFilter;
import javafx.stage.Stage;
import javax.imageio.ImageIO;

/**
 *
 * @author albert
 */
public class AssignmentOneProduct extends Application {
    
    Stage window;
    private TableView<Atv> table;
    private TextField nameInput, priceInput, quantityInput, productAgeInput, brandInput, engineSizeInput;
    private ChoiceBox<String> isFourWheel = new ChoiceBox<>(FXCollections.observableArrayList(
    "Yes", "No"));
    private Button browse;
    private File file = new File("./src/images/defaultimage.jpg");
    private Desktop desktop = Desktop.getDesktop();
    private TextArea textArea;
    Scene sceneOne, sceneTwo;
    private ImageView imageView;
    int numberOfItems = 3;
    
    
    ObservableList<Atv> atvs = FXCollections.observableArrayList();
    Double newTotal = 0.0;
    

   //main method
    public static void main(String[] args) {
        launch(args);
     
    }
    
    @Override
    public void start(Stage primaryStage) throws Exception {   

        imageView = new ImageView();
        imageView.setFitHeight(40);
        imageView.setFitWidth(40);
        BufferedImage bufferedImage = ImageIO.read(file);
        Image image = SwingFXUtils.toFXImage(bufferedImage, null);
        imageView.setImage(image);

            
        window = primaryStage;
        window.setTitle("Products Table - JavaFX");

         //Name column
         TableColumn<Atv, String> nameColumn = new TableColumn<>("Name");
         nameColumn.setMinWidth(200);
         nameColumn.setCellValueFactory(new PropertyValueFactory<>("productName"));
         
          //market price column
         TableColumn<Atv, Double> priceColumn = new TableColumn<>("Price");
         priceColumn.setMinWidth(100);
         priceColumn.setCellValueFactory(new PropertyValueFactory<>("marketPrice"));
         
          //quantity column
         TableColumn<Atv, Integer> quantityColumn = new TableColumn<>("Quantity");
         quantityColumn.setMinWidth(100);
         quantityColumn.setCellValueFactory(new PropertyValueFactory<>("quantity"));
         
          //product age column
         TableColumn<Atv, LocalDate> productAgeColumn = new TableColumn<>("Product Age");
         productAgeColumn.setMinWidth(100);
         productAgeColumn.setCellValueFactory(new PropertyValueFactory<>("productAge"));
         
          //brand column
         TableColumn<Atv, String> brandColumn = new TableColumn<>("Atv Brand");
         brandColumn.setMinWidth(100);
         brandColumn.setCellValueFactory(new PropertyValueFactory<>("brand"));
         
         //engine size column
         TableColumn<Atv, Integer> engineSizeColumn = new TableColumn<>("Engine Size");
         engineSizeColumn.setMinWidth(100);
         engineSizeColumn.setCellValueFactory(new PropertyValueFactory<>("engineSize"));
         
         //Is Four Wheel column
         TableColumn<Atv, String> isFourWheelColumn = new TableColumn<>("Four Wheel");
         isFourWheelColumn.setMinWidth(100);
         isFourWheelColumn.setCellValueFactory(new PropertyValueFactory<>("isFourWheel"));
         
         //Image column
         TableColumn<Atv, File> imageColumn = new TableColumn<>("Image");
         imageColumn.setMinWidth(100);
         imageColumn.setCellValueFactory(new PropertyValueFactory<>("imageFile"));
       
         //Name input
         nameInput = new TextField();
         nameInput.setPromptText("Product Name");
         nameInput.setMinWidth(100);
         
         //Price input
         priceInput = new TextField();
         priceInput.setPromptText("Price");
         
         //Quantity input.
         quantityInput = new TextField();
         quantityInput.setPromptText("Quantity");    
         
         //Product Age input.
         productAgeInput = new TextField();
         productAgeInput.setPromptText("Product Age");
         
         //Brand input.
         brandInput = new TextField();
         brandInput.setPromptText("Brand Name");
         brandInput.setMinWidth(100);
         
         //Engine Size input.
         engineSizeInput = new TextField();
         engineSizeInput.setPromptText("Engine Size"); 
         
         //Create set value for Choice Box, and size.
         isFourWheel.setValue("Yes");   
         isFourWheel.setMinWidth(100);
         
         //Declare textarea.
         textArea = new TextArea();
         textArea.setPromptText("Path of Selected File or Files");
         textArea.setPrefSize(200,50);
         textArea.setEditable(false);
        
         //Browse Button, and functionality added to allow user to view computer files.
         browse = new Button("Browse");
         browse.setOnAction(e -> { 
            try {
                browseButtonClicked();
            } catch (IOException ex) {
                Logger.getLogger(AssignmentOneProduct.class.getName()).log(Level.SEVERE, null, ex);
            }
        });

         //Add, Delete, tableView, and newInstance Buttons created, and OnActions set.
         Button addButton = new Button("Add");
         addButton.setOnAction(e -> addButtonClicked());
         
         Button deleteButton = new Button("Delete");
         deleteButton.setOnAction(e -> deleteButtonClicked());
         
         Button tableViewButton = new Button("Table View");
         tableViewButton.setOnAction(e -> window.setScene(sceneOne));
         
         Button newInstanceButton = new Button("Create a new instance");
         newInstanceButton.setOnAction(e -> window.setScene(sceneTwo));
         
         Label inventoryValue = new Label("Inventory value = " + newTotal);
   
        //creates VBox, and adds input actions to create a new instance.
         VBox inputBox = new VBox();
         inputBox.setPadding(new Insets(15, 15, 15, 15));
         inputBox.setSpacing(10);
         inputBox.getChildren().addAll(nameInput, priceInput, quantityInput, productAgeInput, brandInput, engineSizeInput, isFourWheel, browse, imageView, textArea, addButton, tableViewButton);
        
        //Creates table, and adds columns, and populates table.
         table = new TableView<>();
         table.setItems(getAtv());
         table.getColumns().addAll(nameColumn, priceColumn, quantityColumn, productAgeColumn, brandColumn, engineSizeColumn, isFourWheelColumn, imageColumn);
         
         
         
        //First scene layout.
        VBox layoutOne = new VBox();
        layoutOne.getChildren().addAll(table, newInstanceButton, deleteButton, inventoryValue);
        sceneOne = new Scene(layoutOne);
        //Second scene layout.
        VBox layoutTwo = new VBox();
        layoutTwo.getChildren().addAll(inputBox);
        sceneTwo = new Scene(layoutTwo);
        
        //Starting Scene, and window display setup.
        window.setScene(sceneOne);
        window.show();
    }
     /**
      * This method will handle add button clicked, and add new value to table 
      * view if specifications are met, and canRun is true.
      */
     public void addButtonClicked(){
        boolean canRun = true;
        
        try{
        canRun = true;
        
        Atv atvs = new Atv();
        
          if(nameInput.getText().isEmpty()){
           canRun = false;
           Alert alert = new Alert(AlertType.ERROR);
           alert.setTitle("ERROR");
           alert.setHeaderText("Whoops, you cant do that");
           alert.setContentText("You must enter a product name! ");
           alert.showAndWait();
        }
          
           if(brandInput.getText().isEmpty()){
           canRun = false;
           Alert alert = new Alert(AlertType.ERROR);
           alert.setTitle("ERROR");
           alert.setHeaderText("Whoops, you cant do that");
           alert.setContentText("You must enter a brand name! ");
           alert.showAndWait();
        }
          
           if(textArea.getText().isEmpty()){
           canRun = false;
           Alert alert = new Alert(AlertType.ERROR);
           alert.setTitle("ERROR");
           alert.setHeaderText("Whoops, you cant do that");
           alert.setContentText("You must choose an image file! ");
           alert.showAndWait();
        }
           try{
               atvs.setMarketPrice(Double.parseDouble(priceInput.getText()));
           }catch(NumberFormatException e){
           Alert alert = new Alert(AlertType.ERROR);
           alert.setTitle("ERROR");
           alert.setHeaderText("Whoops, you cant do that");
           alert.setContentText("Price must be a number!");
           alert.showAndWait();
           canRun=false;               
        }
            
          try{
        atvs.setProductName(nameInput.getText());  
        atvs.setImageFile(file);
        atvs.setQuantity(Integer.parseInt(quantityInput.getText()));  

        } catch(NumberFormatException e){
             Alert alert = new Alert(AlertType.ERROR);
           alert.setTitle("ERROR");
           alert.setHeaderText("Whoops, you cant do that");
           alert.setContentText("Quantity Must be a whole number!");
           alert.showAndWait();
            canRun=false;
        }
        
        atvs.setProductAge(LocalDate.parse(productAgeInput.getText()));      
        atvs.setBrand(brandInput.getText());
      
        try{
        atvs.setEngineSize(Integer.parseInt(engineSizeInput.getText()));
        
        }catch(NumberFormatException e){
           Alert alert = new Alert(AlertType.ERROR);
           alert.setTitle("ERROR");
           alert.setHeaderText("Whoops, you cant do that");
           alert.setContentText("Engine size must be an integer!");
           alert.showAndWait();
            canRun=false;
        }
        
        atvs.setIsFourWheel(isFourWheel.getValue());
        
        if(canRun){
        table.getItems().add(atvs);
        }
        
        } catch(NumberFormatException e){
            System.out.println("Error:" + e);
  
        } catch(DateTimeParseException f){
            Alert alert = new Alert(AlertType.ERROR);
           alert.setTitle("ERROR");
           alert.setHeaderText("Whoops, you cant do that");
           alert.setContentText("Error: Product age must be in proper format. eg. 2017-03-22!");
           alert.showAndWait();    
        }
      
         if(canRun){
        nameInput.clear();
        priceInput.clear();
        quantityInput.clear();
        productAgeInput.clear();
        brandInput.clear();
        engineSizeInput.clear();
        isFourWheel.setValue("Yes");
        textArea.clear();     
        currentInventoryValue();
        
        }
 
    }
    /**
     * This method will handle delete button clicked, and delete a row that is 
     * selected in the table.
     */ 
    public void deleteButtonClicked(){
        ObservableList<Atv> atvSelected, allAtvs;
        allAtvs = table.getItems();
        atvSelected = table.getSelectionModel().getSelectedItems();
        
        atvSelected.forEach(allAtvs::remove);
    }
     /**
     * This method will handle browse button clicked, and set image view.
     */ 
    public void browseButtonClicked() throws IOException{
        
          //Image jfile chooser input.
         FileChooser fileChooser = new FileChooser();
         fileChooser.setTitle("Choose File");
         fileChooser.getExtensionFilters().addAll(
         new ExtensionFilter("Image Files", "*.jpg", "*.png")
         );
        //single File Selection 
        file = fileChooser.showOpenDialog(window);
        if(file!= null){
        textArea.setText(file.getAbsolutePath());
        }
        
         
         BufferedImage bufferedImage = ImageIO.read(file);
         Image image = SwingFXUtils.toFXImage(bufferedImage, null);
         imageView.setImage(image);
    }
    public void currentInventoryValue(){
       
      numberOfItems++;
      newTotal = 0.0;
       String total = "";
         
               for(int i = 0; i <= numberOfItems; i++){
               
                  
                  total=table.getColumns().get(1).getCellData(i).toString();
                  
                   newTotal += Double.parseDouble(total);
               }
               System.out.println("Inventory Value: " + newTotal);
    }
    /**
     * This method will get all Atv class objects, and put them into an Observable
     * List.
     * @return 
     */
    public ObservableList<Atv> getAtv(){
        
       atvs.add(new Atv("Laptop", 850.00, 2, LocalDate.of(2017, Month.MARCH, 21), "Honda", 350, "Yes", new File("/src/assignmentoneproduct/images/defaultimage.jpg")));
       atvs.add(new Atv("Tv", 1000.00, 6, LocalDate.of(2017, Month.MARCH, 21), "Yamaha", 250, "Yes", new File("/src/assignmentoneproduct/images/defaultimage.jpg")));
       atvs.add(new Atv("Camera", 600.00, 10, LocalDate.of(2017, Month.MARCH, 21), "KTM", 300, "No", new File("/src/assignmentoneproduct/images/defaultimage.jpg")));
       atvs.add(new Atv("Xbox", 300.00, 5, LocalDate.of(2017, Month.MARCH, 21), "Kawasaki", 200, "No", new File("/src/assignmentoneproduct/images/defaultimage.jpg")));
        return atvs;
    }

}//End of Assignment One Class.
