package Beans;

public class PostSeach{
	private String ken;
	private String shi;
	private String machi;
	public String getKen() {
		return ken;
	}
	public void setKen(String ken) {
		this.ken = ken;
	}
	public String getShi() {
		return shi;
	}
	public void setShi(String shi) {
		this.shi = shi;
	}
	
    public String getMachi() {
		return machi;
	}
	public void setMachi(String machi) {
		this.machi = machi;
	}

	public PostSeach(String ken, String shi, String machi) {
		this.ken = ken;
		this.shi = shi;
		this.machi = machi;
	}

}
