package game;

import javax.websocket.Session;

public class Missile {
	
	private Session playerId;
	private int posX;
	private int posY;
	private int maxDistance;
	private int travelledDistance;
	private String direction;
	private int speed;
	private boolean exploded;
	
	public Missile(Session playerId, int x, int y, int maxDistance, String direction, int speed) {
		this.playerId = playerId;
		this.posX = x;
		this.posY = y;
		this.maxDistance = maxDistance;
		this.direction = direction;
		this.speed = speed;
		this.exploded = false;
		
		this.travelledDistance = 0;
	}
	
	public Session getPlayerId() {
		return playerId;
	}
	
	public int getPosX() {
		return posX;
	}
	
	public void setPosX(int posX) {
		this.posX = posX;
	}
	
	public int getPosY() {
		return posY;
	}
	
	public void setPosY(int posY) {
		this.posY = posY;
	}
	
	public boolean checkIfExploded() {
		return exploded;
	}
	
	public void update() {
		switch(direction) {
		case "up":
			this.posY -= this.speed;
			break;
		case "down":
			this.posY += this.speed;
			break;
		case "left":
			this.posX -= this.speed;
			break;
		case "right":
			this.posX += this.speed;
		}
		this.travelledDistance += this.speed;
		
		if(this.travelledDistance >= this.maxDistance) {
			this.exploded = true;
		}
	}

}
