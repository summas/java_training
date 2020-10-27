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

import java.util.regex.Matcher;
import java.util.regex.Pattern;
/**
 * Servlet implementation class ProductAdd
 */
@WebServlet("/ProductAdd")
public class ProductAdd extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public ProductAdd() {
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
		
		URL url = new URL();
		String name = null;

		  String addid = request.getParameter("ADDID");
		  String pname = request.getParameter("PNAME");
		  Product addProudct = new Product(addid,pname);

		  request.setAttribute("id", addid );
		//入力チェック(半角数字のみ)
			Pattern q = Pattern.compile("^[0-9]*$");
	        Matcher w = q.matcher(addid);
	   //   System.out.println(w.find());

	           if(w.find()==false){       	   
	        	   System.out.println("IDには整数を入力してください");
	        	   request.setAttribute("name", "半角数字を入力してください");
					request.getRequestDispatcher(url.DISP).forward(request, response);
	              	return;
	           } 
              
	           //Select実行して登録確認
	           SqlConnection db = new SqlConnection();
	           SqlManager Sql = new SqlManager();
	           name =  Sql.select(db.Connect(), addProudct);

			 if (name != null) { //既に登録されていれば画面を再表示
				request.setAttribute("name", "登録済みです");
				request.getRequestDispatcher(url.DISP).forward(request, response);
			    return;
			}
		       //INSERT実行
			 	Sql.insert(db.Connect(), addProudct);
	      	    db.close(db.Connect()); 
	           
	        	request.setAttribute("name", pname);
	           request.getRequestDispatcher(url.DISP).forward(request, response);
	}	
}	