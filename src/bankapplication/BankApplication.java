/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package bankapplication;

import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.layout.GridPane;
import javafx.stage.Stage;

/**
 *
 * @author vramakri
 */
public class BankApplication extends Application {
    
    //defining global variables
    private TextField username = new TextField();
    private PasswordField password = new PasswordField();
    private Label loginCheck = new Label("");
    private ArrayList<Customer> currentCustomers = new ArrayList<Customer>();
    private Stage managerStage = new Stage();
    private Stage customerStage = new Stage();
    Manager thisManager = null;
    Customer thisCustomer = null;
    
    @Override
    public void start(final Stage primaryStage) 
    {
        //setting up primaryStage
        username.setPromptText("Enter your username.");
        password.setPromptText("Enter your password.");
        Button login = new Button("Login");

        loginCheck.setVisible(false);

        GridPane primaryGrid = new GridPane();
        primaryGrid.setAlignment(Pos.CENTER);
        
        primaryGrid.setPadding(new Insets(25,25,25,25));   
        
        primaryGrid.getChildren().addAll(username,password,login,loginCheck);
        primaryGrid.setVgap(5);
        primaryGrid.setHgap(5);
        GridPane.setConstraints(username, 1, 0);
        GridPane.setConstraints(password, 1, 1);
        GridPane.setConstraints(login, 1, 2);
        GridPane.setConstraints(loginCheck,1,3);

        
        Scene primaryScene = new Scene(primaryGrid, 300, 250);
        primaryStage.setTitle("Bank Application");
        primaryStage.setResizable(false);
        primaryStage.setScene(primaryScene);
        primaryStage.show();
        
        //setting up managerStage
        Button logout = new Button("Logout");
        Button add = new Button("Add Customer");
        Button delete = new Button("Delete Customer");
        
        final TextField newCustUser = new TextField();
        newCustUser.setPromptText("Enter new customer username");
        newCustUser.setPrefWidth(200);
        //newCustUser.setVisible(false);
        
        final TextField newCustPass = new TextField();
        newCustPass.setPromptText("Enter new customer password");
        //newCustPass.setVisible(false);
       
        
        GridPane managerGrid = new GridPane();
        //managerGrid.setAlignment(Pos.CENTER);
        managerGrid.setPadding(new Insets(10,10,10,10));   
        managerGrid.getChildren().addAll(logout, add, delete,newCustUser,newCustPass);
        managerGrid.setVgap(5);
        managerGrid.setHgap(5);
        GridPane.setConstraints(logout, 7, 0);
        GridPane.setConstraints(add, 2, 10);
        GridPane.setConstraints(delete, 4, 10);
        GridPane.setConstraints(newCustUser, 3, 4);
        GridPane.setConstraints(newCustPass, 3, 5);

        Scene manageScene = new Scene(managerGrid, 500, 300);
        managerStage.setTitle("Manager Login");
        managerStage.setResizable(false);
        managerStage.setScene(manageScene);
        
    
        login.setOnAction(new EventHandler<ActionEvent>() 
        {
            @Override public void handle(ActionEvent e) 
            {  
                if (username.getText().equals("admin"))
                {
                    if(password.getText().equals("admin"))
                    {
                        
                        primaryStage.close();
                        managerStage.show();
                        thisManager = new Manager();

                    }
                    else
                    {
                        loginCheck.setText("login failed - incorrect password \nPlease try again.");
                        loginCheck.setVisible(true);
                    }
                }
            }
        });
        
        add.setOnAction(new EventHandler<ActionEvent>() 
        {
            @Override public void handle(ActionEvent e) 
            {  
                if (thisManager != null)
                {
                    if ( (!(newCustUser.getText().equals(""))) && (!(newCustPass.getText().equals(""))))
                    {
                        if (!currentCustomers.isEmpty())
                        {
                            for (Customer c : currentCustomers)
                            {
                                if (newCustUser.getText().equals(c.username))
                                {
                                    Alert alert = new Alert(AlertType.INFORMATION);
                                    alert.setTitle("Error");
                                    alert.setHeaderText("A customer with this username already exists.");
                                    alert.setContentText("Please try again with a different username.");

                                    alert.showAndWait();
                                    break;
                                }
                            }
                            
                            thisCustomer = new Customer(newCustUser.getText(),newCustPass.getText());
                            try {
                                currentCustomers.add((Customer) thisCustomer.clone());
                            } catch (CloneNotSupportedException ex) {
                                Logger.getLogger(BankApplication.class.getName()).log(Level.SEVERE, null, ex);
                            }
                        }
                    }
                    else
                    {
                        Alert alert = new Alert(AlertType.INFORMATION);
                                    alert.setTitle("Error");
                                    alert.setHeaderText("Required fields are empty.");
                                    alert.setContentText("Please fill in a username and password.");

                                    alert.showAndWait();
    
                    }
                }
            }
        });
        
        
    }

    /**
     * The main() method is ignored in correctly deployed JavaFX application.
     * main() serves only as fallback in case the application can not be
     * launched through deployment artifacts, e.g., in IDEs with limited FX
     * support. NetBeans ignores main().
     *
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        launch(args);
    }
}
