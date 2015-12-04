package com.team4.dao;

import java.util.List;

import com.team4.domain.HYGS;
import com.team4.form.HYGSForm;

public interface HYGSDao {

	List<HYGS> select(HYGSForm hygsf);

}