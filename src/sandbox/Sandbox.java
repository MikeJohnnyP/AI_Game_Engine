package sandbox;

import com.game.engine.controller.Application;
import com.game.engine.model.WindowSpec;

public class Sandbox extends Application {
	
	public Sandbox() {
		// TODO Auto-generated constructor stub
	}


	@Override
	public void clientInit() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void clientShutdown() {
		// TODO Auto-generated method stub
		
	}
	
	public static void main(String[] args) {
		Application.setInstance(new Sandbox());
		Application.Get().init(new WindowSpec(640, 480, "My Game"));
		Application.Get().run();
	}
}
