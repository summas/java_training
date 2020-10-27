package Classes;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import Beans.Product;
import Beans.User;

public class Sql  {

	String pass = null;
	String uid = null;
	//SELECT単品
	public String GetName(Product proudct,Connection conn) {
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		String pname = null;

         try {
			  pstmt = conn.prepareStatement("SELECT * FROM products WHERE id = ?");
			  pstmt.setString(1, proudct.getId());
			  rs = pstmt.executeQuery();
			  if (rs.next()== true) {
			  pname = rs.getString("name");
			  }
					}catch(Exception e) {
					  e.printStackTrace();
					} finally {
					  if (rs != null ) { try {rs.close(); } catch (SQLException e) {e.printStackTrace();} }
					  if (pstmt != null ) { try {pstmt.close(); } catch (SQLException e) {e.printStackTrace();} }
					}
     	return pname;
}

	//DELETE単品
public void Delete(Product proudct, Connection conn) {
	
		PreparedStatement pstmt = null;
		ResultSet rs = null;

         try {

	    	  conn.setAutoCommit(false);
	    	  pstmt = conn.prepareStatement("DELETE FROM products WHERE id = ?");
			  
			  pstmt.setString(1, proudct.getId());
			  pstmt.executeUpdate();
			  conn.commit();
					}catch(Exception e) {
					  e.printStackTrace();
					} finally {
					  if (rs != null ) { try {rs.close(); } catch (SQLException e) {e.printStackTrace();} }
					  if (pstmt != null ) { try {pstmt.close(); } catch (SQLException e) {e.printStackTrace();} }
					}
    }
	//INSERT単品
 public void Insert(Product proudct,Connection conn) {
		PreparedStatement pstmt = null;
		ResultSet rs = null;

          try {
        	  conn.setAutoCommit(false);
              pstmt = conn.prepareStatement("INSERT INTO products  VALUES(?,?)");
			  
			  pstmt.setString(1, proudct.getId()); 
			  pstmt.setString(2, proudct.getName());
			  pstmt.executeUpdate();
			  conn.commit();
			  
					}catch(Exception e) {
					  e.printStackTrace();
					} finally {
					  if (rs != null ) { try {rs.close(); } catch (SQLException e) {e.printStackTrace();} }
					  if (pstmt != null ) { try {pstmt.close(); } catch (SQLException e) {e.printStackTrace();} }
					}
    }
 
	//UPDATE単品
public void Update(Product proudct, Connection conn) {
		
		PreparedStatement pstmt = null;
		ResultSet rs = null;

       try {
    	   conn.setAutoCommit(false);
			 pstmt = conn.prepareStatement("UPDATE products SET name = ? WHERE id = ?");
			 pstmt.setString(1, proudct.getName());
			 pstmt.setString(2, proudct.getId()); 
			 pstmt.executeUpdate();
			 conn.commit();

					}catch(Exception e) {
					  e.printStackTrace();
					} finally {
					  if (rs != null ) { try {rs.close(); } catch (SQLException e) {e.printStackTrace();} }
					  if (pstmt != null ) { try {pstmt.close(); } catch (SQLException e) {e.printStackTrace();} }
					}
 }

//ログイン
public User login( User user, Connection conn) {
	
	PreparedStatement pstmt = null;
	ResultSet rs = null;
	User resUser =null;
	

	try {
		   //暗号化
        Encryption encpass = new Encryption();
        pass = encpass.encrypt(user.getId(), user.getPass());
        
	  pstmt = conn.prepareStatement("SELECT * FROM user WHERE PASS = ?")  ;
	  pstmt.setString(1, pass);
	  rs = pstmt.executeQuery();
	  	if (rs.next()== true) {
	  		 resUser = new User(rs.getString("USER_NAME"), Integer.toString(rs.getInt("Authority")),"");
	  	}
	} catch(Exception e) {
		  e.printStackTrace();
		} finally {
		  if (rs != null ) { try {rs.close(); } catch (SQLException e) {e.printStackTrace();} }
		  if (pstmt != null ) { try {pstmt.close(); } catch (SQLException e) {e.printStackTrace();} }
		}
	return resUser;
	}

}
