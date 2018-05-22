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
    			.add("tileSize", messages[0].getTileSize())
    			.add("missileTileSize", messages[0].getMissileTileSize())
    			.add("playerTileSize", messages[0].getPlayerTileSize())
    			.add("roundTimeElapsed", messages[0].getRoundTimeElapsed())
    			.add("roundNumber", messages[0].getRoundNumber())
    			.add("firstTeamScore", messages[0].getFirstTeamScore())
    			.add("secondTeamScore", messages[0].getSecondTeamScore())
    			.add("playerWindowWidth", messages[0].getPlayerWindowWidth())
    			.add("playerWindowHeight", messages[0].getPlayerWindowHeight())
    			.add("mapWidth", messages[0].getMapWidth())
    			.add("mapHeight", messages[0].getMapHeight())
    			.add("gameEnded", messages[0].isGameEnded())
    			.add("winnerTeam", messages[0].getWinnerTeam())
    			.add("firstTeamFlagCapturedSeconds", messages[0].getFirstTeamFlagCapturedSeconds())
    			.add("secondTeamFlagCapturedSeconds", messages[0].getSecondTeamFlagCapturedSeconds())
    			);
    	
    	for(int i = 1; i < messages.length; ++i) {
    		objectBuilder.add("posX", messages[i].getPosX());
    		objectBuilder.add("posY", messages[i].getPosY());
    		if(messages[i].getMovementDirection() != null) {
    			objectBuilder.add("team", messages[i].getTeam());
    			objectBuilder.add("playerFullHealth", messages[i].getPlayerFullHealth());
    			objectBuilder.add("playerCurrentHealth", messages[i].getPlayerCurrentHealth());
    			objectBuilder.add("alive", messages[i].getAlive());
    			objectBuilder.add("username", messages[i].getUsername());
    			objectBuilder.add("kills", messages[i].getKills());
    			objectBuilder.add("deaths", messages[i].getDeaths());
    			objectBuilder.add("movementDirection", messages[i].getMovementDirection());
    			objectBuilder.add("tankId", messages[i].getTankId());
    		}
    		arrayBuilder.add(objectBuilder);
    	}
    	
    	return arrayBuilder.build().toString();
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