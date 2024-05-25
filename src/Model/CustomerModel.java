package Model;

public class CustomerModel {
	private int CId;
	private String FirstName;
	private String LastName;
	private String Email;
	private String Contact;
	private String Address;
	
	public CustomerModel(){
		
	}
	
	public CustomerModel(String FirstName, String LastName, String Email, String Contact, String Address){
		this.FirstName=FirstName;
		this.LastName=LastName;
		this.Email=Email;
		this.Contact=Contact;
		this.Address=Address;
	}

	public int getCId() {
		return CId;
	}

	public void setCId(int cId) {
		CId = cId;
	}

	public String getFirstName() {
		return FirstName;
	}

	public void setFirstName(String firstName) {
		FirstName = firstName;
	}

	public String getLastName() {
		return LastName;
	}

	public void setLastName(String lastName) {
		LastName = lastName;
	}

	public String getEmail() {
		return Email;
	}

	public void setEmail(String email) {
		Email = email;
	}

	public String getContact() {
		return Contact;
	}

	public void setContact(String contact) {
		Contact = contact;
	}

	public String getAddress() {
		return Address;
	}

	public void setAddress(String address) {
		Address = address;
	}

}
