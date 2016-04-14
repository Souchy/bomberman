package bombermantest.ui.components;

import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.Pixmap;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.Pixmap.Format;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.actions.Actions;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.badlogic.gdx.scenes.scene2d.utils.Drawable;
import com.kotcrab.vis.ui.widget.VisTextArea;

public class ChatboxArea extends VisTextArea {

	private static ChatboxArea singleton;
	
	public static ChatboxArea get(){
		if(singleton == null) singleton = new ChatboxArea();
		return singleton;
	}
	
	private ChatboxArea() {
		Skin skin = new Skin();
		// Generate a 1x1 white texture and store it in the skin named "white".
		Pixmap pixmap = new Pixmap(1, 1, Format.RGBA8888);
		pixmap.setColor(Color.WHITE);
		pixmap.fill();
		skin.add("white", new Texture(pixmap));
		
		Color charcoal = new Color(47/255f, 47/255f, 47/255f, 1);;
		Color bgcolor = charcoal;
		Color fontcolor = Color.WHITE;
		
		VisTextFieldStyle cbStyle = new VisTextFieldStyle();
		cbStyle.background = skin.newDrawable("white", bgcolor);
		cbStyle.backgroundOver = skin.newDrawable("white", bgcolor);
		cbStyle.disabledBackground = skin.newDrawable("white", Color.GRAY);
		cbStyle.disabledFontColor = Color.CHARTREUSE;
		cbStyle.focusedBackground = skin.newDrawable("white", bgcolor);
		cbStyle.font = new BitmapFont(); //FontsLoader.singleton.hongkong.font;
		cbStyle.font.getData().setScale(1.0f);
		cbStyle.fontColor = fontcolor;
		cbStyle.focusedFontColor = fontcolor;
		cbStyle.messageFont = new BitmapFont();
		cbStyle.messageFontColor = Color.YELLOW;
		cbStyle.selection = skin.newDrawable("white", Color.TEAL);
		
		setStyle(cbStyle);
		setMessageText("");
		addMessage("Bou : jhsb dfgkjnsdkfjgnlkfdgn dskf sdlk fns");
		addMessage("Test : jhsb dfgkjnsdkfjgnlkfdgn dskf sdlk fns");
		setTextFieldListener(null);
		removeListener(getDefaultInputListener());
		addListener(new TextAreaListener(){
			@Override public boolean keyDown(InputEvent event, int keycode) { return false; } //no typing allowed
			@Override public boolean keyTyped (InputEvent event, char character) { return false; } //no typing allowed
		});
		
		setBounds(200, 200 + 25 + 10, 300, 150);
		//pack();

		addAction(Actions.alpha(0.2f, 0.3f));
	}
	
	@Override 
	protected void drawCursor(Drawable cursorPatch, Batch batch, BitmapFont font, float x, float y) {
		//no cursor for me :)	
	} 
	
	
	public void addMessage(String text){
		appendText(text + "\n");
	}

}
