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
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import myshop.dbhandler.DBQuries;
import myshop.models.AllSupplierViewModel;

public class AllSuppliersViewController implements Initializable{
	
	@FXML
	public TableView<AllSupplierViewModel> table;
	@FXML 
	public TableColumn<AllSupplierViewModel,String> nameColumn;
	@FXML 
	public TableColumn<AllSupplierViewModel,String> primaryMobileNoColumn;
	@FXML 
	public TableColumn<AllSupplierViewModel,String> secondaryMobileNoColumn;
	@FXML 
	public TableColumn<AllSupplierViewModel,String> landLineColumn;
	@FXML
	public TableColumn<AllSupplierViewModel,String> emailColumn;
	@FXML
	public TableColumn<AllSupplierViewModel,String> nicNoColumn;
	
	public ObservableList<AllSupplierViewModel> supplierList;

//	public MyAccountPaybleModel model;
//	ObservableList<MyAccountPaybleModel> list;


	//private static ObservableList<> data;
	
	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {
		
		DBQuries query = new DBQuries();
		ResultSet rset = query.getAllSuppliersName();
	
		System.out.println("Loop Chalao");
		supplierList = FXCollections.observableArrayList();
				
		
			try {
				while(rset.next())
				{
					//AllSupplierViewModel data = new AllSupplierViewModel(rset.getString("name"), rset.getString("mobile_No_1"), rset.getString("mobile_No_2"), rset.getString("landLineNo"), rset.getString("email"), rset.getString("nicNo"), rset.getString("refferedBy"), rset.getString("notes"), rset.getString("address"));
				//	supplierList.add(rset.getString("name")+""+rset.getString("mobile_No_1")+""+rset.getString("mobile_No_2")+""+rset.getString("landLineNo")+""+rset.getString("email")+""+rset.getString("nicNo"));
					supplierList.add(new AllSupplierViewModel(rset.getString("name"), rset.getString("mobile_No_1"), rset.getString("mobile_No_2"), rset.getString("landLineNo"), rset.getString("email"), rset.getString("nicNo"), rset.getString("refferedBy"), rset.getString("notes"), rset.getString("address")));
			
		}
				} catch (SQLException e) {
				e.printStackTrace();
			}
				
			 for(AllSupplierViewModel e : supplierList){
	        	 System.out.println(e.toString());
	         }

				nameColumn.setCellValueFactory(new PropertyValueFactory< AllSupplierViewModel,String>("name"));
				primaryMobileNoColumn.setCellValueFactory(new PropertyValueFactory<AllSupplierViewModel,String>("mobileNo1"));
				secondaryMobileNoColumn.setCellValueFactory(new PropertyValueFactory<AllSupplierViewModel,String>("mobileNo2"));
				landLineColumn.setCellValueFactory(new PropertyValueFactory<AllSupplierViewModel,String>("landLineNo"));
				emailColumn.setCellValueFactory(new PropertyValueFactory<AllSupplierViewModel,String>("email"));
				nicNoColumn.setCellValueFactory(new PropertyValueFactory<AllSupplierViewModel,String>("nic"));
				
				table.setItems(supplierList);
	
	}

}
