package Classes;


import java.sql.Connection;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;


import Beans.alldispbean;


//全商品セレクト文実行
public class SqlAll{
	
	
	public List<alldispbean> GetAllName(Connection conn) {	
		
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		  List<alldispbean> list = new ArrayList<alldispbean>();
	
		try {

		  pstmt = conn.prepareStatement("SELECT * FROM products  ");
		  rs = pstmt.executeQuery();

	  while (rs.next()) {
			String id = rs.getString("id");
			String name = rs.getString("name");
			list.add(new alldispbean(id,name));
	     }

		} catch(Exception e) {
			  e.printStackTrace();
			} finally {
			  if (rs != null ) { try {rs.close(); } catch (SQLException e) {e.printStackTrace();} }
			  if (pstmt != null ) { try {pstmt.close(); } catch (SQLException e) {e.printStackTrace();} }
			}
		return list;
	}

	//CSV取込みINSERT複数実行String[]
	public void InsertAll(ArrayList<String[]> data, Connection conn) {	

			String chid = null;
			String chid2 = null;

			PreparedStatement pstmt = null;

try {
		  conn.setAutoCommit(false);
		  pstmt = conn.prepareStatement("INSERT INTO products  VALUES(?,?)");

		  for(int i=0;i<data.size();i++){
				 for(int j=0;j<data.get(i).length;j++){
					 String[] s = data.get(i);
					 if(j%2==0){
						 chid = s[j];	
 			         }else{
 			        	chid2 = s[j];	
 			        	 pstmt.setString(1, chid);	
 			        	 pstmt.setString(2, chid2);	
 			        	pstmt.executeUpdate();
 			 	   }
			    }
			 } 
		  conn.commit();
		         } catch(Exception e) {
						try {conn.rollback(); } catch (SQLException e2) {e2.printStackTrace();} 
						  e.printStackTrace();
						} finally {
						  if (pstmt != null ) { try {pstmt.close(); } catch (SQLException e) {e.printStackTrace();} }
						}
	}
	
	
	//CSV取込みINSERT複数実行String
	public void InsertAll2(ArrayList<String> data, Connection conn) {	

		String chid = null;
		String chid2 = null;

		PreparedStatement pstmt = null;

try {
	
	  conn.setAutoCommit(false);
	  pstmt = conn.prepareStatement("INSERT INTO products  VALUES(?,?)");

	  for(int i=0;i<data.size();i++){
				 if(i%2==0){
					 chid = data.get(i); 
			         }else{
			        	chid2 = data.get(i); 	
			        	 pstmt.setString(1, chid);	
			        	 pstmt.setString(2, chid2);	
			        	pstmt.executeUpdate();
			 	   }
		 } 
	  conn.commit();
	         } catch(Exception e) {
					try {conn.rollback(); } catch (SQLException e2) {e2.printStackTrace();} 
					  e.printStackTrace();
					} finally {
					  if (pstmt != null ) { try {pstmt.close(); } catch (SQLException e) {e.printStackTrace();} }
					}
}

	
	
	
	//郵便情報SQL
	public void InsertPostAll(ArrayList<String[]> data, Connection conn) {	

		String citycode1 = null;
		String pnumf2 = null;
		String pnum3 = null;
		String kenhuri4 = null;
		String shihuri5 = null;
		String machihuri6 = null;
		String ken7 = null;
		String shi8= null;
		String machi9= null;
		String no10= null;
		String no11= null;
		String no12= null;
		String no13= null;
		String no14= null;
		String no15= null;

	
		PreparedStatement pstmt = null;

		 
try {        
		
	  conn.setAutoCommit(false);
	  pstmt = conn.prepareStatement("INSERT INTO PostInfo  VALUES(?,?,?,?,?,?,?,?,?,?,?,?,?,?,?)");

	  for(int i=0;i<data.size();i++){
			 for(int j=0;j<data.get(i).length;j++){
				 String[] s = data.get(i);
				 if(j%15==0){
					 citycode1 = s[j];	
			         }else if((j%15==1)){
			        	 pnumf2 = s[j];	
			        }else if((j%15==2)){
			        	pnum3 = s[j];	
			        	 
			         } else if((j%15==3)){
			        	kenhuri4  = s[j];	
			        	 
			         } else if((j%15==4)){
			        	shihuri5 = s[j];	
			        	 
			         } else if((j%15==5)){
			        	 machihuri6 = s[j];	
			        	 
			         } else if((j%15==6)){
			        	ken7 = s[j];	
			        	 
			         } else if((j%15==7)){
			        	shi8 = s[j];
			        }else if((j%15==8)){
			        	machi9 = s[j];	
			        	 
			         } else if((j%15==9)){

			        	no10 = s[j];	
			         } else if((j%15==10)){
			        	no11 = s[j];	
			         } else if((j%15==11)){
			        	no12 = s[j];	
			         } else if((j%15==12)){
			          	no13 = s[j];	
			         } else if((j%15==13)){
			          	no14 = s[j];	
			         }else{
			        	no15  = s[j];	
			        	 pstmt.setString(1, citycode1);	
			        	 pstmt.setString(2, pnumf2 );
			        	 pstmt.setString(3,pnum3);
			        	 pstmt.setString(4,kenhuri4);
			        	pstmt.setString(5,shihuri5);	
			        	pstmt.setString(6,machihuri6);	
			        	pstmt.setString(7,ken7);	
			        	pstmt.setString(8,shi8);	
			        	pstmt.setString(9,machi9);	
			        	pstmt.setString(10,no10);	
			        	pstmt.setString(11,no11);	
			        	pstmt.setString(12,no12);	
			        	pstmt.setString(13,no13);	
			        	pstmt.setString(14,no14);	
			        	pstmt.setString(15,no15);	
			        	pstmt.executeUpdate();
			 	   }	 
		    }
		 } 
	  conn.commit();
	         } catch(Exception e) {
					try {conn.rollback(); } catch (SQLException e2) {e2.printStackTrace();} 
					  e.printStackTrace();
					} finally {
					  if (pstmt != null ) { try {pstmt.close(); } catch (SQLException e) {e.printStackTrace();} }
					}
	}
}
