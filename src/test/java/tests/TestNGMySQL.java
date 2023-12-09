package tests;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class TestNGMySQL {

	public static void main(String[] args) throws SQLException {
		// TODO Auto-generated method stub
		Connection connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/demo", "root", "Milanmilan1?");
		
		if(!connection.isClosed()) {
			System.out.println("Succesfully connected to the database");
		}
		
		Statement statement = connection.createStatement();
		ResultSet resultSet = statement.executeQuery("Select * from employees where id=3");
		
		while(resultSet.next()) {
			
			System.out.println(resultSet.getInt("id"));
			System.out.println(resultSet.getString("name"));
			System.out.println(resultSet.getString("place"));
			System.out.println(resultSet.getInt("experience"));
		}
	}
	

}
