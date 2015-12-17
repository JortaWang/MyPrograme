package utils;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

public class MapComparator {

	public static <K, V> void comparator(Map<K, V> map) {

	}

	public static <K, V> Map<K, V> getOrder(Map<K, V> map) {
		List<Map.Entry<K, V>> infoIds = new ArrayList<Map.Entry<K, V>>(map.entrySet());
		
		// 排序
		Collections.sort(infoIds, new Comparator<Map.Entry<K, V>>() {
			public int compare(Map.Entry<K, V> o1, Map.Entry<K, V> o2) {
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
