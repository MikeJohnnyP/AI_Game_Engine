package com.game.layer;

import java.util.Stack;

import com.game.logger.EngineLogger;

public class LayerStack {
	private final Stack<Layer> layerStack;
	private int layerInsert;
	
	public LayerStack() {
		layerInsert = 0;
		layerStack = new Stack<>();
	}
	
	public void pushLayer(Layer layer) {
		if(layer.getType() != Layer.LayerType.Standard) {
			EngineLogger.Get().warning(layer + " layer is not an standard");
			return;
		}
		layerStack.add(layerInsert, layer);
		++layerInsert;
	}
	
	public void pushOverlay(Layer overlay) {
		if(overlay.getType() != Layer.LayerType.Overlay) {
			EngineLogger.Get().warning(overlay + " layer is not an overlay");
			return;
		}
		layerStack.push(overlay);
	}
	
	public void popLayer(Layer layer) {
		if(layerInsert < 0) {
			EngineLogger.Get().severe("layerInsert is negative");
			return;
		}
		if(layerStack.contains(layer)) {
			layerStack.remove(layer);
			--layerInsert;
		} else {
			EngineLogger.Get().severe("Layer Stack not contains " + layer + " layer");
		}
		
	}
	
	public void popOverlay(Layer overlay) {
		if(layerInsert < 0) {
			EngineLogger.Get().severe("layerInsert is negative");
			return;
		}
		if(layerStack.contains(overlay)) {
			layerStack.remove(overlay);
		} else {
			EngineLogger.Get().severe("Layer Stack not contains " + overlay + " overlay");
		}
		
	}
	
	public Stack<Layer> Get() {
		return layerStack;
	}
	

}
