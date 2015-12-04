package com.team4.dao;

import java.util.List;

import com.team4.domain.KL;
import com.team4.form.KLForm;

public interface KLDao {

	List<KL> select(KLForm klf);

}