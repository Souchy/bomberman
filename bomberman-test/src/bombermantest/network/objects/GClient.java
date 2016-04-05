package bombermantest.network.objects;

import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.utils.Disposable;

import bombermantest.enums.ClientState;
import bombermantest.objects.characters.playables.BPlayer;

public class GClient implements Disposable {
	
	public long id;
	public String name = "Roshi";
	public int wins = 0;
	public int kills = 0;
	public int deaths = 0;
	public ClientState state;
	
	/**
	 *  to instantiate only when GameState = INGAME
	 */
	public BPlayer player;
	
	/**
	 * Couleur de l'équipe en rgba8888 <p>
	 * TODO assigne des numéros/couleurs d'équipes aux gclient
	 */
	public int team;
	private Color color;
	
	public Color geTeamColor(){
		if(color == null) color = new Color(team);
		return color;
	}
	
	@Override
	public void dispose() {
		// TODO Auto-generated method stub
		if(player != null) player.dispose();
	}
	
	@Override
	public String toString(){
		return new StringBuilder()
		.append("id : ").append(id)
		.append(", name : ").append(name)
		.append(", wins : ").append(wins)
		.append(", kills : ").append(kills)
		.append(", deaths : ").append(deaths)
		.append(", state : ").append(state)
		.append(", player : ").append(player)
		.append(", team : ").append(team)
		.toString();
	}
	
}
