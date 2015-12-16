package db.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;

import db.dto.Artical_analysisDTO;
import utils.DBUtil;
import utils.Rs;

/**
 * Artical_analysisDAO Mon Jul 27 11:19:48 CST 2015
 * 
 * @author Yanqi
 */
public class Artical_analysisDAO {

	// add artical_analysis by DTO
	public void addArtical_analysis(Artical_analysisDTO artical_analysisDto) {
		String sql = "insert into artical_analysis values(null,?,?,?,?)";
		DBUtil.executeUpdate(sql, artical_analysisDto.getAa_word(), artical_analysisDto.getAa_type(),
				artical_analysisDto.getAa_grade(), artical_analysisDto.getAa_aoid());
	}
	
	public void addList(List<Artical_analysisDTO> list) {
		String sql = "insert into artical_analysis values(null,?,?,?,?)";
		Connection conn=DBUtil.getConnection();
		//272303-->11085
		//10967
		try {
			conn.setAutoCommit(false);
			PreparedStatement preparedStatement=conn.prepareStatement(sql);
			for(Artical_analysisDTO artical_analysisDto:list){
				preparedStatement.setString(1, artical_analysisDto.getAa_word());
				preparedStatement.setString(2, artical_analysisDto.getAa_type());
				preparedStatement.setString(3, artical_analysisDto.getAa_grade());
				preparedStatement.setInt(4, artical_analysisDto.getAa_aoid());
				preparedStatement.addBatch();
			}
			preparedStatement.executeBatch();
			conn.setAutoCommit(true);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally{
			try {
				if(conn!=null)
					conn.close();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		
//		DBUtil.executeUpdate(sql, artical_analysisDto.getAa_word(), artical_analysisDto.getAa_type(),
//				artical_analysisDto.getAa_grade(), artical_analysisDto.getAa_aoid());
	}

	// update artical_analysis by DTO
	public void updateArtical_analysis(Artical_analysisDTO artical_analysisDto) {
		String sql = "update artical_analysis set aa_word=?,aa_type=?,aa_grade=?,aa_aoid=? where aa_id=?";
		DBUtil.executeUpdate(sql, artical_analysisDto.getAa_word(), artical_analysisDto.getAa_type(),
				artical_analysisDto.getAa_grade(), artical_analysisDto.getAa_aoid(), artical_analysisDto.getAa_id());
	}

	// update artical_analysis by List<Artical_analysisDTO>
	public void updateArtical_analysiss(List<Artical_analysisDTO> list) {
		Iterator<Artical_analysisDTO> iterator = list.iterator();
		while (iterator.hasNext()) {
			Artical_analysisDTO artical_analysisDto = iterator.next();
			this.updateArtical_analysis(artical_analysisDto);
		}
	}

	// remove artical_analysis by primary key
	public void removeArtical_analysisByPrimaryKey(int aa_id) {
		String sql = "delete from artical_analysis where aa_id=?";
		DBUtil.executeUpdate(sql, aa_id);
	}

	// remove artical_analysis by DTO
	public void removeArtical_analysisByDTO(Artical_analysisDTO artical_analysisDto) {
		this.removeArtical_analysisByPrimaryKey(artical_analysisDto.getAa_id());
	}

	// remove artical_analysis by DTOs
	public void removeArtical_analysisByDTOs(List<Artical_analysisDTO> list) {
		Iterator<Artical_analysisDTO> iterator = list.iterator();
		while (iterator.hasNext()) {
			Artical_analysisDTO artical_analysisDto = iterator.next();
			this.removeArtical_analysisByDTO(artical_analysisDto);
		}
	}

	// find artical_analysis by primary key
	public Artical_analysisDTO findArtical_analysisByPrimaryKey(int aa_id) {
		String sql = "select * from artical_analysis where aa_id=?";
		Rs rs = DBUtil.executeQuery(sql, aa_id);
		Artical_analysisDTO artical_analysisDto = null;
		if (rs.next()) {
			artical_analysisDto = new Artical_analysisDTO();
			artical_analysisDto.setAa_id(rs.getInt("aa_id"));
			artical_analysisDto.setAa_word(rs.getString("aa_word"));
			artical_analysisDto.setAa_type(rs.getString("aa_type"));
			artical_analysisDto.setAa_grade(rs.getString("aa_grade"));
			artical_analysisDto.setAa_aoid(rs.getInt("aa_aoid"));
		}
		return artical_analysisDto;
	}

	// find artical_analysis by primary key
	public ArrayList<String> findArtical_analysis4Name(int aa_aoid) {
		String sql = "select distinct aa_word from artical_analysis where aa_aoid=? and aa_type in ('nr' , 'nrf')";
		Rs rs = DBUtil.executeQuery(sql, aa_aoid);
		ArrayList<String> array=new ArrayList<>();
		while (rs.next()) {
			array.add(rs.getString("aa_word"));
		}
		return array;
	}
	
	public HashSet<String> find4grade(int aa_aoid){
		String sql = "select distinct aa_id,aa_word from artical_analysis where aa_aoid=? and aa_grade='正文' and aa_word in ( select distinct aa_word from artical_analysis where aa_word='书记'  or aa_word like '%长' or aa_word like '主%'); ";
		Rs rs = DBUtil.executeQuery(sql, aa_aoid);
		HashSet<String> set=new HashSet<>();
		while (rs.next()) {
			String grade=rs.getString("aa_word");
			int aa_id=rs.getInt("aa_id");
			String sqls="select distinct aa_word from artical_analysis where aa_id=?;";
			Rs rs1=DBUtil.executeQuery(sqls,aa_id-1);
			String grade1=rs1.getString("aa_word");
			if("副".equals(grade1))
				set.add("副"+grade);
			else{
				set.add(grade);
			}
		}
		return set;
	}

	// find all artical_analysis
	public ArrayList<Artical_analysisDTO> findAllArtical_analysis() {
		String sql = "select * from artical_analysis";
		Rs rs = DBUtil.executeQuery(sql);
		ArrayList<Artical_analysisDTO> array = new ArrayList<>();
		while (rs.next()) {
			Artical_analysisDTO artical_analysisDto = new Artical_analysisDTO();
			artical_analysisDto.setAa_id(rs.getInt("aa_id"));
			artical_analysisDto.setAa_word(rs.getString("aa_word"));
			artical_analysisDto.setAa_type(rs.getString("aa_type"));
			artical_analysisDto.setAa_grade(rs.getString("aa_grade"));
			artical_analysisDto.setAa_aoid(rs.getInt("aa_aoid"));
			array.add(artical_analysisDto);
		}
		return array;
	}

	// count
	public int findSum() {
		String sql = "select count(*) from artical_analysis";
		Rs rs = DBUtil.executeQuery(sql);
		int sum = 0;
		if (rs.next()) {
			sum = rs.getInt("count(*)");
		}
		return sum;
	}

	// find by page
	public ArrayList<Artical_analysisDTO> findArtical_analysisByPage(int pageNow, int pageSize) {
		String sql = "select * from artical_analysis limit " + (pageSize * (pageNow - 1)) + "," + pageSize;
		Rs rs = DBUtil.executeQuery(sql);
		ArrayList<Artical_analysisDTO> array = new ArrayList<>();
		while (rs.next()) {
			Artical_analysisDTO artical_analysisDto = new Artical_analysisDTO();
			artical_analysisDto.setAa_id(rs.getInt("aa_id"));
			artical_analysisDto.setAa_word(rs.getString("aa_word"));
			artical_analysisDto.setAa_type(rs.getString("aa_type"));
			artical_analysisDto.setAa_grade(rs.getString("aa_grade"));
			artical_analysisDto.setAa_aoid(rs.getInt("aa_aoid"));
			array.add(artical_analysisDto);
		}
		return array;
	}

}
