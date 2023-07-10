package com.common;

import com.utility.PropertiesUtils;

public class GlobalVariables {

    //project path
    public static String PROJECT_PATH = System.getProperty("user.dir");
    public static String JSON_FILE_PATH = PROJECT_PATH + "/src/test/java/";
    public static String OUTPUT_PATH =  PROJECT_PATH + "/resources/output/";

    //Run parameters
    public static String THREAD_COUNT = PropertiesUtils.getPropValue("threadCount");
    public static String BROWSER = PropertiesUtils.getPropValue("browserName");

    //Defaul wait time
    public static final int WAIT_TIME_60 = 60;

    //URL path
    public static final String THEINTERNET_URL = "https://the-internet.herokuapp.com/";
}
