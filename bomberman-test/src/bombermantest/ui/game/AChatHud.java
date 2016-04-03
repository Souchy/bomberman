package bombermantest.ui.game;

import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.InputListener;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;
import com.badlogic.gdx.scenes.scene2d.utils.DragListener;
import com.badlogic.gdx.utils.Align;
import com.kotcrab.vis.ui.widget.VisLabel;
import com.kotcrab.vis.ui.widget.VisTable;
import com.kotcrab.vis.ui.widget.VisTextArea;
import com.kotcrab.vis.ui.widget.VisTextField;
import com.mygdx.engine.actors.MyLabel;
import com.mygdx.engine.screens.Screen3d;

import bombermantest.events.SpectatingClickListener;

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
		
		// chatbox
		chatBox = new VisTextArea();
		//chatBox.getStyle().font = FontsLoader.singleton.hongkong.font;
		//chatBox.setStyle(chatBox.getStyle()); // pour bien updater le style , sinon les modification peuvent ne pas prendre effets desfois
		chatBox.setText("bou jhsb dfgkjnsdkfjgnlkfdgn dskf sdlk fns");
		chatBox.setMessageText("allo test chatBox");
		chatBox.setTextFieldListener(null);
		chatBox.removeListener(chatBox.getDefaultInputListener());
		//new VisTextField.TextFieldClickListener();
		chatBox.addListener(new InputListener());
		chatBox.addListener(new ClickListener());
		chatBox.addListener(new DragListener());
		//chatBox.setDisabled(true);
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
