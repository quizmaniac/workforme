/**
 * 
 */
package tk.services.main.controller;

import java.io.IOException;

import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import tk.services.main.service.SendEmail;

public class send_mail extends HttpServlet {
    private String host;
    private String port;
    private String user;
    private String pass;
    private String receiver;
    private String subject;
    private String content;
    private String msg;
 
    public void init() {
    	/** The following parameters can be configured from web.xml */
        ServletContext context = getServletContext();
        host = context.getInitParameter("host");
        port = context.getInitParameter("port");
        user = context.getInitParameter("user");
        pass = context.getInitParameter("pass");
    }
 
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        receiver = request.getParameter("recipient"); 		/** Receiver email id */
        subject = "Registeration on WorkForMe"; 			/** Subject must be configured here only */
        content = request.getParameter("WELCOME TO WORKFORME!!!");  		/** Set as an HTML PAGE */
        msg = "Successfuly Sent Mail."; 					/** Default value - in case : no errors */
        /* Change above line to check your mail to confirm your account */
        try {
            SendEmail.sendMail(host,port,user,pass,receiver,subject,content);
            msg = "The e-mail was sent successfully";
        } catch (Exception e) {
            e.printStackTrace();
            msg = "There were an error: " + e.getMessage();
        } finally {
            request.setAttribute("Message", msg);
            getServletContext().getRequestDispatcher("/login.jsp").forward(
                    request, response);
        }
    }
 
}
