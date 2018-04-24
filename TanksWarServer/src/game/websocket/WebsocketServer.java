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

	/*@OnClose
	public void onClose(Session session) {
		System.out.println("Connection closed...");
		peers.remove(session);
	}*/
	
	@OnClose
    public void onClose(Session session) throws IOException, EncodeException {
        System.out.println(session.getId() + " left the chat room.");
        peers.remove(session);
        
        //notify peers about leaving the chat room
        /*for (Session peer : peers) {
            Message message = new Message();
            message.setSender("Server");
            message.setContent((String) session.getUserProperties().get("user") + "%s left the chat room");
            //message.setContent("Test left the chat room");
            message.setReceived(new Date());
            
            MessageEncoder msgEnc = new MessageEncoder();
            String msgToSend = msgEnc.encode(message);
            
            peer.getBasicRemote().sendText(msgToSend);
        }*/
    }
	
	@OnMessage
	public void onMessage(String message, Session session) throws IOException, DecodeException{
		//System.out.println("Message from the client: " + message);
		PushTimeService.updatePlayer(session, message);
		//String echoMsg = "Echo from the server : " + message;
		/*MessageDecoder msgDec = new MessageDecoder();
		Message receivedMessage = msgDec.decode(message);
		System.out.println(receivedMessage.getSender());
		System.out.println(receivedMessage.getContent());
		
		for (Session peer : peers) {
            if (!session.getId().equals(peer.getId())) { // do not resend the message to its sender
                //peer.getBasicRemote().sendObject(message);
            	
            	peer.getBasicRemote().sendText(message);
            }
        }*/
	}
	
	/*@OnMessage
    public void onMessage(Message message, Session session) throws IOException, EncodeException {
        System.out.println(message);
		//broadcast the message
        for (Session peer : peers) {
            if (!session.getId().equals(peer.getId())) { // do not resend the message to its sender
                peer.getBasicRemote().sendObject(message);
            }
        }
    }*/
	
	@OnError
	public void onError(Throwable e){
		e.printStackTrace();
	}
	
	/*
	static Set<Session> peers = Collections.synchronizedSet(new HashSet<Session>());

    @OnOpen
    public void onOpen(Session session) {
        System.out.println(session.getId() + " joined the chat room.");
        peers.add(session);
    }

    @OnMessage
    public void onMessage(Message message, Session session) throws IOException, EncodeException {
        //broadcast the message
        for (Session peer : peers) {
            if (!session.getId().equals(peer.getId())) { // do not resend the message to its sender
                peer.getBasicRemote().sendObject(message);
            }
        }
    }

    @OnClose
    public void onClose(Session session) throws IOException, EncodeException {
        System.out.println(session.getId() + " left the chat room.");
        peers.remove(session);
        
        //notify peers about leaving the chat room
        for (Session peer : peers) {
            Message message = new Message();
            message.setSender("Server");
            message.setContent((String) session.getUserProperties().get("user") + "%s left the chat room");
            message.setReceived(new Date());
            peer.getBasicRemote().sendObject(message);
        }
    }*/
	
}
