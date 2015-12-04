package junit.test;

import java.util.ArrayList;
import java.util.List;

import com.team4.dao.HYGS_HYKPDao;
import com.team4.dao.impl.HYGS_HYKPDaoImpl;
import com.team4.domain.HYGS_HYKP;

public class HYGS_HYKPDaoTest {

	public static void main(String[] args) {
		HYGS_HYKPDao hykpd = new HYGS_HYKPDaoImpl();
		String id = "/*-";
		List<HYGS_HYKP> list = new ArrayList<HYGS_HYKP>();
		list = hykpd.select(id);
		System.out.println(list);
	}

}
