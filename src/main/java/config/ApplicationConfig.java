package config;
public class ApplicationConfig {
    private static final ApplicationConfig instance = new ApplicationConfig();

    private ApplicationConfig(){

    }

    public static ApplicationConfig getInstance() {
        return instance;
    }

    private static final String application_name = "music-library";
    private static final String application_version = "1.0";

    public String getApplicationName() {
        return application_name;
    }
    public String getApplicationVersion() {
        return application_version;
    }

}
