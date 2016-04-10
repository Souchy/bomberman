package bombermantest.events;

import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;
import com.mygdx.engine.game.Lambda;
import com.mygdx.engine.objects.Box2dObject;

import bombermantest.enums.ClientState;
import bombermantest.main.TestGame;
import bombermantest.network.objects.GClient;
import bombermantest.ui.game.GameScreen;

public class SpectatingClickListener extends ClickListener {
	
	private int index = 1;
	private int i = 1;
	
	@Override
	public void clicked(InputEvent event, float x, float y) {
		super.clicked(event, x, y);
		if(TestGame.get().getClientState() == ClientState.SPECTATING //|| 
			//(TestGame.get().getClientState() == ClientState.PLAYING && TestGame.get().universe.player.getStats().life <= 0) // si le main player est mort, il peut spec les autres
			// ^ pu besoin de ça, mtn on met le state du player à spectating quand il meurt
		){
			//System.out.println("clicking, index=["+index+"/"+Client.get().players.size()+"], camTarget=["+camTarget+"], mainPlay=["+TestGame.get().universe.player+"]");
			
			Box2dObject previous = GameScreen.get().camTarget;
			
			if(index == TestGame.get().getClientCount()){
				index = 1;
				System.out.println("reset index");
			}
			
			Lambda lambda = () -> {
				for(GClient target : TestGame.get().getClientList()){
					if(target.player != GameScreen.get().camTarget && i >= index && target.player != null /*&& target != TestGame.get().universe.player*/){
						GameScreen.get().camTarget = target.player;
						index = i;
						break;
					}
					i++;
				}
			};
			
			lambda.call();
				
			System.out.println("spectate = ["+GameScreen.get().camTarget+"], body = ["+(GameScreen.get().camTarget != null ? GameScreen.get().camTarget.body : "null target")+"]");
			
			if(GameScreen.get().camTarget == previous){
				index = 1;
				lambda.call();
				System.out.println("reset index 2");
			}
		}
	}
	
}
