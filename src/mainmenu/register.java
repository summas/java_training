package mainmenu;

import java.io.IOException;
import java.io.InputStream;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Properties;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import Classes.Encryption;
import Productdisp.ProductAdd;

/**
 * Servlet implementation class register
 */
@WebServlet("/register")
public class register extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public register() {
		super();
		// TODO Auto-generated constructor stub
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doGet(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		 HttpSession session = request.getSession(false);
		 if(session == null){
			 ServletContext sc = getServletContext();
				RequestDispatcher rd = sc.getRequestDispatcher("/WEB-INF/JSP/login.jsp");
				rd.forward(request, response);
				return;
			 		 }
		
		ServletContext sc2 = getServletContext();
		RequestDispatcher rd2 = sc2
				.getRequestDispatcher("/WEB-INF/JSP/register.jsp");
		rd2.forward(request, response);

	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doPost(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		String chid = null;

		Connection conn = null;
		PreparedStatement check = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		ResultSet ch = null;

		String pass = request.getParameter("PASS");
		String id = request.getParameter("ID");
		String name = request.getParameter("NAME");

		if (pass == null || id == null) {
			ServletContext sc = getServletContext();
			RequestDispatcher rd = sc
					.getRequestDispatcher("/WEB-INF/JSP/register.jsp");
			rd.forward(request, response);
			return;
		}
		final Properties prop = new Properties();
		InputStream inStream = null;

		try {
			inStream = ProductAdd.class.getClassLoader().getResourceAsStream("baseinfo.properties");
			prop.load(inStream);
			final String urlpath = prop.getProperty("urlpath");

			Class.forName("com.mysql.jdbc.Driver").newInstance();

			conn = DriverManager.getConnection(urlpath, "sumi", "sumi");

			check = conn.prepareStatement("SELECT * FROM user WHERE USER_ID=?");

			check.setString(1, request.getParameter("ID"));
			ch = check.executeQuery();

			if (ch.next() == true) {
				chid = ch.getString("USER_ID");
			}

			if (chid != null) {
				request.setAttribute("id", chid);
				String aaa = ("����ID�͂��łɓo�^����Ă��܂�");
				request.setAttribute("name", aaa);
				ServletContext sc = getServletContext();
				RequestDispatcher rd = sc
						.getRequestDispatcher("/WEB-INF/JSP/register.jsp");
				rd.forward(request, response);
			} else {
				
				   //�Í���
		           Encryption encpass = new Encryption();
		           pass = encpass.encrypt(id, pass);
			
				conn.setAutoCommit(false);

				pstmt = conn.prepareStatement("INSERT INTO user VALUES(?,?,?,?)");
				pstmt.setString(1, request.getParameter("ID"));
				pstmt.setString(2, request.getParameter("NAME"));
				pstmt.setString(3, pass);
				pstmt.setString(4, request.getParameter("AUTH"));

				pstmt.executeUpdate();

				conn.commit();
					request.setAttribute("id", id);
					request.setAttribute("name", name);
					ServletContext sc = getServletContext();
					RequestDispatcher rd = sc.getRequestDispatcher("/WEB-INF/JSP/register.jsp");
					rd.forward(request, response);
					return;
			}
		} catch(Exception e) {
			try {conn.rollback(); } catch (SQLException e2) {e2.printStackTrace();} 
			  e.printStackTrace();
			} finally {
				 if (inStream != null){try {inStream.close();} catch (IOException e){ e.printStackTrace();}}
			  if (rs != null ) { try {rs.close(); } catch (SQLException e) {e.printStackTrace();} }
			  if (pstmt != null ) { try {pstmt.close(); } catch (SQLException e) {e.printStackTrace();} }
			  if (conn != null ) { try {conn.close(); } catch (SQLException e) {e.printStackTrace();} }
			}
	}
}
