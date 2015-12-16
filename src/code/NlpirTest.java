package code;

import java.io.UnsupportedEncodingException;

import utils.SystemParas;

import com.sun.jna.Library;
import com.sun.jna.Native;

public class NlpirTest {

	// 定义接口CLibrary，继承自com.sun.jna.Library
	public interface CLibrary extends Library {
		// 定义并初始化接口的静态变量
		CLibrary Instance = (CLibrary) Native.loadLibrary(
				"lib/NLPIR", CLibrary.class);
		
		public int NLPIR_Init(String sDataPath, int encoding,
				String sLicenceCode);
				
		public String NLPIR_ParagraphProcess(String sSrc, int bPOSTagged);
		public int NLPIR_ImportUserDict(byte[] sPath,boolean b);
		public String NLPIR_GetKeyWords(String sLine, int nMaxKeyLimit,
				boolean bWeightOut);
		public String NLPIR_GetFileKeyWords(String sLine, int nMaxKeyLimit,
				boolean bWeightOut);
		public int NLPIR_AddUserWord(String sWord);//add by qp 2008.11.10
		public int NLPIR_DelUsrWord(String sWord);//add by qp 2008.11.10
		public String NLPIR_GetLastErrorMsg();
		public boolean NLPIR_NWI_Start();//add by yq 2015/6/30
		public int NLPIR_NWI_AddFile(String sFilename);
		public boolean NLPIR_NWI_AddMem(String sText);
		public boolean NLPIR_NWI_Complete();
		public String NLPIR_NWI_GetResult(boolean bWeightOut);
		public String NLPIR_GetNewWords(String sLine,int nMaxKeyLimit,boolean bWeightOut);
		public int NLPIR_NWI_Result2UserDict();
		public int NLPIR_SaveTheUsrDic();
		public void NLPIR_Exit();
	}

	

