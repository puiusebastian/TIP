package game.websocket;

import java.io.IOException;
import java.util.Collections;
import java.util.HashSet;
import java.util.Set;

import javax.websocket.DecodeException;
import javax.websocket.EncodeException;
import javax.websocket.OnClose;
import javax.websocket.OnError;
import javax.websocket.OnMessage;
import javax.websocket.OnOpen;
import javax.websocket.Session;
import javax.websocket.server.ServerEndpoint;

@ServerEndpoint("/websocketendpoint")
public class WebsocketServer {
	
	static Set<Session> peers = Collections.synchronizedSet(new HashSet<Session>());
	
	@OnOpen
	public void onOpen(Session session) {
		System.out.println("Connection open...");
		peers.add(session);
		
		PushTimeService.initialize();
        PushTimeService.add(session);
	}
	
	@OnClose
    public void onClose(Session session) throws IOException, EncodeException {
        System.out.println(session.getId() + " left the chat room.");
        peers.remove(session);
    }
	
	@OnMessage
	public void onMessage(String message, Session session) throws IOException, DecodeException{
		if(message.indexOf('=') > 0) {
			String[] parts = message.split("=");
			if(parts[0].equals("username") && parts[1] != null && parts[1].isEmpty() == false) {
				PushTimeService.setUsername(session, parts[1]);
			}
		}
		else {
			PushTimeService.updatePlayer(session, message);
		}
	}
	
	@OnError
	public void onError(Throwable e){
		e.printStackTrace();
	}
	
}
