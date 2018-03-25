package test.java;

public class SetWebDriver {

    public void startWebDriver() {

        String os = System.getProperty("os.name").toLowerCase();

        if (os.contains("mac")) {
            System.setProperty("webdriver.chrome.driver", System.getProperty("user.dir") + "/chromedriver");
        } else {
            System.getProperty("webdriver.chrome.driver", System.getProperty("user.dir") + "\\chromedriver.exe");
        }

    }
}
