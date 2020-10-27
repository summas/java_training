package CustomerMaser;

import java.io.IOException;
import java.io.InputStream;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Properties;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import Productdisp.ProductAdd;

/**
 * Servlet implementation class CstomerRegister
 */
@WebServlet("/CustomerRegister")
public class CustomerRegister extends HttpServlet {
	private static final long serialVersionUID = 1L;

    /**
     * @see HttpServlet#HttpServlet()
     */
    public CustomerRegister() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	@SuppressWarnings("finally")
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
	
		Connection conn = null;
		
		PreparedStatement pstmt = null;
		ResultSet rs = null;
	
	
		  String postNo = request.getParameter("postNo");
		 
		//入力チェック(半角数字のみ)
			Pattern q = Pattern.compile("^[0-9]*$");
	        Matcher w = q.matcher(postNo);
	   //   System.out.println(w.find());
	        
	           if(w.find()==false){       	   
	        	   System.out.println("IDには整数を入力してください");
	        	   String aaa = ("");
					request.setAttribute("name", aaa);
	        	   ServletContext sc = getServletContext();
					RequestDispatcher rd = sc.getRequestDispatcher("/WEB-INF/JSP/CustomerMaster.jsp");
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
			  conn = DriverManager.getConnection(urlpath,"sumi","sumi");
			  conn.setAutoCommit(false);

			  pstmt = conn.prepareStatement("INSERT INTO 住所  VALUES(?,?,?,?)");
			  
			  pstmt.setString(1, request.getParameter("postNo"));
			  pstmt.setString(2, request.getParameter("ken"));
			  pstmt.setString(3, request.getParameter("shi"));
			  pstmt.setString(4, request.getParameter("banchi"));
				
			  pstmt.executeUpdate();
			  conn.commit();
			  
			} catch(Exception e) {
				try {conn.rollback(); } catch (SQLException e2) {e2.printStackTrace();} 
				  e.printStackTrace();
				} finally {
					 if (inStream != null){try {inStream.close();} catch (IOException e){ e.printStackTrace();}}
				  if (rs != null ) { try {rs.close(); } catch (SQLException e) {e.printStackTrace();} }
				  if (pstmt != null ) { try {pstmt.close(); } catch (SQLException e) {e.printStackTrace();} }
				  if (conn != null ) { try {conn.close(); } catch (SQLException e) {e.printStackTrace();} }
				  
				  ServletContext sc = getServletContext();
					RequestDispatcher rd = sc
							.getRequestDispatcher("/WEB-INF/JSP/CustomerMaster.jsp");
					rd.forward(request, response);
					return;
				}
	}
}
