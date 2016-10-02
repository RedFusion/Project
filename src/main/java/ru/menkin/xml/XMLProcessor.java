package ru.menkin.xml;

import org.apache.log4j.*;
import org.springframework.context.*;
import org.springframework.context.support.*;
import ru.menkin.xml.data.*;
import ru.menkin.xml.oxm.*;

import java.io.*;

public class XMLProcessor {
    private static Logger logger = Logger.getLogger(XMLProcessor.class);
    private static final String file = "D:\\Temp\\Report.xml";
    private OXMapper oxmMapper;

    public void setOxmMapper(OXMapper oxmMapper) {
        this.oxmMapper = oxmMapper;
    }

    public static void main(String[] args) {
        ApplicationContext appContext = new ClassPathXmlApplicationContext("applicationContext.xml");
        XMLProcessor xmlProcessor = (XMLProcessor) appContext.getBean("XMLProcessor");
        byte[] xmlData = xmlProcessor.getFile();
        xmlProcessor.parseXML(xmlData);
    }

    public byte[] getFile() {
        byte[] xmlData = new byte[] {};
        try {
            File file = new File(XMLProcessor.file);
            FileInputStream fis = new FileInputStream(file);
            xmlData = new byte[(int) file.length()];
            fis.read(xmlData);
            fis.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return xmlData;
    }

    public void parseXML(byte[] xmlData) {
        Report report;
        try {
            report = (Report) oxmMapper.unmarshal(xmlData);
            logger.info("MessageID = " + report.getHeader().getMessageID());
            System.out.println(report.getHeader().getMessageID());
        } catch (Exception e) {
            logger.error("Произошла ошибка маршаллинга " + e);
            e.printStackTrace();
        }
    }
}
