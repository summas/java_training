package CSV;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import java.io.File;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import org.apache.commons.fileupload.FileItem;
import org.apache.commons.fileupload.FileUploadException;
import org.apache.commons.fileupload.disk.DiskFileItemFactory;
import org.apache.commons.fileupload.servlet.ServletFileUpload;

import Beans.alldispbean;
import Classes.SqlConnection;
import Classes.SqlManager;
import Classes.URL;


/**
 * Servlet implementation class UploadServlet
 */
@WebServlet("/UploadServlet")
public class UploadServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public UploadServlet() {
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
		 String path=getServletContext().getRealPath("/WEB-INF/data");
		 
		 DiskFileItemFactory factory = new DiskFileItemFactory();
		    ServletFileUpload sfu = new ServletFileUpload(factory);
		 
		 
	  //   DiskFileUpload objDfu=new DiskFileUpload();
	    // �A�b�v���[�h�t�@�C���ő�T�C�Y�i-1�͖����j
		    sfu.setSizeMax(-1);  
	    // �o�b�t�@�T�C�Y
		   // sfu.setSizeThreshold(1024); 
		    factory.setSizeThreshold(1024);
	     // �ꎞ�t�@�C���̕ۑ���t�H���_ 
		   // sfu.setRepositoryPath(path); 
		    factory.setRepository(new File(path)); //�ꎞ�I�ɕۑ�����ۂ̃f�B���N�g��
	    // �w�b�_�̕����G���R�[�f�B���O
		    sfu.setHeaderEncoding("Windows-31J");  

		    try {
	       // �A�b�v���[�h���ꂽ�t�@�C������
	      // FileItem�I�u�W�F�N�g�̃��X�g�Ƃ��Ď擾
	    	 List<FileItem> objLst =sfu.parseRequest(request);
	    	 Iterator<FileItem> objItr =objLst.iterator();

			 String pid = null;
			

	    	 for(FileItem item:objLst) {

	    	              pid = item.getString("UTF-8");
	    			 	ArrayList<String[]> data = new ArrayList<String[]>();
	    			 	 String[] line2 = pid.split("\r\n", 0);
	    			 	 
	    			 	 for (int i=0; i < line2.length; i++){
	    				 data.add(line2[i].split(",", 0));
	    			 	 }

	    			 	 request.setAttribute("pid", pid);

	    			 	List<alldispbean> list = new ArrayList<alldispbean>();

	    			 	String id = null;
	    			 	String name = null;

	    			 	for(int i=0;i<data.size();i++){
	    					 for(int j=0;j<data.get(i).length;j++){
	    						 String[] s = data.get(i);
	    						 if(j%2==0){
	 	    			 			id =(s[j]);
	 	    			         }else{
	 	    			            name= s[j]; 
	 	    			 	   }
	    				    }
	        			 		list.add(new alldispbean(id,name));	
	    				 } 
	    			 	  request.setAttribute("AllDisp", list);
	    			 	  //SQL INSERT���s
	    			 	 SqlConnection db = new SqlConnection();
	    		          SqlManager Sql = new SqlManager();
	    		          Sql.SqlInsertAll(db.Connect(), data);
	    					db.close(db.Connect());
	    			 	  
	    			 	  
	    	         }

	      // ���X�g���珇�Ƀt�@�C���f�[�^�����o���A
	      // �u/WEB-INF/data/���̃t�@�C�����v�̌`����
	      // �A�b�v���[�h�t�@�C����ۑ�
	      while(objItr.hasNext()){
	         FileItem objFi=(FileItem)objItr.next();
	         if(!objFi.isFormField()){
	           // �A�b�v���[�h�t�@�C���̌��t�@�C�������擾
	          String strNam=objFi.getName();
	           if(strNam!=null && !strNam.equals("")){
	             strNam=(new File(strNam)).getName();
	             objFi.write(new File(path + "/" + strNam));
	           }
	         }
	       }
	     } catch (FileUploadException e) {
	       e.printStackTrace();
	     } catch (Exception e) {
	       e.printStackTrace();
	     }
	
			request.setAttribute("title", "CSV�捞��");
		    
			request.getRequestDispatcher(url.ALLDISP).forward(request, response);
	}
}
