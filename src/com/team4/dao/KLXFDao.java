package com.team4.dao;

import java.util.List;

import com.team4.domain.KLXF;
import com.team4.form.KLXFForm;

public interface KLXFDao {

	List<KLXF> select(KLXFForm klxff);

}