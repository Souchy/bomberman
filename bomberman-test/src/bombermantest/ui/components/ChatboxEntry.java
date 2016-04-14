package bombermantest.ui.components;

import com.badlogic.gdx.scenes.scene2d.actions.Actions;
import com.kotcrab.vis.ui.widget.VisTextField;

public class ChatboxEntry extends VisTextField {
	
	private static ChatboxEntry singleton;
	
	public static boolean focused = false;
	
	public static ChatboxEntry get(){
		if(singleton == null) singleton = new ChatboxEntry();
		return singleton;
	}
	
	public ChatboxEntry() {
		AChatboxEntryInputListener.get().area = ChatboxArea.get();
		AChatboxEntryInputListener.get().entry = this;
		
		setText("");
		setMessageText("allo test textEntry");
		addListener(AChatboxEntryInputListener.get());
		//System.out.println("CB ENTRY PREF HEIGHT : "+getPrefHeight()); // -> 25
		setBounds(200, 200, 300, 25);
		//pack();
		
		addAction(Actions.alpha(0.2f, 0.3f));
	}
	
	@Override
	public void focusGained() {
		focused = true;

		ChatboxArea.get().clearActions();
		ChatboxEntry.get().clearActions();

		ChatboxArea.get().addAction(Actions.alpha(1));
		ChatboxEntry.get().addAction(Actions.alpha(1));
		
		super.focusGained();
	}
	
	@Override
	public void focusLost() {
		focused = false;
		
		ChatboxArea.get().addAction(Actions.alpha(0.2f, 0.3f));
		ChatboxEntry.get().addAction(Actions.alpha(0.2f, 0.3f));
		
		super.focusLost();
	}
	
}
