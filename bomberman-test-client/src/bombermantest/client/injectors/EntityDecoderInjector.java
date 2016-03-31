package bombermantest.client.injectors;

import bombermantest.client.network.game.entitydecoders.BoosterDecoder;
import bombermantest.client.network.game.entitydecoders.CrateDecoder;
import bombermantest.client.network.game.entitydecoders.GClientDecoder;
import bombermantest.client.network.game.entitydecoders.NormalBombDecoder;
import bombermantest.client.network.game.entitydecoders.PlayerDecoder;
import bombermantest.client.network.game.entitydecoders.UnbreakableBlockDecoder;
import bombermantest.network.entities.Entities;

public final class EntityDecoderInjector {
	
	public static final void inject(){
		Entities.GClient.setDecoder(new GClientDecoder());
		Entities.Booster.setDecoder(new BoosterDecoder());
		Entities.BPlayer.setDecoder(new PlayerDecoder());
		Entities.NormalBomb.setDecoder(new NormalBombDecoder());
		Entities.UnbreakableBlock.setDecoder(new UnbreakableBlockDecoder());
		Entities.Crate.setDecoder(new CrateDecoder());
	}
	
	
}
