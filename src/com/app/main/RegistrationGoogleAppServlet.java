package com.app.main;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;
import java.util.UUID;
import javax.servlet.ServletException;
import javax.servlet.http.*;

import com.app.models.User;
import com.app.services.Messages;
import com.app.services.Validation;
import com.googlecode.objectify.ObjectifyService;

import static com.googlecode.objectify.ObjectifyService.ofy;

@SuppressWarnings("serial")
public class RegistrationGoogleAppServlet extends HttpServlet 
{
	public void doGet(HttpServletRequest req, HttpServletResponse resp) throws IOException, ServletException 
	{		
		PrintWriter out = resp.getWriter();
		resp.setContentType("text/html");
		ObjectifyService.register(User.class);
		
		out.println("<body>");
		resp.getWriter().println("<div class='listHeader'>Lista gosci:</div>" + "<br> <br>");
		
		List<User> allUsers = ofy().load().type(User.class).list();

		int userIndex = 1;
		for(User element : allUsers)
		{
			if(element != null)
			{
				resp.getWriter().println("Nick: " + userIndex + ": " + element.getUsername() + "<br>");
				resp.getWriter().println("Haslo: " + element.getPassword() + "<br> <br>");
			}
			userIndex++;
		}
		out.println("<a href='/MainPage.html' class='backBtn'>Wroc do strony glownej</a>");
		out.println("</body>");
	}
	
	@Override
	public void doPost(HttpServletRequest req, HttpServletResponse resp) throws IOException 
	{
		ObjectifyService.register(User.class);
		
		String userName = req.getParameter("username");
		String userPassword = req.getParameter("userpassword");
		
		Validation validator = new Validation();
		
		Messages validationRes = validator.ValidateUserRecord(userName, userPassword);
		
		if(validationRes.GetValidationResult())
		{
			User user = new User();	
			user.setUserId(UUID.randomUUID().toString());
			user.setUsername(userName);
			user.setPassword(userPassword);
			
			ObjectifyService.ofy().save().entity(user).now();
			
			resp.sendRedirect("/ksiegagosci");
		}
		else
		{
			resp.sendRedirect("/blad.html");
		}	
	}
}
