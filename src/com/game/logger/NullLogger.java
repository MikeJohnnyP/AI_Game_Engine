package com.game.logger;

import java.util.logging.Logger;

final class NullLogger implements ILogger {
	
	@Override
	public Logger getLog() {
		return null;
	}

}
