package dev.thetechnokid.gather.stages;

import java.awt.Graphics;

import dev.thetechnokid.gather.gfx.Screen;

public abstract class Stage {
	
	private static Stage currentStage;

	public abstract void render(Graphics g, Screen screen);
	public abstract void tick();
	
	public static void setCurrentStage(Stage stage) {
		currentStage = stage;
	}
	
	public static Stage getCurrentStage() {
		return currentStage;
	}
}
