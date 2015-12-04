package com.team4.service;

import java.util.List;

import com.team4.domain.KLXF;
import com.team4.form.KLXFForm;

public interface KLXFService {

	//查询全部职工，并去除重复
	List<String> selectSaler();

	List<KLXF> selectKLXF(KLXFForm form, String power);

}