package game.websocket;

import java.util.*;
import javax.websocket.Session;

import org.joda.time.DateTime;
import org.joda.time.Period;

import game.Missile;
import game.Player;
import servlets.TankPicked;

public class PushTimeService implements Runnable {
    
    private static PushTimeService instance;
    
    private static DateTime roundStartTime = new DateTime();
    private static int roundNumber = 1;
    private int minutesPerRound = 5;
    private int roundsPerGame = 10;
    private int winnerTeam = 0;
    private boolean gameEnded = false;
    private DateTime gameEndedTime;
    
    private int flagCapturingMinutesNeeded = 2;
    private static DateTime firstTeamFlagCapturingStartTime;
    private static DateTime secondTeamFlagCapturingStartTime;
    
    private int firstTeamFlagLeftCoord = 19 * tileSize;
    private int firstTeamFlagRightCoord = 25 * tileSize - 1;
    private int firstTeamFlagTopCoord = 0 * tileSize;
    private int firstTeamFlagBottomCoord = 5 * tileSize - 1;
    
    private int secondTeamFlagLeftCoord = 26 * tileSize;
    private int secondTeamFlagRightCoord = 32 * tileSize - 1;
    private int secondTeamFlagTopCoord = 34 * tileSize;
    private int secondTeamFlagBottomCoord = 40 * tileSize - 1;
    
    // Game configs
    private static int tileSize = 64;
    private int mapRows = 40;
    private int mapColumns = 51;
    private int mapWidth = tileSize * mapColumns;
    private int mapHeight = tileSize * mapRows;
    private int playerWindowWidth = 1088;
    private int playerWindowHeight = 576;
    
    private static int playerTileSize = 42;
    private static int missileTileSize = 10;
    private static int firstTeamPlayerSpawnPointX = 5 * tileSize - playerTileSize/2;
    private static int firstTeamPlayerSpawnPointY = 20 * tileSize - playerTileSize/2;
    private static int secondTeamPlayerSpawnPointX = 46 * tileSize - playerTileSize/2;
    private static int secondTeamPlayerSpawnPointY = 20 * tileSize - playerTileSize/2;
    
    private static Map<Session, Player> playersMap = Collections.synchronizedMap(new HashMap<Session, Player>());
    private static List<Missile> missilesList = Collections.synchronizedList(new ArrayList<Missile>());
    private static int firstTeamNumberOfPlayers = 0;
    private static int secondTeamNumberOfPlayers = 0;
    private static int firstTeamScore = 0;
    private static int secondTeamScore = 0;
    
    private PushTimeService() {}
    
    public static void add(Session s) {
    	if(firstTeamNumberOfPlayers <= secondTeamNumberOfPlayers) {
    		Player newPlayer = new Player(firstTeamPlayerSpawnPointX, firstTeamPlayerSpawnPointY, 1);
    		if(playersMap.keySet().size() > 0) {
    			newPlayer.setAlive(false);
    		}
    		playersMap.put(s, newPlayer);
    		firstTeamNumberOfPlayers++;
    	}
    	else {
    		Player newPlayer = new Player(secondTeamPlayerSpawnPointX, secondTeamPlayerSpawnPointY, 2);
    		if(playersMap.keySet().size() > 0) {
    			newPlayer.setAlive(false);
    		}
    		playersMap.put(s, newPlayer);
    		secondTeamNumberOfPlayers++;
    	}
    	if(playersMap.keySet().size() == 2) {
    		//firstTeamScore = 0;
    		//secondTeamScore = 0;
    		roundNumber = 1;
    		for(Player player : playersMap.values()) {
    			player.setAlive(false);
    		}
    		roundStartTime = new DateTime();
    		initializeGame();
    	}
    }
    
    public static void initialize() {
        if (instance == null) {
            instance = new PushTimeService();
            new Thread(instance).start();
        }
    }
    
    public static void launchMissile(Session session, Player player) {
    	for(Missile missile : missilesList) {
    		if(playersMap.get(missile.getPlayerId()) == player) {
    			return;
    		}
    	}
    	
    	int posX = player.getPosX(), posY = player.getPosY();
    	switch(player.getMovementDirection()) {
    	case "up":
    		posX = player.getPosX() + playerTileSize/2 - missileTileSize/2;
    		posY = player.getPosY() - missileTileSize;
    		break;
    	case "down":
    		posX = player.getPosX() + playerTileSize/2 - missileTileSize/2;
    		posY = player.getPosY() + playerTileSize;
    		break;
    	case "left":
    		posX = player.getPosX() - missileTileSize;
    		posY = player.getPosY() + playerTileSize/2 - missileTileSize/2;
    		break;
    	case "right":
    		posX = player.getPosX() + playerTileSize;
    		posY = player.getPosY() + playerTileSize/2 - missileTileSize/2;
    		break;
    	}
    	Missile missile = new Missile(session, posX, posY, player.getRange(), player.getMovementDirection(), player.getSpeed() + 1);
    	missilesList.add(missile);
    }
    
