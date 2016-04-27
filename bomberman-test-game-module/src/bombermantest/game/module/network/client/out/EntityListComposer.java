package bombermantest.game.module.network.client.out;

import java.util.List;

import org.apache.mina.core.buffer.IoBuffer;
import org.apache.mina.core.session.IoSession;

import bombermantest.game.main.GameGame;
import bombermantest.network.packets.Composer;

public class EntityListComposer implements Composer {

	private static EntityComposer comp = new EntityComposer();
	
	@Override
	public IoBuffer compose(IoBuffer buf, IoSession session, Object... objects) {
		@SuppressWarnings("unchecked")
		List<Object> list = (List<Object>) objects[0];
		
		int nb = list.size();
		int pos = buf.position();
		
		buf.putInt(nb);

		boolean removeServerPlayer = false;
		
		for(int i = 0; i < nb; i++){
			Object entity = list.get(i);
			if (entity != GameGame.get().universe.player) {
				comp.compose(buf, session, entity);
			}else{
				removeServerPlayer = true;
			}
		}
		
		if(removeServerPlayer) buf.putInt(pos, nb - 1); // vu qu'on envoie pas le BPlayer du serv, ça fait 1 entity de moins dans le décompte
		
		return buf;
	}

}
