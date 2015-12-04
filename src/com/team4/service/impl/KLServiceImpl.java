package com.team4.service.impl;

import java.util.ArrayList;
import java.util.List;

import com.team4.dao.KLDao;
import com.team4.dao.impl.KLDaoImpl;
import com.team4.domain.KL;
import com.team4.form.KLForm;
import com.team4.service.KLService;
import com.team4.until.DateUntil;
import com.team4.until.ListUntil;

public class KLServiceImpl implements KLService {
	// 查询全部职工，并去除重复
	public List<String> selectSaler() {
		KLForm klf = new KLForm();
		List<String> list = new ArrayList<String>();
		List<KL> kllist = new ArrayList<KL>();
		KLDao kld = new KLDaoImpl();
		kllist = kld.select(klf);
		for (int i = 0; i < kllist.size(); i++) {

			list.add(kllist.get(i).getSaler());
		}
		return ListUntil.getNoRepeat(list, String.class);
	}

	// 查询全部合约名称，并去除重复
	public List<String> selectContractName() {
		KLForm klf = new KLForm();
		List<String> list = new ArrayList<String>();
		List<KL> kllist = new ArrayList<KL>();
		KLDao kld = new KLDaoImpl();
		kllist = kld.select(klf);
		for (int i = 0; i < kllist.size(); i++) {

			list.add(kllist.get(i).getContractname());
		}
		return ListUntil.getNoRepeat(list, String.class);
	}

	public List<KL> selectKL(KLForm klf, String power) {

		List<KL> list = new ArrayList<KL>();
		KLDao kld = new KLDaoImpl();
		list = kld.select(klf);
		/*
		 * power = 1可以查询当天数据 86400s power = 2 可以查询1个月数据 2592000s power =
		 * 3可以查询全部数据
		 */
		if (power.equals("1")) {
			for (int i = 0; i < list.size(); i++) {
				long dateTime = Long.parseLong(list.get(i).getDate());
				long localtionTime = DateUntil.getLocationTime() / 1000;
				if (localtionTime - dateTime > 86400) {
					list.remove(i);
					i--;
				}
			}
		} else if (power.equals("2")) {
			for (int i = 0; i < list.size(); i++) {
				long dateTime = Long.parseLong(list.get(i).getDate());
				long localtionTime = DateUntil.getLocationTime() / 1000;
				if (localtionTime - dateTime > 2592000) {
					list.remove(i);
					i--;
				}
			}
		} else if (power.equals("3")) {
			return list;
		} else {
			throw new RuntimeException();
		}

		return list;
	}
}
