package segmentation;

import java.io.UnsupportedEncodingException;
import java.util.LinkedList;
import java.util.List;

import code.NlpirTest.CLibrary;

public class Segment {

	public String getSegment(String sInput){
		String argu = "";
		
		String system_charset = "UTF-8";
		int charset_type = 1;
		
		int init_flag = CLibrary.Instance.NLPIR_Init(argu, charset_type, "0");
		String nativeBytes = null;

		if (0 == init_flag) {
			nativeBytes = CLibrary.Instance.NLPIR_GetLastErrorMsg();
			System.err.println("初始化失败！fail reason is "+nativeBytes);
			return null;
		}
		
		int i=0;
		try {
			i=CLibrary.Instance.NLPIR_ImportUserDict("userDic.txt".getBytes("utf-8"), true);
		} catch (UnsupportedEncodingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		System.out.println(i);
		
		nativeBytes = CLibrary.Instance.NLPIR_ParagraphProcess(sInput, 1);
		
		CLibrary.Instance.NLPIR_Exit();
		
		return nativeBytes;
	}
	
	public LinkedList getSegments(List<String> list){
		String argu = "";
		
		String system_charset = "UTF-8";
		int charset_type = 1;
		
		int init_flag = CLibrary.Instance.NLPIR_Init(argu, charset_type, "0");
		
		if (0 == init_flag) {
			String nativeBytes = CLibrary.Instance.NLPIR_GetLastErrorMsg();
			System.err.println("初始化失败！fail reason is "+nativeBytes);
			return null;
		}
		
		int i=0;
		try {
			i=CLibrary.Instance.NLPIR_ImportUserDict("userDic.txt".getBytes("utf-8"), true);
		} catch (UnsupportedEncodingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		LinkedList<String> rList=new LinkedList<String>();
		for(String temp:list){
			rList.add(CLibrary.Instance.NLPIR_ParagraphProcess(temp, 1));
		}
		
		CLibrary.Instance.NLPIR_Exit();
		
		return rList;
	}
	
	

}
