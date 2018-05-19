package game;

public class Player {

	private int posX;
	private int posY;
	private String movementDirection;
	private int speed;
	private int fullHealth;
	private int currentHealth;
	private int damage;
	private int range;
	public boolean moveUp;
	public boolean moveDown;
	public boolean moveLeft;
	public boolean moveRight;
	public String movementBuffer;
	private int team;
	private boolean alive;
	private String username;
	private int kills;
	private int deaths;
	
	public Player(int x, int y, int team) {
		this.posX = x;
		this.posY = y;
		this.team = team;
		if(team == 1) {
			this.movementDirection = "right";
		}
		else if(team == 2) {
			this.movementDirection = "left";
		}
		
		// Tank parameters
		this.speed = 2;
		this.fullHealth = 500;
		this.currentHealth = 500;
		this.damage = 100;
		this.range = 150;
		
		this.movementBuffer = "none";
		this.moveUp = false;
		this.moveDown = false;
		this.moveLeft = false;
		this.moveRight = false;
		this.alive = true;
		
		this.username = "Unknown";
		this.kills = 0;
		this.deaths = 0;
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
	
	public int getFullHealth() {
		return fullHealth;
	}
	
	public int getCurrentHealth() {
		return currentHealth;
	}
	
	public int getDamage() {
		return damage;
	}
	
	public int getRange() {
		return range;
	}
	
	public int getTeam() {
		return team;
	}
	
	public void setTeam(int team) {
		this.team = team;
	}
	
	public boolean isAlive() {
		return alive;
	}
	
	public void setAlive(boolean alive) {
		this.alive = alive;
	}
	
	public String getUsername() {
		return username;
	}

	public int getKills() {
		return kills;
	}

	public int getDeaths() {
		return deaths;
	}

	public void Update(int mapWidth, int mapHeight, int playerTileSize) {
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
			if(this.posY - this.speed >= 0) {
				this.posY -= this.speed;
			}
			this.movementDirection = "up";
		}
		else if(this.moveDown == true) {
			if(this.posY + playerTileSize + this.speed <= mapHeight) {
				this.posY += this.speed;
			}
			this.movementDirection = "down";
		}
		else if(this.moveLeft == true) {
			if(this.posX - this.speed >= 0) {
				this.posX -= this.speed;
			}
			this.movementDirection = "left";
		}
		else if(this.moveRight == true) {
			if(this.posX + playerTileSize + this.speed <= mapWidth) {
				this.posX += this.speed;
			}
			this.movementDirection = "right";
		}
	}
	
	public void increaseKills() {
		this.kills++;
	}
	
	public void takeHit(int damage) {
		this.currentHealth -= damage;
		if(this.currentHealth <= 0) {
			this.alive = false;
			this.currentHealth = 0;
			this.deaths++;
		}
	}
	
	public void revive() {
		this.alive = true;
		this.currentHealth = this.fullHealth;
	}

}
