package com.ebay.utils;

import java.io.File;
import java.io.FileReader;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.TimeZone;

import com.relevantcodes.extentreports.ExtentReports;

public class ExtentManager {
	private static ExtentReports extent;
	 
    public synchronized static ExtentReports getReporter(){
        if(extent == null){
            //Set HTML reporting file location
            String workingDir = System.getProperty("user.dir");
            extent = new ExtentReports(System.getProperty("user.dir")+"//ExtentReport//ExtentReportResults_"+getDateTime()+".html", true);
            extent.loadConfig(new File("extent-config.xml"));
        }
        return extent;
    }
    
    private  final static String getDateTime()  
    {  
        DateFormat df = new SimpleDateFormat("yyyy-MM-dd_hhmmss");  
        df.setTimeZone(TimeZone.getTimeZone("PST"));  
        return df.format(new Date());  
    } 
}
