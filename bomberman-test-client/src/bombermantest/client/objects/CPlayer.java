package bombermantest.client.objects;

import com.badlogic.gdx.math.Vector3;
import com.badlogic.gdx.physics.box2d.BodyDef.BodyType;
import com.mygdx.engine.game.AGame;

import bombermantest.objects.characters.playables.BPlayer;

public class CPlayer extends BPlayer {

	public CPlayer(AGame game, Vector3 pos) {
		super(game, pos);
	}
	

	@Override
	protected void build(){
		super.build();
		body.setType(BodyType.DynamicBody);
		if(game.universe.player == this) controller = new CPlayerController(this); // pas de controller si ce player n'est pas le 'main' player.
	}

}
