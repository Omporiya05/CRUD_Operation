package com.servlet;

import java.io.IOException;
import java.io.PrintWriter;

import com.connections.DBConnect;
import com.doa.StudentDao;
import com.entity.Student;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

@WebServlet("/update")
public class UpdateServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

   
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException 
	{
		try
		{
			PrintWriter out = response.getWriter();
			
			response.setContentType("text/html");
			int id = Integer.parseInt(request.getParameter("id"));
			String name = request.getParameter("name");
			String dob  = request.getParameter("dob");
			String address = request.getParameter("address");
			String qualification = request.getParameter("qualification");
			String email = request.getParameter("email");
			
			Student student = new Student(id,name,dob,address,qualification,email);
			StudentDao studentdao = new StudentDao(DBConnect.getConnection());
			boolean status = studentdao.updateStudentDetails(student);
			
			HttpSession session = request.getSession();
			
			if(status)
			{
				session.setAttribute("successMsg","Student Details Update SuccessFully");
				response.sendRedirect("index.jsp");
			}
			else {
				session.setAttribute("errorMsg","Something Went Wrong !!!s");
				response.sendRedirect("index.jsp");
			}
		}
		catch(Exception e)
		{
			e.printStackTrace();
		}
	}

	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}

}
