package bombermantest.game.module.injectors;

import bombermantest.game.module.network.login.in.ClientTransferParser;
import bombermantest.game.module.network.login.in.LoginAuthentificationResultParser;
import bombermantest.game.module.network.login.out.ClientTransferResultComposer;
import bombermantest.game.module.network.login.out.LoginAuthentificationComposer;
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
