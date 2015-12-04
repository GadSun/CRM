package junit.test;

import org.junit.Test;

import com.team4.dao.KLDao;
import com.team4.dao.impl.KLDaoImpl;
import com.team4.form.KLForm;

public class KLDaoTest {

	/**
	 * @param args
	 */
	@Test
	public static void main(String[] args) {
		// TODO Auto-generated method stub

		KLForm klf = new KLForm();
		String box[] = {"pinyin"};
		klf.setBox(box);
		klf.setSaler("wpy");
		KLDao kldi = new KLDaoImpl();

		
		
		System.out.println(kldi.select(klf).get(0).getId());
	}

}
