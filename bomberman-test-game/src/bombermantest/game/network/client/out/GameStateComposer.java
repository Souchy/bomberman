package bombermantest.game.network.client.out;

import org.apache.mina.core.buffer.IoBuffer;
import org.apache.mina.core.session.IoSession;

import bombermantest.enums.GameState;
import bombermantest.network.packets.Composer;

public class GameStateComposer implements Composer {

	@Override
	public IoBuffer compose(IoBuffer buf, IoSession session, Object... objects) {
		byte onConnect = (byte) ((boolean) objects[0] ? 1 : 0);
		
		buf.putEnum(GameState.state);
		buf.putLong(GameState.state.timer);
		buf.put(onConnect);
		
		//Map<Long, IoSession> sessions = GClientServer.get().acceptor.getManagedSessions();
		
		/*switch(GameState.state){
			case INGAME:
				break;
			case OUTGAME:
				break;
			case PREPARING:
				break;
			default:
				break;
		}*/
		
		return buf;
	}

}
