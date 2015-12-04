package junit.test;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

import com.team4.until.DateUntil;

public class UntilTest {

	/**
	 * @param args
	 * @throws ParseException 
	 */
	public static void main(String[] args) throws ParseException {
		// TODO Auto-generated method stub
		
		String b = DateUntil.longToDate(1445270400);
		System.out.println(b);
	   }
}
