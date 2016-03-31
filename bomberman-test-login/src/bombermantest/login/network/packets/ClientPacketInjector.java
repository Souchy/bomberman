package bombermantest.login.network.packets;

import bombermantest.login.network.client.in.AskGameServerListParser;
import bombermantest.login.network.client.in.ClientAuthentificationParser;
import bombermantest.login.network.client.in.ServerSelectionParser;
import bombermantest.login.network.client.out.ClientAuthentificationResultComposer;
import bombermantest.login.network.client.out.GameServerListComposer;
import bombermantest.login.network.client.out.ServerSelectionResultComposer;
import bombermantest.network.packets.enums.LoginClientPackets;

public final class ClientPacketInjector {
	
	public static final void inject(){
		//Client
		LoginClientPackets.AUTHENTIFICATION.setParser(new ClientAuthentificationParser());
		LoginClientPackets.AUTHENTIFICATION_RESULT.setComposer(new ClientAuthentificationResultComposer());
		
		LoginClientPackets.ASK_GAME_SERVER_LIST.setParser(new AskGameServerListParser());
		LoginClientPackets.GAME_SERVER_LIST.setComposer(new GameServerListComposer());

		LoginClientPackets.SERVER_SELECTION.setParser(new ServerSelectionParser());
		LoginClientPackets.SERVER_SELECTION_RESULT.setComposer(new ServerSelectionResultComposer());
	}

}
