package myshop.view;

import java.net.URL;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ResourceBundle;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableRow;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseButton;
import myshop.Main;
import myshop.dbhandler.DBQuries;
import myshop.models.AllProductViewModel;

public class AllProductViewController implements Initializable{
	
	
	@FXML
	public TableView<AllProductViewModel> table;
	@FXML 
	public TableColumn<AllProductViewModel,String> producIdColumn;
	@FXML 
	public TableColumn<AllProductViewModel,String> productNameColumn;
	
	public ObservableList<AllProductViewModel> productList;
	public static String clickedProductId,clickedProductName;

	@Override
	public void initialize(URL location, ResourceBundle resources) {

		
		DBQuries query = new DBQuries();
		ResultSet rset = query.getAllProducts();
	
		productList = FXCollections.observableArrayList();
		
		try {
			while(rset.next())
				productList.add(new AllProductViewModel(rset.getString("product_Id"), rset.getString("product_Name")));
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		producIdColumn.setCellValueFactory(new PropertyValueFactory<AllProductViewModel,String>("productId"));
		productNameColumn.setCellValueFactory(new PropertyValueFactory<AllProductViewModel,String>("productName"));
		
		table.setItems(productList);
		
		
		
		/////////////////////////
		
	     table.setRowFactory(tv -> {
	    	    TableRow<AllProductViewModel> row = new TableRow<>();
	    	    row.setOnMouseClicked(event -> {
	    	        if (! row.isEmpty() && event.getButton()==MouseButton.PRIMARY 
	    	             && event.getClickCount() == 2) {

	    	        	AllProductViewModel clickedRow = row.getItem();
	    	        	
	    	        	clickedProductId=clickedRow.productId;
	    	        	clickedProductName=clickedRow.productName;
	    				

	    				
	    				try {
	    					Main.showPurchaseBillDetail();
	    				} catch (Exception e) {
	    					//e.printStackTrace();
	    				}
	    	          
	    	        }
	    	        if(! row.isEmpty() &&event.getButton() == MouseButton.SECONDARY)
	    	        {
	    	        	
	    	        }
	    	        if(event.getButton() == MouseButton.PRIMARY&& event.getClickCount() == 1){
	    	        	
	    	        }
	    	    });
	    	    return row ;
	    	});
		
		
		////////////////////////
	
	}
}
