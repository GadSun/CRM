package junit.test;

import com.team4.service.HYGS_HYKPService;
import com.team4.service.impl.HYGS_HYKPServiceImpl;

public class HYGS_HYKPServiceTest {

	public static void main(String[] args){
		String id = "01";
		HYGS_HYKPService emp = new HYGS_HYKPServiceImpl();
			System.out.println(emp.select(id).get(0).getId());
	}
}
