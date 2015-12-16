package utils;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class WeiboUtil {

	public static void main(String[] args) throws InterruptedException {
		long start = System.currentTimeMillis();
		String path = "SinaWeiBo";
		File file=new File(path);
		if(!file.isDirectory()){
			throw new RuntimeException("file path err");
		}
		File[] files=file.listFiles();
		double sum=0;
		double finish=0;
		for(File tmpFile:files){
			sum+=tmpFile.length();
		}
		Connection connection = DBUtil.getConnection();
		String sql = "insert into sina_weibo values(null,?,?,?,?,?,null);";
		PreparedStatement preparedStatement=null;
//		try {
//			connection.setAutoCommit(false);  
//			preparedStatement = connection.prepareStatement(sql);
//		} catch (SQLException e1) {
//			e1.printStackTrace();
//		}
		long count=0L;
		for(int i=0;i<files.length;i++){
			File tmpFile=files[i];
			if(tmpFile.isDirectory())
				continue;
			//split(tmpFile,connection,preparedStatement);
			count+=count(tmpFile);
			finish+=tmpFile.length();
			//System.out.println("\n\n\nfinished percent:"+((finish/sum)*100)+"%");
		}
		try {
			connection.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		long end = System.currentTimeMillis();
		long plus = (end-start);
		long minute = plus/60000;
		long second = (plus-minute*60000)/1000;
		System.out.println("数据量为："+count);
		System.out.println("切分字段一共耗时"+minute+"分"+second+"秒");
	}
	
	public static int count(File file){
		String content = loadFile(file);
		String[] sentences = content.split("\n");
		for(int i=0;i<sentences.length;i++){
			if(sentences[i].indexOf("")>0)
				System.out.println(sentences[i]);
		}
		return sentences.length;
	}
	
	public static void split(File file,Connection connection,PreparedStatement preparedStatement){
		try {
			String content = loadFile(file);
			String[] sentences = content.split("\n");
			
			String name = file.getName();
			name=name.split("\\.")[0];
			for(int i=0;i<sentences.length;i++){
				String sentence = sentences[i];
				String like = regexUtil(sentence," 赞[","]").substring(2);
				String comment = regexUtil(sentence," 评论[","]").substring(3);
				String share = regexUtil(sentence," 转发[","]").substring(3);
				preparedStatement.setString(1, sentence);
				preparedStatement.setString(2, name);
				preparedStatement.setInt(3, parseInt(like.substring(0, like.length()-1)));
				preparedStatement.setInt(4, parseInt(comment.substring(0, comment.length()-1)));
				preparedStatement.setInt(5, parseInt(share.substring(0, share.length()-1)));
				preparedStatement.addBatch();
				if(i%2048==0&&i!=0){
					preparedStatement.executeBatch();
					connection.commit();
				}
			}
			preparedStatement.executeBatch();
			connection.commit();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	
	public static String regexUtil(String sentence,String regexBegin,String regexEnd){
		int start = sentence.indexOf(regexBegin);
		if(start>-1){
			int end=start++;
			while(end<sentence.length()&&!String.valueOf(sentence.charAt(end)).equals(regexEnd)){
				end++;
			}
			if(end<(sentence.length()-1))
				end++;
			return sentence.substring(start,end);
		}
		return "0000000000";
	}
	
	public static int parseInt(String s){
		if(s==null)
			return 0;
		try {
			return Integer.parseInt(s);
		} catch (NumberFormatException e) {
			System.err.println("zhuanhuan");
			return 0;
		}
	}
	
	public static String loadFile(File file){
		BufferedReader bufferedReader=null;
		try {
			bufferedReader = new BufferedReader(new FileReader(file));
		} catch (FileNotFoundException e1) {
			e1.printStackTrace();
		}
		
		StringBuilder builder = new StringBuilder();
		String temp = "";
		String charset = "";
		try {
			while((temp=bufferedReader.readLine())!=null){
				if(temp.startsWith("http://")||temp.startsWith("设置")||temp.startsWith("彩版"))
					continue;
				charset = new String(temp.getBytes("UTF-8"),"UTF-8");
				builder.append(charset);
				builder.append("\n");
			}
		} catch (IOException e) {
			e.printStackTrace();
		}
		
		return builder.toString();
	}

}
