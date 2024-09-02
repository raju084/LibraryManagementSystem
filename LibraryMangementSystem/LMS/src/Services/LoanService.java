package Services;
import Models.Loan;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.sql.ResultSet;
public class LoanService {
	public void issueBook(Loan loan) {
		 try (Connection conn =DB.DATABASECONNCETION.getConnection();
	             PreparedStatement stmt = conn.prepareStatement(sql)) {
	            stmt.setInt(1, loan.getBookId());
	            stmt.setInt(2, loan.getMemberId());
	            stmt.setDate(3, new java.sql.Date(loan.getLoanDate().getTime()));
	            stmt.setDate(4, new java.sql.Date(loan.getReturnDate().getTime()));
	            stmt.setString(5, loan.getStatus());
	            stmt.executeUpdate();
	        } catch (SQLException e) {
	            e.printStackTrace();
	        }
	}
	 public List<Loan> getAllLoans() {
	        List<Loan> loans = new ArrayList<>();
	        String sql = "SELECT * FROM Loans";
	        try (Connection conn = DB.DATABASECONNCETION.getConnection();
	             PreparedStatement stmt = conn.prepareStatement(sql);
	             Resultset rs = stmt.executeQuery()) {
	            while (rs.next()) {
	                Loan loan = new Loan(
	                        rs.getInt("book_id"),
	                        rs.getInt("member_id"),
	                        rs.getDate("loan_date"),
	                        rs.getDate("return_date"),
	                        rs.getString("status")
	                );
	                loan.setId(rs.getInt("id")); // Assuming Loan class has an id field
	                loans.add(loan);
	            }
	        } catch (SQLException e) {
	            e.printStackTrace();
	        }
	        return loans;
	    }
	 // Method to return a book and update the loan status
	    public void returnBook(int loanId, Date returnDate) {
	        String sql = "UPDATE Loans SET return_date = ?, status = 'Returned' WHERE id = ?";
	        try (Connection conn = DBConnection.getConnection();
	             PreparedStatement stmt = conn.prepareStatement(sql)) {
	            stmt.setDate(1, new java.sql.Date(returnDate.getTime()));
	            stmt.setInt(2, loanId);
	            stmt.executeUpdate();
	        } catch (SQLException e) {
	            e.printStackTrace();
	        }
	    }
	 // Method to update the status of a loan
	    public void updateLoanStatus(int loanId, String status) {
	        String sql = "UPDATE Loans SET status = ? WHERE id = ?";
	        try (Connection conn = DBConnection.getConnection();
	             PreparedStatement stmt = conn.prepareStatement(sql)) {
	            stmt.setString(1, status);
	            stmt.setInt(2, loanId);
	            stmt.executeUpdate();
	        } catch (SQLException e) {
	            e.printStackTrace();
	        }
	    }
	    public List<Loan> getLoansByMemberId(int memberId) {
	        List<Loan> loans = new ArrayList<>();
	        String sql = "SELECT * FROM Loans WHERE member_id = ?";
	        try (Connection conn = DBConnection.getConnection();
	             PreparedStatement stmt = conn.prepareStatement(sql)) {
	            stmt.setInt(1, memberId);
	            ResultSet rs = stmt.executeQuery();
	            while (rs.next()) {
	                Loan loan = new Loan(
	                        rs.getInt("book_id"),
	                        rs.getInt("member_id"),
	                        rs.getDate("loan_date"),
	                        rs.getDate("return_date"),
	                        rs.getString("status")
	                );
	                loan.setId(rs.getInt("id")); // Assuming Loan class has an id field
	                loans.add(loan);
	            }
	        } catch (SQLException e) {
	            e.printStackTrace();
	        }
	        return loans;
	    }	
}
