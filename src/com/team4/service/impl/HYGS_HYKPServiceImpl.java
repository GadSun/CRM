package com.team4.service.impl;

import java.util.ArrayList;
import java.util.List;

import com.team4.dao.HYGS_HYKPDao;
import com.team4.dao.impl.HYGS_HYKPDaoImpl;
import com.team4.domain.HYGS_HYKP;
import com.team4.service.HYGS_HYKPService;

public class HYGS_HYKPServiceImpl implements HYGS_HYKPService {

	public List<HYGS_HYKP> select(String id) {
		HYGS_HYKPDao dao = new HYGS_HYKPDaoImpl();
		// password = ServiceUntil.md5(password);md5加密
		List<HYGS_HYKP> list = new ArrayList<HYGS_HYKP>();
		try {
			list = dao.select(id);
		} catch (Exception e) {
			// TODO: handle exception
			throw new RuntimeException(e);
		}
		return list;
	}
}
