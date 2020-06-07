package tk.services.main.service;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.text.SimpleDateFormat;
import java.util.Base64;
import java.util.Date;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import javax.crypto.Cipher;
import javax.crypto.SecretKey;
import javax.crypto.spec.SecretKeySpec;

import tk.services.main.model.User;

public class RegisterS {
	private static String username="root";
	private static String pass="";
	private static String url="jdbc:mysql://localhost:3306/workforme";
	
	public static boolean addUser(User user) throws Exception {
		String usern=user.getUsername();
		Pattern p = Pattern.compile("^[A-Za-z]+$");
		Matcher m = p.matcher(usern);
		boolean b = m.matches();
		if(b!=true) {
			System.out.println("Incorret username");
			return false;
		}
		String email=user.getEmail();
		String number=user.getNumber();
		if(number.length()!=10) {
			System.out.println("Incorret number");
			return false;
		}/*
		String name=user.getName();
		p = Pattern.compile("^[A-Za-z]+$");
		m = p.matcher(name);
		b = m.matches();
		if(b!=true) {
			System.out.println("Incorret name");
			return false;
		}*/
		String password=user.getPassword();
		byte[] decodedKey = Base64.getDecoder().decode("N6fIB4olbiU=");
		SecretKey key = new SecretKeySpec(decodedKey, 0, decodedKey.length, "DES"); 
		Cipher c =Cipher.getInstance("DES/ECB/PKCS5Padding");
		c.init(Cipher.ENCRYPT_MODE, key);
		byte []encoded = c.doFinal(password.getBytes());
		String enc_password=new String(encoded);
		String date = user.getDob();
		SimpleDateFormat sdf = new SimpleDateFormat("dd-MM-yyyy");
		Date startDate = sdf.parse(date);
		java.sql.Date sqlDate = new java.sql.Date(new SimpleDateFormat("yyyy-MM-dd").parse(date).getTime());
		//java.sql.Date sqlDate = new java.sql.Date(startDate.getTime());
		/*String addr = user.getAddress();*/
		
		Class.forName("com.mysql.cj.jdbc.Driver");
		Connection con = DriverManager.getConnection(url,username,pass);
		PreparedStatement ps;
		/*
		 Check for unique name and email and no
		 */
		String q="select * from users where username = ?";
		ps = con.prepareStatement(q);
		ps.setString(1, usern);
		ResultSet rs=ps.executeQuery();
		if(rs.next()) {
			System.out.println("Duplicate Username");
			return false;
		}
		q="select * from users where email = ?";
		ps.setString(1, email);
		rs=ps.executeQuery();
		if(rs.next()) {
			System.out.println("Duplicate email");
			return false;
		}
		q="select * from users where mobile = ?";
		ps.setString(1, number);
		rs=ps.executeQuery();
		if(rs.next()) {
			System.out.println("Duplicate Phone no.");
			return false;
		}
		String query = "insert into users(username,email,dob,mobile,password) values(?,?,?,?,?);";
		ps = con.prepareStatement(query);
		ps.setString(1, usern);
		ps.setString(2, email);
		ps.setDate(3, sqlDate );
		ps.setString(4, number);
		ps.setString(5, enc_password);
		int i = ps.executeUpdate();
		System.out.println("Added User");
		con.close();
		if(i>0)
			return true;
		else
			return false;
	}
}