    public static void updatePlayer(Session session, String action) {
    	Player player = playersMap.get(session);
    	if(player.isAlive() == true) {
	    	switch(action) {
	    	case "up":
	    		player.movementBuffer = "up";
	    		player.moveUp = true;
	    		break;
	    	case "down":
	    		player.movementBuffer = "down";
	    		player.moveDown = true;
	    		break;
	    	case "left":
	    		player.movementBuffer = "left";
	    		player.moveLeft = true;
	    		break;
	    	case "right":
	    		player.movementBuffer = "right";
	    		player.moveRight = true;
	    		break;
	    	case "no_up":
	    		player.movementBuffer = "no_up";
	    		player.moveUp = false;
	    		break;
	    	case "no_down":
	    		player.movementBuffer = "no_down";
	    		player.moveDown = false;
	    		break;
	    	case "no_left":
	    		player.movementBuffer = "no_left";
	    		player.moveLeft = false;
	    		break;
	    	case "no_right":
	    		player.movementBuffer = "no_right";
	    		player.moveRight = false;
	    		break;
	    	case "space":
	    		launchMissile(session, player);
	    		break;
	    	}
    	}
    	else {
    		player.moveUp = false;
    		player.moveDown = false;
    		player.moveLeft = false;
    		player.moveRight = false;
    	}
    }
    
    public static void setUsername(Session session, String username) {
    	Player player = playersMap.get(session);
    	player.setUsername(username);
    	
    	TankPicked tp = new TankPicked(username);
    	System.out.println(tp.tank_id);
    	player.setTankId(tp.tank_id);
    	player.setSpeed(tp.speed);
    	player.setFullHealth(tp.health);
    	player.setCurrentHealth(tp.health);
    	player.setDamage(tp.damage);
    	player.setRange(tp.tank_range);
    }
    
    private static void checkMissilesCollisionWithPlayers() {
    	Iterator<Missile> missilesListIterator = missilesList.iterator();
        while(missilesListIterator.hasNext()) {
        	Missile missile = missilesListIterator.next();
        	int missileCenterX = missile.getPosX() + missileTileSize;
        	int missileCenterY = missile.getPosY() + missileTileSize;
        	for(Player player : playersMap.values()) {
        		if(player.isAlive() == true && player.getTeam() != playersMap.get(missile.getPlayerId()).getTeam()) {
	        		int playerLeftEdge = player.getPosX();
	        		int playerRightEdge = playerLeftEdge + tileSize;
	        		int playerTopEdge = player.getPosY();
	        		int playerBottomEdge = playerTopEdge + tileSize;
	        		if(playerLeftEdge <= missileCenterX && missileCenterX <= playerRightEdge
	        				&& playerTopEdge <= missileCenterY && missileCenterY <= playerBottomEdge) {
	        			player.takeHit(playersMap.get(missile.getPlayerId()).getDamage());
	        			if(player.isAlive() == false) {
	        				playersMap.get(missile.getPlayerId()).increaseKills();
	        			}
	        			missilesListIterator.remove();
	        			return;
	        		}
        		}
        	}
        }
    }
    
    private void checkFlagCapturing() {
    	boolean firstTeamFlagBeingCaptured = false;;
    	boolean secondTeamFlagBeingCaptured = false;
    	for(Player player : playersMap.values()) {
    		int centerX = player.getPosX() + playerTileSize / 2;
			int centerY = player.getPosY() + playerTileSize / 2;
			
			// Check first team flag
    		if(player.isAlive() == true && player.getTeam() == 2 && firstTeamFlagBeingCaptured == false) {
    			if(firstTeamFlagLeftCoord <= centerX && centerX <= firstTeamFlagRightCoord &&
    					firstTeamFlagTopCoord <= centerY && centerY <= firstTeamFlagBottomCoord) {
    				firstTeamFlagBeingCaptured = true;
    			}
    		}
			
    		// Check second team flag
    		if(player.isAlive() == true && player.getTeam() == 1 && secondTeamFlagBeingCaptured == false) {
    			if(secondTeamFlagLeftCoord <= centerX && centerX <= secondTeamFlagRightCoord &&
    					secondTeamFlagTopCoord <= centerY && centerY <= secondTeamFlagBottomCoord) {
    				secondTeamFlagBeingCaptured = true;
    			}
    		}
    	}
    	
    	if(firstTeamFlagBeingCaptured == true && firstTeamFlagCapturingStartTime == null) {
    		firstTeamFlagCapturingStartTime = new DateTime();
    	}
    	else if(firstTeamFlagBeingCaptured == false && firstTeamFlagCapturingStartTime != null) {
    		firstTeamFlagCapturingStartTime = null;
    	}
    	
    	if(secondTeamFlagBeingCaptured == true && secondTeamFlagCapturingStartTime == null) {
    		secondTeamFlagCapturingStartTime = new DateTime();
    	}
    	else if(secondTeamFlagBeingCaptured == false && secondTeamFlagCapturingStartTime != null) {
    		secondTeamFlagCapturingStartTime = null;
    	}
    }
    
