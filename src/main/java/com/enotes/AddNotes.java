package com.enotes;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;


@WebServlet("/addNotes")
public class AddNotes extends HttpServlet {

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		try {
			String email=req.getParameter("email");
			email = email.trim();
			String title = req.getParameter("title");
			String content = req.getParameter("content");
			String creationDate = req.getParameter("creationDate");
			String updationDate = req.getParameter("updationDate");
			
			Notes app = new Notes();
			app.setEmailid(email);
			app.setTitle(title);
			app.setContent(content);
			app.setCreationDate(creationDate);
			app.setUpdationDate(updationDate);
			
			
			                   		
            NotesDAO dao = new NotesDAO(DBConnect.getConn()); 
            
			boolean f = dao.addNotes(app);

			HttpSession session = req.getSession();

			if (f) {
                session.setAttribute("succMsg", "Notes Added Successfully...");
				resp.sendRedirect("addNotes.jsp");
			} else {
				session.setAttribute("failedMsg", "Something wrong on Server..!!");
				resp.sendRedirect("addNotes.jsp");
			}			
		}
		catch (Exception e) {
			e.printStackTrace();
		}
	}
	

}
