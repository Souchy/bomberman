package bombermantest.configs;

import com.mygdx.engine.services.JsonLoader;

public class Test extends com.mygdx.engine.configs.Test {

	private static Test singleton;
	
	/**
	 * Pourcentage
	 */
	public int crateGenerationRate; 

	public static Test get(){
		if(singleton == null) singleton = JsonLoader.singleton.loadJson(Test.class); // si null, on load
		if(singleton == null) JsonLoader.singleton.saveJson(singleton = new Test()); // si encore null (ex. le fichier n'existait pas) on créé un nouveau et save
		return singleton;
	}

}
