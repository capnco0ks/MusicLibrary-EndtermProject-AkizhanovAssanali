package config;

public class LoggingService {
    private static final LoggingService instance = new LoggingService();
    private LoggingService(){
    }
    public static LoggingService getInstance() {
        return instance;
    }
    public void info(String message) {
        System.out.println("[INFO] "+ message);
    }
    public void error(String message, Throwable t) {
        System.err.println("[ERROR] "+message);
        t.printStackTrace();
    }
}
