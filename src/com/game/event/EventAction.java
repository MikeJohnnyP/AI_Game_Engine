package com.game.event;

public class EventAction<T> implements IEventAction{

	private EventCallback<T> m_Callback;
	
	public EventAction(EventCallback<T> callback){
		m_Callback = callback;
	}
	
	@Override
	public boolean execute(EventContext eventContext) {
		return m_Callback.execute((T)eventContext);
	}

}
