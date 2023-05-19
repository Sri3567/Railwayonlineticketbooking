package project;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Scanner;

import javax.naming.spi.DirStateFactory.Result;



public class srini {
	String phoneno1;
	String name;
	String password1;
	public srini(String phoneno,String name, String password) {
		this.phoneno1 = phoneno;
		this.name = name;
		this.password1 = password;
		
	}
	
	public void display() throws Exception {
		Scanner sc=new Scanner(System.in);
		
			
		Class.forName("com.mysql.jdbc.Driver");
		
		Connection con=DriverManager.getConnection("jdbc:mysql://localhost:3306/project?characterEncoding=latin1","root","Sri357@&&567sri");
		
		Statement st = con.createStatement();
		
		System.out.println("Enter phoneno");
		String phoneno=sc.next();
		System.out.println("Enter name");
		String name=sc.next();
		System.out.println("Enter password");
		String password=sc.next();
		
		st.executeUpdate("insert into LoginForm value('"+phoneno+"' , '"+name+"' , '"+password+"')");
		System.out.println("Values insertad successfully");
		
		ResultSet rs=st.executeQuery("select*from LoginForm ");
		
		while(rs.next()) {
			String roll=rs.getString("phoneno");
			String name1=rs.getString("Name");
			String password1=rs.getString("Password");
			
			System.out.println(roll+" "+name1+" "+password1);
		}
		st.close();
		con.close();
		

	}

}
