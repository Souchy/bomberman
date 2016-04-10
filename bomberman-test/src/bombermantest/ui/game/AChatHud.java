package bombermantest.ui.game;

import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.Pixmap;
import com.badlogic.gdx.graphics.Pixmap.Format;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.InputListener;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.badlogic.gdx.scenes.scene2d.ui.TextArea;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;
import com.badlogic.gdx.scenes.scene2d.utils.DragListener;
import com.badlogic.gdx.scenes.scene2d.utils.Drawable;
import com.badlogic.gdx.utils.Align;
import com.kotcrab.vis.ui.widget.VisTable;
import com.kotcrab.vis.ui.widget.VisTextArea;
import com.kotcrab.vis.ui.widget.VisTextField;
import com.kotcrab.vis.ui.widget.VisTextField.TextFieldClickListener;
import com.kotcrab.vis.ui.widget.VisTextField.VisTextFieldStyle;
import com.mygdx.engine.actors.MyLabel;
import com.mygdx.engine.screens.Screen3d;

public abstract class AChatHud extends Screen3d {

	protected static AChatHud singleton;
	
	/** to override */
	public static AChatHud get(){
		return singleton;
	}
	
	protected VisTable table;
	protected VisTextArea chatBox;
	protected VisTextField textEntry;
	protected MyLabel play;
	
	
	@Override
	public void postCreateHook() {
        // Table
		table = new VisTable(true);
		table.setPosition(200, 200);
		table.align(Align.left);
		hud.addActor(table);
		
		Skin skin = new Skin();
		// Generate a 1x1 white texture and store it in the skin named "white".
		Pixmap pixmap = new Pixmap(1, 1, Format.RGBA8888);
		pixmap.setColor(Color.WHITE);
		pixmap.fill();
		skin.add("white", new Texture(pixmap));
		
		Color charcoal = new Color(47/255f, 47/255f, 47/255f, 1);;
		Color bgcolor = Color.WHITE;//charcoal;
		Color fontcolor = Color.WHITE;
		
		VisTextFieldStyle cbStyle = new VisTextFieldStyle();
		cbStyle.background = skin.newDrawable("white", bgcolor);
		cbStyle.backgroundOver = skin.newDrawable("white", bgcolor);
		cbStyle.disabledBackground = skin.newDrawable("white", Color.GRAY);
		cbStyle.disabledFontColor = Color.CHARTREUSE;
		cbStyle.focusedBackground = skin.newDrawable("white", bgcolor);
		cbStyle.font = new BitmapFont(); //FontsLoader.singleton.hongkong.font;
		cbStyle.font.getData().setScale(1.1f);
		cbStyle.fontColor = fontcolor;
		cbStyle.focusedFontColor = fontcolor;
		cbStyle.messageFont = new BitmapFont();
		cbStyle.messageFontColor = Color.YELLOW;
		cbStyle.selection = skin.newDrawable("white", Color.TEAL);
		//cbStyle.errorBorder = skin.newDrawable("white", Color.RED);
		//cbStyle.focusBorder = skin.newDrawable("white", Color.CORAL);
		
		// chatbox
		chatBox = new VisTextArea(){
			@Override protected void drawCursor(Drawable cursorPatch, Batch batch, BitmapFont font, float x, float y) { } //no cursor for me :)
		};
		chatBox.setStyle(cbStyle);
		chatBox.setMessageText("");
		addMessage("Bou : jhsb dfgkjnsdkfjgnlkfdgn dskf sdlk fns");
		addMessage("Test : jhsb dfgkjnsdkfjgnlkfdgn dskf sdlk fns");
		chatBox.setTextFieldListener(null);
		chatBox.removeListener(chatBox.getDefaultInputListener());
		chatBox.addListener(chatBox.new TextAreaListener(){
			@Override public boolean keyDown(InputEvent event, int keycode) { return false; } //no typing allowed
			@Override public boolean keyTyped (InputEvent event, char character) { return false; } //no typing allowed
		});
		chatBox.pack();
		table.add(chatBox).width(300).height(150);
		table.row();
		
		// TextEntry field
		textEntry = new VisTextField();
		textEntry.setText("");
		textEntry.setMessageText("allo test textEntry");
		textEntry.addListener(new InputListener(){
			@Override
			public boolean keyDown(InputEvent event, int keycode) {
				if(keycode == getFocusChatKey()){
					enterMessage(textEntry.getText());
					textEntry.setText("");
				}
				return super.keyDown(event, keycode);
			}
		});
		textEntry.pack();
		table.add(textEntry).width(300);
		table.row();
		
		table.pack();
	}
	
	@Override 
	public void resize(int width, int height){ 
		super.resize(width, height);
		
		table.setPosition(200, 200);
	}
	
	public void setColor(Color color){
		chatBox.setColor(color);
	}
	
	public void addMessage(String text){
		chatBox.appendText(text + "\n");
	}

	
	@Override public void preDrawHook(float delta) { }
	
	@Override public void postDrawHook(float delta) { }
	
	protected abstract void enterMessage(String text);
	
	protected abstract void enterCommand(String text);
	
	protected abstract int getFocusChatKey();
	
}
