/**
 * <pre>
 * @author DONGHYUNLEE HANAJUNG YOUNGHWANGBO
 * @version ver.1.0
 * @since jdk1.8
 * </pre>
 */
package work.util;
import java.text.DecimalFormatSymbols;
import java.text.NumberFormat;
import java.text.SimpleDateFormat;
import java.util.Currency;
import java.util.Date;
import java.util.Locale;
import java.util.Random;


/**
 * ##Utility Ŭ����
 * -- ���� ��ü ������ �� ������ ������ ����
 * -- ��ü �������� ��밡���� �޼���� ����
 *
 */
public class MyUtility {
	
	private MyUtility() {	
	}

	public static String convertSecureString(String data) {
		String preData = data.substring(1, data.length()); // (a, b) a��° �ε������� b-1��° �ε����� ����! 
		System.out.println(preData);
		StringBuilder convertData = new StringBuilder();
		for(int index = 0 ; index < preData.length() ; index++) {
			convertData.append("*");
		}
		
		return convertData.toString();
	}
	
	/*1
	 * ## 4�ڸ� ������ ���������� ���ڿ� ��ȯ �޼���
	 * @see java.util.Random
	 * @see java.lang.System
	 * @see java.lang.StringBuffer
	 * @see java.lang.stringBuilder
	 */
	public static StringBuffer randomGenerator() {
		StringBuffer randomNumber = new StringBuffer();
		Random rnd = new Random();
		randomNumber.append(rnd.nextInt(10))
		.append(rnd.nextInt(10))
		.append(rnd.nextInt(10))
		.append(rnd.nextInt(10));
		
		return randomNumber;
	}
	
	/*2
	 * ## �ƱԸ�Ʈ�� ���޹��� �ڸ����� ������ ���������� ���ڿ� ��ȯ �޼���
	 * ## �ߺ�����
	 */
	public static StringBuffer randomGenerator(int digitOfNumber) {
		StringBuffer randomNumber = new StringBuffer();
		Random rnd = new Random();
		
		for(int index = 0; index < digitOfNumber ; index++) {
			randomNumber.append(rnd.nextInt(10));
		}
		return randomNumber;
	}
	
	/*3
	 * ���糯¥�� �⵵4.��2.��2. �ڸ� ������ ���ڿ� ��ȯ �޼���
	 * @see java.util.Date
	 * @see java.text.SimpleDateFormat
	 * @see java.util.Locale
	 */
	public static String dateGenerator() {
		SimpleDateFormat simpleDateFormat = new SimpleDateFormat ( "yyyy.MM.dd hh:mm", Locale.KOREA );
		Date currentTime = new Date ();
		String time = simpleDateFormat.format ( currentTime );
		return time;
	}
	
	/*4
	 * ���糯¥�� �ƱԸ�Ʈ�� ���޹��� ������ ���ڿ� ��ȯ �޼���
	 * �ߺ�����
	 */
	public static String dateGenerator(String weartherFormat) {
		SimpleDateFormat simpleDateFormat = new SimpleDateFormat (weartherFormat, Locale.KOREA );
		Date currentTime = new Date ();
		String time = simpleDateFormat.format ( currentTime );
		return time;
	}
	/* 5.
	 * ������ �����͸� �ƱԸ�Ʈ�� ���޹޾Ƽ� õ�������� �ĸ� ǥ���� ���ڿ� ��ȯ
	 * @see java.text.NumberFormat 
	 */
	public static String commaGenerator(int number) {
	
		String commaNumber = NumberFormat.getInstance().format(number);

		return commaNumber;
	}
	/*6
	 * ������ �����͸� �ƱԸ�Ʈ�� ���޹޾Ƽ�
	 * �Ǿտ� �⺻��ȭ����(��) ��ȣ, õ�������� �ĸ� ǥ���� ���ڿ� ��ȯ  
	 */
	public static String commaAndGenerator(int number) {
		NumberFormat mNumberFormat = NumberFormat.getCurrencyInstance(Locale.KOREA);
		return mNumberFormat.format(number);
	}
	
	/*7
	 * ������ �����͸� �������� �ƱԸ�Ʈ�� ���޹޾Ƽ�
	 * �Ǿտ� �ƱԸ�Ʈ�� ���޹��� �������� ��ȭ���� ��ȣ, õ�������� �ĸ� ǥ���� ���ڿ� ��ȯ
	 * @see java.util.Locale
	 */
	public static String commaAndGenerator(int number, Locale locale) {
		
		DecimalFormatSymbols decimalFormatSymbols = new DecimalFormatSymbols(locale);
		String commaNumber = decimalFormatSymbols.getCurrencySymbol();
		commaNumber += NumberFormat.getInstance().format(number);
		
		return commaNumber;
	}
}
