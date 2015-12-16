package utils;

import java.sql.*;
import java.util.*;
import java.io.*;

public class Rs{

	ArrayList<HashMap> list=new ArrayList<>();
	HashMap<String,Object> map=new HashMap<>();

	int currentColumnNum=-1;
	int nextColumnNum=-1;

	public void setObject(String columnName,Object obj){
		if(nextColumnNum==currentColumnNum){
			map.put(columnName, obj);
		}else{
			map=new HashMap<String, Object>();
			map.put(columnName, obj);
			currentColumnNum++;
		}
	}

	public Object getObject(String columnName){
		if(nextColumnNum!=currentColumnNum){
			map=list.get(nextColumnNum);
			currentColumnNum++;
		}
		return map.get(columnName);
	}

	public void saveColumn(){
		list.add(map);
		nextColumnNum++;
	}

	public boolean next(){
		if(currentColumnNum<list.size()-1){
			nextColumnNum++;
			return true;
		}else{
			return false;
		}
	}

	public void setFirst(){
		currentColumnNum=-1;
		nextColumnNum=-1;
	}

	public boolean hasNext(){
		return currentColumnNum<list.size()-1;
	}

	public int getInt(String columnName){
		return Integer.parseInt(getObject(columnName).toString());
	}

	public double getDouble(String columnName){
		return Double.parseDouble(getObject(columnName).toString());
	}

	public String getString(String columnName){
		return getObject(columnName).toString();
	}

	public boolean getBoolean(String columnName){
		return (Boolean)getObject(columnName);
	}

	public byte getByte(String columnName){
		return (byte)getObject(columnName);
	}

	public byte[] getBytes(String columnName){
		return (byte[])getObject(columnName);
	}

	public java.sql.Date getDate(String columnName){
		return (java.sql.Date)getObject(columnName);
	}

	public float getFloat(String columnName){
		return (float)getObject(columnName);
	}

	public long getLong(String columnName){
		return (long)getObject(columnName);
	}

	public short getShort(String columnName){
		return (short)getObject(columnName);
	}

	public Time getTime(String columnName){
		return (Time)getObject(columnName);
	}

	public Timestamp getTimestamp(String columnName){
		return (Timestamp)getObject(columnName);
	}

}

