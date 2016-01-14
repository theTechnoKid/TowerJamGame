package dev.thetechnokid.gather.entities;

import dev.thetechnokid.gather.Game;
import dev.thetechnokid.gather.gfx.Tile;

public class Player extends Entity {

	private final int speed = 3;
	private final int dmg = 10;
	private final int def = 10;
	private int hp = 100;
	
	int frame = 1;
	long lastTime = System.currentTimeMillis();

	public Player() {
		super(Tile.PLAYER_WALK1);
	}

	@Override
	public void tick() {
		checkAnim();
		checkInput();
		checkHits();
	}

	private void checkHits() {
		for (Entity e : Game.getCurrentGame().getScreen().getController().getEntities()) {
			if (e instanceof Enemy) {
				Enemy enemy = (Enemy) e;
				if(bounds().intersects(enemy.bounds())&&handler.isSpace()) {
					enemy.hit(dmg*(Game.getCurrentGame().getLogicManager().getSwordTier()+1));
					System.out.println(Game.getCurrentGame().getLogicManager().getSwordTier()+1);
				}
			}
		}
	}
	
	private void checkInput() {
		if (handler.isUp()) {
			y -= speed;
			if (y < 0)
				y = 0;
			this.tile = frame == 1 ? Tile.PLAYER_UP1 : Tile.PLAYER_UP2;
		} if (handler.isDown()) {
			y += speed;
			if (y > Game.HEIGHT - Tile.DRAW_SIZE)
				y = Game.HEIGHT - Tile.DRAW_SIZE;
			this.tile = frame == 1 ? Tile.PLAYER_DOWN1 : Tile.PLAYER_DOWN2;
		} if (handler.isRight()) {
			x += speed;
			if (x >= Game.WIDTH - Tile.DRAW_SIZE)
				x = Game.WIDTH - Tile.DRAW_SIZE;
			this.tile = frame == 1 ? Tile.PLAYER_WALK1 : Tile.PLAYER_WALK2;
		} if (handler.isLeft()) {
			x -= speed;
			if (x < 0)
				x = 0;
			this.tile = frame == 1 ? Tile.PLAYER_WALK1FLIP : Tile.PLAYER_WALK2FLIP;
		}
	}

	private void checkAnim() {
		if(System.currentTimeMillis()-lastTime >= 200) {
			if(frame == 1) frame=2;
			else frame = 1;
			lastTime = System.currentTimeMillis();
		}
	}

	
	public void hit(int damage) {
		damage = damage-(def*Game.getCurrentGame().getLogicManager().getArmorTier());
		if(hp >= damage) {
			hp -= damage;
		} else {
			destroyed = true;
		}
	}
}
