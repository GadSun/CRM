package junit.test;

import com.team4.service.HYGS_HYXYJService;
import com.team4.service.impl.HYGS_HYXYJServiceImpl;

public class HYGS_HYXYJServiceTest {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		String id = "01";
		HYGS_HYXYJService emp = new HYGS_HYXYJServiceImpl();
		System.out.println(emp.select(id).get(0).getName());
	}

}
