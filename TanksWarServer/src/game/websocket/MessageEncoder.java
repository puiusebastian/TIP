package game.websocket;

import javax.websocket.EncodeException;
import javax.websocket.Encoder;
import javax.websocket.EndpointConfig;
import javax.json.*;

public class MessageEncoder implements Encoder.Text<Message[]> {

    @Override
    public String encode(final Message[] messages) throws EncodeException {
    	JsonArrayBuilder arrayBuilder = Json.createArrayBuilder();
    	JsonObjectBuilder objectBuilder = Json.createObjectBuilder();
    	
    	arrayBuilder.add(Json.createObjectBuilder()
    			.add("index", messages[0].getIndex())
    			.add("numberOfPlayers", messages[0].getNumberOfPlayers())
    			.add("numberOfMissiles", messages[0].getNumberOfMissiles())
    			);
    	
    	
    	for(int i = 1; i < messages.length; ++i) {
    		objectBuilder.add("posX", messages[i].getPosX());
    		objectBuilder.add("posY", messages[i].getPosY());
    		if(messages[i].getMovementDirection() != null) {
    			objectBuilder.add("movementDirection", messages[i].getMovementDirection());
    		}
    		arrayBuilder.add(objectBuilder);
    		
    		/*arrayBuilder.add(Json.createObjectBuilder()
    				.add("posX", messages[i].getPosX())
    				.add("posY", messages[i].getPosY())
    				.add("movementDirection", messages[i].getMovementDirection())
    				);*/
    	}
    	
    	return arrayBuilder.build().toString();
    			
        /*return Json.createObjectBuilder()
                       .add("message", message.getContent())
                       .add("sender", message.getSender())
                       .add("received", "")
                       .build().toString();
                       */
    }

	@Override
	public void destroy() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void init(EndpointConfig arg0) {
		// TODO Auto-generated method stub
		
	}

}