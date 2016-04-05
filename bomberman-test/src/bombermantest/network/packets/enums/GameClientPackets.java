package bombermantest.network.packets.enums;

import java.util.Collection;

import org.apache.mina.core.buffer.IoBuffer;
import org.apache.mina.core.session.IoSession;

import bombermantest.network.packets.Composer;
import bombermantest.network.packets.IPacket;
import bombermantest.network.packets.Packet;
import bombermantest.network.packets.Parser;

public enum GameClientPackets implements IPacket {

	AUTHENTIFICATION(true, 0, 0), //(new AuthentificationParser(), new AuthentificationComposer(), true, 0, 0),
	
	AUTHENTIFICATION_RESULT(false, 1 + 8, 0), //(new AuthentificationResultParser(), new AuthentificationResultComposer(), false, 1, 0),

	LOST_PLAYER(true, 0, 0), 
	
	GAME_STATE(false, 1 + 8 + 1, 0),
	
	ENTITY(true, 0, 0),
	ENTITY_LIST(true, 0, 0),
	
	MOVE_PLAYER(true, 0, 0),
	MOVE_PLAYER_LIST(true, 0, 0),
	USE_WEAPON(true, 0, 0),
	
	CHAT(true, 0, 0), 
	COMMAND(true, 0, 0), 
	SUICIDE(false, 8, 0),
	
	;

	private final Packet packet;
	
	private GameClientPackets(boolean autoexpand, int baseLength, int playerMult){
		packet = new Packet(this, null, null, autoexpand, baseLength, playerMult);
	}
	

	@Override
	public void parse(IoSession session, IoBuffer buf) {
		packet.parse(session, buf);
	}

	@Override
	public IoBuffer compose(IoSession session, Object...objects) {
		return packet.compose(session, objects);
	}
	
	@Override
	public void broadcast(Collection<IoSession> collection, Object... objects) {
		packet.broadcast(collection, objects);
	}
	
	@Override
	public IoBuffer allocate() {
		return packet.allocate();
	}

	@Override
	public IoBuffer allocate(int nb) {
		return packet.allocate(nb);
	}

	@Override
	public IoBuffer allocateAutoExpand() {
		return packet.allocateAutoExpand();
	}
	
	@Override
	public boolean isLengthCorrect(int size) {
		return packet.isLengthCorrect(size);
	}
	
	@Override
	public void setParser(Parser p) {
		packet.setParser(p);
	}

	@Override
	public void setComposer(Composer c) {
		packet.setComposer(c);
	}

	
	
}
