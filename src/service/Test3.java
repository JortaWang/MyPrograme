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
		String[] tmpFileStrs = fileStr.split("\n");
		for(int i=0;i<tmpFileStrs.length;i++){
			String tempLine = tmpFileStrs[i];
			String[] tempColumn = tempLine.split("\\s");
			sb.append(tempColumn[5]);
			sb.append("\n");
		}
		String str = Segment.getSegment(sb.toString());
		String[] splits = str.split("\n");
		for(int i=0;i<splits.length;i++){
			String spl = splits[i];
//			System.out.println(spl);
			String[] tmpWord = spl.split("\\s");
			String output="";
			for(int j=0;j<tmpWord.length;j++){
				String[] tmp = tmpWord[j].split("/");
				if("t".equals(tmp[1])||"nrg".equals(tmp[1])||"np".equals(tmp[1])||"nr".equals(tmp[1])||tmp[1].indexOf("vi")>=0){
					//output += tmpWord[j]+" ";
					output += tmp[0]+" ";
//					if(!checkString(tmp[0])){
//						System.out.println(spl);
//						System.out.println(tmpFileStrs[i].split("\\s")[5]);
//						break;
//						break;
//					}
				}
			}
			if(!"".equals(output))
				System.out.println(output);
		}
	}
	
	public static boolean checkString(String tmp){
		File file = new File("patternList/groupWord.txt");
		String str = LoadFileUtil.loadFile(file);
		String[] strArr = str.split("\\n");
		for(String s:strArr){
			if(s.equals(tmp)){
				return true;
			}
		}
		return false;
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
