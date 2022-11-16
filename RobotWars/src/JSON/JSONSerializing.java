package JSON;

import java.util.ArrayList;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

public class JSONSerializing {

	public JSONObject parseJSON(String pMsg) throws JSONException{ //json.parseJSON("100,200:1,2,0;1,2,0:10,10");
		String[] parse = pMsg.split(":");
		int posRobot1[] = new int[2];
		int posStrike1[] = null;
		ArrayList<int[]> bull = new ArrayList<int[]>();
		
		if (parse.length >= 1) {
			String[] posRobot = parse[0].split(",");
			posRobot1[0] = Integer.parseInt(posRobot[0]);
			posRobot1[1] = Integer.parseInt(posRobot[1]);
			
		}
		if(parse.length >= 2) {
			String[] pBullets = parse[1].split(";");
		
			if (pBullets.length >= 1) {
				for (String balas : pBullets) {
					String[] bullets = balas.split(",");
			
					int x = Integer.parseInt(bullets[0]);
					int y = Integer.parseInt(bullets[1]);
					int c = Integer.parseInt(bullets[2]);
					int coord[] = {x,y,c};
					//System.out.println(x+" "+y+" "+c);
					bull.add(coord);

				}
			}
			
			
		}
		if(parse.length == 3 ) {
			String[] posStrike = parse[2].split(",");
			posStrike1 = new int[2];
			posStrike1[0] = Integer.parseInt(posStrike[0]);
			posStrike1[1] =	Integer.parseInt(posStrike[1]);
		}
		
		return Serializate(posRobot1,posStrike1,bull);
	}

	public JSONObject Serializate(int posRobot[],int posStrike[], ArrayList<int[]>pBullets) throws JSONException {
		JSONObject data = new JSONObject();
		
		
        data.put("RobotPosX", posRobot[0]);
        data.put("RobotPosY", posRobot[1]);
        if (posStrike!= null && posStrike.length > 0) {
        	data.put("StrikePosX", posStrike[0]);
            data.put("StrikePosY", posStrike[1]);
        }else {
        	data.put("StrikePosX", "");
            data.put("StrikePosY", "");
        }
        
        
        JSONArray bullets = new JSONArray();
        if(pBullets.size()>0) {
        	
        	for (int coord[] : pBullets) {
        		JSONArray coords = new JSONArray();
        		coords.put(coord[0]); //X
        		coords.put(coord[1]); //Y
        		coords.put(coord[2]); //DIREC
        		bullets.put(coords);        		
        	}
        	 
        }
        data.put("Bullets", bullets);
       
        
        String userJson = data.toString();
       
     
		return data;
	}
}
