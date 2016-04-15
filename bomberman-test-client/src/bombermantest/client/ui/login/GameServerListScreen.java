package bombermantest.client.ui.login;

import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.Pixmap;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.Pixmap.Format;
import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.badlogic.gdx.utils.Align;
import com.kotcrab.vis.ui.widget.VisTable;
import com.mygdx.engine.screens.Screen3d;

import bombermantest.client.network.client.game.CGame;
import bombermantest.client.ui.components.GameServerListLine;

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

	private VisTable table = new VisTable(true);
	private VisTable titleTable = new VisTable();
	private Color textColor = Color.GRAY;
	private Color backColor = new Color(47/255f, 47/255f, 47/255f, 1);
	private int pad = 10;
	
	@Override
	public void postCreateHook() {
		//hud.setDebugAll(true);
		
		Skin skin = new Skin();
		// Generate a 1x1 white texture and store it in the skin named "white".
		Pixmap pixmap = new Pixmap(1, 1, Format.RGBA8888);
		pixmap.setColor(Color.WHITE);
		pixmap.fill();
		skin.add("white", new Texture(pixmap));

		float cellw = 100;
		titleTable.setBackground(skin.newDrawable("white", backColor));
		titleTable.add("Server Name").width(cellw).padLeft(pad);
		titleTable.add("Map").width(cellw);
		titleTable.add("Mode").width(cellw);
		titleTable.add("Password").width(cellw);
		titleTable.add("Players").width(cellw);
		titleTable.add("Capacity").width(cellw).padRight(pad);
		
		titleTable.getCells().forEach(c -> {
			c.getActor().setColor(textColor);
			c.padTop(pad);
			c.padBottom(pad);
			((Label)c.getActor()).setAlignment(Align.center);
		});
		titleTable.pack();
		
		table.add(titleTable);
		table.pack();
		
		hud.addActor(table);
	}

	@Override
	public void preDrawHook(float delta) { }

	@Override
	public void postDrawHook(float delta) { }

	public void clearList() {
		table.clear();
		table.add(titleTable);
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
		
		table.setPosition(hud.getWidth() / 2 - table.getWidth() / 2, hud.getHeight() - table.getHeight() - 150);
	}

}
