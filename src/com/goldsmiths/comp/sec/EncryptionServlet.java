package com.goldsmiths.comp.sec;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Servlet implementation class EncryptionServlet
 */
@WebServlet(description = "Actionable servlet responsible for functionality to frontend", urlPatterns = {
		"/EncryptionServlet" })
public class EncryptionServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public EncryptionServlet() {
		super();
		// TODO Auto-generated constructor stub
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		String init_text = request.getParameter("init_text");
		System.out.println("Initial Text: " + init_text);
		// TODO: ADD ENCRYPTION FUNCTIONALITY

		/*
		 * PrintWriter out = response.getWriter(); out.println("<html>\n" +
		 * "<head><title>" + init_text + "</title></head>\n" +
		 * "<body bgcolor = \"#f0f0f0\">\n" + "<h1 align = \"center\">" + init_text +
		 * "</h1>\n" + "</body>" + "</html>");
		 */
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
