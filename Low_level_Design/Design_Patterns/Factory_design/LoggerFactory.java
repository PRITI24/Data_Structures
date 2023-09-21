package logger;

public class loggerFactory {
    public static ILogger createLogger(LogLevel loglevel){
        switch(loglevel){
            case DEBUG:
                return new debugLogger();
            case INFO:
                return new infoLogger();
            case ERROR:
                return new errorLogger(); 
            default:
                return null;
        }
    }
}