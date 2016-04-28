package bombermantest.client.ui.components.commands;

public interface ChatCommand {
	
	public boolean condition(String input);
	public void accept(String input);

}
