package bombermantest.objects.characters.playables;

import com.mygdx.engine.objects.characters.EntityStats;

public class BombermanStats extends EntityStats {

	public BPlayer player;
	
	public int nbBombsMax = 1;
	public int nbBombs = 1;
	public int power = 2;
	public float speed = 0;
	
	
	/*public long clientid;
	public String name = "Roshi";
	public int wins = 0;
	public int kills = 0;
	public int deaths = 0;*/
	
	//public ClientState state = ClientState.SPECTATING;
	
	
	public float getTotSpeed(){
		return accelForce.x + speed;
	}
	
}
