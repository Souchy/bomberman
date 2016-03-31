package bombermantest.game.network.client.entityencoders;

import org.apache.mina.core.buffer.IoBuffer;

import com.badlogic.gdx.graphics.g3d.attributes.ColorAttribute;
import com.mygdx.engine.configs.AConstants;
import com.mygdx.engine.objects.items.scenery.wall.UnbreakableBlock;
import com.mygdx.engine.services.ModelsLoader;

import bombermantest.network.entities.EntityEncoder;

public class UnbreakableBlockEncoder implements EntityEncoder<UnbreakableBlock> {

	@Override
	public void encode(UnbreakableBlock item, IoBuffer buf) {

		String name = ModelsLoader.singleton.getAssetFileName(item.gfx.model);
		String gfxname = name.substring(name.lastIndexOf("/")+1, name.indexOf("."));
		
		ColorAttribute c = item.gfx.materials.peek().get(ColorAttribute.class, ColorAttribute.Diffuse);
		int[] rgb = AConstants.colorToRgb(c.color);

		buf.putInt(rgb[0]);
		buf.putInt(rgb[1]);
		buf.putInt(rgb[2]);

		int gfxId = ModelsLoader.singleton.indexOfKey(gfxname);
		buf.putInt(gfxId);
		//System.out.println("Encoding, gfxid = ["+gfxId+"] gfxname =["+gfxname+"]");

	}

}
