package com.game.logger;

import java.util.logging.Logger;

public class EngineLogger {
	
	private static boolean isLogging = true;
	private final static JavaLogger logger = new JavaLogger();
	private static NullLogger nlogger = new NullLogger();
	
	private EngineLogger() {}
	
	public static Logger Get() {
		if(isLogging) 
			return logger.getLog();
		else
			return nlogger.getLog();
	}
	
	public static boolean getIsLogging() {
		return isLogging;
	}
	
	public static void setIsLogging(boolean value) {
		isLogging = value;
	}
}
