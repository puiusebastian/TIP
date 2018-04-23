package game.websocket;

public class Message {
	
	private int index;
	private int posX;
	private int posY;
	private int team;
	private boolean alive;
	private String movementDirection;
	private int numberOfPlayers;
	private int numberOfMissiles;
	private int tileSize;
	private int missileSize;
	private String roundTimeElapsed;
	private int roundNumber;
	private int firstTeamScore;
	private int secondTeamScore;
	private int playerWindowWidth;
	private int playerWindowHeight;
	
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
	public int getTeam() {
		return team;
	}
	public void setTeam(int team) {
		this.team = team;
	}
	public boolean getAlive() {
		return alive;
	}
	public void setAlive(boolean alive) {
		this.alive = alive;
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
	public int getTileSize() {
		return tileSize;
	}
	public void setTileSize(int tileSize) {
		this.tileSize = tileSize;
	}
	public int getMissileSize() {
		return missileSize;
	}
	public void setMissileSize(int missileSize) {
		this.missileSize = missileSize;
	}
	public String getRoundTimeElapsed() {
		return roundTimeElapsed;
	}
	public void setRoundTimeElapsed(String roundElapsedTime) {
		this.roundTimeElapsed = roundElapsedTime;
	}
	public int getRoundNumber() {
		return roundNumber;
	}
	public void setRoundNumber(int roundNumber) {
		this.roundNumber = roundNumber;
	}
	public int getFirstTeamScore() {
		return firstTeamScore;
	}
	public void setFirstTeamScore(int firstTeamScore) {
		this.firstTeamScore = firstTeamScore;
	}
	public int getSecondTeamScore() {
		return secondTeamScore;
	}
	public void setSecondTeamScore(int secondTeamScore) {
		this.secondTeamScore = secondTeamScore;
	}
	public int getPlayerWindowWidth() {
		return playerWindowWidth;
	}
	public void setPlayerWindowWidth(int playerWindowWidth) {
		this.playerWindowWidth = playerWindowWidth;
	}
	public int getPlayerWindowHeight() {
		return playerWindowHeight;
	}
	public void setPlayerWindowHeight(int playerWindowHeight) {
		this.playerWindowHeight = playerWindowHeight;
	}
	
}