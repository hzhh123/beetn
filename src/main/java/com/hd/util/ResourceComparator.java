package com.hd.util;

import java.util.Comparator;

import com.hd.entity.Resource;

/**
 * @author hzhh123
 * @time 2018年1月23日下午3:55:52
 **/
public class ResourceComparator implements Comparator<Resource> {

	@Override
	public int compare(Resource o1, Resource o2) {
		if (o1.getSeq() > o2.getSeq())
			return 1;
		else if (o1.getSeq() == o2.getSeq()) {
			if (o1.getId() > o2.getId())
				return 1;
			else if (o1.getSeq() == o2.getSeq())
				return 0;
			else
				return -1;
		} else
			return -1;
	}

}
