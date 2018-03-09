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
 * ##Utility 클래스
 * -- 직접 객체 생성할 수 없도록 생성자 정의
 * -- 객체 생성없이 사용가능한 메서드로 구성
 *
 */
public class MyUtility {
	
	private MyUtility() {	
	}

	public static String convertSecureString(String data) {
		String preData = data.substring(1, data.length()); // (a, b) a번째 인덱스에서 b-1번째 인덱스만 추출! 
		System.out.println(preData);
		StringBuilder convertData = new StringBuilder();
		for(int index = 0 ; index < preData.length() ; index++) {
			convertData.append("*");
		}
		
		return convertData.toString();
	}
	
	/*1
	 * ## 4자리 임의의 숫자형식의 문자열 반환 메서드
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
	 * ## 아규먼트로 전달받은 자릿수로 임의의 숫자형식의 문자열 반환 메서드
	 * ## 중복정의
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
	 * 현재날짜를 년도4.월2.일2. 자리 형식의 문자열 반환 메서드
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
	 * 현재날짜를 아규먼트로 전달받은 형식의 문자열 반환 메서드
	 * 중복정의
	 */
	public static String dateGenerator(String weartherFormat) {
		SimpleDateFormat simpleDateFormat = new SimpleDateFormat (weartherFormat, Locale.KOREA );
		Date currentTime = new Date ();
		String time = simpleDateFormat.format ( currentTime );
		return time;
	}
	/* 5.
	 * 정수형 데이터를 아규먼트로 전달받아서 천단위마다 컴마 표기한 문자열 반환
	 * @see java.text.NumberFormat 
	 */
	public static String commaGenerator(int number) {
	
		String commaNumber = NumberFormat.getInstance().format(number);

		return commaNumber;
	}
	/*6
	 * 정수형 데이터를 아규먼트로 전달받아서
	 * 맨앞에 기본통화단위(원) 기호, 천단위마다 컴마 표기한 문자열 반환  
	 */
	public static String commaAndGenerator(int number) {
		NumberFormat mNumberFormat = NumberFormat.getCurrencyInstance(Locale.KOREA);
		return mNumberFormat.format(number);
	}
	
	/*7
	 * 정수형 데이터를 로케일을 아규먼트로 전달받아서
	 * 맨앞에 아규먼트로 전달받은 로케일의 통화단위 기호, 천단위마다 컴마 표기한 문자열 반환
	 * @see java.util.Locale
	 */
	public static String commaAndGenerator(int number, Locale locale) {
		
		DecimalFormatSymbols decimalFormatSymbols = new DecimalFormatSymbols(locale);
		String commaNumber = decimalFormatSymbols.getCurrencySymbol();
		commaNumber += NumberFormat.getInstance().format(number);
		
		return commaNumber;
	}
}
