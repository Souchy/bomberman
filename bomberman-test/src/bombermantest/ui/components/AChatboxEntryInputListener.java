package bombermantest.ui.components;

import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.InputListener;

public abstract class AChatboxEntryInputListener extends InputListener {
	
	protected static AChatboxEntryInputListener singleton;
	
	public static AChatboxEntryInputListener get(){
		return singleton;
	}
	
	protected ChatboxArea area;
	protected ChatboxEntry entry;
	
	/* protected AChatboxEntryInputListener(ChatboxEntry entry, ChatboxArea area) {
		this.area = area;
		this.entry = entry;
	}*/
	
	@Override
	public boolean keyDown(InputEvent event, int keycode) {
		System.out.println("key : " + keycode);
		if(keycode == getFocusChatKey() && entry.getText().length() > 0){
			enterMessage(entry.getText());
			entry.setText("");
		}
		return super.keyDown(event, keycode);
	}
	
	protected abstract void enterMessage(String text);
	
	protected abstract void enterCommand(String text);
	
	public abstract int getFocusChatKey();

}
