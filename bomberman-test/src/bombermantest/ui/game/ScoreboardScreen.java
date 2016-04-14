package bombermantest.ui.game;

import java.util.ArrayList;
import java.util.List;

import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.Pixmap;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.Pixmap.Format;
import com.badlogic.gdx.scenes.scene2d.ui.Cell;
import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.badlogic.gdx.utils.Align;
import com.kotcrab.vis.ui.widget.VisTable;
import com.kotcrab.vis.ui.widget.VisTextField.VisTextFieldStyle;
import com.mygdx.engine.configs.AConstants;
import com.mygdx.engine.screens.Screen3d;

import bombermantest.main.TestGame;
import bombermantest.network.objects.GClient;
import bombermantest.ui.components.ScoreLine;

public class ScoreboardScreen extends Screen3d {

	private static ScoreboardScreen singleton;
	public static ScoreboardScreen get(){
		if(singleton == null){
			singleton = new ScoreboardScreen(); 
			singleton.create(null);
		}
		return singleton;
	}
	
	private VisTable table;
	private VisTable titleTable = new VisTable();
	private Color textColor = Color.BLACK;
	private Color backColor = Color.CYAN;
	private int pad = 20;
	
	@Override
	public void postCreateHook() {
		//hud.setDebugAll(true);
		
		Skin skin = new Skin();
		// Generate a 1x1 white texture and store it in the skin named "white".
		Pixmap pixmap = new Pixmap(1, 1, Format.RGBA8888);
		pixmap.setColor(Color.WHITE);
		pixmap.fill();
		skin.add("white", new Texture(pixmap));
		
		//Color charcoal = new Color(47/255f, 47/255f, 47/255f, 1);;
		//Color bgcolor = charcoal;
		//Color fontcolor = Color.WHITE;
		
		titleTable.add("Name").width(50);
		titleTable.add("Wins").width(50);
		titleTable.add("Kills").width(50);
		titleTable.add("Deaths").width(50);
		titleTable.add("State").width(50);
		titleTable.getCells().forEach(c -> {
			c.getActor().setColor(textColor);
			c.align(Align.center);
			c.pad(pad);
		});
		titleTable.setBackground(skin.newDrawable("white", backColor));
		
        // Table
		table = new VisTable(true);
		//table.setBackground(skin.newDrawable("white", backColor));
		table.pad(pad);
		
		table.setPosition(getHudCamWidth() / 2 - table.getWidth() / 2, getHudCamHeight() / 2 - table.getHeight() / 2);
		hud.addActor(table);
		
	}
	
	public void show(){
		super.show();
		updateClientList();
	}
	
	@Override
	public void preDrawHook(float delta) { }

	@Override
	public void postDrawHook(float delta) { }

	public void updateClientList() {
		clearList();
		TestGame.get().getClientList().forEach(c -> add(c));
		table.setPosition(getHudCamWidth() / 2 - table.getWidth() / 2, getHudCamHeight() / 2 - table.getHeight() / 2);
	}

	
	
	public void clearList() {
		table.clear();
		
		table.add(titleTable);
		
		table.row();
		table.pack();
	}
	
	public void add(GClient client) {
		/*List<Cell<Label>> row = new ArrayList<>();
		
		table.row();
        
		row.add(table.add(client.name));
		row.add(table.add(Integer.toString(client.wins)));
		row.add(table.add(Integer.toString(client.kills)));
		row.add(table.add(Integer.toString(client.deaths)));
		row.add(table.add(client.state.name()));
		
		table.row();
		
		row.forEach(c -> {
			c.getActor().addListener(AConstants.hoverEffect);
			c.getActor().setColor(textColor);
			c.pad(pad);
		});

		table.pack();*/
		
		table.add(new ScoreLine(client));
		table.row();
		table.pack();
		
	}
	
	@Override 
	public void resize(int width, int height){ 
		super.resize(width, height);
		
		table.setPosition(getHudCamWidth() / 2 - table.getWidth() / 2, getHudCamHeight() / 2 - table.getHeight() / 2);
	}

}
