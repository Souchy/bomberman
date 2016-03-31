package bombermantest.network.packets.enums;

import java.util.Collection;

import org.apache.mina.core.buffer.IoBuffer;
import org.apache.mina.core.session.IoSession;

import bombermantest.network.packets.Composer;
import bombermantest.network.packets.IPacket;
import bombermantest.network.packets.Packet;
import bombermantest.network.packets.Parser;

public enum LoginClientPackets implements IPacket {
	
	AUTHENTIFICATION(true, 0, 0), //(new AuthentificationParser(), new AuthentificationComposer(), true, 0, 0),
	
	AUTHENTIFICATION_RESULT(false, 1, 0), //(new AuthentificationResultParser(), new AuthentificationResultComposer(), false, 1, 0),
	
	ASK_GAME_SERVER_LIST(false, 0, 0), //(new AskGameServerListParser(), new AskGameServerListComposer(), false, 0, 0),
	GAME_SERVER_LIST(true, 0, 0), //(new GameServerListParser(), new GameServerListComposer(), true, 0, 0),
	
	SERVER_SELECTION(false, 8, 0),
	SERVER_SELECTION_RESULT(true, 0, 0),
	;
	
	/*
	public Parser parser;
	public Composer composer;
	public final autoexpand;
	public final baseLength
	public final mult;
	*/
	
	private final Packet packet;
	
	private LoginClientPackets(boolean autoexpand, int baseLength, int playerMult){
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
	public void broadcast(Collection<IoSession> sessions, Object... objects) {
		packet.broadcast(sessions, objects);
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
