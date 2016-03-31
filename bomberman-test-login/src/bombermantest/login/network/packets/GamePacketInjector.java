package bombermantest.login.network.packets;

import bombermantest.login.network.game.in.ClientTransferResultParser;
import bombermantest.login.network.game.in.GameAuthentificationParser;
import bombermantest.login.network.game.out.ClientTransferComposer;
import bombermantest.login.network.game.out.GameAuthentificationResultComposer;
import bombermantest.network.packets.enums.LoginGamePackets;

public class GamePacketInjector {

	public static final void inject(){

		//Game
		LoginGamePackets.AUTHENTIFICATION.setParser(new GameAuthentificationParser());
		LoginGamePackets.AUTHENTIFICATION_RESULT.setComposer(new GameAuthentificationResultComposer());
		
		LoginGamePackets.CLIENT_TRANSFER.setComposer(new ClientTransferComposer());
		LoginGamePackets.CLIENT_TRANSFER_RESULT.setParser(new ClientTransferResultParser());
		
	}
	
}
