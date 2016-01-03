package gettothecastle.stages;

import gettothecastle.gfx.Screen;

public abstract class Stage {
	
	private static Stage currentStage;

	public abstract void render(Screen screen);
	public abstract void tick();
	
	public static void setCurrentStage(Stage stage) {
		currentStage = stage;
	}
	
	public static Stage getCurrentStage() {
		return currentStage;
	}
}
