package Bank;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
class BankUser{
	public static void main(String[]args) throws NumberFormatException, IOException {
		int ch;
		String name;
		int SenderAC = 0;
		int passcode;
		while(true) {
		System.out.println("1.Create Account");
		System.out.println("2.Login Account");
		System.out.println("3.Exit");
		try {
			BufferedReader sin=new BufferedReader(new InputStreamReader(System.in));
			System.out.println("Enter your choice:");
			ch=Integer.parseInt(sin.readLine());
			switch(ch) {
			case 1 ->{
				System.out.println("Enter UserName:");
				name=sin.readLine();
				System.out.println("Enter password:");
				passcode=Integer.parseInt(sin.readLine());
				if(BankManagement.createAccount(name,passcode)) {
					System.out.println("Account is successfully created");
				}
			}
			case 2 ->{
				System.out.println("Enter UserName:");
				name=sin.readLine();
				System.out.println("Enter password:");
				passcode=Integer.parseInt(sin.readLine());
				if(!BankManagement.loginAccount(name, passcode)) {
					System.out.println("UserName or pasword is invalid");
				}
			}
			case 3 ->{
				System.out.println("Enter Account Number:");
				int AC=Integer.parseInt(sin.readLine());
				if(BankManagement.depositMoney(SenderAC)) {
					System.out.println("Amount is successfully deposited to your account");
				}
				else {
					System.out.println("Balance is higher than 1000.Can't deposit at now");
				}
			}
			case 4 ->{
				System.out.println("Enter Customer's Name:");
				name=sin.readLine();
				if(BankManagement.requestLoan(name)) {
					System.out.println("Successfully Loan is approved");
				}
				else {
					System.out.println("Loan is not approved");
				}
			}
			case 5 ->{
				System.out.println("Successfully exited");
				}
			}
		}
		catch(Exception e) {
			System.out.println("e");
			}
		}
	}
}
