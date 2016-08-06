package com.flow.pub.util;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;


public class DateUtil {
	// 每个月的天数
	static private int[] daysInMonth = new int[] { 31, 28, 31, 30, 31, 30, 31,
			31, 30, 31, 30, 31 };

	/**
	 * 日期/时间格式化显示（年、月、日、时、分、秒、毫秒、星期）
	 * 
	 * @param strDate
	 *            需要格式化的日期
	 * @param strOldFormat
	 *            该日期的格式串
	 * @param strNewFormat
	 *            需要格式化的格式串
	 * @return 格式化后的字符串（String）
	 * @throws ParseException
	 */
	public static String msFormatDateTime(String strDate, String strOldFormat,
			String strNewFormat) throws ParseException {
		java.util.Date dtDate = new Date();

		if (strNewFormat.equals("") | strOldFormat.equals(""))
			strNewFormat = "yyyy-MM-dd HH:mm:ss";

		SimpleDateFormat myFormatter = new SimpleDateFormat(strNewFormat);
		myFormatter.setLenient(false);
		dtDate = new SimpleDateFormat(strOldFormat).parse(strDate);
		return myFormatter.format(dtDate.getTime());
	}

	/**
	 * 日期/时间格式化显示（年、月、日、时、分、秒、毫秒、星期）
	 * 
	 * @param dtmDate
	 *            需要格式化的日期（java.util.Date）
	 * @param strFormat
	 *            该日期的格式串
	 * @return 格式化后的字符串（String）
	 */
	public static String msFormatDateTime(java.util.Date dtmDate,
			String strFormat) {

		if (strFormat.equals(""))
			strFormat = "yyyy-MM-dd HH:mm:ss";

		SimpleDateFormat myFormat = new SimpleDateFormat(strFormat);

		return myFormat.format(dtmDate.getTime());
	}

	/**
	 * 取得给定日期数天前（后）的日期函数
	 * 
	 * @param strDate
	 *            需要进行加减的日期("yyyy-MM-dd")
	 * @param intStep
	 *            需要计算的间隔天数
	 * @return 返回计算后的日期（java.util.Date）
	 * @throws ParseException
	 */
	public static java.util.Date msInterDate(String strDate, int intStep)
			throws ParseException {

		String strFormat = "yyyy-MM-dd";
		java.util.Date dtDate = null;
		Calendar cal = Calendar.getInstance();
		SimpleDateFormat myFormatter = new SimpleDateFormat(strFormat);
		myFormatter.setLenient(false);
		dtDate = myFormatter.parse(strDate);

		cal.setTime(dtDate);
		cal.add(Calendar.DAY_OF_MONTH, intStep);

		return cal.getTime();
	}

	/**
	 * 取得给定日期数天前（后）的日期函数
	 * 
	 * @param strDate
	 *            需要进行加减的日期("yyyy-MM-dd")
	 * @param intStep
	 *            需要计算的间隔天数
	 * @return 返回计算后的日期（String）
	 * @throws ParseException
	 */
	public static String msInterDateString(String strDate, int intStep)
			throws ParseException {
		String strFormat = "yyyy-MM-dd";
		java.util.Date dtDate = null;
		Calendar cal = Calendar.getInstance();
		SimpleDateFormat myFormatter = new SimpleDateFormat(strFormat);
		myFormatter.setLenient(false);
		dtDate = myFormatter.parse(strDate);

		cal.setTime(dtDate);
		cal.add(Calendar.DAY_OF_MONTH, intStep);

		return msFormatDateTime(cal.getTime(), "yyyy-MM-dd");
	}

	/**
	 * 日期比较
	 * 
	 * @param strDate1
	 *            需要进行计较的日期1(yyyy-MM-dd)
	 * @param strDate2
	 *            需要进行计较的日期2(yyyy-MM-dd)
	 * @return 比较的结果（int） -1：strDate1 < strDate2 0：strDate1 = strDate2
	 *         1：strDate1 > strDate2
	 * @throws ParseException
	 */
	public static int msCompareDate(String strDate1, String strDate2)
			throws ParseException {
		String strFormat = "yyyy-MM-dd";
		java.util.Date dtDate1 = null;
		java.util.Date dtDate2 = null;
		int intCom = 0;
		SimpleDateFormat myFormatter = new SimpleDateFormat(strFormat);
		myFormatter.setLenient(false);
		dtDate1 = myFormatter.parse(strDate1);
		dtDate2 = myFormatter.parse(strDate2);

		intCom = dtDate1.compareTo(dtDate2);
		if (intCom > 0)
			return 1;
		if (intCom < 0)
			return -1;
		return 0;
	}

