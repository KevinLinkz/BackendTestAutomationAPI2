package com.automationTest.service;

import com.automationTest.utilities.globaldata.MyConfig;

import java.io.File;
import java.io.IOException;

public class ScreenshotService {
    private static volatile ScreenshotService instance = null;
    ThreadLocal<Integer> trdIntCounterData = new ThreadLocal<>();
    private ScreenshotService() {

    }

    public static ScreenshotService getInit() {
        if (instance == null) {
            synchronized (ScreenshotService.class) {
                if (instance == null) {
                    instance = new ScreenshotService();
                }
            }
        }
        return instance;
    }
    public Integer getCounterData() {
        return trdIntCounterData.get();
    }

    public void setCounterdata(Integer intCounterData) {
        trdIntCounterData.set(intCounterData);
    }

    //Screenshot Notepad use AHK
    public String screenShotNotepadAHK() {
        String strScreenshotFullPathName = "";
        String strPathCapture = IOService.getInit().getPathFromResource("AutoHotKey/pathNotepad.txt");
        strScreenshotFullPathName = MyConfig.strPathFolderResultCap + "ScreenShot_" + getCounterData() + ".png";
        IOService.getInit().createTextFile(strPathCapture, strScreenshotFullPathName);
        setCounterdata(getCounterData() + 1);

        try {
            Runtime.getRuntime().exec(IOService.getInit().getPathFromResource("AutoHotKey/NotepadScreenShot.exe"), null, new File("C:\\"));
            Thread.sleep(2000);
        } catch (IOException e) {
            e.printStackTrace();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        return strScreenshotFullPathName;

    }



}
