package bombermantest.client.ui.login;

import java.util.ArrayList;
import java.util.List;

import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.InputListener;
import com.badlogic.gdx.scenes.scene2d.ui.Cell;
import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.kotcrab.vis.ui.widget.VisTable;
import com.kotcrab.vis.ui.widget.VisTextButton;
import com.kotcrab.vis.ui.widget.VisTextField;
import com.kotcrab.vis.ui.widget.VisWindow;
import com.mygdx.engine.configs.AConstants;
import com.mygdx.engine.screens.Screen3d;

import bombermantest.client.main.testClientConfig;
import bombermantest.client.network.client.game.CGame;
import bombermantest.client.network.client.login.LoginClient;
import bombermantest.client.ui.components.GameServerListLine;
import bombermantest.network.packets.enums.LoginClientPackets;

@SuppressWarnings("rawtypes")
public class GameServerListScreen extends Screen3d {

	private static GameServerListScreen singleton;
	
	@SuppressWarnings("unchecked")
	public static GameServerListScreen get(){
		if(singleton == null){
			singleton = new GameServerListScreen(); 
			singleton.create(null);
		}
		return singleton;
	}

	private VisTable table;
	
	@Override
	public void postCreateHook() {
		hud.setDebugAll(true);
		
        // Table
		table = new VisTable(true);
		hud.addActor(table);
	}

	@Override
	public void preDrawHook(float delta) { }

	@Override
	public void postDrawHook(float delta) { }

	public void clearList() {
		table.clear();
		table.pack();
	}
	
	public void add(CGame server) {
		table.add(new GameServerListLine(server));
		table.row();
		table.pack();
	}

	@Override
	public void resize(int width, int height) {
		super.resize(width, height);

		table.setPosition(hud.getWidth()/2 - table.getWidth()/2, hud.getHeight()/2 - table.getHeight()/2);
	}

}
