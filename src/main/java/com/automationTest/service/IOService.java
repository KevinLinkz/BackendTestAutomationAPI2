package com.automationTest.service;


import com.automationTest.utilities.globaldata.MyConfig;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.net.URI;
import java.net.URISyntaxException;
import java.net.URLDecoder;
import java.text.SimpleDateFormat;
import java.util.Date;

public class IOService {
    private static volatile IOService instance = null;
    private IOService() {

    }

    public static IOService getInit() {
        if (instance == null) {
            synchronized (IOService.class) {
                if (instance == null) {
                    instance = new IOService();
                }
            }
        }
        return instance;
    }

    public boolean createTextFile(String strPath, String strBody) {
        try {
            File file = new File(strPath);
            if (!file.exists())
                file.createNewFile();

            FileWriter fileWriter = new FileWriter(strPath, false);
            fileWriter.write(strBody);
            fileWriter.close();
            return true;
        } catch (IOException e) {
            e.printStackTrace();
            return false;
        }
    }

    public void initFolder() {
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("ddMMyyHHmm");
        MyConfig.strPathFolderResultTesting = System.getProperty("user.dir") + "\\Results\\" + simpleDateFormat.format(new Date());
        MyConfig.strPathFolderResultCap = MyConfig.strPathFolderResultTesting + "\\CAP\\";

        File file = new File(MyConfig.strPathFolderResultTesting);
        if (!file.exists()) {
            file.mkdirs();
            System.out.println("Directory Result is created!");
        }

    }

    public String getPathFromResource(String strFileName) {
        String strTemp = "";
        try {
            strTemp = IOService.class.getClassLoader().getResource(strFileName).getPath();

            strTemp = URLDecoder.decode(strTemp, "utf-8");
            strTemp = new File(strTemp).getPath();

        } catch (UnsupportedEncodingException | NullPointerException e) {
            e.printStackTrace();
        }
        return strTemp;
    }

    public URI getURIFromResource(String strFileName) {
        URI uriTemp = null;
        try {
            uriTemp = IOService.class.getClassLoader().getResource(strFileName).toURI();

        } catch (NullPointerException e) {
            e.printStackTrace();
        } catch (URISyntaxException e) {
            e.printStackTrace();
        }
        return uriTemp;
    }
}
