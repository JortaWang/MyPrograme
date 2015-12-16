package service;

import java.io.File;
import java.util.HashSet;
import java.util.Set;

import utils.LoadFileUtil;

public class Test3 {

	
	
	public static void main(String[] args) {
		
		
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

}
