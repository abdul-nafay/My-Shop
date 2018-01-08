package myshop.view;

import java.net.URL;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.ResourceBundle;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.Scene;
import javafx.scene.control.CheckBox;
import javafx.scene.control.ComboBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.Label;
import javafx.scene.control.RadioButton;
import javafx.scene.control.TextField;
import myshop.Main;
import myshop.dbhandler.DBQuries;

public class AddPaymentWindowController implements Initializable {
	
	@FXML
	private DatePicker datePicker;
	@FXML
	private CheckBox supplierCheckBox;
	@FXML
	private CheckBox customerCheckBox;
	@FXML
	private RadioButton bankRadioButton;
	@FXML
	private RadioButton cashRadioButton;
	@FXML
	private Label dealerLabel;
	@FXML 
	private ComboBox<String> dealerComboBox;
	@FXML
	private TextField amountTextField;
	
	private ObservableList<String> supplierNameList;
	private ObservableList<String> customerNameList;
	private String dealer=null;
	private String paymentType=null;
	Scene scene;
	private int result;
	DBQuries query = new DBQuries();
	
	
	@Override
	public void initialize(URL location, ResourceBundle resources) {
		
		ResultSet rset1 = query.getAllSuppliersName();
		ResultSet rset2 = query.getAllCustomersName();
		
		supplierNameList = FXCollections.observableArrayList();
		customerNameList= FXCollections.observableArrayList();
		
		try {
			while(rset1.next())
				supplierNameList.add(rset1.getString("name")+"_"+rset1.getString("mobile_No_1"));
			while(rset2.next())
				customerNameList.add(rset2.getString("name")+"_"+rset2.getString("mobile_No_1"));
		} 
		catch (SQLException e) {
			e.printStackTrace();
		}
	}
	
	@FXML
	public  void customerSelected(){
		if(customerCheckBox.isSelected())
			supplierCheckBox.setSelected(false);
		if(!customerCheckBox.isSelected() && !supplierCheckBox.isSelected())
			customerCheckBox.setSelected(true);	
		dealerLabel.setText("Customer ID / Name:");
		dealerComboBox.setItems(customerNameList);
		dealer="Customer";
		System.out.println(dealer);
	}
	
	@FXML
	public void supplierSelected(){
		if(supplierCheckBox.isSelected())
			customerCheckBox.setSelected(false);
		if(!customerCheckBox.isSelected() && !supplierCheckBox.isSelected())
			supplierCheckBox.setSelected(true);	
		dealerLabel.setText("Supplier ID / Name :");
		dealerComboBox.setItems(supplierNameList);
		dealer="Supplier";
		System.out.println(dealer);
	}
	
	@FXML
	public  void bankSelected(){
		if(bankRadioButton.isSelected())
			cashRadioButton.setSelected(false);
		if(!bankRadioButton.isSelected() && !cashRadioButton.isSelected())
			bankRadioButton.setSelected(true);	
		paymentType="Bank";
		System.out.println("bank");
	}
	
	@FXML
	public void cashSelected(){
		if(cashRadioButton.isSelected())
			bankRadioButton.setSelected(false);
		if(!cashRadioButton.isSelected() && !bankRadioButton.isSelected())
			cashRadioButton.setSelected(true);	
		paymentType="Cash";
		System.out.println("cash");
	}
	
	public boolean allFieldsAreSelected(LocalDate currDate){
		return currDate!=null;
	}
	@FXML
	void addBtnPressed(){
		if(datePicker.getValue() != null && dealerComboBox.getValue() != null && paymentType.length() > 0 && amountTextField.getText().length()>0){
				LocalDate currDate = datePicker.getValue();
				int year= currDate.getYear();
				int month=currDate.getMonthValue();
				int date =currDate.getDayOfMonth();
				String dateFormat = date+"/"+month+"/"+year;
				String isdealer = dealerComboBox.getValue();
				String[] str =isdealer.split("_");
				String amount = amountTextField.getText();
			if(dealer.equals("Customer"))
				result = query.InsertIntoAccountReceivableOfCustomer(dateFormat ,str[1], str[0], paymentType , Float.parseFloat(amount));
			if(dealer.equals("Supplier"))
				result = query.InsertIntoAccountPayableOfSupplier(dateFormat ,str[1], str[0], paymentType , Float.parseFloat(amount));		
			System.out.println(result);
			if(result==1){
				Main.successDialogBox();
				datePicker.getEditor().setText("");
				customerCheckBox.setSelected(false);
				supplierCheckBox.setSelected(false);
				bankRadioButton.setSelected(false);
				cashRadioButton.setSelected(false);
				dealerComboBox.setValue("");				
				amountTextField.setText("");
			}
		}
		else
			Main.promptDialogBox("Please fill all the required fields");
	}
	
	@FXML
	public void cancelButtonPressed(){
		System.out.println("cancel presser");
		scene.getWindow().hide();

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
	
	
	}