	/**
	 * 获得日期的星期
	 * 
	 * @param strDate
	 *            需要计算星期的日期(yyyy-MM-dd)
	 * @return 计算后的星期（int）
	 * @throws ParseException
	 */
	public static int msGetWeeks(String strDate) throws ParseException {
		String strFormat = "yyyy-MM-dd";
		java.util.Date dtDate = null;
		int intDay = 0;
		SimpleDateFormat myFormatter = new SimpleDateFormat(strFormat);
		Calendar cal = Calendar.getInstance();

		dtDate = myFormatter.parse(strDate);
		cal.setTime(dtDate);
		intDay = cal.get(Calendar.DAY_OF_WEEK) - 1;
		if (intDay == 0)
			intDay = 7;
		return intDay;
	}

	/**
	 * 获得给定月份的星期数,以星期日为一周的第一天
	 * 
	 * @param strMonth
	 *            需要计算星期的日期(yyyy-MM-dd)
	 * @return 计算后的星期数（int）
	 * @throws ParseException
	 */
	public static int msGetWeeksofMonth(String strMonth) throws ParseException {
		String strFormat = "yyyy-MM-dd";
		int intDays = msGetDaysofMonth(strMonth);
		String strDays = strMonth.substring(0, 8) + String.valueOf(intDays);
		java.util.Date dtDate = null;
		int intWeeks = 0;
		SimpleDateFormat myFormatter = new SimpleDateFormat(strFormat);
		Calendar cal = Calendar.getInstance();

		dtDate = myFormatter.parse(strDays);

		cal.setTime(dtDate);
		intWeeks = cal.get(Calendar.WEEK_OF_MONTH);

		return intWeeks;
	}

	/**
	 * 获得给定月份的天数
	 * 
	 * @param strMonth
	 *            月份
	 * @return 计算后的天数（int）
	 */
	public static int msGetDaysofMonth(String strMonth) {
		int intReturn = 0;
		int intMonth = StringUtil.msReturnInt(strMonth.substring(5, 7));
		switch (intMonth) {
		case 1:
			intReturn = daysInMonth[0];
			break;
		case 2:
			intReturn = daysInMonth[1];
			break;
		case 3:
			intReturn = daysInMonth[2];
			break;
		case 4:
			intReturn = daysInMonth[3];
			break;
		case 5:
			intReturn = daysInMonth[4];
			break;
		case 6:
			intReturn = daysInMonth[5];
			break;
		case 7:
			intReturn = daysInMonth[6];
			break;
		case 8:
			intReturn = daysInMonth[7];
			break;
		case 9:
			intReturn = daysInMonth[8];
			break;
		case 10:
			intReturn = daysInMonth[9];
			break;
		case 11:
			intReturn = daysInMonth[10];
			break;
		case 12:
			intReturn = daysInMonth[11];
			break;
		}
		int intYear = StringUtil.msReturnInt(strMonth.substring(0, 4));
		if (intYear % 4 == 0 && intYear % 100 != 0 || intYear % 400 == 0) {
			if (intMonth == 2)
				intReturn = 29;

		}
		return intReturn;
	}

	/**
	 * 获取当前日期、时间
	 * 
	 * @return 系统当前的日期/时间（Date）
	 */
	public static java.util.Date msGetCurrentDate() {
		java.util.Date dtDate = new Date();
		return dtDate;
	}

	/**
	 * 返回格式化的当前日期/时间
	 * 
	 * @param strFormat
	 *            格式串
	 * @return 当前日期/时间格式化后的字符串（String）
	 */
	public static String getFormatCurrentDate(String strFormat) {
		return msFormatDateTime(msGetCurrentDate(), strFormat);
	}

	/**
	 * 获得系统时间，格式自定义
	 * 
	 * @param formatter
	 * @return
	 */
	public static String getCurrentTime(String formatter) {
		return DateUtil.getFormatCurrentDate(formatter);
	}

