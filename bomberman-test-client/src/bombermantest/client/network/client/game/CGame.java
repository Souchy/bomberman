package bombermantest.client.network.client.game;

import com.badlogic.gdx.utils.Disposable;

import bombermantest.enums.GameMode;

public class CGame implements Disposable {
	
	public long serverID;
	public String serverName;
	public boolean hasPassword;
	
	public String map; // map name
	public GameMode mode;
	
	public int port;
	public byte nbCapacity;
	public byte nbPlayers;
	
	@Override
	public void dispose() {
		// TODO Auto-generated method stub
		
	}

}
