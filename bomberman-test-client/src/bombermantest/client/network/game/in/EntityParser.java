package bombermantest.client.network.game.in;

import static com.mygdx.engine.configs.AConstants.getVector3;

import org.apache.mina.core.buffer.IoBuffer;
import org.apache.mina.core.session.IoSession;

import com.badlogic.gdx.math.Vector3;
import com.mygdx.engine.objects.Box2dObject;

import bombermantest.network.entities.Entities;
import bombermantest.network.packets.Parser;

public class EntityParser implements Parser {

	@Override
	public void parse(IoSession session, IoBuffer buf) {
		Entities type = buf.getEnum(Entities.class);
		Object entity = type.decode(buf);
		
		if(type.isBox2dObject()){
			Box2dObject box = (Box2dObject) entity;
			Vector3 pos = getVector3(buf, new Vector3()); // 3 * 4
			box.gfx.transform.setTranslation(pos); //set la gfxpos pour que le body soit placé dessus quand .build() et .toBox2d() est callé
			box.publish();
		}
	}

}
