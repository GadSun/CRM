package junit.test;

import java.util.ArrayList;
import java.util.List;

import com.team4.dao.HYGS_DFPEDao;
import com.team4.dao.impl.HYGS_DFPEDaoImpl;
import com.team4.domain.HYGS_DFPE;

public class HYGS_DFPEDaoTest {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub

		HYGS_DFPEDao hykpd = new HYGS_DFPEDaoImpl();
		String id = "01";
		List<HYGS_DFPE> list = new ArrayList<HYGS_DFPE>();
		list = hykpd.select(id);
		System.out.println(list.get(0).getFitype());
	}

}
