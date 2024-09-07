package Main;
import java.util.List;
import java.util.Scanner;
import java.util.Date;
import Models.Book;
import Models.Member;
import Models.Loan;
import Services.BookServices;
import Services.LoanService;
import Services.MemberService;
public class Main {

	public static void main(String[] args) {
		    BookServices bookService = new BookServices();
	        MemberService memberService = new MemberService();
	        LoanService loanService = new LoanService();

	        Scanner scanner = new Scanner(System.in);
	        int choice;

	        do {
	            System.out.println("Library Management System");
	            System.out.println("1. Add Book");
	            System.out.println("2. View Books");
	            System.out.println("3. Add Member");
	            System.out.println("4. View Members");
	            System.out.println("5. Issue Book");
	            System.out.println("6. View Loans");
	            System.out.println("0. Exit");
	            System.out.print("Enter your choice: ");
	            choice = scanner.nextInt();
	            //scanner.nextLine(); 

	            switch (choice) {
	                case 1:
	                    System.out.print("Enter title: ");
	                    String title = scanner.nextLine();
	                    System.out.print("Enter author: ");
	                    String author = scanner.nextLine();
	                    System.out.print("Enter publisher: ");
	                    String publisher = scanner.nextLine();
	                    System.out.print("Enter year: ");
	                    int year = scanner.nextInt();
	                    System.out.print("Enter available copies: ");
	                    int availableCopies = scanner.nextInt();
	                    bookService.addBook(new Book(title, author, publisher, year, availableCopies));
	                    break;
	                case 2:
	                    List<Book> books = bookService.getAllBooks();
	                    books.forEach(book -> System.out.println(book.getTitle() + " by " + book.getAuthor()));
	                    break;
	                case 3:
	                    System.out.print("Enter name: ");
	                    String name = scanner.next();
	                    System.out.print("Enter email: ");
	                    String email = scanner.next();
	                    System.out.print("Enter phone: ");
	                    String phone = scanner.next();
	                    System.out.print("Enter address: ");
	                    String address = scanner.next();
	                    memberService.addMember(new Member(name, email, phone, address));
	                    break;
	                case 4:
	                    List<Member> members = memberService.getAllMembers();
	                    members.forEach(member -> System.out.println(member.getName() + " - " + member.getEmail() +" - " +member.getPhone() + " - " + member.getAddress()));
	                    break;
	                case 5:
	                    System.out.print("Enter book ID: ");
	                    int bookId = scanner.nextInt();
	                    System.out.print("Enter member ID: ");
	                    int memberId = scanner.nextInt();
	                    System.out.print("Enter ReturnDate (yyyy-MM-dd): ");
	                    String returnDateString = scanner.next(); 
	                    System.out.print("Enter Status: ");
	                    String status = scanner.next();
	                    loanService.issueBook(new Loan(bookId, memberId, new Date(), null, "Issued"));
	                    break;
	                case 6:
	                    List<Loan> loans = loanService.getAllLoans();
	                    loans.forEach(loan -> System.out.println("Book ID: " + loan.getBookId() + ", Member ID: " + loan.getMemberId() + "Return Date: " +loan.getReturnData() + "Statu:"));
	                    break;
	                case 0:
	                    System.out.println("Exiting...");
	                    break;
	                default:
	                    System.out.println("Invalid choice, please try again.");
	            }
	        } while (choice != 0);
		// TODO Auto-generated method stub

	}

}
