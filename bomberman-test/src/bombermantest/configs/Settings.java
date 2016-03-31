package bombermantest.configs;

import com.mygdx.engine.services.JsonLoader;

public class Settings {
	
	private static Settings singleton;
	
	public static Settings get(){
		if(singleton == null) singleton = JsonLoader.singleton.loadJson(Settings.class);
		if(singleton == null) JsonLoader.singleton.saveJson(singleton = new Settings());
		return singleton;
	}

}
