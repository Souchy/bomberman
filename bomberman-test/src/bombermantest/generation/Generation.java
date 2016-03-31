package bombermantest.generation;

import com.badlogic.gdx.utils.Array;
import com.mygdx.engine.game.AGame;
import com.mygdx.engine.objects.items.Item;

public enum Generation implements Generator {

	Classic(new ClassicMap()),
	Octogonal(new OctogonalMap()),
	Illuminati(new IlluminatiMap()),
	;
	
	//public static Generation currentMap;
	public static Array<Item> currentMap;

	private Generator generator;
	
	private Generation(Generator gen){
		generator = gen;
	}
		
	@Override
	public Array<Item> generate(AGame game, boolean prePublish) {
		//currentMap = this;
		return currentMap = generator.generate(game, prePublish);
	}

}
