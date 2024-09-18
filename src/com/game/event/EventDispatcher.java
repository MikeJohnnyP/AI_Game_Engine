package com.game.event;

import java.util.ArrayList;
import java.util.HashMap;

import com.game.logger.EngineLogger;

public class EventDispatcher {
	private HashMap<String, ArrayList<IEventAction>> eventActionMap; 
	public EventDispatcher() {
		eventActionMap = new HashMap<>();
		EngineLogger.Get().info("EventDispatcher is already");
	}
	
	public <T> void addEventListener(Class<T> type,EventCallback<T> callback) {
		String typeName = type.getName();
		IEventAction eventAction = new EventAction<T>(callback);
		if(!eventActionMap.containsKey(typeName)) {
			eventActionMap.put(typeName, new ArrayList<>());
		}
		eventActionMap.get(typeName).add(eventAction);
	}
	
	public <T> void dispatchEventListener(T eventContext) {
		String typeName = eventContext.getClass().getTypeName();
		
		if(!eventActionMap.containsKey(typeName)) return;
		
		for(var eventAction : eventActionMap.get(typeName)) {
			if(eventAction.execute((EventContext)eventContext)) {
				break;
			}
		}
	}
}
