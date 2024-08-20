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

@WebServlet("/register")
public class RegisterServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

   
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		try 
		{
			response.setContentType("text/html");
			PrintWriter out = response.getWriter();
			
			String name = request.getParameter("name");
			String dob  = request.getParameter("dob");
			String address = request.getParameter("address");
			String qualification = request.getParameter("qualification");
			String email = request.getParameter("email");
			
			Student student = new Student(name,dob,address,qualification,email);
			StudentDao studentdao = new StudentDao(DBConnect.getConnection());
			
			boolean status = studentdao.addStudent(student);
			
			HttpSession session = request.getSession(true);
			
			if(status)
			{
				session.setAttribute("successMsg","Student Details Added SuccessFully");
				response.sendRedirect("addStudent.jsp");
			}
			else {
				session.setAttribute("errorMsg","Something Went Wrong !!!s");
				response.sendRedirect("addStudent.jsp");
			}
		}
		catch(Exception e)
		{
			e.printStackTrace();
		}
	}

	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
