package com.servlet;

import java.io.IOException;

import com.connections.DBConnect;
import com.doa.StudentDao;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

@WebServlet("/delete")
public class DeleteServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		try {
			int id = Integer.parseInt(request.getParameter("id"));
			StudentDao studentdao = new StudentDao(DBConnect.getConnection());
			boolean status = studentdao.deleteStudent(id);

			HttpSession session = request.getSession();

			if (status) {
				session.setAttribute("successMsg", "Student Delete SuccessFully");
				response.sendRedirect("index.jsp");
			} else {
				session.setAttribute("errorMsg", "Something Went Wrong !!!s");
				response.sendRedirect("index.jsp");
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		doGet(request, response);
	}

}
