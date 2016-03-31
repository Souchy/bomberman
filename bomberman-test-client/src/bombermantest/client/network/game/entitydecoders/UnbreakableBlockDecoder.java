package bombermantest.client.network.game.entitydecoders;

import org.apache.mina.core.buffer.IoBuffer;

import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.math.Vector3;
import com.mygdx.engine.configs.AConstants;
import com.mygdx.engine.objects.items.scenery.wall.UnbreakableBlock;
import com.mygdx.engine.services.ModelsLoader;

import bombermantest.client.main.ClientGame;
import bombermantest.network.entities.EntityDecoder;

public class UnbreakableBlockDecoder implements EntityDecoder<UnbreakableBlock> {

	@Override
	public UnbreakableBlock decode(IoBuffer buf) {
		int red = buf.getInt();
		int green = buf.getInt();
		int blue = buf.getInt();
		
		int gfxId = buf.getInt();
		String gfxName = ModelsLoader.singleton.keyOfIndex(gfxId);
		//System.out.println("Decoding, gfxid = ["+gfxId+"], gfxid2 = ["+gfxId2+"], gfxname =["+gfxName+"]");
		
		UnbreakableBlock block = new UnbreakableBlock(ClientGame.get(), Vector3.Zero, gfxName);
		
		Color c = AConstants.rgbToColor(red, green, blue);
		block.setColor(c);
		
		//block.prePublish();
		
		return block;
	}

}
