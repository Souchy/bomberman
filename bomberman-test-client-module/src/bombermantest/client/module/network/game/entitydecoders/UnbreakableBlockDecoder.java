package bombermantest.client.module.network.game.entitydecoders;

import java.nio.charset.CharacterCodingException;

import org.apache.mina.core.buffer.IoBuffer;

import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.math.Vector3;
import com.mygdx.engine.configs.AConstants;
import com.mygdx.engine.objects.items.scenery.wall.UnbreakableBlock;
import com.mygdx.engine.services.ModelsLoader;

import bombermantest.client.main.ClientGame;
import bombermantest.network.entities.EntityDecoder;
import bombermantest.network.packets.Parser;

public class UnbreakableBlockDecoder implements EntityDecoder<UnbreakableBlock> {

	@Override
	public UnbreakableBlock decode(IoBuffer buf) {
		UnbreakableBlock block = null;
		try {
			int red = buf.getInt();
			int green = buf.getInt();
			int blue = buf.getInt();
			
			//int gfxId = buf.getInt();
			//String gfxName = ModelsLoader.singleton.keyOfIndex(gfxId);
			String gfxName = buf.getPrefixedString(Parser.decoder);
			//System.out.println("Decoding, gfxid = ["+gfxId+"], gfxid2 = ["+gfxId2+"], gfxname =["+gfxName+"]");
			
			block = new UnbreakableBlock(ClientGame.get(), Vector3.Zero, gfxName);
			
			Color c = AConstants.rgbToColor(red, green, blue);
			block.setColor(c);
			
			//block.prePublish();

		} catch (CharacterCodingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return block;
	}

}
