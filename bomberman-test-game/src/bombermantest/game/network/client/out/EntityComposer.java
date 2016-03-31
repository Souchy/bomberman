package bombermantest.game.network.client.out;

import static com.mygdx.engine.configs.AConstants.putVector3;

import org.apache.mina.core.buffer.IoBuffer;
import org.apache.mina.core.session.IoSession;

import com.mygdx.engine.objects.Box2dObject;

import bombermantest.network.entities.Entities;
import bombermantest.network.packets.Composer;

public class EntityComposer implements Composer {

	@Override
	public IoBuffer compose(IoBuffer buf, IoSession session, Object... objects) {
		Object entity = objects[0];
		Entities type = Entities.valueOf(entity.getClass().getSimpleName());
		buf.putEnum(type);
		type.encode(entity, buf);
		
		if(type.isBox2dObject()){
			Box2dObject box = (Box2dObject) entity;
			putVector3(buf, box.getGfxPos()); // 3* 4
		}
		
		return buf;
	}

}
