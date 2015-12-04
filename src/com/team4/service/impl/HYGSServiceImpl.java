package com.team4.service.impl;

import java.util.ArrayList;
import java.util.List;

import com.team4.dao.HYGSDao;
import com.team4.dao.KLXFDao;
import com.team4.dao.impl.HYGSDaoImpl;
import com.team4.dao.impl.KLXFDaoImpl;
import com.team4.domain.HYGS;
import com.team4.domain.KLXF;
import com.team4.form.HYGSForm;
import com.team4.service.HYGSService;
import com.team4.until.DateUntil;
import com.team4.until.ListUntil;

public class HYGSServiceImpl implements HYGSService {

	//查询全部职工，并去除重复
	public List<String> selectSaler(){
		HYGSForm hygsf = new HYGSForm();
		HYGSDao hygsd = new HYGSDaoImpl();
		List<HYGS> hygslist = new ArrayList<HYGS>();
		List<String> list = new ArrayList<String>();
		hygslist = hygsd.select(hygsf);
		for(int i = 0;i<hygslist.size();i++){
			list.add(hygslist.get(i).getSaler());
		}
		return ListUntil.getNoRepeat(list, String.class);
	}
	
	//查询合约类型，并去除重复
	public List<String> selectCotype(){
		HYGSForm hygsf = new HYGSForm();
		HYGSDao hygsd = new HYGSDaoImpl();
		List<HYGS> hygslist = new ArrayList<HYGS>();
		List<String> list = new ArrayList<String>();
		hygslist = hygsd.select(hygsf);
		for(int i = 0;i<hygslist.size();i++){
			list.add(hygslist.get(i).getCotype());
		}
		return ListUntil.getNoRepeat(list, String.class);
	}
	
	public List<HYGS> selectHYGS(HYGSForm form,String power){
		List<HYGS> list = new ArrayList<HYGS>();
		HYGSDao hygsd = new HYGSDaoImpl();
		list = hygsd.select(form);
		long dateTime;
		/*
		 * power = 1可以查询当天数据 86400s power = 2 可以查询1个月数据 2592000s power =
		 * 3可以查询全部数据
		 */
		if (power.equals("1")) {
			for (int i = 0; i < list.size(); i++) {
				try {
					dateTime = DateUntil.dateToLong(list.get(i).getBedate());
				} catch (Exception e) {
					// TODO: handle exception
					throw new RuntimeException(e);
				}
				long localtionTime = DateUntil.getLocationTime() / 1000;
				if (localtionTime - dateTime > 86400) {
					list.remove(i);
					i--;
				}
			}
		} else if (power.equals("2")) {
			for (int i = 0; i < list.size(); i++) {
				try {
					dateTime = DateUntil.dateToLong(list.get(i).getBedate());
				} catch (Exception e) {
					// TODO: handle exception
					throw new RuntimeException(e);
				}
				long localtionTime = DateUntil.getLocationTime() / 1000;
				if (localtionTime - dateTime > 2592000) {
					list.remove(i);
					i--;
				}
			}
		} else if (power.equals("3")) {
			return list;
		} else{
			throw new RuntimeException();
		}
		return list;
	}
}
