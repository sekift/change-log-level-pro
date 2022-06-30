package com.sekift.logger;

import com.sekift.logger.impl.LogbackProcessUnitImpl;
import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class LogbackProcessUnitImplTest {
    Logger logger = LoggerFactory.getLogger(LogbackProcessUnitImplTest.class);

    @Test
    public void setLogLevelTest() {
        System.out.println("test start");
        IProcessUnit process = LogbackProcessUnitImpl.getSingleton();
        process.setLogLevel("info");

        logger.debug("debug ...");
        System.out.println("--------");
        process.setLogLevel("debug");
        logger.debug("debug ...");
    }

    @Test
    public void setLogLevelTest2() {
        System.out.println("test start");
        IProcessUnit process = LogbackProcessUnitImpl.getSingleton();

        logger.debug("debug ...");
        System.out.println("--------");
        process.setLogLevel("com.sekift.logger.MethodInvokerProcessUnitTest", "debug");
        logger.debug("debug ...");
    }
}
