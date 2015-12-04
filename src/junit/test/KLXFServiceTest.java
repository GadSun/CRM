package junit.test;

import java.util.ArrayList;
import java.util.List;

import org.junit.Test;

import com.team4.domain.KLXF;
import com.team4.form.KLXFForm;
import com.team4.service.KLXFService;
import com.team4.service.impl.KLXFServiceImpl;

public class KLXFServiceTest {

	/**
	 * @param args
	 */
	@Test
	public static void main(String[] args) {
		// TODO Auto-generated method stub

		List<String> listForSaler = new ArrayList<String>();
		List<String> listForContractName = new ArrayList<String>();
		List<KLXF> list = new ArrayList<KLXF>();
		KLXFService klxfs = new KLXFServiceImpl();
		String power = "1";
		KLXFForm klxff = new KLXFForm();
		list = klxfs.selectKLXF(klxff, power);
		listForSaler = klxfs.selectSaler();
		System.out.println(list.size());
		System.out.println(listForSaler.size());
		System.out.println(listForContractName.size());
	}

}
