package com.netpay.frameworkweb;

public class DriverManagerFactory {
	private DriverManagerFactory() {
		
	}
    public static DriverManager getDriverManager(DriverType driverType) {
        DriverManager driverManager = null;
        switch (driverType) {
            case CHROME:
                driverManager = new ChromeDriverManager();
                break;

            case FIREFOX:
                driverManager = new FirFoxDriverManager();
                break;

            case GRID_CHROME:
                driverManager = new RemoteDriverManagerChrome();
                break;
                
            case GRID_FIREFOX:
                driverManager = new RemoteDriverManagerFirefox();
                break;

            default:
                // chrome is default driver manager
                driverManager = new ChromeDriverManager();
                break;
        }
        return driverManager;
    }
}
