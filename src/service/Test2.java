package service;

import java.sql.Date;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.Iterator;

import db.dao.Artical_analysisDAO;
import db.dao.Artical_originalDAO;
import db.dto.Artical_originalDTO;

public class Test2 {

	public static void main(String[] args) {

		Artical_originalDAO artical_originalDAO = new Artical_originalDAO();
		ArrayList<Artical_originalDTO> array = artical_originalDAO.findAllArtical_original();

		for (Artical_originalDTO artical_originalDTO : array) {
			String content=artical_originalDTO.getAo_content();
			String title=artical_originalDTO.getAo_title();
			Date time=artical_originalDTO.getAo_time();
			int aoid=artical_originalDTO.getAoid();
			System.out.println("文章号：" + aoid);
			System.out.print("来源：");
			if (content.matches("[\\w\\W]*据[\\w\\W]*消息[\\w\\W]*")) {
				int i = content.indexOf("据");
				int j = content.indexOf("消息");
				System.out.println(content.substring(i + 1, j));
			} else {
				String[] arg = content.split("。");
				boolean b = false;
				for (String arg1 : arg) {
					if (arg1.startsWith("（") && arg1.endsWith("）")) {
						System.out.println(arg1.substring(1, arg1.length() - 1));
						b = true;
						break;
					}
				}
				if (!b) {
					System.out.println("未知");
				}
			}

			System.out.print("时间：");
			if (time != null) {
				SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
				String timeS = sdf.format(time);
				System.out.println(timeS);
			} else {
				System.out.println("未知");
			}

			System.out.print("姓名：");
			Artical_analysisDAO analysisDAO = new Artical_analysisDAO();
			ArrayList<String> names = analysisDAO.findArtical_analysis4Name(aoid);
			for (int i = 0; i < names.size(); i++) {
				System.out.print(names.get(i));
				if (i != names.size() - 1)
					System.out.print(" ");
				else
					System.out.println();
			}

			System.out.print("级别：");
			HashSet<String> grades = analysisDAO.find4grade(aoid);
			Iterator<String> iterator = grades.iterator();
			while (iterator.hasNext()) {
				String grade = iterator.next();
				if (grade.length() > 1)
					System.out.print(grade);
				if (iterator.hasNext())
					System.out.print(" ");
				else
					System.out.println();
			}

			String[] temp = title.split("原");
a: 			if (temp.length > 1) {
				System.out.println("单位：" + temp[0]);
			} else {
				iterator = grades.iterator();
				while (iterator.hasNext()) {
					String grade = iterator.next();
					temp = title.split(grade);
					if (temp.length > 1) {
						if (temp[0].endsWith("副"))
							System.out.println("单位：" + temp[0].substring(0, temp[0].length() - 1));
						else
							System.out.println("单位：" + temp[0]);
						break a;
					}
				}
				System.out.println("单位：未知");
			}

			System.out.print("原因：");
			boolean b = false;
			if (content.matches("[\\w\\W]*便利[\\w\\W]*") || content.matches("[\\w\\W]*为他人谋取利益[\\w\\W]*")
					|| content.matches("[\\w\\W]*滥用职权[\\w\\W]*")) {
				System.out.print("滥用职权");
				b = true;
			}

			if (content.matches("[\\w\\W]*贿赂[\\w\\W]*") || content.matches("[\\w\\W]*礼金[\\w\\W]*")
					|| content.matches("[\\w\\W]*财物[\\w\\W]*")) {
				if (b)
					System.out.print("、");
				System.out.print("收受贿赂");
				b = true;
			}

			if (content.matches("[\\w\\W]*通奸[\\w\\W]*")) {
				if (b)
					System.out.print("、");
				System.out.print("生活作风");
				b = true;
			}

			if (content.matches("[\\w\\W]*贪污[\\w\\W]*") || content.matches("[\\w\\W]*挪用[\\w\\W]*")
					|| content.matches("[\\w\\W]*私分[\\w\\W]*")) {
				if (b)
					System.out.print("、");
				System.out.print("贪污");
				b = true;
			}

			if (content.matches("[\\w\\W]*组织审查[\\w\\W]*") && (content.matches("[\\w\\W]*干扰[\\w\\W]*")
					|| content.matches("[\\w\\W]*妨碍[\\w\\W]*") || content.matches("[\\w\\W]*对抗[\\w\\W]*"))) {
				if (b)
					System.out.print("、");
				System.out.print("妨碍审查");
				b = true;
			}

			if (content.matches("[\\w\\W]*私自出国[\\w\\W]*")) {
				if (b)
					System.out.print("、");
				System.out.print("私自出国");
				b = true;
			}

			if ((!b) && content.matches("[\\w\\W]*接受组织调查[\\w\\W]*")) {
				System.out.print("调查中");
			} else if (!b)
				System.out.print("未知");

			b = false;
			System.out.print("\n处分：");

			if (content.matches("[\\w\\W]*开除党籍[\\w\\W]*")) {
				System.out.print("开除党籍");
				b = true;
			}

			if (content.matches("[\\w\\W]*开除公职[\\w\\W]*")) {
				if (b)
					System.out.print("、");
				System.out.print("开除公职");
				b = true;
			}

			if (content.matches("[\\w\\W]*行政开除[\\w\\W]*")) {
				if (b)
					System.out.print("、");
				System.out.print("行政开除");
				b = true;
			}

			if (content.matches("[\\w\\W]*移送司法机关依法处理[\\w\\W]*")) {
				if (b)
					System.out.print("、");
				System.out.print("移送司法机关依法处理");
				b = true;
			}

			if (content.matches("[\\w\\W]*收缴其违纪所得[\\w\\W]*")) {
				if (b)
					System.out.print("、");
				System.out.print("收缴其违纪所得");
				b = true;
			}

			if ((!b) && content.matches("[\\w\\W]*接受组织调查[\\w\\W]*")) {
				System.out.print("调查中");
			} else if (!b)
				System.out.print("未知");

			System.out.println();
			System.out.println();
			System.out.println();
		}
	}

}
