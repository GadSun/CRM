package junit.test;

import java.util.ArrayList;
import java.util.List;

import com.team4.domain.HYGS;
import com.team4.form.HYGSForm;
import com.team4.service.HYGSService;
import com.team4.service.impl.HYGSServiceImpl;

public class HYGSServiceTest {

	public static void main(String[] args){
		List<String> listForSaler = new ArrayList<String>();
		List<String> listForCotype = new ArrayList<String>();
		List<HYGS> list = new ArrayList<HYGS>();
		String power = null;
		HYGSService hygss = new HYGSServiceImpl();
		HYGSForm klxff = new HYGSForm();
		list = hygss.selectHYGS(klxff, power);
		listForSaler = hygss.selectSaler();
		listForCotype = hygss.selectCotype();
		System.out.println(list.size());
		System.out.println(listForSaler.size());
		System.out.println(listForCotype.size());
	}
}
