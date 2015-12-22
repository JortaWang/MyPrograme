package utils;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

public class MapComparator {

	public static <K, V> Map<K, V> getOrder(Map<K, V> map) {
		List<Map.Entry<K, V>> infoIds = new ArrayList<Map.Entry<K, V>>(map.entrySet());
		
		// 排序
		Collections.sort(infoIds, new Comparator<Map.Entry<K, V>>() {
			public int compare(Map.Entry<K, V> o1, Map.Entry<K, V> o2) {
				if(o1.getValue() instanceof Integer && o2.getValue() instanceof Integer){
					return (Integer)o1.getValue() - (Integer)o2.getValue();
				}else if(o1.getValue() instanceof Double && o2.getValue() instanceof Double){
					return (int)((Double)o1.getValue() - (Double)o2.getValue());
				}
				return 0;
			}
		});

		/* 转换成新map输出 */
		LinkedHashMap<K, V> newMap = new LinkedHashMap<K, V>();

		for (Map.Entry<K, V> entity : infoIds) {
			newMap.put(entity.getKey(), entity.getValue());
		}

		return newMap;
	}

}
