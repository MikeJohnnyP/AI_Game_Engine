package com.game.animation;

import java.util.HashMap;

import com.game.logger.EngineLogger;

public class StateMachine {
	
	private HashMap<Integer, AnimationState> states = new HashMap<>();
	private AnimationState currentState = null;
	
	public void addState(Integer state, AnimationState animationState) {
		this.states.put(state, animationState);
	}
	
	public void setDefaultState(Integer state) {
		if(!states.containsKey(state)) { EngineLogger.Get().warning("Unable to find state '" + state +"' in set default state"); return; }
			if(currentState == null) {
				currentState = states.get(state);
				return;
			}
		
	}
	
	public void triggerState(Integer state) {
		if(!states.containsKey(state)) { EngineLogger.Get().warning("Unable to find state '" + state +"'"); return; }
		if(currentState != null) {
			currentState = states.get(state);
		}
	}
	
	public void update(float dt) {
		if(currentState != null) {
			currentState.update(dt);
		}
	}
	
	public AnimationState getState() {
		return currentState;
	}
}
