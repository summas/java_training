package mainmenu;

import java.io.IOException;
import java.io.InputStream;
import java.sql.Connection;
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

import Classes.SqlConnection;

/**
 * Servlet implementation class userUpdate
 */
@WebServlet("/userUpdate")
public class userUpdate extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public userUpdate() {
		super();
		// TODO Auto-generated constructor stub
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// TODO Auto-generated method stub
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	@SuppressWarnings("resource")
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// TODO Auto-generated method stub

		String name = null;
		String id = null;

		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;

		String pid = request.getParameter("ID");
		// String name = request.getParameter("PASS");

		if (pid == null) {

			// �u�Y�����鏤�i�͂���܂���v�@�̂悤�ȃR�����g�o�́H
		}

		final Properties prop = new Properties();
		InputStream inStream = null;

		try {

			inStream = userUpdate.class.getClassLoader().getResourceAsStream("baseinfo.properties");
			prop.load(inStream);

			Class.forName("com.mysql.jdbc.Driver").newInstance();
			SqlConnection db = new SqlConnection();
			conn = db.Connect();
			conn.setAutoCommit(false);
			pstmt = conn.prepareStatement("UPDATE user SET PASS = ? WHERE ID = ?");

			pstmt.setString(1, request.getParameter("PASS"));
			pstmt.setString(2, request.getParameter("ID"));

			int num = pstmt.executeUpdate();

			conn.commit();

			if (num <= 0) {
				ServletContext sc = getServletContext();
				RequestDispatcher rd = sc.getRequestDispatcher("/WEB-INF/JSP/register.jsp");
				rd.forward(request, response);
				return;
			}

			pstmt = conn.prepareStatement("SELECT * FROM user WHERE ID = ?");

			pstmt.setString(1, request.getParameter("ID"));

			rs = pstmt.executeQuery();

			if (rs.next() == true) {
				id = rs.getString("ID");
				name = rs.getString("NAME");
			}

			if (id != null) {
				request.setAttribute("id", id);
				request.setAttribute("name", name);

				ServletContext sc2 = getServletContext();
				RequestDispatcher rd2 = sc2.getRequestDispatcher("/WEB-INF/JSP/register.jsp");
				rd2.forward(request, response);

			} else {
				ServletContext sc = getServletContext();
				RequestDispatcher rd = sc.getRequestDispatcher("/WEB-INF/JSP/register.jsp");
				rd.forward(request, response);
			}
		} catch (Exception e) {
			try {
				conn.rollback();
			} catch (SQLException e2) {
				e2.printStackTrace();
			}
			e.printStackTrace();
		} finally {
			if (inStream != null) {
				try {
					inStream.close();
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
			if (rs != null) {
				try {
					rs.close();
				} catch (SQLException e) {
					e.printStackTrace();
				}
			}
			if (pstmt != null) {
				try {
					pstmt.close();
				} catch (SQLException e) {
					e.printStackTrace();
				}
			}
			if (conn != null) {
				try {
					conn.close();
				} catch (SQLException e) {
					e.printStackTrace();
				}
			}
		}

	}

}
