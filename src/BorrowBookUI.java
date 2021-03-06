//Amarjit

import java.util.Scanner;


public class BorrowBookUI {    
	
	public static enum UI_STATE { INITIALISED, READY, RESTRICTED, SCANNING, IDENTIFIED, FINALISING, COMPLETED, CANCELLED };

	private BorrowBookControl control;
	private Scanner input;
	private UI_STATE state;

	
	public BorrowBookUI(BorrowBookControl control) {
		this.control = control;
		input = new Scanner(System.in);
		state = UI_STATE.initialised;  // changed uppercase to lowercase   
		control.setUI(this);
	}

	
	private String input(String prompt) {
		System.out.print(prompt);
		return input.nextLine();
	}	
		
		
	private void output(Object object) {
		System.out.println(object);
	}
	
			
	public void setState(UI_STATE state) {
		this.state = state;
	}

	
	public void run() {
		  

             output("Borrow Book Use Case UI\n");
		                                       // loop structure clear
		    while (true) {
			
			switch (state) {			
			
			case CANCELLED:
				output("Borrowing Cancelled");
				return;

				
			case READY:
				String memStr = input("Swipe member card (press <enter> to cancel): ");
				if (memStr.length() == 0) {
					control.cancel();
					break;
				}
				try {
					int memberId = Integer.valueOf(memStr).intValue();
					control.Swiped(memberId);
				}
				catch (NumberFormatException e) {
					output("Invalid Member Id");
				}
				break;

				
			case RESTRICTED:
				input("Press <any key> to cancel");
				control.CANCEL();  // changed lowercase to uppercase 
				break;
			
				
			case SCANNING:

				String bookStr = input("Scan Book (<enter> completes): ");
				if (bookStr.length() == 0) {
					control.complete();// changed uppercase to lowercase 
					break;
				}
				try {
					int bookId = Integer.valueOf(bookStr).intValue();
					control.scanned(bookId);  // use lower letters
					
				} catch (NumberFormatException e) {
					output("Invalid Book Id");
				} 
				break;
					
				
			case FINALISING:

				String ans = input("Commit loans? (Y/N): ");
				if (ans.toUpperCase().equals("N")) {                  // structure changes
					control.cancel();
					
				} else {
					control.commitLoans();
					input("Press <any key> to complete ");
				}

				break;
				
				
			case COMPLETED:
				output("Borrowing Completed");
				return;
	
				
			default:
				output("Unhandled state");
				throw new RuntimeException("BorrowBookUI : unhandled state :" + state);			
			}
		}		
	}


	public void display(Object object) {
		output(object);		
	}


}
