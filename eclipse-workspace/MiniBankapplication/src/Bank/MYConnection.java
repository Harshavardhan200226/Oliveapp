package Bank;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
class MYConnection{
	static Connection con;
	public static Connection getConnection(){
	try {
		Class.forName("com.mysql.cj.jdbc.Driver");
		con=DriverManager.getConnection("jdbc:mysql://localhost:3306/Bank","root","Harshavardhan256");
	}
	catch(Exception e) {
		e.printStackTrace();
	}
	return con;
	}
	public static void main(String[]args){
		getConnection();
	}
}