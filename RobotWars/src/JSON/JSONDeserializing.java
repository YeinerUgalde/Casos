package JSON;

import java.util.ArrayList;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import Server.DataManager;

public class JSONDeserializing {
	
	private DataManager dataManager;
	private ArrayList<int[]> bullets;
	private ArrayList<int[]> trampas;
	private int robotPos[];
	private int strikePos[];
	public JSONDeserializing() {
		
		dataManager = DataManager.getInstance();
		
		robotPos = new int[2];
		
	}
	
	
	
	public String Deserializing(JSONObject pJson) throws JSONException {
		if (pJson == null)return null;
		if(pJson.get("RobotPosX") != "" && pJson.get("RobotPosY")!= "") {
			robotPos[0] = (int) pJson.get("RobotPosX");
			robotPos[1] = (int) pJson.get("RobotPosY");
		}
		
		
		dataManager.setRobotPos(robotPos);
		
		if (pJson.get("StrikePosX") != null && pJson.get("StrikePosX") != "") {
			strikePos = new int[2];
	
			strikePos[0] = (int) pJson.get("StrikePosX");
			strikePos[1] = (int) pJson.get("StrikePosY");
			
			dataManager.setStrikePos(strikePos);
		}
		
		if (pJson.get("Bullets") != null) {
			
			bullets = new ArrayList<int[]>();			
			JSONArray bulletsJson = (JSONArray) pJson.get("Bullets");
			
			for(int i = 0; i < bulletsJson.length(); i++) {
				JSONArray data = bulletsJson.getJSONArray(i);
				int coord[] = {(int)data.get(0), (int)data.get(1), (int)data.get(2)};
				bullets.add(coord);
			}
			
			dataManager.setBullets(bullets);
		}
		
		if (pJson.get("Trampas") != null) {
			
			trampas = new ArrayList<int[]>();			
			JSONArray trampasJSON = (JSONArray) pJson.get("Trampas");
			
			for(int i = 0; i < trampasJSON.length(); i++) {
				JSONArray data = trampasJSON.getJSONArray(i);
				int coord[] = {(int)data.get(0), (int)data.get(1), (int)data.get(2)};
				trampas.add(coord);
			}
			
			dataManager.setTrampas(trampas);
		}
		
		if (pJson.get("Energy") != null) {
			
						
			String energy = (String) pJson.get("Energy");
			
			if(energy != "") {
				dataManager.setEnergy(energy);
			}
			
			
		}
		
		if (pJson.has("Segundo") ) {
			
			
			dataManager.setSegundoJugador(true);
			
		}
		
		
		dataManager.run();
        
		return null;
	}

	public ArrayList<int[]> getBullets() {
		return bullets;
	}

	public int[] getRobotPos() {
		return robotPos;
	}

	public int[] getStrikePos() {
		return strikePos;
	}
}

