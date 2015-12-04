package junit.test;

import java.util.ArrayList;
import java.util.List;

import org.junit.Test;

import com.team4.domain.KL;
import com.team4.form.KLForm;
import com.team4.service.KLService;
import com.team4.service.impl.KLServiceImpl;

public class KLServiceTest {

	/**
	 * @param args
	 */
	@Test
	public static void main(String[] args){
		List<KL> listForKL = new ArrayList<KL>();
		List<String> listForSaler = new ArrayList<String>();
		List<String> listForContractname = new ArrayList<String>();
		KLService kls = new KLServiceImpl();
		KLForm klf = new KLForm();
		listForKL = kls.selectKL(klf, "2");
		listForSaler = kls.selectSaler();
		listForContractname = kls.selectContractName();
		System.out.println(listForKL.size());
		System.out.println(listForSaler.size());
		System.out.println(listForContractname.size());
	}
}
