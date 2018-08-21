
//Kapil

import java.text.SimpleDateFormat;
import java.util.Scanner;


public class Main {
	
	private static Scanner  in;   // changed variables uppercase to lowercase
	private static library  lib;      // changed variables uppercase to lowercase
	private static String  menu;// changed variables uppercase to lowercase
	private static Calendar  cal;// changed variables uppercase to lowercase
	private static SimpleDateFormat  sdf;// changed variables uppercase to lowercase
	
	
	private static String  get_menu() {      // changed method uppercase to lowercase
		StringBuilder sb = new StringBuilder();
		//appending to string builder
		sb.append("\nLibrary Main Menu\n\n")
		  .append("  M  : add member\n")
		  .append("  LM : list members\n")
		  .append("\n")
		  .append("  B  : add book\n")
		  .append("  LB : list books\n")
		  .append("  FB : fix books\n")
		  .append("\n")
		  .append("  L  : take out a loan\n")
		  .append("  R  : return a loan\n")
		  .append("  LL : list loans\n")
		  .append("\n")
		  .append("  P  : pay fine\n")
		  .append("\n")
		  .append("  T  : increment date\n")
		  .append("  Q  : quit\n")
		  .append("\n")
		  .append("Choice : ");
		  
		return sb.toString();
	}


	public static void main(String[] args) {		
		try {			
			in =  new Scanner(System.in);  // changed variables uppercase to lowercase
			lib =  library.INSTANCE();     // changed variables uppercase to lowercase
			cal =  Calendar.getInstance();      // changed variables uppercase to lowercase
			sdf =  new SimpleDateFormat("dd/MM/yyyy");   // changed variables uppercase to lowercase
	
			for (member m : lib.Members()) {
				output(m);
			}
			output(" ");
			for (book b : lib.Books()) {
				output(b);
			}
						
			MENU =  get_menu(); // changed method uppercase to lowercase
			
			boolean e = false;
			
			while (!e) {
				
				output("\n" +  sdf.format(cal.Date()));   // changed variables uppercase to lowercase
				String c = input(MENU);
				
				switch (c.toUpperCase()) {
				
				case "M": 
					addMember();
					break;
					
				case "LM": 
					listMembers();
					break;
					
				case "B": 
					addBook();
					break;
					
				case "LB": 
					listBooks();
					break;
					
				case "FB": 
					fixBooks();
					break;
					
				case "L": 
					borrowBook();
					break;
					
				case "R": 
					returnBook();
					break;
					
				case "LL": 
					listCurrentLoans();
					break;
					
				case "P": 
					payFine();
					break;
					
				case "T": 
					incrementDate();
					break;
					
				case "Q": 
					e = true;
					break;
					
				default: 
					output("\nInvalid option\n");
					break;
				}
				
				library.SAVE();
			}			
		} catch (RuntimeException e) {
			output(e);
		}		
		output("\nEnded\n");
	}	

	
	private static void payFine() {
		new  PayFineUI(new PayFineControl()).run();		
	}


	private static void listCurrentLoans() {
		output("");
		for (Loan loan :  lib.CurrentLoans()) {    // changed Class Loan lowercase to uppercase 
			output(loan + "\n"); 
		}		
	}



	private static void listBooks() {
		output("");
		for (Book book :  lib.Books()) {     // changed Class lowercase to uppercase 
			output(book + "\n");
		}		
	}



	private static void listMembers() {
		output("");
		for (Member member :  lib.Members()) {   // changed Class lowercase to uppercase 
			output(member + "\n");
		}		
	}



	private static void borrowBook() {
		new BorrowBookUI(new BorrowBookControl()).run();		
	}


	private static void returnBook() {
		new ReturnBookUI(new ReturnBookControl()).run();		
	}


	private static void fixBooks() {
		new FixBookUI(new FixBookControl()).run();		
	}


	private static void incrementDate() {
		try {
			int days = Integer.valueOf(input("Enter number of days: ")).intValue();
			 cal.incrementDate(days);    // changed variables uppercase to lowercase
		 lib.checkCurrentLoans();
			 output(SDF.format(cal.Date()));   // changed variables uppercase to lowercase
			
		} catch (NumberFormatException e) {
			 output("\nInvalid number of days\n");
		}
	}


	private static void addBook() {
		
		String author = input("Enter author: ");
		String title  = input("Enter title: ");
		String callNo = input("Enter call number: ");
		 Book book = lib.add_book(author, title, callNo);   // changed method uppercase to lowercase
		output("\n" + book + "\n");
		
	}

	
	private static void addMember() {
		try {
			String lastName = input("Enter last name: ");
			String firstName  = input("Enter first name: ");
			String email = input("Enter email: ");
			int phoneNo = Integer.valueOf(input("Enter phone number: ")).intValue();
			Member member = lib.add_mem(lastName, firstName, email, phoneNo);
			output("\n" + member + "\n");
			
		} catch (NumberFormatException e) {
			 output("\nInvalid phone number\n");
		}
		
	}


	private static String input(String prompt) {
		System.out.print(prompt);
		return in.nextLine();
	}
	
	
	
	private static void output(Object object) {
		System.out.println(object);
	}

	
}
