package com.gar.resource.constants;

public class GarConstants {

    private GarConstants() {
    }

    // Regex for acceptable logins
    public static final String LOGIN_REGEX = "^[_'.@A-Za-z0-9-]*$";

    /** PACKAGES NAME **/
    public final static String BASE_PACKAGE_NAME = "com.gar.resource";
    public final static String MODEL_PACKAGE_NAME = "com.gar.resource.domain";
    public final static String DAO_PACKAGE_NAME = "com.gar.resource.repository";

    /** TABLES NAME **/
    public final static String GAR_USER_TABLE_NAME = "GAR_USER";
    public final static String GAR_AUTHORITY_TABLE_NAME = "GAR_AUTHORITY";
    public final static String GAR_AUDIT_EVENT_TABLE_NAME = "GAR_AUDIT_EVENT";
    public final static String GAR_TASK_HISTORY_TABLE_NAME = "GAR_TASK_HISTORY";
    public final static String GAR_OPERATION_HISTORY_TABLE_NAME = "GAR_OPERATION_HISTORY";

    /** SCHEMA **/
    public final static String GAR_SCHEMA = "GAR";
    
    public static final String SYSTEM_ACCOUNT = "system";
    public static final String ANONYMOUS_USER = "anonymoususer";
    public static final String CHECK_ERROR_MESSAGE = "Incorrect password";

    public static final int PASSWORD_MIN_LENGTH = 4;
    public static final int PASSWORD_MAX_LENGTH = 100;

    public static final String ADMIN = "ROLE_ADMIN";
    public static final String USER = "ROLE_USER";
    public static final String ANONYMOUS = "ROLE_ANONYMOUS";

    public static final String APPLICATION_NAME = "garApp";
}
