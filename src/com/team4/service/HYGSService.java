package com.team4.service;

import java.util.List;

import com.team4.domain.HYGS;
import com.team4.form.HYGSForm;

public interface HYGSService {

	//查询全部职工，并去除重复
	List<String> selectSaler();

	//查询合约类型，并去除重复
	List<String> selectCotype();

	List<HYGS> selectHYGS(HYGSForm form, String power);

}