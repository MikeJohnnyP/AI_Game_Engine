package com.game;

public class ObJectRelease<T extends AutoCloseable> implements AutoCloseable {

	private T resource;
	public ObJectRelease(T resoure) {
		this.resource = resoure;
	}
	
	public T getResource() {
		return resource;
	}
	
	@Override
	public void close() throws Exception {
		if (resource != null) {
			try {
				resource.close();
			} finally {
				resource = null;
			}
		}
		
	}

}
