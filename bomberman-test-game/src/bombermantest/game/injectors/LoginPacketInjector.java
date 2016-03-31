package bombermantest.game.injectors;

import bombermantest.game.network.login.in.ClientTransferParser;
import bombermantest.game.network.login.in.LoginAuthentificationResultParser;
import bombermantest.game.network.login.out.ClientTransferResultComposer;
import bombermantest.game.network.login.out.LoginAuthentificationComposer;
import bombermantest.network.packets.enums.LoginGamePackets;

public final class LoginPacketInjector {

	public static final void inject() {
		
		//Login
		LoginGamePackets.AUTHENTIFICATION.setComposer(new LoginAuthentificationComposer());
		LoginGamePackets.AUTHENTIFICATION_RESULT.setParser(new LoginAuthentificationResultParser());
		
		LoginGamePackets.CLIENT_TRANSFER.setParser(new ClientTransferParser());
		LoginGamePackets.CLIENT_TRANSFER_RESULT.setComposer(new ClientTransferResultComposer());
		
	}

}
