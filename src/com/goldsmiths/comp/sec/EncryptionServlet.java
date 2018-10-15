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


public class ShiftCeasar {


    public static String encrypt(String plaintext, int shift){
        if(shift > 26){
          shift%=26;
        }
        String cipher = "";
        int textlenght = plaintext.length();
          for (int i = 0; i<textlenght; i++){

            char c = plaintext.charAt(i);
            char ch = (char)(c+shift);
            cipher +=ch;

          }

        return cipher;
    }
    public static String dencrypt(String plaintext, int shift){
      if(shift > 26){
        shift%=26;
      }else{

      }
        String cipher = "";
        int textlenght = plaintext.length();
          for (int i = 0; i<textlenght; i++){

            char c = plaintext.charAt(i);
            char ch = (char)(c-shift);
            cipher +=ch;

          }

        return cipher;
    }
public static void main(String[] args) {
  String text = "this is a very very long and complex text";
  String cipper= encrypt(text,105);
  System.out.println(cipper);

  String text2 = "uijt!jt!b!wfsz!wfsz!mpoh!boe!dpnqmfy!ufyu";
  String cipper2= dencrypt(text2,105);
  System.out.println(cipper2);
}


}
