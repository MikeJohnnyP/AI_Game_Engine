package com.game;

import java.util.HashMap;

import com.game.graphics.Texture;
import com.game.logger.EngineLogger;

public class AssetPool {
	private HashMap<String, Object> resource = new HashMap<>(); 
	private static AssetPool instance;
	private AssetPool() {}
	
	public static AssetPool Get() {
		if(instance != null) return instance;
		else {
			instance = new AssetPool();
			return instance;
		}
	}
	
	public <T> T loadAsset(String name, T object) {
		resource.put(name, object);
		return object;
	}
	
	public <T> T getAsset(String name) {
		if(!resource.containsKey(name)) { 
			EngineLogger.Get().severe("Not contains "+ name + " in asset pool");
			return null;
		}
		return (T)resource.get(name);
	}
	
	public boolean containsKey(String name) {
		return resource.containsKey(name);
	}
	
	public Texture loadAsset(String name, String path) {
		Texture temp = new Texture(path, name);
		resource.put(name, temp);
		return temp;
		
	}
}
