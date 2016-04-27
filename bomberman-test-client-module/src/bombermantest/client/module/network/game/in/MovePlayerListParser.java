package bombermantest.client.module.network.game.in;

import static com.mygdx.engine.configs.AConstants.getVector2;
import static com.mygdx.engine.configs.AConstants.getVector3;

import org.apache.mina.core.buffer.IoBuffer;
import org.apache.mina.core.session.IoSession;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.math.Vector3;

import bombermantest.client.network.client.game.GameClient;
import bombermantest.main.TestGame;
import bombermantest.network.packets.Parser;
import bombermantest.objects.characters.playables.BPlayer;

public class MovePlayerListParser implements Parser {

	@Override
	public void parse(IoSession session, IoBuffer buf) {
		int nb = buf.getInt();
		//boolean initPos = (buf.get() == 1); // TODO initPos dans les packets de MovePlayerList
		
		for(int i = 0; i < nb; i++){
			long id = buf.getLong();
			Vector2 pos = getVector2(buf, new Vector2());
			Vector3 angles = getVector3(buf, new Vector3());
			
			if(TestGame.get().getClient(id) != null){
				if (id != GameClient.myid){ //|| initPos)) {
					BPlayer player = TestGame.get().getClient(id).player;
					
					if(player == null){
						System.out.println("[Client] MovePlayerParser, le player du client ["+id+"] est null");
						continue;
					}
					
					if(player.body != null){
						
						Gdx.app.postRunnable(() -> {
							//player.body.getTransform().setPosition(pos);//.setTransform(pos.x, pos.y, player.body.getAngle());//
							player.body.setTransform(pos, player.body.getAngle());
						});
						//player.dirAngles = angles;
						player.dirAngles.x = angles.x;
						player.dirAngles.y = angles.y;
						player.dirAngles.z = angles.z;
						
					}else{
						System.out.println("[Client] MovePlayerParser, le body du player ["+id+"] est null");
					}
				}else{
					//System.out.println("[Client] MovePlayerParser , dont move myself ");
				}
			}else{
				System.out.println("[Client] MovePlayerParser client inconnu");
			}
			
		}
		
	}

}