	public static void main(String[] args) throws Exception {
		String argu = "";
		// String system_charset = "GBK";//GBK----0
		String system_charset = "UTF-8";
		int charset_type = 1;
		
		int init_flag = CLibrary.Instance.NLPIR_Init(argu, charset_type, "0");
		String nativeBytes = null;

		if (0 == init_flag) {
			nativeBytes = CLibrary.Instance.NLPIR_GetLastErrorMsg();
			System.err.println("初始化失败！fail reason is "+nativeBytes);
			return;
		}
		
		int i=CLibrary.Instance.NLPIR_ImportUserDict("userDic.txt".getBytes("UTF-8"),false);
		
		System.out.println(i);
		String sInput = "昨日，据人民日报报道，中国人民大学原校长纪宝成因违纪被内部通报，给予留党察看两年处分，并被取消副部级待遇。据知情人士透露，去年底，中国人民大学在高层范围内曾经通报过纪宝成的问题。  2011年11月，纪宝成不再担任人民大学校长职务。    1 “身边人”蔡荣生被逮捕 女秘书胡娟卸任领导职务    中国人民大学多名教职员工向新京报记者表示，纪宝成的问题与中国人民大学原招生处处长蔡荣生有关。按惯例，招生处处长这一职位在人民大学要轮岗，三年轮一次。但蔡荣生有了纪宝成学生这个特殊身份，一连担任了8年招生处处长。    蔡荣生曾在人大商学院攻读在职博士，导师正是纪宝成。2014年5月，南京市人民检察院向公众宣布，以涉嫌受贿罪决定对中国人民大学招生就业处原处长蔡荣生予以逮捕。经查，2006年至2013年期间，蔡荣生利用职务便利，在学校特殊类型招生过程中为考生提供帮助，收受贿赂1000余万元。但直到目前，蔡荣生涉嫌受贿一案，尚未有官方公布的最新进展。    中国人民大学一名中层干部向新京报记者讲述，蔡荣生被调查后，她专门当面向纪宝成询问过有关蔡荣生的事情，纪宝成当时回应称，蔡荣生有清华本科、人大硕士、人大博士学历，和在政府部门的工作经历，他想回学校工作，我能不让吗?    另据了解，纪宝成之前的女秘书胡娟因破格提拔教授等事，曾遭遇诸多质疑。此前，她已卸任人大教育学院执行院长一职。2009年6月，胡娟获得中国人民大学的理学博士学位，1年后被学校评聘为教授，2013年成为博士生导师。   2 通报后派校医送其回家 两干部通风报信受处分  新京报记者从知情人士处获悉，去年底，中国人民大学在高层范围内曾经通报过纪宝成的问题。通报行文非常简短，只是称因违纪问题，纪宝成受到了留党察看处分，并取消副部级待遇。但纪宝成到底涉及了什么问题，通报行文中并没有提及。    该人士称，通报纪宝成的问题时，还特意派了两名校医护送纪宝成回家，担心他的身体出问题。此外，中国人民大学还有两名处级干部由于向纪宝成通风报信，也受到了处分。    2011年11月23日，中组部副部长到人民大学宣布：因年龄原因，纪宝成不再担任人民大学校长职务。   3 题词已经被去掉落款 博导名单中仍有名字    随着2014年底“被处分”传言的扩散，纪宝成在公开场合露面的方式也有所转变。 2014年11月，在人大原副校长、一级教授郑杭生的遗体告别仪式上，还有纪宝成敬献的花圈。而到了2015年3月，在另一名人大原副校长罗国杰的遗体告别仪式通稿中，则未再提及纪宝成。新京报记者发现，在中国人民大学校内，纪宝成在一勺池、校友之家和育贤楼等地的题词，也在今年被更换字体、去掉落款或整体撤走。纪宝成的校内职务和社会兼职也多有变化。去年，他卸任了人民大学国学院院长，改任名誉院长。湖北工业大学官网也在“现任领导”页面撤下纪宝成的照片和信息，2012年，纪宝成曾受聘为湖工大顾问。 如今，中国职业技术教育学会官网上的“会长”一栏已是空的，但几个月前一名副会长还向新京报记者透露，虽然听说纪宝成受处分，但当时纪宝成本人并未向学会提出辞呈，学会也未召开会议撤销其会长职务，“学会属于群团组织，有自己的章程，并不适用行政单位对他的处分。”  不过，纪宝成的教学科研似乎未受影响。去年中国人民大学研究生招生网发布的《2015年已拟录取直博、硕博连读生导师名单》中，依然有纪宝成的名字，显示他拟录取1名攻读人大教育学院教育经济与管理专业的连读生。";
		System.out.println("分词标本为： "+sInput);
		
		try {
			nativeBytes = CLibrary.Instance.NLPIR_ParagraphProcess(sInput, 1);
			System.out.println("分词结果为： " + nativeBytes);
			
			CLibrary.Instance.NLPIR_AddUserWord("中国人民大学 nt");
			CLibrary.Instance.NLPIR_AddUserWord("人大教育学院 nt");
//			nativeBytes = CLibrary.Instance.NLPIR_ParagraphProcess(sInput, 1);
//			System.out.println("增加用户词典后分词结果为： " + nativeBytes);
//			
//			CLibrary.Instance.NLPIR_DelUsrWord("要求美方加强对输");
//			nativeBytes = CLibrary.Instance.NLPIR_ParagraphProcess(sInput, 1);
//			System.out.println("删除用户词典后分词结果为： " + nativeBytes);
//			String test=CLibrary.Instance.NLPIR_GetNewWords(sInput,10,false);
			CLibrary.Instance.NLPIR_NWI_Start();
			CLibrary.Instance.NLPIR_NWI_AddMem(sInput);
			CLibrary.Instance.NLPIR_NWI_Complete();
			System.out.println("新词为： "+CLibrary.Instance.NLPIR_NWI_GetResult(false));
			
//			int nCountKey = 0;
//			String nativeByte = CLibrary.Instance.NLPIR_GetKeyWords(sInput, 10,false);
//
//			System.out.print("关键词提取结果是：" + nativeByte);
//
//			nativeByte = CLibrary.Instance.NLPIR_GetFileKeyWords("D:\\NLPIR\\feedback\\huawei\\5341\\5341\\产经广场\\2012\\5\\16766.txt", 10,false);
//
//			System.out.print("关键词提取结果是：" + nativeByte);
//			System.out.println("新词结果："+test);
			
			int num1=CLibrary.Instance.NLPIR_NWI_Result2UserDict();
			System.out.println("num1>>"+num1);
			int num2=CLibrary.Instance.NLPIR_SaveTheUsrDic();
			System.out.println("num2>>"+num2);
			
			CLibrary.Instance.NLPIR_NWI_Start();
			CLibrary.Instance.NLPIR_NWI_AddMem(sInput);
			CLibrary.Instance.NLPIR_NWI_Complete();
			System.out.println("新词为： "+CLibrary.Instance.NLPIR_NWI_GetResult(true));
			
			CLibrary.Instance.NLPIR_Exit();

		} catch (Exception ex) {
			// TODO Auto-generated catch block
			ex.printStackTrace();
		}

	}
}
