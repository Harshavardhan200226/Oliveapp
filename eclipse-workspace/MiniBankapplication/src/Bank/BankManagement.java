package Bank;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.sql.*;
import java.util.Scanner;

public class BankManagement {
    private static final int NULL = 0;
    static Connection con = MYConnection.getConnection();
	private static int loanAmount;

    // Create Account
    public static boolean createAccount(String cname, int passCode) {
        if (cname.isEmpty() || passCode == NULL) {
            System.out.println("All fields are required!");
            return false;
        }

        try {
            String sql = "INSERT INTO customer(cname, balance, pass_code) VALUES (?, 1000, ?)";
            PreparedStatement ps = con.prepareStatement(sql);
            ps.setString(1, cname);
            ps.setInt(2, passCode);

            int rows = ps.executeUpdate();
            if (rows == 1) {
                System.out.println("Account created successfully! You can now login.");
                return true;
            }
        } catch (SQLIntegrityConstraintViolationException e) {
            System.out.println("Username already exists! Try another one.");
        } catch (Exception e) {
            e.printStackTrace();
        }
        return false;
    }

    // Login Account
    public static boolean loginAccount(String cname, int passCode) {
        if (cname.isEmpty() || passCode == NULL) {
            System.out.println("All fields are required!");
            return false;
        }

        try {
            String sql = "SELECT * FROM customer WHERE cname = ? AND pass_code = ?";
            PreparedStatement ps = con.prepareStatement(sql);
            ps.setString(1, cname);
            ps.setInt(2, passCode);
            ResultSet rs = ps.executeQuery();

            BufferedReader sc = new BufferedReader(new InputStreamReader(System.in));

            if (rs.next()) {
                int senderAc = rs.getInt("ac_no");
                int Amount=rs.getInt("Money");
                int ch;

                while (true) {
                    System.out.println("\n Hello, " + rs.getString("cname") + "! What would you like to do?");
                    System.out.println("1) Transfer Money");
                    System.out.println("2) View Balance");
                    System.out.println("3) Deposit Money");
                    System.out.println("4) Request Loan");
                    System.out.println("5) Logout");
                    System.out.print("Enter Choice: ");
                    ch = Integer.parseInt(sc.readLine());

                    if (ch == 1) {
                        System.out.print("Enter Receiver A/c No: ");
                        int receiverAc = Integer.parseInt(sc.readLine());
                        System.out.print("Enter Amount: ");
                        int amt = Integer.parseInt(sc.readLine());
                        if (transferMoney(senderAc, receiverAc, amt)) {
                            System.out.println("Transaction successful!");
                        } else {
                            System.out.println("Transaction failed! Please try again.");
                        }
                    }else if (ch == 2) {
                        getBalance(senderAc);
                    }else if(ch == 3) {
                    		depositMoney(senderAc);
                    }
                    else if(ch == 4) {
                    		requestLoan(cname);
                    }
                    	else if (ch == 5) {
                        System.out.println("Logged out successfully. Returning to main menu.");
                        break;
                        }
                    else{
                        System.out.println("Invalid choice! Try again.");
                    }
                }
                return true;
            } else {
                System.out.println("Invalid username or password!");
                return false;
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
        return false;
    }

    // Get Balance
    public static void getBalance(int acNo) {
        try {
            String sql = "SELECT * FROM customer WHERE ac_no = ?";
            PreparedStatement ps = con.prepareStatement(sql);
            ps.setInt(1, acNo);
            ResultSet rs = ps.executeQuery();

            System.out.println("\n-------------------------------------------------");
            System.out.printf("%12s %15s %10s\n", "Account No", "Customer Name", "Balance");

            while (rs.next()) {
                System.out.printf("%12d %15s %10d.00\n",
                        rs.getInt("ac_no"),
                        rs.getString("cname"),
                        rs.getInt("balance"));
            }
            System.out.println("-------------------------------------------------");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    // Transfer Money
    public static boolean transferMoney(int sender_ac, int receiver_ac, int amount) {
        if (receiver_ac == NULL || amount == NULL) {
            System.out.println("All fields are required!");
            return false;
        }

        try {
            con.setAutoCommit(false);

            String checkBalance = "SELECT balance FROM customer WHERE ac_no = ?";
            PreparedStatement ps = con.prepareStatement(checkBalance);
            ps.setInt(1, sender_ac);
            ResultSet rs = ps.executeQuery();

            if (rs.next() && rs.getInt("balance") < amount) {
                System.out.println("Insufficient Balance!");
                return false;
            }

            String debit = "UPDATE customer SET balance = balance - ? WHERE ac_no = ?";
            PreparedStatement psDebit = con.prepareStatement(debit);
            psDebit.setInt(1, amount);
            psDebit.setInt(2, sender_ac);
            psDebit.executeUpdate();

            String credit = "UPDATE customer SET balance = balance + ? WHERE ac_no = ?";
            PreparedStatement psCredit = con.prepareStatement(credit);
            psCredit.setInt(1, amount);
            psCredit.setInt(2, receiver_ac);
            psCredit.executeUpdate();

            con.commit();
            return true;

        } catch (Exception e) {
            try {
                con.rollback();
            } catch (SQLException ex) {
                ex.printStackTrace();
            }
            e.printStackTrace();
        }
        return false;
    }
	public static boolean depositMoney(int senderAC) {
		if(senderAC==NULL) {
			System.out.println("All fields are required");
			return false;
		}
		try {
		String dep="Update customer set balance = balance + ? where ac_no=?";
		int Money=50000;
		PreparedStatement ps=con.prepareStatement(dep);
		ps.setInt(1, Money);
		ps.setInt(2,senderAC);
		int rows=ps.executeUpdate(); 
		if(rows==1) {
			System.out.println("Successfully deposited amount: " +Money);
            getBalance(senderAC);
            return true;
		    }
		else {
			System.out.println("Server is not working, please try later");
			}
		}
		catch(Exception e) {
			System.out.println(e);
		}
		return false;
	}
	public static boolean requestLoan(String cname) {
	    Scanner sc = new Scanner(System.in);
	    if (cname == null || cname.isEmpty()) {
	        System.out.println("All fields are required");
	        return false;
	    }

	    String sql = "SELECT cname, MonthlySalary FROM customer WHERE cname = ?";
	    try (PreparedStatement ps = con.prepareStatement(sql)) {
	        ps.setString(1, cname);
	        ResultSet rs = ps.executeQuery();

	        if (rs.next()) {
	            System.out.println("--- Loan Application Process ---");
	            System.out.println("Customer's Name:");
	            String customerName = sc.nextLine();

	            System.out.println("Contact Number:");
	            long number = sc.nextLong();
	            sc.nextLine();

	            System.out.println("Gmail:");
	            String gmail = sc.nextLine();

	            System.out.println("Age:");
	            int age = sc.nextInt();
	            sc.nextLine();

	            System.out.println("Highest Education:");
	            String education = sc.nextLine();

	            System.out.println("Enter your Job:");
	            String job = sc.nextLine();
	            System.out.println("Enter Company:");
	            String Company = sc.nextLine();

	            System.out.println("Work Experience:");
	            int experience = sc.nextInt();

	            System.out.println("Enter your Annual Package (LPA):");
	            int lpa = sc.nextInt();
	            sc.nextLine();

	            double calculatedMonthlySalary = (lpa / 12.0) * 100;
	            System.out.println("Calculated Monthly salary: " + calculatedMonthlySalary);
	            
	            if (calculatedMonthlySalary < 20000) {
	                System.out.println("Sorry, according to your profile we can't sanction this loan.");
	            } else {
	                System.out.println("Sir, Please again recheck the information you provided for us");
	                System.out.println("Yeah, sure");
	                System.out.println("Candidate Name:" + customerName);
	                System.out.println("Contact Number:" + number);
	                System.out.println("Gmail:" + gmail);
	                System.out.println("Age:" + age);
	                System.out.println("Highest Education:" + education);
	                System.out.println("Job:" + job);
	                System.out.println("I am doing in this " + Company + " from past three years as a " + job);
	                System.out.println("Work Experience:" + experience);
	                System.out.println("Annual Package:" + lpa);
	                System.out.println("Monthly Salary:" + calculatedMonthlySalary);
	                System.out.println("Yeah, This is all correct information");
	                System.out.println("Great news, You are eligible for a loan up to 20 lakhs");
	                System.out.print("Enter your desired loan amount: ");
	                double loanAmount = sc.nextDouble();

	                if (loanAmount > 2000000) {
	                    System.out.println("For amounts over 20L, please provide Proofs (Certificates)...");
	                    System.out.println("What are the certificates you want?");
	                    System.out.println("Tenth Class, Intermediate, Graduation certification, Aadhar and Pan card. These certificates are enough");
	                    System.out.println("Ok, I will provide all these certificates");
	                    System.out.println("Here the certificates , please check it");
	                    System.out.println("Tenth class original certificate is provided");
	                    System.out.println("Intermediate original Marks Memo is provided");
	                    System.out.println("Graduation original certificate is provided");
	                    System.out.println("All certificates are perfect");
	                }
	                
	                int rate = 5;
	                int time = 2;
	                sc.nextLine();
	                double totalInterest = (loanAmount * rate * time) / 100.0;
	                System.out.println("Total interest over period: " + totalInterest);
	                System.out.println("Sir,you need to pay the interest for every month");
	                System.out.println("Thank you sir, for using our Bank Services");
	                System.out.println("-------------SEE YOU AGAIN--------------");
	            }
	        } else {
	            System.out.println("Customer name not found in database.");
	        }
	        return true;
	    } catch (Exception e) {
	        System.err.println("Error details: " + e.getMessage());
	        e.printStackTrace();
	    }
	    return false;
	}
}