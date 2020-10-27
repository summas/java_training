package Productdisp;

import java.io.IOException;



import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import Beans.Product;
import Classes.SqlConnection;
import Classes.SqlManager;
import Classes.URL;

/**
 * Servlet implementation class ProductUpdate
 */
@WebServlet("/ProductUpdate")
public class ProductUpdate extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public ProductUpdate() {
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
	@SuppressWarnings("static-access")
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub

	        String pid = request.getParameter("PID");
            String pname = request.getParameter("PNAME");
            Product updateP = new Product(pid,pname);
            URL url = new URL();
            
            SqlConnection db = new SqlConnection();
            SqlManager Sel = new SqlManager();
            Sel.update(db.Connect(),updateP);
            db.close(db.Connect());

				request.setAttribute("id", pid);
			 	request.setAttribute("name", pname);

			 	request.getRequestDispatcher(url.DISP).forward(request, response);

          return;
	}

}
