# change-log-level-pro 日志级别动态调整工具
change-log-level( https://github.com/sekift/change-log-level )的升级版 <br />

# 版本说明
由于slf4j在绑定多个实现时，只能选择最新的实现（即logback），为了使log4j与log4j2也能使用，因此将项目分拆成多个部分，可选择性地打包引入使用。<br/>
四个module分别为：<br />
1、change-common：公共类，其他实现项目都需引入；<br />
2、change-log4j：log4j实现；<br />
3、change-log4j2：log4j2实现；<br />
4、change-logback：logback实现；<br />

# 问题
1、由于是验证方案的可行性，有很多代码可优化的地方自行优化；<br />
2、log4j2在运行单元测试时会先报一个错误（如下），不过不影响后面运行，可自行解决；<br />
````
2020-08-08 02:27:49,026 main WARN Error while converting string [] to type [class org.apache.logging.log4j.Level]. Using default value [null]. java.lang.IllegalArgumentException: Unknown level constant [].
	at org.apache.logging.log4j.Level.valueOf(Level.java:320)
	at org.apache.logging.log4j.core.config.plugins.convert.TypeConverters$LevelConverter.convert(TypeConverters.java:288)
	at org.apache.logging.log4j.core.config.plugins.convert.TypeConverters$LevelConverter.convert(TypeConverters.java:284)
	at org.apache.logging.log4j.core.config.plugins.convert.TypeConverters.convert(TypeConverters.java:419)
	at org.apache.logging.log4j.core.config.plugins.visitors.AbstractPluginVisitor.convert(AbstractPluginVisitor.java:149)
	at org.apache.logging.log4j.core.config.plugins.visitors.PluginAttributeVisitor.visit(PluginAttributeVisitor.java:45)
	at org.apache.logging.log4j.core.config.plugins.util.PluginBuilder.generateParameters(PluginBuilder.java:254)
	at org.apache.logging.log4j.core.config.plugins.util.PluginBuilder.build(PluginBuilder.java:136)
	at org.apache.logging.log4j.core.config.AbstractConfiguration.createPluginObject(AbstractConfiguration.java:959)
	at org.apache.logging.log4j.core.config.AbstractConfiguration.createConfiguration(AbstractConfiguration.java:899)
	at org.apache.logging.log4j.core.config.AbstractConfiguration.createConfiguration(AbstractConfiguration.java:891)
	at org.apache.logging.log4j.core.config.AbstractConfiguration.doConfigure(AbstractConfiguration.java:514)
	at org.apache.logging.log4j.core.config.AbstractConfiguration.initialize(AbstractConfiguration.java:238)
	at org.apache.logging.log4j.core.config.AbstractConfiguration.start(AbstractConfiguration.java:250)
	at org.apache.logging.log4j.core.LoggerContext.setConfiguration(LoggerContext.java:547)
	at org.apache.logging.log4j.core.LoggerContext.reconfigure(LoggerContext.java:619)
	at org.apache.logging.log4j.core.LoggerContext.reconfigure(LoggerContext.java:636)
	at org.apache.logging.log4j.core.LoggerContext.start(LoggerContext.java:231)
	at org.apache.logging.log4j.core.impl.Log4jContextFactory.getContext(Log4jContextFactory.java:153)
	at org.apache.logging.log4j.core.impl.Log4jContextFactory.getContext(Log4jContextFactory.java:45)
	at org.apache.logging.log4j.LogManager.getContext(LogManager.java:194)
	at org.apache.logging.log4j.spi.AbstractLoggerAdapter.getContext(AbstractLoggerAdapter.java:121)
	at org.apache.logging.slf4j.Log4jLoggerFactory.getContext(Log4jLoggerFactory.java:43)
	at org.apache.logging.log4j.spi.AbstractLoggerAdapter.getLogger(AbstractLoggerAdapter.java:46)
	at org.apache.logging.slf4j.Log4jLoggerFactory.getLogger(Log4jLoggerFactory.java:29)
	at org.slf4j.LoggerFactory.getLogger(LoggerFactory.java:358)
	at org.slf4j.LoggerFactory.getLogger(LoggerFactory.java:383)
	at com.sekift.logger.Log4jProcessUnitImplTest.<init>(ChangeLogLevelProcessUnitTest.java:12)
	at sun.reflect.NativeConstructorAccessorImpl.newInstance0(Native Method)
	at sun.reflect.NativeConstructorAccessorImpl.newInstance(NativeConstructorAccessorImpl.java:62)
	at sun.reflect.DelegatingConstructorAccessorImpl.newInstance(DelegatingConstructorAccessorImpl.java:45)
	at java.lang.reflect.Constructor.newInstance(Constructor.java:423)
	at org.junit.runners.BlockJUnit4ClassRunner.createTest(BlockJUnit4ClassRunner.java:217)
	at org.junit.runners.BlockJUnit4ClassRunner$1.runReflectiveCall(BlockJUnit4ClassRunner.java:266)
	at org.junit.internal.runners.model.ReflectiveCallable.run(ReflectiveCallable.java:12)
	at org.junit.runners.BlockJUnit4ClassRunner.methodBlock(BlockJUnit4ClassRunner.java:263)
	at org.junit.runners.BlockJUnit4ClassRunner.runChild(BlockJUnit4ClassRunner.java:78)
	at org.junit.runners.BlockJUnit4ClassRunner.runChild(BlockJUnit4ClassRunner.java:57)
	at org.junit.runners.ParentRunner$3.run(ParentRunner.java:290)
	at org.junit.runners.ParentRunner$1.schedule(ParentRunner.java:71)
	at org.junit.runners.ParentRunner.runChildren(ParentRunner.java:288)
	at org.junit.runners.ParentRunner.access$000(ParentRunner.java:58)
	at org.junit.runners.ParentRunner$2.evaluate(ParentRunner.java:268)
	at org.junit.runners.ParentRunner.run(ParentRunner.java:363)
	at org.junit.runner.JUnitCore.run(JUnitCore.java:137)
	at com.intellij.junit4.JUnit4IdeaTestRunner.startRunnerWithArgs(JUnit4IdeaTestRunner.java:68)
	at com.intellij.rt.execution.junit.IdeaTestRunner$Repeater.startRunnerWithArgs(IdeaTestRunner.java:47)
	at com.intellij.rt.execution.junit.JUnitStarter.prepareStreamsAndStart(JUnitStarter.java:242)
	at com.intellij.rt.execution.junit.JUnitStarter.main(JUnitStarter.java:70)

2020-08-08 02:27:49,436:DEBUG main (ChangeLogLevelProcessUnitTest.java:18) - test start
2020-08-08 02:27:49,444:DEBUG main (ChangeLogLevelProcessUnitTest.java:19) - type:org.apache.logging.slf4j.Log4jLoggerFactory
2020-08-08 02:27:49,469:INFO main (AbstractProcessUnitImpl.java:45) - [LoggerLevel]start
2020-08-08 02:27:49,473:DEBUG main (AbstractProcessUnitImpl.java:48) - [LoggerLevel]log type=org.apache.logging.slf4j.Log4jLoggerFactory
2020-08-08 02:27:49,512:INFO main (AbstractProcessUnitImpl.java:66) - [LoggerLevel]loggerMap={root=root, com.sekift.logger.MethodInvokerProcessUnitTest=com.sekift.logger.MethodInvokerProcessUnitTest}
2020-08-08 02:27:49,618:INFO main (AbstractProcessUnitImpl.java:87) - result={"loggerList":[{"loggerLevel":"DEBUG","loggerName":"root"},{"loggerLevel":"DEBUG","loggerName":"com.sekift.logger.MethodInvokerProcessUnitTest"}],"logFramework":"LOG4J2"}
2020-08-08 02:27:49,621:DEBUG main (ChangeLogLevelProcessUnitTest.java:21) - frame:LOG4J2
......
````

# 更新
2020-08-08 <br />
1、log4j/log4j2/logback项目分拆，可分别引入，解决上一个版本只能选择logback的错误。<br />

# 使用帮助
引入依赖<br />
通过工厂获取对应的实现：AbstractProcessUnitImpl process = ProcessUnitFactory.newInstance(serverId).getXXX()<br />
通过process.setLogLevel(var)方法进行日志级别修改操作<br />

参数说明：<br />
1、String 类型，将所有logger统一设定为某个级别<br />
2、List<LoggerBean> 类型，指定类的logger级别<br />
3、null 空参数，默认设置所有logger为默认级别（INFO），如果需要修改默认级别，可以自行修改。<br />

# 未来
按照美团的使用方案，可以通过HTTP或其他RPC提供给其他项目；如果可视化还需要配置后台web或client。<br />
因此如果需要作为中间件本程序还没有完成，但是作为项目引入仍然可以使用。<br />

# 更多请看
https://tech.meituan.com/change_log_level.html 《日志级别动态调整——小工具解决大问题》<br />

