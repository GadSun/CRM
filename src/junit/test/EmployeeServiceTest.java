package junit.test;

import org.junit.Test;

import com.team4.domain.Employee;
import com.team4.service.impl.EmployeeServiceImpl;

public class EmployeeServiceTest {

	/**
	 * @param args
	 */
	@Test
	public static void main(String[] args) {
		// TODO Auto-generated method stub

		String id = "123";
		String password = "zhuang11";
		EmployeeServiceImpl emp = new EmployeeServiceImpl();
		if (emp.login(id, password).size() != 0) {
			System.out.println(emp.login(id, password).get(0).getId());
		}
	}

}