    private static void initializeGame() {
    	for(Player player : playersMap.values()) {
    		if(player.getTeam() == 1) {
    			player.setPosX(firstTeamPlayerSpawnPointX);
    			player.setPosY(firstTeamPlayerSpawnPointY);
    		}
    		else if(player.getTeam() == 2) {
    			player.setPosX(secondTeamPlayerSpawnPointX);
    			player.setPosY(secondTeamPlayerSpawnPointY);
    		}
    		
    		player.revive();
    	}
    	
    	firstTeamFlagCapturingStartTime = null;
    	secondTeamFlagCapturingStartTime = null;
    }
    
    private void updateGameState() {
    	if(gameEnded == true) {
    		DateTime currentTime = new DateTime();
            Period waitingTimeElapsed = new Period(gameEndedTime, currentTime);
    		if(waitingTimeElapsed.getSeconds() >= 5) {
	    		firstTeamScore = 0;
	    		secondTeamScore = 0;
	    		roundStartTime = new DateTime();
	    		initializeGame();
	    		gameEndedTime = null;
	    		roundNumber = 1;
	    		gameEnded = false;
    		}
    	}
    	else if(playersMap.keySet().size() > 1) {
	    	boolean endOfRound = false;
	    	
	    	// Check if all players from a team are dead
	    	if(firstTeamNumberOfPlayers != 0 && secondTeamNumberOfPlayers != 0) {
		    	int firstTeamAlivePlayers = 0;
		    	int secondTeamAlivePlayers = 0;
		    	for(Player player : playersMap.values()) {
		    		if(player.isAlive() == true) {
		    			if(player.getTeam() == 1) {
		    				firstTeamAlivePlayers++;
		    			}
		    			else if(player.getTeam() == 2) {
		    				secondTeamAlivePlayers++;
		    			}
		    		}
		    	}
		    	if(firstTeamAlivePlayers == 0 && secondTeamAlivePlayers != 0) {
		    		secondTeamScore++;
		    		endOfRound = true;
		    	}
		    	if(firstTeamAlivePlayers != 0 && secondTeamAlivePlayers == 0) {
		    		firstTeamScore++;
		    		endOfRound = true;
		    	}
	    	}
	    	
	    	// Check if time is up for the current round
	    	if(endOfRound == false) {
	    		DateTime currentTime = new DateTime();
	            Period roundTimeElapsed = new Period(roundStartTime, currentTime);
	            if(roundTimeElapsed.getMinutes() == minutesPerRound) {
	            	endOfRound = true;
	            }
	    	}
	    	
	    	// Check if flags have been captured
	    	if(firstTeamFlagCapturingStartTime != null) {
	    		DateTime currentTime = new DateTime();
	            Period capturingTimeElapsed = new Period(firstTeamFlagCapturingStartTime, currentTime);
	            if(capturingTimeElapsed.getMinutes() >= flagCapturingMinutesNeeded) {
	            	secondTeamScore++;
	            	endOfRound = true;
	            }
	    	}
	    	if(secondTeamFlagCapturingStartTime != null) {
	    		DateTime currentTime = new DateTime();
	            Period capturingTimeElapsed = new Period(secondTeamFlagCapturingStartTime, currentTime);
	            if(capturingTimeElapsed.getMinutes() >= flagCapturingMinutesNeeded) {
	            	firstTeamScore++;
	            	endOfRound = true;
	            }
	    	}
	    	
	    	// If current round has ended, reinitialize the game
	    	if(endOfRound == true) {
	    		roundNumber++;
	    		
	    		// Check if the game has ended
	    		if(roundNumber > roundsPerGame) {
	    			gameEnded = true;
	    			if(firstTeamScore > secondTeamScore) {
	    				winnerTeam = 1;
	    			}
	    			else if(firstTeamScore < secondTeamScore) {
	    				winnerTeam = 2;
	    			}
	    			else {
	    				winnerTeam = 0;
	    			}
	    			gameEndedTime = new DateTime();
	    			return;
	    		}
	    		
	    		roundStartTime = new DateTime();
	    		initializeGame();
	    	}
    	}
    }
    
