package bombermantest.client.injectors;

import bombermantest.client.network.login.in.GameServerListParser;
import bombermantest.client.network.login.in.LoginAuthentificationResultParser;
import bombermantest.client.network.login.in.ServerSelectionResultParser;
import bombermantest.client.network.login.out.AskGameServerListComposer;
import bombermantest.client.network.login.out.LoginAuthentificationComposer;
import bombermantest.client.network.login.out.ServerSelectionComposer;
import bombermantest.network.packets.enums.LoginClientPackets;

public final class LoginPacketInjector {
	
	public static final void inject(){
		//Client
		LoginClientPackets.AUTHENTIFICATION.setComposer(new LoginAuthentificationComposer());
		LoginClientPackets.AUTHENTIFICATION_RESULT.setParser(new LoginAuthentificationResultParser());
		
		LoginClientPackets.ASK_GAME_SERVER_LIST.setComposer(new AskGameServerListComposer());
		LoginClientPackets.GAME_SERVER_LIST.setParser(new GameServerListParser());
		
		LoginClientPackets.SERVER_SELECTION.setComposer(new ServerSelectionComposer());
		LoginClientPackets.SERVER_SELECTION_RESULT.setParser(new ServerSelectionResultParser());
		
	}
	

}
