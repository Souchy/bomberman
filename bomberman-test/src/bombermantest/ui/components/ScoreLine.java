package bombermantest.ui.components;

import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.Pixmap;
import com.badlogic.gdx.graphics.Pixmap.Format;
import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.utils.Align;
import com.kotcrab.vis.ui.widget.VisTable;
import com.mygdx.engine.configs.AConstants;

import bombermantest.network.objects.GClient;

public class ScoreLine extends VisTable {

	//private static Skin skin = new Skin();
	private static Pixmap pixmap = new Pixmap(1, 1, Format.RGBA8888);
	private static Color textColor = Color.BLACK;
	private static Color backColor = new Color(Color.CORAL.r, Color.CORAL.g, Color.CORAL.b, 0.8f); //Color.CORAL;
	private static int pad = 10;
	
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
		
		add(client.name).width(250).padLeft(pad);
		add(Integer.toString(client.wins)).width(70);
		add(Integer.toString(client.kills)).width(70);
		add(Integer.toString(client.deaths)).width(70);
		add(client.state.name()).width(90).padRight(pad);
		
		getCells().forEach(c -> {
			//c.getActor().addListener(AConstants.hoverEffect);
			c.getActor().setColor(textColor);
			c.padTop(pad);
			c.padBottom(pad);
			((Label)c.getActor()).setAlignment(Align.center);
		});
		((Label)getCells().first().getActor()).setAlignment(Align.left);
		
		pack();
	}

}
