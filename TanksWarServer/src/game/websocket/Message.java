package game.websocket;

import java.util.Date;

public class Message {
	
	private int index;
	private int posX;
	private int posY;
	private String movementDirection;
	private int numberOfPlayers;
	private int numberOfMissiles;
	
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
	public String getMovementDirection() {
		return movementDirection;
	}
	public void setMovementDirection(String movementDirection) {
		this.movementDirection = movementDirection;
	}
	public int getIndex() {
		return index;
	}
	public void setIndex(int index) {
		this.index = index;
	}
	public int getNumberOfPlayers() {
		return numberOfPlayers;
	}
	public void setNumberOfPlayers(int numberOfPlayers) {
		this.numberOfPlayers = numberOfPlayers;
	}
	public int getNumberOfMissiles() {
		return numberOfMissiles;
	}
	public void setNumberOfMissiles(int numberOfMissiles) {
		this.numberOfMissiles = numberOfMissiles;
	}

    /*private String content;
    private String sender;
    private Date received;

    // getters and setters
    public void setSender(String sender) {
    	this.sender = sender;
    }
    
    public void setContent(String content) {
    	this.content = content;
    }
    
    public void setReceived(Date received) {
    	this.received = received;
    }

	public String getContent() {
		return content;
	}

	public String getSender() {
		return sender;
	}

	public Date getReceived() {
		return received;
	}*/
}