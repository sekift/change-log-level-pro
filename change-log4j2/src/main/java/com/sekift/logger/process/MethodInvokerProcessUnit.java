package com.sekift.logger.process;

import com.sekift.logger.bean.LoggerBean;
import com.sekift.logger.enums.LogFrameworkType;
import com.sekift.logger.impl.AbstractProcessUnitImpl;
import org.apache.commons.collections4.CollectionUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.List;

import static com.sekift.logger.constant.LogConstant.*;


/**
 * 方法调用处理单元
 *
 * @author sekift
 * @date 2018-04-27
 */
public class MethodInvokerProcessUnit extends AbstractProcessUnitImpl {
    private Logger log = LoggerFactory.getLogger(MethodInvokerProcessUnit.class);

    @Override
    public String setLogLevel(List<LoggerBean> loggerList) {
        log.info("setLogLevel: loggerList={}", loggerList);
        if (CollectionUtils.isEmpty(loggerList)) {
            throw new RuntimeException(LOGGER_LIST_IS_NULL);
        }
        for (LoggerBean loggerbean : loggerList) {
            Object logger = loggerMap.get(loggerbean.getName());
            if (null == logger) {
                throw new RuntimeException(LOGGER_NOT_EXSIT);
            }
            if (logFrameworkType == LogFrameworkType.LOG4J2) {
                org.apache.logging.log4j.core.config.LoggerConfig loggerConfig = (org.apache.logging.log4j.core.config.LoggerConfig) logger;
                org.apache.logging.log4j.Level targetLevel = org.apache.logging.log4j.Level.toLevel(loggerbean.getLevel());
                //log.info("[LoggerLevel]目前的日志级别为 "+targetLevel);
                loggerConfig.setLevel(targetLevel);
                org.apache.logging.log4j.core.LoggerContext ctx = (org.apache.logging.log4j.core.LoggerContext) org.apache.logging.log4j.LogManager.getContext(false);
                ctx.updateLoggers(); // This causes all Loggers to refetch information from their LoggerConfig.
            } else {
                throw new RuntimeException(LOGGER_TYPE_UNKNOWN);
            }
        }
        return "success";
    }

    @Override
    public String setLogLevel(String logLevel) {
        return PARAMETER_TYPE_ERROR;
    }

}
