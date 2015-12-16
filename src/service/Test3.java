package service;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.HashSet;
import java.util.Set;

import segmentation.Segment;
import utils.LoadFileUtil;

public class Test3 {

	
	
	public static void main(String[] args) {
		String path = "File/评论.txt";
		File file=new File(path);
		String fileStr = LoadFileUtil.loadFile(file);
		StringBuilder sb = new StringBuilder();
		for(String strLine:fileStr.split("\\n")){
			String[] str=strLine.split("\\s");
			sb.append(str[5]);
		}
		String str = Segment.getSegment(sb.toString());
		String[] splits = str.split(" ");
		for(String spl:splits){
			System.out.println(spl);
		}
	}
	
	public static Set buildSet(){
		File file = new File("patternList/netWord.txt");
		String str = LoadFileUtil.loadFile(file);
		String[] strArr = str.split("/n");
		Set<String> set = new HashSet<>();
		for(String s:strArr){
			set.add(s);
		}
		return set;
	}
	
	public static String loadWeibo(File file){
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
