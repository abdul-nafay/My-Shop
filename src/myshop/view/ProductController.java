package myshop.view;

import java.net.URL;
import java.util.ResourceBundle;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import myshop.Main;
import myshop.dbhandler.DBQuries;

public class ProductController implements Initializable{

	@FXML
	private TextField prodNameTextField;
	@FXML 
	private TextField prodIdTextField;
	@FXML 
	private Button cancelBtn;
	Scene scene;
	
	
	@Override
	public void initialize(URL location, ResourceBundle resources) {
		
	}
	
	@FXML
	public void addButtonPressed(){
		
		String prodname = prodNameTextField.getText();
		String prodId =  prodIdTextField.getText();
		
		if(prodname.length() >0 && prodId.length() > 0){
			DBQuries query = new DBQuries();
			int result = query.insertIntoProducts(prodId, prodname);
				if(result==1){
					Main.successDialogBox();
					prodNameTextField.setText("");
					prodIdTextField.setText("");
				}
				else
					Main.faillureDialogBox("Item with same name and id already exists");
		}
		else
			Main.promptDialogBox("Please fill all the required fields");
		
	}

	public void setScene(Scene scene) { 
		this.scene = scene; 
		scene.setOnKeyPressed(
				event->{
					switch(event.getCode()){
					case ESCAPE:
						cancelButtonPressed();
					default:
						break;
					}
				}
			);
	}
	
	@FXML
	public void cancelButtonPressed(){
		scene.getWindow().hide();
	}


		
	
	
}
