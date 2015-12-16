
package db.dto;

/**
 * Artical_analysisDTO
 * Mon Jul 27 11:19:48 CST 2015
 * @author Yanqi
 */

public class Artical_analysisDTO implements java.io.Serializable{

	public static final String AA_ID="aa_id";
	public static final String AA_WORD="aa_word";
	public static final String AA_TYPE="aa_type";
	public static final String AA_GRADE="aa_grade";
	public static final String AA_AOID="aa_aoid";

	private int aa_id;
	private String aa_word;
	private String aa_type;
	private String aa_grade;
	private int aa_aoid;

	public void setAa_id(int aa_id){
		this.aa_id=aa_id;
	}

	public int getAa_id(){
		return aa_id;
	}

	public void setAa_word(String aa_word){
		this.aa_word=aa_word;
	}

	public String getAa_word(){
		return aa_word;
	}

	public void setAa_type(String aa_type){
		this.aa_type=aa_type;
	}

	public String getAa_type(){
		return aa_type;
	}

	public void setAa_grade(String aa_grade){
		this.aa_grade=aa_grade;
	}

	public String getAa_grade(){
		return aa_grade;
	}

	public void setAa_aoid(int aa_aoid){
		this.aa_aoid=aa_aoid;
	}

	public int getAa_aoid(){
		return aa_aoid;
	}

}
