package db.dao;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import db.dto.Artical_originalDTO;
import utils.DBUtil;
import utils.Rs;


/**
 * Artical_originalDAO
 * Mon Jul 27 11:19:48 CST 2015
 * @author Yanqi
 */
public class Artical_originalDAO{

	//add artical_original by DTO
	public void addArtical_original(Artical_originalDTO artical_originalDto){
		String sql="insert into artical_original values(?,?,?,?)";
		DBUtil.executeUpdate(sql,artical_originalDto.getAoid(),artical_originalDto.getAo_title(),artical_originalDto.getAo_time(),artical_originalDto.getAo_content());
	}

	//update artical_original by DTO
	public void updateArtical_original(Artical_originalDTO artical_originalDto){
		String sql="update artical_original set ao_title=?,ao_time=?,ao_content=? where aoid=?";
		DBUtil.executeUpdate(sql,artical_originalDto.getAo_title(),artical_originalDto.getAo_time(),artical_originalDto.getAo_content(),artical_originalDto.getAoid());
	}

	//update artical_original by List<Artical_originalDTO>
	public void updateArtical_originals(List<Artical_originalDTO> list){
		Iterator<Artical_originalDTO> iterator=list.iterator();
		while(iterator.hasNext()){
			Artical_originalDTO artical_originalDto=iterator.next();
			this.updateArtical_original(artical_originalDto);
		}
	}

	//remove artical_original by primary key
	public void removeArtical_originalByPrimaryKey(int aoid){
		String sql="delete from artical_original where aoid=?";
		DBUtil.executeUpdate(sql,aoid);
	}

	//remove artical_original by DTO
	public void removeArtical_originalByDTO(Artical_originalDTO artical_originalDto){
		this.removeArtical_originalByPrimaryKey(artical_originalDto.getAoid());
	}

	//remove artical_original by DTOs
	public void removeArtical_originalByDTOs(List<Artical_originalDTO> list){
		Iterator<Artical_originalDTO> iterator=list.iterator();
		while(iterator.hasNext()){
			Artical_originalDTO artical_originalDto=iterator.next();
			this.removeArtical_originalByDTO(artical_originalDto);
		}
	}

	//find artical_original by primary key
	public Artical_originalDTO findArtical_originalByPrimaryKey(int aoid){
		String sql="select * from artical_original where aoid=?";
		Rs rs=DBUtil.executeQuery(sql,aoid);
		Artical_originalDTO artical_originalDto=null;
		if(rs.next()){
			artical_originalDto=new Artical_originalDTO();
			artical_originalDto.setAoid(rs.getInt("aoid"));
			artical_originalDto.setAo_title(rs.getString("ao_title"));
			artical_originalDto.setAo_time(rs.getDate("ao_time"));
			artical_originalDto.setAo_content(rs.getString("ao_content"));
		} 
		return artical_originalDto;
	}
	
	//find all artical_original
	public ArrayList<Artical_originalDTO> findAllArtical_original(){
		String sql="select * from artical_original";
		Rs rs=DBUtil.executeQuery(sql);
		ArrayList<Artical_originalDTO> array=new ArrayList<>();
		while(rs.next()){
			Artical_originalDTO artical_originalDto=new Artical_originalDTO();
			artical_originalDto.setAoid(rs.getInt("aoid"));
			artical_originalDto.setAo_title(rs.getString("ao_title"));
			artical_originalDto.setAo_time(rs.getDate("ao_time"));
			artical_originalDto.setAo_content(rs.getString("ao_content"));
			array.add(artical_originalDto);
		}
		return array;
	}

	//count
	public int findSum(){
		String sql="select count(*) from artical_original";
		Rs rs=DBUtil.executeQuery(sql);
		int sum=0;
		if(rs.next()){
			sum=rs.getInt("count(*)");
		}
		return sum;
	}

	//find by page
	public ArrayList<Artical_originalDTO> findArtical_originalByPage(int pageNow,int pageSize){
		String sql="select * from artical_original limit "+(pageSize*(pageNow-1))+","+pageSize;
		Rs rs=DBUtil.executeQuery(sql);
		ArrayList<Artical_originalDTO> array=new ArrayList<>();
		while(rs.next()){
			Artical_originalDTO artical_originalDto=new Artical_originalDTO();
			artical_originalDto.setAoid(rs.getInt("aoid"));
			artical_originalDto.setAo_title(rs.getString("ao_title"));
			artical_originalDto.setAo_time(rs.getDate("ao_time"));
			artical_originalDto.setAo_content(rs.getString("ao_content"));
			array.add(artical_originalDto);
		}
		return array;
	}

}

