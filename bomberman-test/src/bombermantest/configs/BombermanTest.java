package bombermantest.configs;

import com.mygdx.engine.configs.Test;
import com.mygdx.engine.services.JsonLoader;

public class BombermanTest extends Test {

	//private static BombermanTest singleton;
	
	/**
	 * Pourcentage/chance de spawn une boîte, par case.
	 */
	public int crateGenerationRate; 

	public static BombermanTest get(){
		if(singleton == null) singleton = JsonLoader.singleton.loadJson(BombermanTest.class); // si null, on load
		if(singleton == null) JsonLoader.singleton.saveJson(singleton = new BombermanTest()); // si encore null (ex. le fichier n'existait pas) on créé un nouveau et save
		return (BombermanTest) singleton;
	}

}
