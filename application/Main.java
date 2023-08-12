package application;

import org.springframework.context.support.AbstractApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.stage.Stage;
import mainpackage.Engineers;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.layout.GridPane;


public class Main extends Application {
	@Override
	public void start(Stage primaryStage) {
		try {
			primaryStage.setTitle("Engineers Login Application");

	        Label usernameLabel = new Label("Username:");
	        TextField usernameField = new TextField();
	        
	        Label passwordLabel = new Label("Password:");
	        PasswordField passwordField = new PasswordField();
	        
	        Button loginButton = new Button("Login");
	        Label outputLabel = new Label();
	        
	        AbstractApplicationContext ctx = new ClassPathXmlApplicationContext("engineersinfo.xml");
			Engineers engineers = ctx.getBean("engineers", Engineers.class);

			outputLabel.setText("Enter 'exit' for both username and password if you want to exit.");
			
	        loginButton.setOnAction(event -> {
	            
	            
	    		String inputname = usernameField.getText();
		        String inputpw = passwordField.getText();
	    	    boolean ifNotExit = !inputname.equals("exit") || !inputpw.equals("exit");
	    	    boolean loginFail = engineers.login(inputname, inputpw) && ifNotExit;
	    	    if (loginFail == false && ifNotExit == true) {
		            outputLabel.setText("Welcome "+inputname);
		        } else if (loginFail == false && ifNotExit == false) {
		        	primaryStage.close();
		        } else {
		            outputLabel.setText("Login failed. Please check your credentials.");
		        }
	            
	        });
	        
	        
	        GridPane grid = new GridPane();
	        grid.setHgap(10);
	        grid.setVgap(10);
	        grid.setPadding(new Insets(20, 20, 20, 20));
	        
	        grid.add(usernameLabel, 0, 0);
	        grid.add(usernameField, 1, 0);
	        grid.add(passwordLabel, 0, 1);
	        grid.add(passwordField, 1, 1);
	        grid.add(loginButton, 1, 2);
	        grid.add(outputLabel, 0, 3, 2, 1);

	        Scene scene = new Scene(grid, 300, 200);
			scene.getStylesheets().add(getClass().getResource("application.css").toExternalForm());
			primaryStage.setScene(scene);
			primaryStage.show();
			ctx.close();
		} catch(Exception e) {
			e.printStackTrace();
		}
	}
	
	public static void main(String[] args) {
		
		launch(args);
		
	}
}
