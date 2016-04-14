package bombermantest.ui.components;

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
import com.mygdx.engine.configs.AConstants;

import bombermantest.network.objects.GClient;

public class ScoreLine extends VisTable {
	
	private static Color textColor = Color.BLACK;
	private Color backColor = Color.CORAL;
	private int pad = 20;
	
	//private static Skin skin = new Skin();
	private static Pixmap pixmap = new Pixmap(1, 1, Format.RGBA8888);
	
	static {
		// Generate a 1x1 white texture and store it in the skin named "white".
		Pixmap pixmap = new Pixmap(1, 1, Format.RGBA8888);
		pixmap.setColor(Color.WHITE);
		pixmap.fill();
		//skin.add("default", new Texture(pixmap));
	}
	
	public ScoreLine(GClient client) {
		//setSkin(skin);
		getSkin().add("white", new Texture(pixmap));
		setBackground(getSkin().newDrawable("white", backColor));
		
		add(client.name).width(50);
		add(Integer.toString(client.wins)).width(50);
		add(Integer.toString(client.kills)).width(50);
		add(Integer.toString(client.deaths)).width(50);
		add(client.state.name()).width(50);
		
		getCells().forEach(c -> {
			c.getActor().addListener(AConstants.hoverEffect);
			c.getActor().setColor(textColor);
			c.align(Align.center);
			c.pad(pad);
		});
		
		pack();
	}

}
