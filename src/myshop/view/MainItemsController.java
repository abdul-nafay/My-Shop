package myshop.view;

import java.io.IOException;

import javafx.fxml.FXML;
import myshop.Main;

public class MainItemsController {

	@FXML
	private void purchaseEntryBtn() throws IOException{
		Main.showPurchaseBillWindow();
	}
	
	@FXML
	private void salesInvoiceBtn() throws IOException{
		Main.showSalesInvoiceWindow();
	}
	
	@FXML
	private void addPaymentBtn() throws IOException{
		Main.showAddPaymentWindow();
	}
	
}
