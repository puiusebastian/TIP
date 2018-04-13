package game;

public class Player {

	private int posX;
	private int posY;
	private String movementDirection;
	private int speed;
	public boolean moveUp;
	public boolean moveDown;
	public boolean moveLeft;
	public boolean moveRight;
	public String movementBuffer;
	
	public Player(int x, int y) {
		this.posX = x;
		this.posY = y;
		this.movementDirection = "up";
		this.speed = 2;
		this.movementBuffer = "none";
		this.moveUp = false;
		this.moveDown = false;
		this.moveLeft = false;
		this.moveRight = false;
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

	public String getMovementDirection() {
		return movementDirection;
	}

	public void setMovementDirection(String movementDirection) {
		this.movementDirection = movementDirection;
	}
	
	public void Update() {
		// Update movement direction
		/*switch(this.movementBuffer) {
    	case "up":
    		this.moveUp = true;
    		break;
    	case "down":
    		this.moveDown = true;
    		break;
    	case "left":
    		this.moveLeft = true;
    		break;
    	case "right":
    		this.moveRight = true;
    		break;
    	case "no_up":
    		this.moveUp = false;
    		break;
    	case "no_down":
    		this.moveDown = false;
    		break;
    	case "no_left":
    		this.moveLeft = false;
    		break;
    	case "no_right":
    		this.moveRight = false;
    		break;
    	}
		*/
		// Move the player
		if(this.moveUp == true) {
			this.posY -= this.speed;
			this.movementDirection = "up";
		}
		else if(this.moveDown == true) {
			this.posY += this.speed;
			this.movementDirection = "down";
		}
		else if(this.moveLeft == true) {
			this.posX -= this.speed;
			this.movementDirection = "left";
		}
		else if(this.moveRight == true) {
			this.posX += this.speed;
			this.movementDirection = "right";
		}
	}

}
