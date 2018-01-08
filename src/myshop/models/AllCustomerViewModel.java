package myshop.models;

public class AllCustomerViewModel {

	public String name;
	public String mobileNo1;
	public String mobileNo2;
	public String landLineNo;
	public String email;
	public String nic;
	public String refferedBy;
	public String notes;
	public String address;
	public AllCustomerViewModel(String name, String mobileNo1, String mobileNo2, String landLineNo, String email,
			String nic, String refferedBy, String notes, String address) {
		super();
		this.name = name;
		this.mobileNo1 = mobileNo1;
		this.mobileNo2 = mobileNo2;
		this.landLineNo = landLineNo;
		this.email = email;
		this.nic = nic;
		this.refferedBy = refferedBy;
		this.notes = notes;
		this.address = address;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getMobileNo1() {
		return mobileNo1;
	}
	public void setMobileNo1(String mobileNo1) {
		this.mobileNo1 = mobileNo1;
	}
	public String getMobileNo2() {
		return mobileNo2;
	}
	public void setMobileNo2(String mobileNo2) {
		this.mobileNo2 = mobileNo2;
	}
	public String getLandLineNo() {
		return landLineNo;
	}
	public void setLandLineNo(String landLineNo) {
		this.landLineNo = landLineNo;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public String getNic() {
		return nic;
	}
	public void setNic(String nic) {
		this.nic = nic;
	}
	public String getRefferedBy() {
		return refferedBy;
	}
	public void setRefferedBy(String refferedBy) {
		this.refferedBy = refferedBy;
	}
	public String getNotes() {
		return notes;
	}
	public void setNotes(String notes) {
		this.notes = notes;
	}
	public String getAddress() {
		return address;
	}
	public void setAddress(String address) {
		this.address = address;
	}
	
	
	
}
