package bombermantest.client.module.network.login.in;

import org.apache.mina.core.buffer.IoBuffer;
import org.apache.mina.core.session.IoSession;

import com.badlogic.gdx.Gdx;

import bombermantest.client.main.ClientGame;
import bombermantest.client.ui.login.GameServerListScreen;
import bombermantest.network.packets.Parser;
import bombermantest.network.packets.enums.LoginClientPackets;


public class LoginAuthentificationResultParser implements Parser {

	@Override
	public void parse(IoSession session, IoBuffer buf) {
		byte b = buf.get();
		boolean accepted = b == 1;
		System.out.println("b = ["+b+"], accepted : "+accepted);
		
		if(accepted){
			LoginClientPackets.ASK_GAME_SERVER_LIST.compose(session);
			Gdx.app.postRunnable(() -> {
				ClientGame.get().setScreen(GameServerListScreen.get());
			});
		}
	}

}
