package JDBC;
import java.util.*;
import java.math.BigDecimal;
import java.sql.*;
class CEOTable{
	public static void main(String[]args) throws Exception{
		Statement stmt=null;
		Connection con=null;
		try {
			Class.forName("com.mysql.cj.jdbc.Driver");
			con=DriverManager.getConnection("jdbc:mysql://localhost:3306/Collegedb","root","Harshavardhan256");
			System.out.println("Connection created");
			stmt=con.createStatement();
			String sql="CREATE TABLE if not exists BioData(name varchar(20), age int(20), height decimal(5,6), weight int(20),address varchar(20)))";
			ResultSet rs=stmt.executeQuery("Select * from BioData");
			while(rs.next()) {
				String name=rs.getString("name");
				int age=rs.getInt("age");
				BigDecimal height=rs.getBigDecimal("height");
				int weight=rs.getInt("weight");
				String address=rs.getString("address");
				System.out.println("-------Yours Bio Data--------");
				System.out.println("Name:"+name);
				System.out.println("Age:"+age);
				System.out.println("Height:"+height);
				System.out.println("Weight:"+weight);
				System.out.println("Address:"+address);
				
				
			}
			rs.close();
			stmt.close();
			con.close();
		}
		catch(Exception e) {
			e.printStackTrace();
		}
		finally {
		}
		
	}
}

