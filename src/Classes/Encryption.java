package Classes;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

public class Encryption {

	String encpass = null;
	public String encrypt(String Id, String Pass) {
		
		try{
		StringBuilder buff = new StringBuilder();
		if (Pass != null && !Pass.isEmpty()) {
		MessageDigest md = MessageDigest.getInstance("SHA-1");
		md.update(Id.getBytes());
		md.update(Pass.getBytes());
		byte[] digest = md.digest();
		   for (byte d : digest) {
		 buff.append((int)d&0xFF);
	         }
		   }
		encpass = buff.toString();
		
		return encpass;
		}catch(NoSuchAlgorithmException e){
			 e.printStackTrace();
		}
		return encpass;
	}
}