	/**
	 * 取上个工作日
	 * 
	 * @param strFormat
	 *            (yyyy-MM-dd) 日期
	 * @return 上个工作日(yyyy-MM-dd)
	 * @throws ParseException
	 */
	public static String msGetLastWorkDate(String strFormat)
			throws ParseException {
		SimpleDateFormat myFormatter = new SimpleDateFormat("yyyy-MM-dd");
		java.util.Date dtDate = null;
		Calendar cal = Calendar.getInstance();

		dtDate = myFormatter.parse(strFormat);
		cal.setTime(dtDate);
		do {
			cal.add(Calendar.DAY_OF_YEAR, -1);
		} while ((cal.get(Calendar.DAY_OF_WEEK) == 1)
				| (cal.get(Calendar.DAY_OF_WEEK) == 7));
		return myFormatter.format(cal.getTime());
	}

	/**
	 * 取得当前日期数天前（后）的日期函数
	 * 
	 * @param intStep
	 *            间隔天数
	 * @return 计算后的日期（java.util.Date）
	 */
	public static java.util.Date msCurInterDate(int intStep) {
		Calendar cal = Calendar.getInstance();

		cal.setTime(new Date());
		cal.add(Calendar.DAY_OF_MONTH, intStep);

		return cal.getTime();
	}

	/**
	 * 两日期的间隔天数
	 * 
	 * @param strDate1
	 *            需要进行计较的日期1(yyyy-MM-dd)
	 * @param strDate2
	 *            需要进行计较的日期2(yyyy-MM-dd)
	 * @return 间隔秒数（int）
	 * @throws ParseException
	 */
	public static long diffSecond(String strDateBegin, String strDateEnd)
			throws ParseException {
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
		Date dateBegin = sdf.parse(strDateBegin);
		Date dateEnd = sdf.parse(strDateEnd);

		long milliSencods = dateEnd.getTime() - dateBegin.getTime();
		long intDiff = milliSencods / 1000;
		return intDiff;
	}

	/**
	 * 两日期的间隔天数
	 * 
	 * @param strDate1
	 *            需要进行计较的日期1(yyyy-MM-dd)
	 * @param strDate2
	 *            需要进行计较的日期2(yyyy-MM-dd)
	 * @return 间隔天数（int）
	 * @throws ParseException
	 */
	public static int diffDay(String strDateBegin, String strDateEnd)
			throws ParseException {
		long milliSencods = diffSecond(strDateBegin, strDateEnd);
		long intDiff = milliSencods / (60 * 60 * 24);
		return (int) intDiff;
	}

	/**
	 * 获取两个日期相差的月数,按一个月30天算
	 * 
	 * @param date1
	 *            需要进行计较的日期1(yyyy-MM-dd)
	 * @param date2
	 *            需要进行计较的日期2(yyyy-MM-dd)
	 * @return 间隔月数（int）
	 * @throws ParseException
	 */
	public static int diffMonth(String strDateBegin, String strDateEnd)
			throws ParseException {
		long milliSencods = diffSecond(strDateBegin, strDateEnd);
		long intDiff = milliSencods / (60 * 60 * 24 * 30);
		return (int) intDiff;
	}

	/**
	 * 獲取連個日期相差的年數，一年365天算
	 * 
	 * @param strDate1
	 *            需要进行计较的日期1(yyyy-MM-dd)
	 * @param strDate2
	 *            需要进行计较的日期2(yyyy-MM-dd)
	 * @return 间隔年数（int）
	 * @throws ParseException
	 */
	public static int diffYear(String strDateBegin, String strDateEnd)
			throws ParseException {
		long milliSencods = diffSecond(strDateBegin, strDateEnd);
		long intDiff = milliSencods / (60 * 60 * 24 * 365);
		return (int) intDiff;
	}

	/**
	 * 获取两个日期中的较大者
	 * 
	 * @param strDate1
	 *            日期
	 * @param strDate2
	 *            日期
	 * @return 返回较大的日期
	 * @throws ParseException
	 */
	public static String msGetMaxTime(String strDate1, String strDate2)
			throws ParseException {
		int intTemp = msCompareDate(strDate1, strDate2);

		if (intTemp == -2)
			return "日期错误";
		if ((intTemp == 0) || (intTemp == 1)) {
			return strDate1;
		} else {
			return strDate2;
		}
	}

