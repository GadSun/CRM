package junit.test;

import java.util.ArrayList;
import java.util.List;

import com.team4.dao.HYGSDao;
import com.team4.dao.impl.HYGSDaoImpl;
import com.team4.domain.HYGS;
import com.team4.form.HYGSForm;

public class HYGSDaoTest {

	public static void main(String[] args){
		HYGSForm hygsf = new HYGSForm();
		HYGSDao hygsd = new HYGSDaoImpl();
		
		List<HYGS> list = new ArrayList<HYGS>();
		list = hygsd.select(hygsf);
		System.out.println(list.get(0).getConame());
	}
	
}
