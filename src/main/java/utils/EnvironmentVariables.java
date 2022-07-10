package utils;

public class EnvironmentVariables {
	static ConfigProperties config = new ConfigProperties();
	public final static String BROWSER = config.getPropertyValue("BROWSER");
	public final static String BASE_URL = config.getPropertyValue("BASE_URL");
	public final static String HEADLESS = config.getPropertyValue("HEADLESS");
	public static String WEB_USERNAME = "";
	public static String WEB_PASSWORD = "";
}