	/**
	 * 取得日期信息
	 * 
	 * @return 当前日期
	 */
	public static String msGetToday() {
		Date curDate = new Date();
		SimpleDateFormat formatDate = new SimpleDateFormat("yyyy年MM月dd日");
		String strDate = formatDate.format(curDate);
		String[] weekdays = new String[] { "", "星期日", "星期一", "星期二", "星期三",
				"星期四", "星期五", "星期六" };
		Calendar cal = Calendar.getInstance();
		strDate = strDate + "&nbsp;&nbsp;"
				+ weekdays[cal.get(Calendar.DAY_OF_WEEK)];

		if (cal.get(Calendar.DAY_OF_WEEK) == 7) {
			strDate = "<font color='#b5ffb5'>" + strDate + "</font>";
		}
		if (cal.get(Calendar.DAY_OF_WEEK) == 1) {
			strDate = "<font color='#ffff80'>" + strDate + "</font>";
		}

		return strDate;
	}

	/**
	 * 取上一天
	 * 
	 * @param strFormat
	 *            (yyyy-MM-dd) 日期
	 * @return 上个工作日(yyyy-MM-dd)
	 * @throws ParseException
	 */
	public static String msGetLastDate(String strFormat) throws ParseException {
		SimpleDateFormat myFormatter = new SimpleDateFormat("yyyy-MM-dd");
		java.util.Date dtDate = null;
		Calendar cal = Calendar.getInstance();

		dtDate = myFormatter.parse(strFormat);

		cal.setTime(dtDate);
		cal.add(Calendar.DAY_OF_YEAR, -1);

		return myFormatter.format(cal.getTime());
	}

	/**
	 * 取上个月
	 * 
	 * @param strFormat
	 *            (yyyy-MM-dd) 日期
	 * @return 上个月(yyyy-MM-dd)
	 * @throws ParseException
	 */
	public static String msGetLastMonth(String strFormat) throws ParseException {
		SimpleDateFormat myFormatter = new SimpleDateFormat("yyyy-MM-dd");
		java.util.Date dtDate = null;
		Calendar cal = Calendar.getInstance();

		dtDate = myFormatter.parse(strFormat);
		cal.setTime(dtDate);
		cal.add(Calendar.MONTH, -1);

		return myFormatter.format(cal.getTime());
	}

	/**
	 * 取得给定日期数月前后的日期函数
	 * 
	 * @param strDate
	 *            需要进行加减的日期("yyyy-MM-dd")
	 * @param intMon
	 *            需要计算的间隔天数
	 * @return 返回计算后的日期（String）
	 * @throws ParseException
	 */
	public static String msDateAfterMonth(String strDate, int intMon)
			throws ParseException {
		String strFormat = "yyyy-MM-dd";
		java.util.Date dtDate = null;
		Calendar cal = Calendar.getInstance();
		SimpleDateFormat myFormatter = new SimpleDateFormat(strFormat);
		myFormatter.setLenient(false);
		dtDate = myFormatter.parse(strDate);
		cal.setTime(dtDate);
		cal.add(Calendar.MONTH, intMon);

		return msFormatDateTime(cal.getTime(), "yyyy-MM-dd");
	}

	/**
	 * 获得昨天
	 * 
	 * @return
	 */
	public static Date getYesterday() {
		Calendar cal = Calendar.getInstance();
		cal.add(Calendar.DATE, -1);
		return cal.getTime();
	}

	/**
	 * 日期格式化
	 * 
	 * @param pattern
	 * @param date
	 * @return
	 */
	public static String getParseDate(String pattern, Date date) {
		SimpleDateFormat sdf = new SimpleDateFormat();
		sdf.applyPattern(pattern);
		return sdf.format(date);
	}

	/**
	 * 转换为日期
	 * 
	 * @param dateStr
	 * @return
	 */

	public static String formTimeString(String[] arr) {
		StringBuffer buf = new StringBuffer();
		if (1 == arr[2].length()) {
			buf.append("0");
			buf.append(arr[2]);
		} else {
			buf.append(arr[2]);
		}
		buf.append(":");
		if (1 == arr[1].length()) {
			buf.append("0");
			buf.append(arr[1]);
		} else {
			buf.append(arr[1]);
		}
		buf.append(":");
		if (1 == arr[0].length()) {
			buf.append("0");
			buf.append(arr[0]);
		} else {
			buf.append(arr[0]);
		}
		return buf.toString();
	}

