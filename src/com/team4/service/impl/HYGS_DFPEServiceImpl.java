package com.team4.service.impl;

import java.util.ArrayList;
import java.util.List;

import com.team4.dao.HYGS_DFPEDao;
import com.team4.dao.impl.HYGS_DFPEDaoImpl;
import com.team4.domain.HYGS_DFPE;
import com.team4.service.HYGS_DFPEService;

public class HYGS_DFPEServiceImpl implements HYGS_DFPEService {

	public List<HYGS_DFPE> select(String id) {
		HYGS_DFPEDao dao = new HYGS_DFPEDaoImpl();
		// password = ServiceUntil.md5(password);md5加密
		List<HYGS_DFPE> list = new ArrayList<HYGS_DFPE>();
		try {
			list = dao.select(id);
		} catch (Exception e) {
			// TODO: handle exception
			throw new RuntimeException(e);
		}
		return list;
	}
}
