//Sandun Harshana

import java.io.Serializable;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@SuppressWarnings("serial")
public class Member implements Serializable {

	private String LASTNAME;
	private String FIRSTNAME;
	private String EMAIL;
	private int PHONENO;
	private int ID;
	private double FINES;
	
	private Map<Integer, loan> LNS;

	
	public member(String lastName, String firstName, String email, int phoneNo, int id) {
		this.LASTNAME = lastName;
		this.FIRSTNAME = firstName;
		this.EMAIL = email;
		this.PHONENO = phoneNo;
		this.ID = id;
		
		this.LNS = new HashMap<>();
	}

	
	public String toString() {
		StringBuilder sb = new StringBuilder();
		sb.append("Member:  ").append(ID).append("\n")
		  .append("  Name:  ").append(LN).append(", ").append(FN).append("\n")
		  .append("  Email: ").append(EM).append("\n")
		  .append("  Phone: ").append(PN)
		  .append("\n")
		  .append(String.format("  Fines Owed :  $%.2f", FINES))
		  .append("\n");
		
		for (loan loan : LNS.values()) {
			sb.append(loan).append("\n");
		}		  
		return sb.toString();
	}

	//get id
	public int getId() {
		return ID;
	}

	//get loan list
	public List<loan> getLoans() {
		return new ArrayList<loan>(LNS.values());
	}

	//get current loan count
	public int getNumberOfCurrentLoans() {
		return LNS.size();
	}

	//get fine
	public double getFinesOwed() {
		return FINES;
	}

	
	public void takeOutLoan(loan loan) {
		if (!LNS.containsKey(loan.getId())) {
			LNS.put(loan.getId(), loan);
		}
		else {
			throw new RuntimeException("Duplicate loan added to member");
		}		
	}

	//get last name
	public String getLastName() {
		return LN;
	}

	//get first name
	public String getFirstName() {
		return FN;
	}


	public void addFine(double fine) {
		FINES += fine;
	}
	
	public double payFine(double amount) {
		if (amount < 0) {
			throw new RuntimeException("Member.payFine: amount must be positive");
		}
		double change = 0;
		if (amount > FINES) {
			change = amount - FINES;
			FINES = 0;
		}
		else {
			FINES -= amount;
		}
		return change;
	}


	public void dischargeLoan(loan loan) {
		if (LNS.containsKey(loan.getId())) {
			LNS.remove(loan.getId());
		}
		else {
			throw new RuntimeException("No such loan held by member");
		}		
	}

}
