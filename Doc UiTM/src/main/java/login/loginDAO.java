package login;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;

import db.ConnectionManager;
import medicalstaff.medicalstaff;

public class loginDAO {
	static Connection con = null;
	static ResultSet rs = null;
	static PreparedStatement ps = null;
	static Statement stmt = null;
	
	static String userid;
	static String password;
	static String usertype;
	static String medstaffid;
	static String mpassword;
	static String role;
	
     public static String authenticateUser(users user) {
     
         userid = user.getUserid(); 
         password = user.getPassword();
        
         String useridDB = "";
         String passwordDB = "";
         String usertypeDB ="";
         
         try
         {
        	 con = ConnectionManager.getConnection();
        	 
        	 String sql = "SELECT * FROM users";
             stmt = con.createStatement();
             
             rs = stmt.executeQuery(sql); 
             
             while(rs.next()) 
             	{
	            	 useridDB = rs.getString("userid"); 
	            	 passwordDB = rs.getString("password");
	            	 usertypeDB = rs.getString("usertype");
	            	 
	            	 user.setUsertype(usertypeDB);
	            	 user.setUserid(useridDB);
	            	 
	            	 if(userid.equals(useridDB) && password.equals(passwordDB))
	            	   return "SUCCESS";
             	}
         	}catch(Exception e)
             {
                e.printStackTrace();
             }
             return "Invalid user credentials"; 
     }
      
     public static users getUserById(String userid) {
 		users user = new users();
 		try {
 			//call getConnection() method 
 			con = ConnectionManager.getConnection();
 			//3. create statement  
 			String sql = "select * from users where userid=?";
 			ps=con.prepareStatement(sql);
 			ps.setString(1, userid);
 			//4. execute query
 			rs = ps.executeQuery();

 			if (rs.next()) {
 				user.setUserid(rs.getString("userid"));
 				user.setPassword(rs.getString("password"));
 				user.setUsertype(rs.getString("usertype"));

 			}
 			//5. close connection
 			con.close();
 		}catch(Exception e) {
 			e.printStackTrace();		
 		}

 		return user;
 	}
 	
     
}