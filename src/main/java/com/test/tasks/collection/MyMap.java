package com.test.tasks.collection;

import java.util.HashMap;
import java.util.Map;

public class MyMap {
    private Map<Object, Integer> map = new HashMap<>();
    private int count = 1;

    public Map<Object, Integer> mapa(Object[] o) {
        for (int i = 0; i < o.length - 1; i++) {
            for (int j = 1; j < o.length; j++) {
                if (o[i].equals(o[j])) {
                    map.put(o[i], count++);
                } else {
                    map.put(o[i], count);
                }
            }
            count = 1;
        }
        return map;
    }

}
