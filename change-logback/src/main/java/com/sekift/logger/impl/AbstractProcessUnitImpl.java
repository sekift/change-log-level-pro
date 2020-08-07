package com.sekift.logger.impl;

import static com.sekift.logger.constant.LogConstant.LOGBACK_LOGGER_FACTORY;
import static com.sekift.logger.constant.LogConstant.LOGGER_LEVEL;
import static com.sekift.logger.constant.LogConstant.LOGGER_LIST;
import static com.sekift.logger.constant.LogConstant.LOGGER_NAME;
import static com.sekift.logger.constant.LogConstant.LOGGER_TYPE_UNKNOWN;
import static com.sekift.logger.constant.LogConstant.LOG_FRAMEWORK;

import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ConcurrentMap;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.slf4j.impl.StaticLoggerBinder;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.sekift.logger.IProcessUnit;
import com.sekift.logger.constant.LogLevelConstant;
import com.sekift.logger.enums.LogFrameworkType;

/**
 * 日志调整抽象类
 * 支持logback
 * @author sekift
 * @date 2018-04-27
 */
public abstract class AbstractProcessUnitImpl implements IProcessUnit{
    private Logger log = LoggerFactory.getLogger(AbstractProcessUnitImpl.class);

    protected String defaultLevel = LogLevelConstant.INFO;

    public final LogFrameworkType logFrameworkType;
    protected final ConcurrentHashMap<String, Object> loggerMap = new ConcurrentHashMap<>();
    
    public AbstractProcessUnitImpl() {
        log.info("[LoggerLevel]start");
        String type = StaticLoggerBinder.getSingleton().getLoggerFactoryClassStr();
        if (log.isDebugEnabled()) {
            log.debug("[LoggerLevel]log type={}", type);
        }
        if (LOGBACK_LOGGER_FACTORY.equals(type)) {
            logFrameworkType = LogFrameworkType.LOGBACK;
            ch.qos.logback.classic.LoggerContext loggerContext = (ch.qos.logback.classic.LoggerContext) LoggerFactory.getILoggerFactory();
            for (ch.qos.logback.classic.Logger logger : loggerContext.getLoggerList()) {
                if (logger.getLevel() != null) {
                    loggerMap.put(logger.getName(), logger);
                }
            }
            ch.qos.logback.classic.Logger rootLogger = (ch.qos.logback.classic.Logger) LoggerFactory.getLogger(Logger.ROOT_LOGGER_NAME);
            loggerMap.put(rootLogger.getName(), rootLogger);
        } else {
            logFrameworkType = LogFrameworkType.UNKNOWN;
            log.error("[LoggerLevel]Log框架无法识别:type={}", type);
            return;
        }
        log.info("[LoggerLevel]loggerMap={}", loggerMap);
        this.getLoggerList();
    }
    
	private String getLoggerList() {
		JSONObject result = new JSONObject();
		result.put(LOG_FRAMEWORK, logFrameworkType);
		JSONArray loggerList = new JSONArray();
		for (ConcurrentMap.Entry<String, Object> entry : loggerMap.entrySet()) {
			JSONObject loggerJSON = new JSONObject();
			loggerJSON.put(LOGGER_NAME, entry.getKey());
			if (logFrameworkType == LogFrameworkType.LOGBACK) {
				ch.qos.logback.classic.Logger targetLogger = (ch.qos.logback.classic.Logger) entry.getValue();
				loggerJSON.put(LOGGER_LEVEL, targetLogger.getLevel().toString());
			} else {
				loggerJSON.put(LOGGER_LEVEL, LOGGER_TYPE_UNKNOWN);
			}
			loggerList.add(loggerJSON);
		}
		result.put(LOGGER_LIST, loggerList);
		log.info("result={}", result.toString());
		return result.toString();
	}

    public abstract String setLogLevel(String logLevel);
    
    public abstract String setLogLevel(JSONArray data);

    public void setDefaultLevel(String defaultLevel) {
        this.defaultLevel = defaultLevel;
    }
}
