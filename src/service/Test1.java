package service;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.sql.Date;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.LinkedList;

import db.dao.Artical_analysisDAO;
import db.dao.Artical_originalDAO;
import db.dto.Artical_analysisDTO;
import db.dto.Artical_originalDTO;
import segmentation.Segment;

public class Test1 {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		long time1=System.currentTimeMillis();
//		file2DB();
		analyze();
		long time2=System.currentTimeMillis();
		System.out.println(time2-time1);
	}

	public static void analyze() {
		// TODO 自动生成的方法存根
		Artical_originalDAO artical_originalDAO = new Artical_originalDAO();
		int num = artical_originalDAO.findSum();
		System.out.println(num);
		for (int j = 1; j <= num; j++) {
			Artical_originalDTO artical_originalDTO = artical_originalDAO.findArtical_originalByPrimaryKey(j);
			String title = artical_originalDTO.getAo_title();
			String content = artical_originalDTO.getAo_content();
			LinkedList<String> list = new LinkedList<String>();
			list.add(title);
			list.add(content);

			Segment segment = new Segment();
			LinkedList<String> rList = segment.getSegments(list);
			int k = 0;
			Artical_analysisDAO analysisDAO = new Artical_analysisDAO();
			ArrayList<Artical_analysisDTO> array=new ArrayList<>();
			for (int i = 0; i < rList.size(); i++) {
				String[] temp1 = rList.get(i).split(" ");
				for (String temp2 : temp1) {
					Artical_analysisDTO analysisDTO = new Artical_analysisDTO();
					if (i == 0)
						analysisDTO.setAa_grade("标题");
					else
						analysisDTO.setAa_grade("正文");
					analysisDTO.setAa_id(k++);
					analysisDTO.setAa_aoid(j);
					String[] temp3 = temp2.split("/");
					analysisDTO.setAa_word(temp3[0]);
					if (temp3.length == 1)
						continue;
					//删除标点
//					if(temp3[1].startsWith("w"))
//						continue;
					analysisDTO.setAa_type(temp3[1]);
					array.add(analysisDTO);
				}
			}
//			analysisDAO.addArtical_analysis(analysisDTO);
			analysisDAO.addList(array);
			// select aa_word,count(aa_word) as 频率 from artical_analysis group by aa_word order by 频率 desc;
		}
	}

	public static void file2DB() {
		for (int i = 0; i < 20; i++) {
			File file = new File("File/one_" + i + ".txt");
			Artical_originalDTO artical_originalDto = null;
			try (BufferedReader bufferedReader = new BufferedReader(new FileReader(file));) {
				artical_originalDto = new Artical_originalDTO();

				bufferedReader.readLine();
				artical_originalDto.setAo_title(bufferedReader.readLine().trim());
				bufferedReader.readLine();
				SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm");                
				java.util.Date date=null;
				try {
					date = sdf.parse(bufferedReader.readLine());
				} catch (ParseException e) {
					// TODO Auto-generated catch block
					date=new java.util.Date();
					e.printStackTrace();
				}
				artical_originalDto.setAo_time(new Date(date.getTime()));;
				artical_originalDto.setAo_content(bufferedReader.readLine().trim());

				Artical_originalDAO artical_originalDAO = new Artical_originalDAO();
				artical_originalDAO.addArtical_original(artical_originalDto);

			} catch (FileNotFoundException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (IOException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
		}
	}
	
//	delete from artical_original;
//	delete from artical_analysis;
//	alter table artical_original auto_increment=1;
//	alter table artical_analysis auto_increment=1;
	
//	select distinct aa_word from artical_analysis where aa_aoid=1 and aa_word in ( select distinct aa_word from artical_analysis where aa_word='书记'  or aa_word like '%长' or aa_word like '主%'); 

}
