package junit.test;

import com.team4.service.HYGS_DFPEService;
import com.team4.service.impl.HYGS_DFPEServiceImpl;

public class HYGS_DFPEServiceTest {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		String id = "01";
		HYGS_DFPEService emp = new HYGS_DFPEServiceImpl();
			System.out.println(emp.select(id).get(0).getId());
	}

}
