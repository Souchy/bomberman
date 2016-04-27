package bombermantest.ui.game;

import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.Pixmap;
import com.badlogic.gdx.graphics.Pixmap.Format;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.badlogic.gdx.utils.Align;
import com.kotcrab.vis.ui.widget.VisTable;

import bombermantest.main.TestGame;
import bombermantest.network.objects.GClient;
import bombermantest.ui.components.ScoreLine;

public class Scoreboard extends VisTable { 

	private static Scoreboard singleton;
	
	public static Scoreboard get(){
		if(singleton == null) singleton = new Scoreboard(); 
		return singleton;
	}
	
	private VisTable titleTable = new VisTable();
	//private Color textColor = Color.BLACK;
	//private Color backColor = new Color(Color.CYAN.r, Color.CYAN.g, Color.CYAN.b, 0.8f);
	private Color textColor = Color.GRAY;
	private Color backColor = new Color(47/255f, 47/255f, 47/255f, 1);
	private int pad = 10;
	
	/**
	 * HACK
	 */
	private Stage hud = GameScreen.get().hud;
	
	private Scoreboard(){
		//hud.setDebugAll(true);
		
		Skin skin = new Skin();
		// Generate a 1x1 white texture and store it in the skin named "white".
		Pixmap pixmap = new Pixmap(1, 1, Format.RGBA8888);
		pixmap.setColor(Color.WHITE);
		pixmap.fill();
		skin.add("white", new Texture(pixmap));
		
		titleTable.setBackground(skin.newDrawable("white", backColor));
		titleTable.add("Name").width(250).padLeft(pad);
		titleTable.add("Wins").width(70);
		titleTable.add("Kills").width(70);
		titleTable.add("Deaths").width(70);
		titleTable.add("State").width(90).padRight(pad);
		titleTable.getCells().forEach(c -> {
			c.getActor().setColor(textColor);
			c.padTop(pad);
			c.padBottom(pad);
			((Label)c.getActor()).setAlignment(Align.center);
		});
		((Label)titleTable.getCells().first().getActor()).setAlignment(Align.left);
		titleTable.pack();
		
		//table.setBackground(skin.newDrawable("white", backColor));
		pad(pad);
		
		setPosition(hud.getWidth() / 2 - getWidth() / 2, hud.getHeight() - getHeight() - 150);
	}

	public void updateClientList() {
		clearList();
		TestGame.get().getClientList().forEach(c -> add(c));
		setPosition(hud.getWidth() / 2 - getWidth() / 2, hud.getHeight() - getHeight() - 150);
	}
	
	public void clearList() {
		clear();
		
		add(titleTable);
		
		row();
		pack();
	}
	
	public void add(GClient client) {
		add(new ScoreLine(client));
		row();
		pack();
		
	}

}
