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
import myshop.models.AllCustomerViewModel;

public class AllCustomerViewController implements Initializable{

	
	@FXML
	public TableView<AllCustomerViewModel> table;
	@FXML 
	public TableColumn<AllCustomerViewModel,String> nameColumn;
	@FXML 
	public TableColumn<AllCustomerViewModel,String> primaryMobileNoColumn;
	@FXML 
	public TableColumn<AllCustomerViewModel,String> secondaryMobileNoColumn;
	@FXML 
	public TableColumn<AllCustomerViewModel,String> landLineColumn;
	@FXML
	public TableColumn<AllCustomerViewModel,String> emailColumn;
	@FXML
	public TableColumn<AllCustomerViewModel,String> nicNoColumn;
	
	public ObservableList<AllCustomerViewModel> customerList;
	
	
	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {

		
		DBQuries query = new DBQuries();
		ResultSet rset = query.getAllCustomersName();
	
		customerList = FXCollections.observableArrayList();
		
		try {
			while(rset.next())
				customerList.add(new AllCustomerViewModel(rset.getString("name"), rset.getString("mobile_No_1"), rset.getString("mobile_No_2"), rset.getString("landLineNo"), rset.getString("email"), rset.getString("nicNo"), rset.getString("refferedBy"), rset.getString("notes"), rset.getString("address")));
		} catch (SQLException e) {
			e.printStackTrace();
		}
	
		
		nameColumn.setCellValueFactory(new PropertyValueFactory< AllCustomerViewModel,String>("name"));
		primaryMobileNoColumn.setCellValueFactory(new PropertyValueFactory<AllCustomerViewModel,String>("mobileNo1"));
		secondaryMobileNoColumn.setCellValueFactory(new PropertyValueFactory<AllCustomerViewModel,String>("mobileNo2"));
		landLineColumn.setCellValueFactory(new PropertyValueFactory<AllCustomerViewModel,String>("landLineNo"));
		emailColumn.setCellValueFactory(new PropertyValueFactory<AllCustomerViewModel,String>("email"));
		nicNoColumn.setCellValueFactory(new PropertyValueFactory<AllCustomerViewModel,String>("nic"));
		
		table.setItems(customerList);
	}

}
