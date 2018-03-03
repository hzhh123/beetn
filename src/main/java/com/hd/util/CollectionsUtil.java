package com.hd.util;

import java.util.Collections;
import java.util.List;

public class CollectionsUtil<T> {
    /**
     * 比较两个list是否相等
     * @param a
     * @param b
     * @param <T>
     * @return
     */
    public static <T extends Comparable<T>> boolean compare(List<T> a, List<T> b) {
        if(a.size() != b.size())
            return false;
        Collections.sort(a);
        Collections.sort(b);
        for(int i=0;i<a.size();i++){
            if(!a.get(i).equals(b.get(i)))
                return false;
        }
        return true;
    }
}
