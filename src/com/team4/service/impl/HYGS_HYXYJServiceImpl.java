package com.team4.service.impl;

import java.util.ArrayList;
import java.util.List;

import com.team4.dao.HYGS_HYXYJDao;
import com.team4.dao.impl.HYGS_HYXYJDaoImpl;
import com.team4.domain.HYGS_DFPE;
import com.team4.domain.HYGS_HYXYJ;
import com.team4.service.HYGS_HYXYJService;

public class HYGS_HYXYJServiceImpl implements HYGS_HYXYJService {
	public List<HYGS_HYXYJ> select(String id) {
		HYGS_HYXYJDao dao = new HYGS_HYXYJDaoImpl();
		// password = ServiceUntil.md5(password);md5加密
		List<HYGS_HYXYJ> list = new ArrayList<HYGS_HYXYJ>();
		try {
			list = dao.select(id);
		} catch (Exception e) {
			// TODO: handle exception
			throw new RuntimeException(e);
		}
		return list;
	}
}
