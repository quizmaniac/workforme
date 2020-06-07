package tk.services.main.service;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.Base64;

import javax.crypto.Cipher;
import javax.crypto.SecretKey;
import javax.crypto.spec.SecretKeySpec;

public class Login {
	private static String user="root";
	private static String pass="";
	private static String url="jdbc:mysql://localhost:3306/workforme";
	
	public static boolean verifyUser(String username, String password) throws Exception {
		Class.forName("com.mysql.cj.jdbc.Driver");
		Connection con = DriverManager.getConnection(url,user,pass);
		PreparedStatement ps;
		String q="select * from users where username = ?";
		ps = con.prepareStatement(q);
		ps.setString(1, username);
		ResultSet rs=ps.executeQuery();
		rs.next();
		String pass=rs.getString(8);
		byte[] decodedKey = Base64.getDecoder().decode("N6fIB4olbiU=");
		SecretKey key = new SecretKeySpec(decodedKey, 0, decodedKey.length, "DES"); 
		Cipher c =Cipher.getInstance("DES/ECB/PKCS5Padding");
		c.init(Cipher.DECRYPT_MODE, key);
		//byte[] decodedVal = new BASE64Decoder().decodeBuffer(/*encrypted data//);
		byte[] decodedVal = pass.getBytes();
		byte []decVal = c.doFinal(decodedVal);
		String decryptedVal = new String(decVal);
		con.close();
		if(decryptedVal.equals(password)) {
			return true;
		}else
			return false;
	}
}
