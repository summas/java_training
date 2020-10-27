package CustomerMaser;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.regex.Matcher;
import java.util.regex.Pattern;



import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.google.gson.Gson;

import Beans.PostSeach;
import Classes.SqlConnection;
import Classes.URL;


/**
 * Servlet implementation class CustomerMasterScript
 */
@WebServlet("/CustomerMasterScript")
public class CustomerMasterAjax extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private final String REQUEST_STRING = "requestJs";
    /**
     * @see HttpServlet#HttpServlet()
     */
    public CustomerMasterAjax() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	@SuppressWarnings("static-access")
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		
		URL url = new URL();
		String empty = "";
		
		
		request.setAttribute("ken", empty);
	 	request.setAttribute("shi", empty);
	 	request.setAttribute("machi", empty);
		request.getRequestDispatcher(url.CUSTOMER).forward(request, response);	
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		
		String postNo = request.getParameter(REQUEST_STRING);
		

		//入力チェック(半角数字のみ)
		Pattern q = Pattern.compile("^[0-9]*$");
        Matcher w = q.matcher(postNo);
   //   System.out.println(w.find());
        
           if(w.find()==false){       	   
        	   System.out.println("7桁の半角数字を入力してください");
              	return;
           } 
           if(postNo.length()!=7) {
        	   System.out.println("7桁の半角数字を入力してください");
             	return;
           }
		
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		//セッションチェック
		
	
		postNo = "\"" + postNo +"\"";

		  
	         SqlConnection db = new SqlConnection();
		try {
			
	    	  conn = db.Connect();
			  pstmt = conn.prepareStatement("SELECT * FROM postinfo  WHERE pnum3 = ?");

			  pstmt.setString(1, postNo);
			  
			  rs = pstmt.executeQuery();
			  if (rs.next()) {
					String ken = rs.getString("citycode1");
					String shi = rs.getString("shi8");
					String machi = rs.getString("machi9");
					String regex = "\"";
					  Pattern p = Pattern.compile(regex);
					  Matcher l = p.matcher(ken);
					  Matcher m = p.matcher(shi);
					  Matcher n = p.matcher(machi);
					    ken = l.replaceAll("");
					    shi = m.replaceAll("");
					    machi = n.replaceAll("");
					    ken = ken.substring(0,2);
					PostSeach info = new PostSeach(ken, shi,machi);

					   System.out.println(info.getKen());

					   Gson gson = new Gson();

						  response.setContentType("application/json; charset=utf-8");
					PrintWriter out = response.getWriter();
					String callback = request.getParameter("callback");
					out.println( callback + "(" + gson.toJson(info) + ")");
				}

			 	}catch(Exception e) {
				  e.printStackTrace();
				} finally {
				  if (rs != null ) { try {rs.close(); } catch (SQLException e) {e.printStackTrace();} }
				  if (pstmt != null ) { try {pstmt.close(); } catch (SQLException e) {e.printStackTrace();} }
				  db.close(db.Connect());
				}
		}
}
