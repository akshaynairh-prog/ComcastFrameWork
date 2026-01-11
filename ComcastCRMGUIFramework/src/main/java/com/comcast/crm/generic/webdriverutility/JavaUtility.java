package com.comcast.crm.generic.webdriverutility;


import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.Random;


public class JavaUtility {
public int getRandomNumber() {
	Random random=new Random();
	return (random.nextInt(100000));
}

public String getSystemdateYYYYMMDD() {
	Date dateobj=new Date();
	
	SimpleDateFormat sdf=new SimpleDateFormat("yyyy-MM-dd");
	return(sdf.format(dateobj));
}
public String getRequiredDateYYYYMMDD(int days) {

	Date date=new Date();
	SimpleDateFormat sdf=new SimpleDateFormat("yyyy-MM-dd");
	
	sdf.format(date);
	Calendar cal=sdf.getCalendar();
	cal.add(Calendar.DAY_OF_MONTH,days);
	return(sdf.format(cal.getTime()));
}

}