	/**
	 * 
	 * @param date
	 * @param formatStyle
	 * @return 格式化后的日期字符串
	 */
	public static String toString(Date date, String formatStyle) {
		SimpleDateFormat simple = new SimpleDateFormat(formatStyle);
		return simple.format(date);
	}

	public static String getTimeZone(Calendar cal, int day) {
		String date = "";
		try {
			if (cal.get(Calendar.MONTH) < 11) {
				if (cal.get(Calendar.DATE) + day > cal
						.getActualMaximum(Calendar.DAY_OF_MONTH)) {
					date = cal.get(Calendar.YEAR) + "年"
							+ (formatNum(cal.get(Calendar.MONTH) + 2)) + "月01日";
				} else {
					date = cal.get(Calendar.YEAR) + "年"
							+ (formatNum(cal.get(Calendar.MONTH) + 1)) + "月"
							+ (formatNum(cal.get(Calendar.DATE) + day)) + "日";
				}
			} else {
				if (cal.get(Calendar.DATE) >= cal
						.getActualMaximum(Calendar.DAY_OF_MONTH)) {
					date = (cal.get(Calendar.YEAR) + 1) + "年01月01日";
				} else {
					date = cal.get(Calendar.YEAR) + "年"
							+ (formatNum(cal.get(Calendar.MONTH) + 1)) + "月"
							+ (formatNum(cal.get(Calendar.DATE) + day)) + "日";
				}
			}
		} catch (Exception ex) {
			ex.printStackTrace();
		}
		return date;
	}

	public static Date getTZ(Calendar cal, int day) {
		Date d = new Date();
		SimpleDateFormat sdfTemp = new SimpleDateFormat("yyyy年MM月dd日 HH:mm:ss");
		try {
			d = sdfTemp.parse(getTimeZone(cal, day) + " 00:00:00");
		} catch (ParseException e) {
			e.printStackTrace();
		}
		return d;
	}

	public static String formateDate(Date d) {
		SimpleDateFormat sdfTemp = new SimpleDateFormat("yyyy年MM月dd日");
		return sdfTemp.format(d);
	}

	public static String formatNum(int num) {
		if (num < 10)
			return "0" + num;
		return Integer.valueOf(num).toString();
	}

	/**
	 * 去掉时间的时分秒 生成合同计划表时用
	 * 
	 * @param date
	 * @return
	 */
	public static Date getTimeofDate(Date date) {
		SimpleDateFormat my = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss");
		String str = my.format(date);
		String[] strs = str.split(" ");
		String start = strs[0] + " 00:00:00";
		Date startTime = null;
		try {
			startTime = my.parse(start);
		} catch (ParseException e) {
		}
		return startTime;
	}

	public static List<String> getPayDate(String strDate, int loanTerm) {
		List<String> list = new ArrayList<String>();
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		for (int i = 1; i <= loanTerm; i++) {
			Calendar calendar = Calendar.getInstance();
			Date date2 = null;
			try {
				date2 = sdf.parse(strDate);
			} catch (ParseException e) {
				e.printStackTrace();
			}
			calendar.setTimeInMillis(date2.getTime());
			calendar.add(Calendar.MONTH, i);
			Date date = calendar.getTime();
			list.add(sdf.format(date));

		}
		return list;
	}

	/**
	 * 获取系统当前日期和时间，格式为yyyy-MM-dd HH:mm:ss
	 * 
	 * @return 返回计算后的日期时间（String）
	 */
	public static String getCurrentDateTime() {
		return DateUtil.getFormatCurrentDate("yyyy-MM-dd HH:mm:ss");
	}

	/**
	 * 获取系统当前时间，格式为HH:mm:ss
	 * 
	 * @return 返回计算后的时间（String）
	 */
	public static String getCurrentTime() {
		return DateUtil.getFormatCurrentDate("HH:mm:ss");
	}

	/**
	 * 获取系统当前日期，格式为yyyy-MM-dd
	 * 
	 * @return 返回计算后的日期（String）
	 */
	public static String getCurrentDate() {
		return DateUtil.getFormatCurrentDate("yyyy-MM-dd");
	}

}