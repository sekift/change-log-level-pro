package com.sekift.logger;

/**
 * 日志处理接口
 *
 * @author sekift
 * @date 2018-04-27
 */
public interface IProcessUnit {

    /**
     * 设置所有Logger等级
     *
     * @param logLevel
     * @return 处理结果
     */
    String setLogLevel(String logLevel);

    /**
     * 设置指定Logger等级
     *
     * @param loggerName
     * @param loggerLevel
     * @return 处理结果
     */
    String setLogLevel(String loggerName, String loggerLevel);

    /**
     * 获取日志对象集合
     *
     * @return
     */
    String getLoggerList();
}
