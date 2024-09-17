package com.game.logger;

import java.util.logging.Logger;

final class JavaLogger implements com.game.logger.ILogger {
	private static Logger logger = Logger.getGlobal();
	
	@Override
	public Logger getLog() {
		return logger;
	}

	
}
