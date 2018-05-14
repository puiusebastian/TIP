package game.websocket;

public class Message {
	
	private int index;
	private int posX;
	private int posY;
	private int playerFullHealth;
	private int playerCurrentHealth;
	private int team;
	private boolean alive;
	private String username;
	private int kills;
	private int deaths;
	private String movementDirection;
	private int numberOfPlayers;
	private int numberOfMissiles;
	private int tileSize;
	private int missileTileSize;
	private int playerTileSize;
	private String roundTimeElapsed;
	private int roundNumber;
	private int firstTeamScore;
	private int secondTeamScore;
	private int playerWindowWidth;
	private int playerWindowHeight;
	private int mapWidth;
	private int mapHeight;
	private boolean gameEnded;
	private int winnerTeam;
	private int firstTeamFlagCapturedSeconds;
	private int secondTeamFlagCapturedSeconds;
	
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
	public int getPlayerFullHealth() {
		return playerFullHealth;
	}
	public void setPlayerFullHealth(int playerFullHealth) {
		this.playerFullHealth = playerFullHealth;
	}
	public int getPlayerCurrentHealth() {
		return playerCurrentHealth;
	}
	public void setPlayerCurrentHealth(int playerCurrentHealth) {
		this.playerCurrentHealth = playerCurrentHealth;
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
	public String getUsername() {
		return username;
	}
	public void setUsername(String username) {
		this.username = username;
	}
	public int getKills() {
		return kills;
	}
	public void setKills(int kills) {
		this.kills = kills;
	}
	public int getDeaths() {
		return deaths;
	}
	public void setDeaths(int deaths) {
		this.deaths = deaths;
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
	public int getMissileTileSize() {
		return missileTileSize;
	}
	public void setMissileTileSize(int missileTileSize) {
		this.missileTileSize = missileTileSize;
	}
	public int getPlayerTileSize() {
		return playerTileSize;
	}
	public void setPlayerTileSize(int playerTileSize) {
		this.playerTileSize = playerTileSize;
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
	public int getMapWidth() {
		return mapWidth;
	}
	public void setMapWidth(int mapWidth) {
		this.mapWidth = mapWidth;
	}
	public int getMapHeight() {
		return mapHeight;
	}
	public void setMapHeight(int mapHeight) {
		this.mapHeight = mapHeight;
	}
	public boolean isGameEnded() {
		return gameEnded;
	}
	public void setGameEnded(boolean gameEnded) {
		this.gameEnded = gameEnded;
	}
	public int getWinnerTeam() {
		return winnerTeam;
	}
	public void setWinnerTeam(int winnerTeam) {
		this.winnerTeam = winnerTeam;
	}
	public int getFirstTeamFlagCapturedSeconds() {
		return firstTeamFlagCapturedSeconds;
	}
	public void setFirstTeamFlagCapturedSeconds(int firstTeamFlagCapturedSeconds) {
		this.firstTeamFlagCapturedSeconds = firstTeamFlagCapturedSeconds;
	}
	public int getSecondTeamFlagCapturedSeconds() {
		return secondTeamFlagCapturedSeconds;
	}
	public void setSecondTeamFlagCapturedSeconds(int secondTeamFlagCapturedSeconds) {
		this.secondTeamFlagCapturedSeconds = secondTeamFlagCapturedSeconds;
	}
	
}