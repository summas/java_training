package Mail;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import java.util.Properties;

import javax.activation.DataHandler;
import javax.activation.FileDataSource;
import javax.mail.Multipart;
import javax.mail.Session;
import javax.mail.Message;
import javax.mail.Transport;
import javax.mail.internet.MimeBodyPart;
import javax.mail.internet.MimeMessage;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMultipart;
import javax.mail.internet.MimeUtility;
/**
 * Servlet implementation class mailtest
 */
@WebServlet("/mail")
public class mail extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public mail() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		 String mailresult = "";
		    request.setAttribute("result", mailresult );
		ServletContext sc = getServletContext();
		RequestDispatcher rd = sc.getRequestDispatcher("/WEB-INF/JSP/Mailtest.jsp");
		rd.forward(request, response);
		return;
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		
		DataHandler imgdh = null;
		   String title = request.getParameter("TITLE");
		    String text = request.getParameter("MAIL");

		    // ���[�����M
		    try {
	
		      Properties pt = System.getProperties();

		      // SMTP �T�[�o�[�A�h���X
		      pt.put("mail.smtp.host", "mail.iyoics.jp");

		      // ���[������
		      Session session = Session.getDefaultInstance(pt, null);
		      MimeMessage ml = new MimeMessage(session);

		      // ���M��
		      ml.setFrom(new InternetAddress("sumi_m@iyoics.jp", "sumi", "iso-2022-jp"));

		      // ���M��
		      ml.setRecipients(Message.RecipientType.TO, "sumi_m@iyoics.jp");

		      // �`��

		      // �^�C�g��
		      ml.setSubject(title, "iso-2022-jp");
	      
		      Multipart mp = new MimeMultipart();

		      MimeBodyPart mbp1 = new MimeBodyPart();
		      
		      // �{��
		      text = text.replace('①', '1');
		      text = text.replace('②', '2');
		      text = text.replace('③', '3');
		      text = text.replace('④', '4');
		      text = text.replace('⑤', '5');
		      text = text.replace('⑥', '6');
		      text = text.replace('⑦', '7');
		      text = text.replace('⑧', '8');
		      text = text.replace('⑨', '9');
		      text = text.replace('～', '-');
		      text = text.replace('－', '-');
		      mbp1.setText(text, "iso-2022-jp");
		      //   ml.setText(text, "iso-2022-jp");
		     mbp1.setHeader("Content-Type", "text/plain");
		     
//			       ml.setHeader("Content-Type", "text/plain");
	     mp.addBodyPart(mbp1);
		      // ���M��

		   // �Y�t����t�@�C���f�[�^�\�[�X���w��
		  	String filename = request.getParameter("FILENAME");
			if(filename != ""){
			    MimeBodyPart mbp2 = new MimeBodyPart();
				 FileDataSource fds = new FileDataSource(filename);
				imgdh = new DataHandler(fds);
		   mbp2.setDataHandler(imgdh);
		   mbp2.setFileName(MimeUtility.encodeWord(fds.getName()));
		   mp.addBodyPart(mbp2);
			}

		   // �}���`�p�[�g�I�u�W�F�N�g�����b�Z�[�W�ɐݒ�
		   ml.setSentDate(new java.util.Date());
		   ml.setContent(mp);

		      // ���M
		      Transport.send(ml);
		      System.out.println("���M�I��");

		  } catch (Exception e) {
			  e.printStackTrace();
		    }
		    
		    String mailresult = "���M����܂���";
		    request.setAttribute("result", mailresult );
		    ServletContext sc = getServletContext();
			RequestDispatcher rd = sc.getRequestDispatcher("/WEB-INF/JSP/Mailtest.jsp");
			rd.forward(request, response);
			return;
	}
}
		
		
