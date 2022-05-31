package com.automationTest.service;


import com.automationTest.report.ExtentTestFactory;
import com.automationTest.utilities.globaldata.MyConfig;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class LogsService {
    private static volatile LogsService instance = null;
    private LogsService() {

    }

    public static LogsService getInit() {
        if (instance == null) {
            synchronized (LogsService.class) {
                if (instance == null) {
                    instance = new LogsService();
                }
            }
        }
        return instance;
    }

    public void appendLog(String strStatus, String strTextLog, String strScreenShot) {
        String strPathLog = createLog();

        appendTxtLog(strStatus, strTextLog,strPathLog);
        ExtentTestFactory.getInit().setStatusTest(strStatus, strTextLog, strScreenShot);

    }

    private synchronized void appendTxtLog( String strStatus, String strTextLog, String strPathLog) {
        try {
            SimpleDateFormat simpleDateFormat = new SimpleDateFormat("dd-MM-yyyy HH:mm:ss");

            FileWriter fileWriter = new FileWriter(strPathLog, true);
            synchronized (fileWriter) {
                fileWriter.write(simpleDateFormat.format(new Date()) + "   " + strStatus + "   " + strTextLog + "\n");
                fileWriter.close();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

    }

    private  String createLog() {
        String strPathLog = MyConfig.strPathFolderResultTesting + "\\log.txt";
        try {
            File file = new File(strPathLog);
            if (!file.isFile())
                file.createNewFile();

        } catch (IOException e) {
            e.printStackTrace();
        }
        return strPathLog;

    }

}
