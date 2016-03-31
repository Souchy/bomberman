package bombermantest.ui.game;

import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.InputListener;
import com.kotcrab.vis.ui.widget.VisTable;
import com.kotcrab.vis.ui.widget.VisTextArea;
import com.kotcrab.vis.ui.widget.VisTextField;
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
	
	@Override
	public void postCreateHook() {
        // Table
		table = new VisTable(true);
		table.setPosition(200, 200);
		hud.addActor(table);
		
		// chatbox
		chatBox = new VisTextArea();
		//chatBox.getStyle().font = FontsLoader.singleton.hongkong.font;
		//chatBox.setStyle(chatBox.getStyle()); // pour bien updater le style , sinon les modification peuvent ne pas prendre effets desfois
		chatBox.setText("");
		chatBox.setWidth(300);
		chatBox.setHeight(200);
		chatBox.pack();
		table.add(chatBox);
		table.row();
		
		// TextEntry field
		textEntry = new VisTextField();
		textEntry.setText("");
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
		table.add(textEntry);
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
