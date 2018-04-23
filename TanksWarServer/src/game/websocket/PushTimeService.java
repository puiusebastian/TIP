package game.websocket;

import java.util.*;
import javax.websocket.Session;

import org.joda.time.DateTime;
import org.joda.time.Period;

import game.Missile;
import game.Player;

public class PushTimeService implements Runnable {
    
    private static PushTimeService instance;
    
    private DateTime roundStartTime = new DateTime();
    private int roundNumber = 0;
    private int minutesPerRound = 5;
    private int roundsPerGame = 5;
    
    // Game configs
    private int mapWidth = 1400;
    private int mapHeight = 1200;
    private int playerWindowWidth = 800;
    private int playerWindowHeight = 500;
    private static int tileSize = 30;
    private static int missileSize = 10;
    private static int firstTeamPlayerSpawnPointX = 100;
    private static int firstTeamPlayerSpawnPointY = 250;
    private static int secondTeamPlayerSpawnPointX = 400;
    private static int secondTeamPlayerSpawnPointY = 250;
    
    private static Map<Session, Player> playersMap = Collections.synchronizedMap(new HashMap<Session, Player>());
    private static List<Missile> missilesList = Collections.synchronizedList(new ArrayList<Missile>());
    private static int firstTeamNumberOfPlayers = 0;
    private static int secondTeamNumberOfPlayers = 0;
    private int firstTeamScore = 0;
    private int secondTeamScore = 0;
    
    private PushTimeService() {}
    
    public static void add(Session s) {
    	if(firstTeamNumberOfPlayers <= secondTeamNumberOfPlayers) {
    		playersMap.put(s, new Player(firstTeamPlayerSpawnPointX, firstTeamPlayerSpawnPointY, 1));
    		firstTeamNumberOfPlayers++;
    	}
    	else {
    		playersMap.put(s, new Player(secondTeamPlayerSpawnPointX, secondTeamPlayerSpawnPointY, 2));
    		secondTeamNumberOfPlayers++;
    	}
    }
    
    public static void initialize() {
        if (instance == null) {
            instance = new PushTimeService();
            new Thread(instance).start();
        }
    }
    
    public static void launchMissile(Session session, Player player) {
    	int posX = player.getPosX(), posY = player.getPosY();
    	switch(player.getMovementDirection()) {
    	case "up":
    		posX = player.getPosX() + tileSize/2 - missileSize/2;
    		posY = player.getPosY() - missileSize;
    		break;
    	case "down":
    		posX = player.getPosX() + tileSize/2 - missileSize/2;
    		posY = player.getPosY() + tileSize;
    		break;
    	case "left":
    		posX = player.getPosX() - missileSize;
    		posY = player.getPosY() + tileSize/2 - missileSize/2;
    		break;
    	case "right":
    		posX = player.getPosX() + tileSize;
    		posY = player.getPosY() + tileSize/2 - missileSize/2;
    		break;
    	}
    	Missile missile = new Missile(session, posX, posY, 100, player.getMovementDirection(), 3);
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
    
    private static void checkMissilesCollisionWithPlayers() {
    	Iterator<Missile> missilesListIterator = missilesList.iterator();
        while(missilesListIterator.hasNext()) {
        	Missile missile = missilesListIterator.next();
        	int missileCenterX = missile.getPosX() + missileSize;
        	int missileCenterY = missile.getPosY() + missileSize;
        	for(Player player : playersMap.values()) {
        		if(player.getTeam() != playersMap.get(missile.getPlayerId()).getTeam()) {
	        		int playerLeftEdge = player.getPosX();
	        		int playerRightEdge = playerLeftEdge + tileSize;
	        		int playerTopEdge = player.getPosY();
	        		int playerBottomEdge = playerTopEdge + tileSize;
	        		if(playerLeftEdge <= missileCenterX && missileCenterX <= playerRightEdge
	        				&& playerTopEdge <= missileCenterY && missileCenterY <= playerBottomEdge) {
	        			player.setAlive(false);
	        			missilesListIterator.remove();
	        			return;
	        		}
        		}
        	}
        }
    }
    
    private void initializeGame() {
    	for(Player player : playersMap.values()) {
    		if(player.getTeam() == 1) {
    			player.setPosX(firstTeamPlayerSpawnPointX);
    			player.setPosY(firstTeamPlayerSpawnPointY);
    		}
    		else if(player.getTeam() == 2) {
    			player.setPosX(secondTeamPlayerSpawnPointX);
    			player.setPosY(secondTeamPlayerSpawnPointY);
    		}
    		
    		player.setAlive(true);
    	}
    }
    
    private void updateGameState() {
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
    	
    	// If current round has ended, reinitialize the game
    	if(endOfRound == true) {
    		roundNumber++;
    		roundStartTime = new DateTime();
    		initializeGame();
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
                	
                	player.Update();
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
                
                // Send data to users
                DateTime currentTime = new DateTime();
                Period roundTimeElapsed = new Period(roundStartTime, currentTime);
                
                Message[] messages = new Message[playersMap.keySet().size() + missilesList.size() + 1];
                
                messages[0] = new Message();
                messages[0].setNumberOfPlayers(playersMap.keySet().size());
                messages[0].setNumberOfMissiles(missilesList.size());
                messages[0].setTileSize(PushTimeService.tileSize);
                messages[0].setMissileSize(PushTimeService.missileSize);
                messages[0].setRoundTimeElapsed(Integer.toString(roundTimeElapsed.getMinutes()) + ":" + Integer.toString(roundTimeElapsed.getSeconds()));
                messages[0].setRoundNumber(roundNumber);
                messages[0].setFirstTeamScore(firstTeamScore);
                messages[0].setSecondTeamScore(secondTeamScore);
                messages[0].setPlayerWindowWidth(playerWindowWidth);
                messages[0].setPlayerWindowHeight(playerWindowHeight);
                
                int index = 1;
                for (Session key : playersMap.keySet()) {
                	Player player = playersMap.get(key);
                	
                	messages[index] = new Message();
                	messages[index].setPosX(player.getPosX());
                	messages[index].setPosY(player.getPosY());
                	messages[index].setTeam(player.getTeam());
                	messages[index].setAlive(player.isAlive());
                	messages[index].setMovementDirection(player.getMovementDirection());
                	
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
                	String messageToSend = msgEnc.encode(messages);
                    if (key.isOpen()) {
                    	//System.out.println(msgEnc.encode(messages));
                    	//System.out.println(messageToSend);
                        //s.getBasicRemote().sendText(Integer.toString(x));
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