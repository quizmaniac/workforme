/**
 * Author: 	Waquar Shamsi
 * Date:	23-05-2020
 */
package tk.services.main.controller;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import tk.services.main.service.Login;

public class login extends HttpServlet{
	public void doGet(HttpServletRequest req,HttpServletResponse res) throws ServletException,IOException{
		RequestDispatcher rs = req.getRequestDispatcher("login.html");
		rs.forward(req, res);
	}
	public void doPost(HttpServletRequest req, HttpServletResponse res) throws IOException, ServletException{
		String username=req.getParameter("username");
		String password=req.getParameter("password");
		RequestDispatcher rs;
			try {
				if(Login.verifyUser(username, password)==true) {
					rs = req.getRequestDispatcher("user_page.html");
					rs.forward(req, res);
				}else {
					String mesg="Wrong username or password.";
					req.setAttribute("mesg", mesg);
					rs = req.getRequestDispatcher("login.html");
					rs.forward(req, res);
				}
			} catch (ServletException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		
	}
}
