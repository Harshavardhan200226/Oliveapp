package JDBC;
import java.sql.*;
public class JavaExample{
	public static void main(String[]args) throws Exception {
		Statement stmt=null;
		Connection con=null;
		try {
			Class.forName("com.mysql.cj.jdbc.Driver");
			con=DriverManager.getConnection("jdbc:mysql://localhost:/unisoft","root","Harshavardhan256");
			stmt=con.createStatement();
			String sql="CREATE TABLE if not exists CRI(id int (20)primary key, name varchar(20),team varchar(20))";
			System.out.println("Table is created");
			stmt.executeUpdate(sql);
			stmt.executeUpdate("INSERT INTO CRI values(101,'Virat','India')");
			stmt.executeUpdate("INSERT INTO CRI values(102,'Mccullum','New Zealand')");
			stmt.executeUpdate("Insert into CRI values(103,'Devilliers','South Africa')");
			System.out.println("Successfully we add values to the table");
		}
		catch(Exception e) {
			e.printStackTrace();
		}
		finally {
			stmt.close();
			con.close();
		}
	}
}