    @Override
    public void run() {
        while (true) {
            try {
                Thread.sleep(10);
                
                // Update game state
                updateGameState();
                
                // Update players
                for (Session key : playersMap.keySet()) {
                	Player player = playersMap.get(key);
                	
                	player.Update(mapWidth, mapHeight, playerTileSize);
                }
                
                // Update missiles
                Iterator<Missile> missilesListIterator = missilesList.iterator();
                while(missilesListIterator.hasNext()) {
                	Missile missile = missilesListIterator.next();
                	if(missile.checkIfExploded() == true) {
                		missilesListIterator.remove();
                	}
                	else {
                		missile.update();
                	}
                }
                
                // Check missiles collision with players
                checkMissilesCollisionWithPlayers();
                
                // Check if flags are being captured
                checkFlagCapturing();
                
                // Send data to users
                DateTime currentTime = new DateTime();
                Period roundTimeElapsed = new Period(roundStartTime, currentTime);
                
                Message[] messages = new Message[playersMap.keySet().size() + missilesList.size() + 1];
                
                messages[0] = new Message();
                messages[0].setNumberOfPlayers(playersMap.keySet().size());
                messages[0].setNumberOfMissiles(missilesList.size());
                messages[0].setTileSize(PushTimeService.tileSize);
                messages[0].setMissileTileSize(PushTimeService.missileTileSize);
                messages[0].setPlayerTileSize(playerTileSize);
                messages[0].setRoundTimeElapsed(Integer.toString(roundTimeElapsed.getMinutes()) + ":" + Integer.toString(roundTimeElapsed.getSeconds()));
                messages[0].setRoundNumber(roundsPerGame - roundNumber);
                messages[0].setFirstTeamScore(firstTeamScore);
                messages[0].setSecondTeamScore(secondTeamScore);
                messages[0].setPlayerWindowWidth(playerWindowWidth);
                messages[0].setPlayerWindowHeight(playerWindowHeight);
                messages[0].setMapWidth(mapWidth);
                messages[0].setMapHeight(mapHeight);
                messages[0].setGameEnded(gameEnded);
                messages[0].setWinnerTeam(winnerTeam);
                if(firstTeamFlagCapturingStartTime != null) {
                	currentTime = new DateTime();
    	            Period capturingTimeElapsed = new Period(firstTeamFlagCapturingStartTime, currentTime);
    	            messages[0].setFirstTeamFlagCapturedSeconds(capturingTimeElapsed.getMinutes() * 60 + capturingTimeElapsed.getSeconds());
                }
                else {
                	messages[0].setFirstTeamFlagCapturedSeconds(0);
                }
                if(secondTeamFlagCapturingStartTime != null) {
                	currentTime = new DateTime();
    	            Period capturingTimeElapsed = new Period(secondTeamFlagCapturingStartTime, currentTime);
    	            messages[0].setSecondTeamFlagCapturedSeconds(capturingTimeElapsed.getMinutes() * 60 + capturingTimeElapsed.getSeconds());
                }
                else {
                	messages[0].setSecondTeamFlagCapturedSeconds(0);
                }
                
                int index = 1;
                for (Session key : playersMap.keySet()) {
                	Player player = playersMap.get(key);
                	
                	messages[index] = new Message();
                	messages[index].setPosX(player.getPosX());
                	messages[index].setPosY(player.getPosY());
                	messages[index].setPlayerFullHealth(player.getFullHealth());
                	messages[index].setPlayerCurrentHealth(player.getCurrentHealth());
                	messages[index].setTeam(player.getTeam());
                	messages[index].setAlive(player.isAlive());
                	messages[index].setUsername(player.getUsername());
                	messages[index].setKills(player.getKills());
                	messages[index].setDeaths(player.getDeaths());
                	messages[index].setMovementDirection(player.getMovementDirection());
                	messages[index].setTankId(player.getTankId());
                	
                	index++;
                }
                for (Missile missile : missilesList) {
                	messages[index] = new Message();
                	messages[index].setPosX(missile.getPosX());
                	messages[index].setPosY(missile.getPosY());

                	index++;
                }

                MessageEncoder msgEnc = new MessageEncoder();
                index = 0;
                for (Session key : playersMap.keySet()) {
                	messages[0].setIndex(index);
                	index++;
                	
                    if (key.isOpen()) {
                    	String messageToSend = msgEnc.encode(messages);
                    	key.getBasicRemote().sendText(messageToSend);
                    } else {
                    	if(playersMap.get(key).getTeam() == 1) {
                    		firstTeamNumberOfPlayers--;
                    	}
                    	else {
                    		secondTeamNumberOfPlayers--;
                    	}
                        playersMap.remove(key);
                    }
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }
    
}