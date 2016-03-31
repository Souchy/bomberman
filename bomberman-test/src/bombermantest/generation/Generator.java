package bombermantest.generation;

import com.badlogic.gdx.utils.Array;
import com.mygdx.engine.game.AGame;
import com.mygdx.engine.objects.items.Item;

public interface Generator {
	
	public Array<Item> generate(AGame game, boolean prePublish);

}
