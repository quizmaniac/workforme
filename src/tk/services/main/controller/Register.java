package tk.services.main.controller;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import tk.services.main.model.User;
import tk.services.main.service.RegisterS;

public class Register extends HttpServlet{
	User user = new User();
	public void doGet(HttpServletRequest req,HttpServletResponse res) throws ServletException,IOException{
		RequestDispatcher rs = req.getRequestDispatcher("login.html");
		rs.forward(req, res);
	}
	public void doPost(HttpServletRequest req, HttpServletResponse res) throws IOException, ServletException{
		user.setNumber(req.getParameter("phnum"));
		user.setEmail(req.getParameter("email"));
		user.setPassword(req.getParameter("pass"));
		user.setUsername(req.getParameter("user"));
		user.setDob(req.getParameter("dob"));
		//user.setAddress(req.getParameter("address"));
		try {
			RegisterS.addUser(user);
		} catch (Exception e) {
			e.printStackTrace();
		}
		RequestDispatcher rs = req.getRequestDispatcher("login.html");
		rs.forward(req, res);
	}
}
