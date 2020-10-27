package CSV;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.net.HttpURLConnection;
import java.net.SocketException;
import java.net.URLEncoder;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import Beans.alldispbean;
import Classes.SqlConnection;
import Classes.SqlManager;

import java.util.ArrayList;
import java.util.List;

/**
 * Servlet implementation class CsvOut2
 */
@WebServlet("/CsvOut")
public class CsvOut extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public CsvOut() {
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
		// TODO Auto-generated method stub

		String filename = request.getParameter("FILENAME");
		String filenameCsv = filename + ".csv";	
		List<alldispbean> list = new ArrayList<alldispbean>();	

		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(new FileOutputStream(filenameCsv)));
		bw.write("���iID,���i��");bw.newLine();

		  //SQL Select�� �S���i���X�g�擾
	      SqlConnection db = new SqlConnection();
	      SqlManager Sql = new SqlManager();
	      list =  Sql.SqlDispAll(db.Connect());
	      db.close(db.Connect());
	      
		for(int i=0;i<list.size();i++){
			bw.write(list.get(i).getId());bw.write(",");
			bw.write(list.get(i).getName());bw.newLine();
		}

		bw.close();

		File file = new File(filenameCsv);
		BufferedInputStream in = null;
		BufferedOutputStream out = null;
		try {
			//HTTP�w�b�_�̏o��
			response.setContentType("application/octet-stream");
			response.setHeader("Content-disposition", "attachment; filename=\"" + URLEncoder.encode(file.getName(),"UTF-8"));
			response.setContentLength((int)file.length());
			response.setHeader("Expires", "0");
			response.setHeader("Cache-Control", "must-revalidate, post-check=0,pre-check=0");
			response.setHeader("Pragma", "private");

			in = new BufferedInputStream(new FileInputStream(file));
			out = new BufferedOutputStream(response.getOutputStream());
			           byte buf[]=new byte[1024];
			           int len;
			           while((len=in.read(buf))!=-1){
			               out.write(buf,0,len);
			           }
			       } catch (SocketException e) {
			           //���_�E�����[�h�������Ƀ_�E�����[�h�_�C�A���O�́u�L�����Z���v��
			           //�N���b�N���ꂽ�ꍇ�̗�O�B
			           //���������A�_�E�����[�h�_�C�A���O���\������Ă���o�b�N�O���E���h��
			           //�u���E�U�ւ̃_�E�����[�h���s���Ă��邱�Ƃɗ��ӂ��邱�ƁB
			           //���܂菬�����t�@�C���ł́A�_�C�A���O���\������鎞�ɂ́A�_�E�����[�h
			           //�����͊������A�T�[�u���b�g�͏I�����Ă��܂��Ă���ASocketException��
			           //���������Ȃ��Ƃ������Ƃł��B
			       } catch (Exception e) {
			           //�t�@�C���_�E�����[�h�p��HTTP�w�b�_�����Z�b�g���܂��B
			           response.reset();
			           response.sendError(HttpURLConnection.HTTP_INTERNAL_ERROR , e.toString());
			       } finally {
			           if (in != null) {
			               in.close();
			           }
			           if (out != null) {
			               out.flush();
			               out.close();
			           }
			       }
			       return;
	}
}
