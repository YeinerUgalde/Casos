package JSON;

import java.util.ArrayList;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import Server.DataManager;

public class JSONDeserializing {
	
	private DataManager dataManager;
	private ArrayList<int[]> bullets;
	private int robotPos[];
	private int strikePos[];
	public JSONDeserializing() {
		
		dataManager = DataManager.getInstance();
		
		robotPos = new int[2];
		
	}
	
	
	
	public String Deserializing(JSONObject pJson) throws JSONException {
		if (pJson == null)return null;
		robotPos[0] = (int) pJson.get("RobotPosX");
		robotPos[1] = (int) pJson.get("RobotPosY");
		
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

