package junit.test;

import java.util.ArrayList;
import java.util.List;

import com.team4.dao.HYGS_HYXYJDao;
import com.team4.dao.impl.HYGS_HYXYJDaoImpl;
import com.team4.domain.HYGS_HYXYJ;

public class HYGS_HYXYJDaoTest {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub

		HYGS_HYXYJDao hyxyjd = new HYGS_HYXYJDaoImpl();
		String id = "01";
		List<HYGS_HYXYJ> list = new ArrayList<HYGS_HYXYJ>();
		list = hyxyjd.select(id);
		System.out.println(list.get(0).getName());
	}

}
