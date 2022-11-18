package JSON;

import java.util.ArrayList;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

public class JSONSerializing {

	public JSONObject parseJSON(String pMsg) throws JSONException{ //json.parseJSON("100,200:1,2,0;1,2,0:10,10");
		
		if(pMsg == null) {
			//System.out.println("parse: "+ pMsg);
			return null;
		}
		//System.out.println("parse: "+ pMsg);
		String[] parse = pMsg.split(":");
		int posRobot1[] = new int[2];
		int posStrike1[] = null;
		ArrayList<int[]> bull = new ArrayList<int[]>();
		ArrayList<int[]> trampasArray = new ArrayList<int[]>();
		String energy = "";
		String segundoJugador = "";
		
		if (parse.length >= 1) {
			if (parse[0]!="") {
				String[] posRobot = parse[0].split(",");
				posRobot1[0] = Integer.parseInt(posRobot[0]);
				posRobot1[1] = Integer.parseInt(posRobot[1]);	
			}
			
			
		}
		if(parse.length >= 2 ) {
			String[] pBullets = parse[1].split(";");
			//System.out.println("parse: "+ pMsg);
			if (pBullets.length >= 1) {
				for (String balas : pBullets) {
					String[] bullets = balas.split(",");
					//System.out.println("bullest: "+ bullets[0]+" "+bullets[1]+" "+bullets[2]);
					if(bullets.length < 2) {
						continue;
					}
					int x = Integer.parseInt(bullets[0]);
					int y = Integer.parseInt(bullets[1]);
					int c = Integer.parseInt(bullets[2]);
					int coord[] = {x,y,c};
					//System.out.println(x+" "+y+" "+c);
					bull.add(coord);

				}
			}
			
			
		}
		if(parse.length >= 3 ) {
			if (parse[2]!="") {
				String[] posStrike = parse[2].split(",");
				posStrike1 = new int[2];
				posStrike1[0] = Integer.parseInt(posStrike[0]);
				posStrike1[1] =	Integer.parseInt(posStrike[1]);
			}
		}
		
		if(parse.length >= 4 ) {
			String[] trampas = parse[3].split(";");
			
			if (trampas.length >= 1) {
				for (String trampa : trampas) {
					String[] trampS = trampa.split(",");
					if(trampS.length < 2) {
						continue;
					}
					int x = Integer.parseInt(trampS[0]);
					int y = Integer.parseInt(trampS[1]);
					int c = Integer.parseInt(trampS[2]);
					int coord[] = {x,y,c};
					//System.out.println(x+" "+y+" "+c);
					trampasArray.add(coord);

				}
			}
		}
		
		if(parse.length >= 5 ) {
			
			if(parse[4].length()>=1) {
				energy = parse[4];
			}
			
		}
		
		if(parse.length == 6 ) {
			
			if(parse[5].length()>=1) {
				segundoJugador = parse[5];
			}
			
		}
		return Serializate(posRobot1,posStrike1,bull,trampasArray,energy,segundoJugador);
	}

	public JSONObject Serializate(int posRobot[],int posStrike[], ArrayList<int[]>pBullets,ArrayList<int[]>pTrampas,String pEnergy,String pSegundo) throws JSONException {
		JSONObject data = new JSONObject();
		
		if(posRobot[0] != 0 && posRobot[1]!=0) {
			data.put("RobotPosX", posRobot[0]);
	        data.put("RobotPosY", posRobot[1]);
		}else {
			data.put("RobotPosX", "");
	        data.put("RobotPosY", "");
		}
        
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
        
        JSONArray trampas = new JSONArray();
        if(pTrampas.size()>0) {
        	
        	for (int coord[] : pTrampas) {
        		JSONArray coords = new JSONArray();
        		coords.put(coord[0]); //X
        		coords.put(coord[1]); //Y
        		coords.put(coord[2]); //DIREC
        		trampas.put(coords);        		
        	}
        	 
        }
        data.put("Trampas", trampas);
        if(pSegundo.length() > 0 && pSegundo != "") {
        	data.put("Segundo", pSegundo);
        }
        
        
        data.put("Energy", pEnergy);
        
        String userJson = data.toString();
       
        System.out.println(userJson);
        
		return data;
	}
}
