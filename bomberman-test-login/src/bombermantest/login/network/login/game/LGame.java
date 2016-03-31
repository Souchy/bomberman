package bombermantest.login.network.login.game;

import com.badlogic.gdx.utils.Disposable;

import bombermantest.enums.GameMode;

public class LGame implements Disposable { //extends ServerClient {

	//public LGame(IoSession session) {
	//	super(session);
	//}
	
	public String playerUsername; // the owner of the gameserver
	public String playerPassword;
	
	public String serverName;
	public String serverPassword;

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
