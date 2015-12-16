
package db.dto;

/**
 * Artical_originalDTO
 * Mon Jul 27 11:19:48 CST 2015
 * @author Yanqi
 */

public class Artical_originalDTO implements java.io.Serializable{

	public static final String AOID="aoid";
	public static final String AO_TITLE="ao_title";
	public static final String AO_TIME="ao_time";
	public static final String AO_CONTENT="ao_content";

	private int aoid;
	private String ao_title;
	private java.sql.Date ao_time;
	private String ao_content;

	public void setAoid(int aoid){
		this.aoid=aoid;
	}

	public int getAoid(){
		return aoid;
	}

	public void setAo_title(String ao_title){
		this.ao_title=ao_title;
	}

	public String getAo_title(){
		return ao_title;
	}

	public void setAo_time(java.sql.Date ao_time){
		this.ao_time=ao_time;
	}

	public java.sql.Date getAo_time(){
		return ao_time;
	}

	public void setAo_content(String ao_content){
		this.ao_content=ao_content;
	}

	public String getAo_content(){
		return ao_content;
	}

}
