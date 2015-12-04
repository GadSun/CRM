package junit.test;

import java.util.ArrayList;
import java.util.List;

import org.junit.Test;

import com.team4.dao.EmployeeDao;
import com.team4.dao.impl.EmployeeDaoImpl;
import com.team4.domain.Employee;

public class EmployeeDaoTest {

	/**
	 * @param args
	 */

	@Test
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		String id = "123";
		String password = "zhuang11";
		EmployeeDao emp = new EmployeeDaoImpl();
		List<Employee> list = new ArrayList<Employee>();
		list = emp.find(id, password);
		if (list.isEmpty() == false) {
			System.out.println(list.size());
			System.out.println(list.get(0).getId());
		} else {
			System.out.println("empty");
		}
	}

}
