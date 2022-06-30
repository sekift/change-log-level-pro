package com.sekift.logger;

import com.sekift.logger.impl.Log4j2ProcessUnitImpl;
import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class Log4j2ProcessUnitImplTest {

    Logger logger = LoggerFactory.getLogger(Log4j2ProcessUnitImplTest.class);

    @Test
    public void setLogLevelTest() {
        System.out.println("test start");
        IProcessUnit process = Log4j2ProcessUnitImpl.getSingleton();
        process.setLogLevel("info");

        logger.debug("debug ...");
        System.out.println("--------");
        process.setLogLevel("debug");
        logger.debug("debug ...");
    }

    @Test
    public void setLogLevelTest2() {
        System.out.println("test start");
        IProcessUnit process = Log4j2ProcessUnitImpl.getSingleton();

        logger.debug("debug ...");
        System.out.println("--------");
        process.setLogLevel("com.sekift.logger.MethodInvokerProcessUnitTest", "debug");
        logger.debug("debug ...");
    }
}
