package com.team4.service;

import java.util.List;

import com.team4.domain.KL;
import com.team4.form.KLForm;

public interface KLService {

	//查询全部职工，并去除重复
	List<String> selectSaler();

	//查询全部合约名称，并去除重复
	List<String> selectContractName();

	List<KL> selectKL(KLForm klf, String power);

}