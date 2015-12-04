package junit.test;

import java.util.ArrayList;
import java.util.List;

import org.junit.Test;

import com.team4.dao.KLXFDao;
import com.team4.dao.impl.KLXFDaoImpl;
import com.team4.domain.KLXF;
import com.team4.form.KLXFForm;

public class KLXFDaoTest {

	/**
	 * @param args
	 */
	@Test
	public static void main(String[] args) {
		// TODO Auto-generated method stub

		KLXFForm klxff = new KLXFForm();
		List<KLXF> list = new ArrayList<KLXF>();
		KLXFDao klxfd = new KLXFDaoImpl();
		list = klxfd.select(klxff);
		System.out.println(list.get(0).getBath_cost());
	}

}
