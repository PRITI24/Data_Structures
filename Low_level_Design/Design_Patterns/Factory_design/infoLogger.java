package logger;

public class infoLogger implements ILogger {
    public void log(String msg) {
        System.out.println("INFO: "+msg);
    }
}