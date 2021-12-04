package edu.fa.constant;
//constant used in project
public class Constant {

//Account role----------------------------------------------------------
	public static final String ACCOUNT_ROLE_ADMIN = "ROLE_ADMIN";
	public static final String ACCOUNT_ROLE_TRAINER = "ROLE_TRAINER";
	public static final String ACCOUNT_ROLE_TRAINEE = "ROLE_TRAINEE";
//Account status--------------------------------------------------------
	public static final int ACCOUNT_ACTIVE_STATUS = 1;
	public static final int ACCOUNT_DELETE_STATUS = 2;
	public static final int ACCOUNT_INACTIVE_STATUS = 0;
//Account gender--------------------------------------------------------
	public static final int ACCOUNT_GENDER_MALE = 1;
	public static final int ACCOUNT_GENDER_FEMALE = 2;
//Course status---------------------------------------------------------	
	public static final int COURSE_STATUS_DENIED = -1; 
	public static final int COURSE_STATUS_INACTIVE = 0;
	public static final int COURSE_STATUS_WAITING = 1;
	public static final int COURSE_STATUS_STARTED = 2;
	public static final int COURSE_STATUS_ENDED = 3;
//Course calendar-------------------------------------------------------
	public static final int CALENDAR_DAY_EVEN = 1;
	public static final int CALENDAR_DAY_ODD = 2;
	public static final int CALENDAR_TIME_MORNING = 1;
	public static final int CALENDAR_TIME_AFTERNOON = 2;
	public static final int CALENDAR_TIME_EVENING = 3;
//Paging----------------------------------------------------------------
	public static final int TOTAL_CONTENT_PER_PAGE = 10;
	public static  String PHANTRAM = "0%";
	public static  String PHANTRAMTEACHER = "0%";

}
