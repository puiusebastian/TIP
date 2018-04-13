package game.websocket;

import java.util.*;
import javax.websocket.Session;

import game.Missile;
import game.Player;
public class PushTimeService implements Runnable {
    
    private static PushTimeService instance;
    //private static Map<String, Session> sMap = new HashMap<String, Session>();
    private static Map<Session, Player> playersMap = new HashMap<Session, Player>();
    private static List<Missile> missilesList = new ArrayList<Missile>();
    
    private PushTimeService() {}
    
    public static void add(Session s) {
        playersMap.put(s, new Player(250, 250));
    }
    
    public static void initialize() {
        if (instance == null) {
            instance = new PushTimeService();
            new Thread(instance).start();
        }
    }
    
    public static void launchMissile(String sessionId, Player player) {
    	Missile missile = new Missile(sessionId, player.getPosX(), player.getPosY(), 100, player.getMovementDirection(), 3);
    	missilesList.add(missile);
    }
    
    public static void updatePlayer(Session session, String action) {
    	Player player = playersMap.get(session);
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
    		launchMissile(session.getId(), player);
    		break;
    	}
    }
    
    @Override
    public void run() {
        while (true) {
            try {
                Thread.sleep(10);
                
                // Update players
                for (Session key : playersMap.keySet()) {
                	Player player = playersMap.get(key);
                	
                	player.Update();
                }
                
                // Update missiles
                for(Missile missile : missilesList) {
                	if(missile.checkIfExploded() == true) {
                		missilesList.remove(missile);
                	}
                	else {
                		missile.update();
                	}
                }
                
                // Send data to users
                Message[] messages = new Message[playersMap.keySet().size() + missilesList.size() + 1];
                
                messages[0] = new Message();
                messages[0].setNumberOfPlayers(playersMap.keySet().size());
                messages[0].setNumberOfMissiles(missilesList.size());
                
                int index = 1;
                for (Session key : playersMap.keySet()) {
                	Player player = playersMap.get(key);
                	
                	messages[index] = new Message();
                	messages[index].setPosX(player.getPosX());
                	messages[index].setPosY(player.getPosY());
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
                    if (key.isOpen()) {
                    	messages[0].setIndex(index);
                    	String messageToSend = msgEnc.encode(messages);
                    	//System.out.println(msgEnc.encode(messages));
                    	//System.out.println(messageToSend);
                        //s.getBasicRemote().sendText(Integer.toString(x));
                    	key.getBasicRemote().sendText(messageToSend);
                    } else {
                        playersMap.remove(key);
                    }
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }
    
}