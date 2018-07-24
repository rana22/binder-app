//package enterprise.binder.config;
//
//import ch.qos.logback.classic.LoggerContext;
//import ch.qos.logback.classic.encoder.PatternLayoutEncoder;
//import ch.qos.logback.core.ConsoleAppender;
//import ch.qos.logback.core.rolling.RollingFileAppender;
//import ch.qos.logback.core.rolling.SizeAndTimeBasedRollingPolicy;
//import ch.qos.logback.core.util.FileSize;
//import ch.qos.logback.ext.spring.ApplicationContextHolder;
//import org.slf4j.LoggerFactory;
//import org.springframework.context.annotation.Bean;
//import org.springframework.context.annotation.Configuration;
//
//@Configuration
//public class LogbackConfig {
//
//    @Bean
//    public static ApplicationContextHolder applicationContextHolder() {
//        return new ApplicationContextHolder ();
//    }
//
//    @Bean
//    public static LoggerContext loggerContext() {
//        return (LoggerContext) LoggerFactory.getILoggerFactory();
//    }
//
//    @Bean
//    public static PatternLayoutEncoder encoder (LoggerContext ctx) {
//        PatternLayoutEncoder encoder = new PatternLayoutEncoder();
//        encoder.setContext(ctx);
//        encoder.setPattern("%-12date{YYYY-MM-dd HH:mm} %-5level - %msg%n");
//        return encoder;
//    }
//
//    @Bean
//    public static RollingFileAppender rollingFileAppender(LoggerContext ctx){
//        RollingFileAppender rollingFileAppender = new RollingFileAppender();
//        rollingFileAppender.setContext(ctx);
//        rollingFileAppender.setFile("log/log");
//        rollingFileAppender.setName("document.log");
//
//        SizeAndTimeBasedRollingPolicy sizeAndTimeBasedRollingPolicy = new SizeAndTimeBasedRollingPolicy();
//        sizeAndTimeBasedRollingPolicy.setMaxHistory(1);
//        sizeAndTimeBasedRollingPolicy.setMaxFileSize(new FileSize(1000L));
//
//        rollingFileAppender.setRollingPolicy(sizeAndTimeBasedRollingPolicy);
//
//        return rollingFileAppender;
//    }
//
//    @Bean
//    public static ConsoleAppender consoleAppender (LoggerContext ctx, PatternLayoutEncoder encoder) {
//        ConsoleAppender appender = new ConsoleAppender();
//        appender.setContext(ctx);
//        appender.setEncoder(encoder);
//        return appender;
//    }
//}
