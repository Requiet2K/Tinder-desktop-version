package tinder;

import java.io.FileInputStream;
import java.io.InputStream;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.awt.Image;
import java.awt.Toolkit;

public class dbConnection {
	
	public Connection getConnection() throws SQLException {
		Connection con = DriverManager.getConnection("jdbc:mysql://localhost/finder","root","admin1234");
		return con;
	}
	
	public ResultSet getResultSet() throws SQLException {
		Statement st = getConnection().createStatement();
		ResultSet rs = st.executeQuery("select * from finder.user");
		return rs;
	}
	
	public void deleteUser(int id) throws SQLException {
		String query = "delete from finder.user where id=?";
		PreparedStatement ps = getConnection().prepareStatement(query);
		ps.setInt(1, id);
		ps.executeUpdate();
		
	}
	
	public void newUser(User user) throws SQLException {

		PreparedStatement ps = getConnection().prepareStatement("insert into finder.user (name,surname,age,mail,phone,region,gender,password,picture) values (?,?,?,?,?,?,?,?,?)");
		ps.setString(1, user.name);
		ps.setString(2, user.surname);
		ps.setInt(3, user.age);
		ps.setString(4, user.mail);
		ps.setString(5, user.phone);
		ps.setString(6, user.region);
		ps.setString(7, user.gender);
		ps.setString(8, user.password);
		ps.setBinaryStream(9, user.picture);
		ps.executeUpdate();
	}
	
	public void Update(User user) throws SQLException {
		String query = "update finder.user set name = ? , surname = ? , age = ? , mail = ? , phone = ? , region = ? , gender = ? , password = ? where id = ?";
		PreparedStatement ps = getConnection().prepareStatement(query);
		ps.setString(1, user.name);
		ps.setString(2, user.surname);
		ps.setInt(3, user.age);
		ps.setString(4, user.mail);
		ps.setString(5, user.phone);
		ps.setString(6, user.region);
		ps.setString(7, user.gender);
		ps.setString(8, user.password);
		ps.setInt(9, user.id);
		ps.executeUpdate();
	}
	
	public User getValues(int Id) throws SQLException {
		
		String query = "select * from finder.user where id="+Id;
		
		Statement st = getConnection().createStatement();
		ResultSet rs = st.executeQuery(query);
		
		User user = new User();
		
		while(rs.next()) {
			user.id = rs.getInt(1);
			user.name = rs.getString(2);
			user.surname = rs.getString(3);
			user.age = rs.getInt(4);
			user.mail = rs.getString(5);
			user.phone = rs.getString(6);
			user.region = rs.getString(7);
			user.gender = rs.getString(8);
			user.password = rs.getString(9);
			user.picture = rs.getBinaryStream(10);
		}
		
		return user;

	}
	
	public Image getPhoto(int Id) throws SQLException {
		
		PreparedStatement ps = getConnection().prepareStatement("SELECT picture FROM finder.user WHERE id = ?");
		
		ps.setInt(1,Id);																									// login.loggedUser.id yazýlacak
		
	    ResultSet res = ps.executeQuery();
	    byte[] image = null;
	      
	    while (res.next()) {
	      image = res.getBytes(1);
	    }
	    
	    Image img = Toolkit.getDefaultToolkit().createImage(image); 
	    
	    return img;
	}
}
