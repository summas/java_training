package mainmenu;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 * Servlet implementation class SessionInvalidate
 */
@WebServlet("/SessionInvalidate")
public class SessionInvalidate extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public SessionInvalidate() {
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
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
	//	Object session = null;
		// TODO Auto-generated method stub
		 HttpSession session=request.getSession(false);
		 System.out.println(session);
		 
		  /* セッションが存在すれば破棄します。 */
	  if(session != null){
		    /* セッションsessionを破棄 */
		    session.invalidate();
		    }

		 System.out.println(session);
		 String aaa = "";
		   request.setAttribute("iderr", aaa);
		ServletContext sc = getServletContext();
		RequestDispatcher rd = sc.getRequestDispatcher("/WEB-INF/JSP/login.jsp");
		rd.forward(request, response);
		
	}

}
