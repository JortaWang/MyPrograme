package utils;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;

public class LoadFileUtil {

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
