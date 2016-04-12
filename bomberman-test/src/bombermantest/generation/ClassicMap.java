package bombermantest.generation;

import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.g3d.attributes.ColorAttribute;
import com.badlogic.gdx.math.Vector3;
import com.badlogic.gdx.utils.Array;
import com.mygdx.engine.configs.AConstants;
import com.mygdx.engine.game.AGame;
import com.mygdx.engine.objects.items.Item;
import com.mygdx.engine.objects.items.scenery.wall.UnbreakableBlock;

import bombermantest.objects.items.scenery.solid.Crate;

public class ClassicMap implements Generator {
	
	private static int mapWidth = 64;
	
	@Override
	public Array<Item> generate(AGame game, boolean usePostRunnable){
		Array<Item> map = new Array<>();
		
		ColorAttribute white = new ColorAttribute(ColorAttribute.Diffuse, Color.WHITE);
		ColorAttribute gray = new ColorAttribute(ColorAttribute.Diffuse, Color.GRAY);
		ColorAttribute black = new ColorAttribute(ColorAttribute.Diffuse, Color.BLACK);
		
		for(int y = 0; y < mapWidth; y++){
			for(int x = 0; x < mapWidth; x++){
				// Plancher
				UnbreakableBlock floor = new UnbreakableBlock(game, new Vector3(x, y, -1), "tilewhite1");
				floor.setColor(white);
				floor.publish(usePostRunnable);
				map.add(floor);
				
				// Obstacles
				if(y % 2 == 0 && x % 2 == 0){
					UnbreakableBlock wall = new UnbreakableBlock(game, new Vector3(x, y, 0), "tilewhite1"); //UnbreakableBlock.getModelName());
					wall.setColor(gray);
					wall.publish(usePostRunnable);
					map.add(wall);
				}
				else if (AConstants.rnd.nextBoolean()){
					Crate crate = new Crate(game, new Vector3(x, y, 0));
					//crate.setColor(brown); déjà fait dans le constructeur de Crate
					crate.publish(usePostRunnable); 
					map.add(crate);
				}
			}
		}

		UnbreakableBlock floor1 = new UnbreakableBlock(game, new Vector3(0, -10, -1), "tilewhite1");
		floor1.setColor(white);
		floor1.publish(usePostRunnable);
		map.add(floor1);

		UnbreakableBlock floor2 = new UnbreakableBlock(game, new Vector3(10, -10, -1), "tilewhite1");
		floor2.setColor(white);
		floor2.publish(usePostRunnable);
		map.add(floor2);
		
		return map;
	}

	
}
