package bombermantest.ui.components;

import com.badlogic.gdx.Gdx;
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
		
	}
	
	@Override
	public void focusGained() {
		focused = true;
		super.focusGained();
	}
	
	@Override
	public void focusLost() {
		focused = false;
		super.focusLost();
	}
	
}
