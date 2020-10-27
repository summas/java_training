package login;

//import java.io.IOException;

//import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import Beans.User;
import Classes.SqlConnection;
import Classes.SqlManager;
import Classes.URL;

import java.io.IOException;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Servlet implementation class login
 */
@WebServlet("/login")
public class login extends HttpServlet {
	private static final long serialVersionUID = 1L;
	URL url = new URL();
    /**
     * @see HttpServlet#HttpServlet()
     */
    public login() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		   doPost(request, response);
	}
	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	@SuppressWarnings("static-access")
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub

		String id ="";
		String pass ="";
		id = request.getParameter("ID");
		pass = request.getParameter("PASS");
		User user = new User(id,pass,"");
	
		   
		//NULL�A�󗓃`�F�b�N �N�������O�C����ʕ\��
		   if(id == null|| id == ""||pass == null||pass == "") {
			   request.setAttribute("iderr", "");
			   request.getRequestDispatcher(url.LOGIN).forward(request, response);
				return;
		   }

		 //���̓`�F�b�N(���p�����̂�)
		Pattern q = Pattern.compile("^[0-9]*$");
        Matcher w = q.matcher(id);
   //   System.out.println(w.find());

           if(w.find()==false){  
        	   	request.setAttribute("iderr","�E���ԍ��ɂ�4���̐�������͂��Ă�������");
       			request.getRequestDispatcher(url.LOGIN).forward(request, response);
              	return;
           } 
           if(id.length()!=4) {
        	   request.setAttribute("iderr","�E���ԍ��ɂ�4���̐�������͂��Ă�������");
      		 request.getRequestDispatcher(url.LOGIN).forward(request, response);
             	return;
           }

           //SQL login���s
			//SQL SELECT���s
		   SqlConnection db = new SqlConnection();
           SqlManager Sql = new SqlManager();
           User logUser=  Sql.SqlLogin(db.Connect(), user);
           db.close(db.Connect());

	  HttpSession session = request.getSession(true);
	  session.setAttribute("name",logUser.getId()); 
      session.setAttribute("id",id ); 
      session.setAttribute("logok","1" ); 
      session.setAttribute("auth",logUser.getPass()); //logUser.getPass�͌���

		if (logUser.getName()  != null && logUser.getId()  != "" &&id != null && id != "") {
			 request.getRequestDispatcher(url.MAIN).forward(request, response);
	    }
   else{
	   //ID�ƃp�X���[�h���g���ăp�X���[�h�̈Í������s���Ă���̂Ńp�X���[�h������SELECT���𔭍s���Ă��邪���Ȃ�����ł���B
	   request.setAttribute("iderr", "�E���ԍ����p�X���[�h���Ԉ���Ă��܂��B");
		 request.getRequestDispatcher(url.LOGIN).forward(request, response);
		}
    }
}
