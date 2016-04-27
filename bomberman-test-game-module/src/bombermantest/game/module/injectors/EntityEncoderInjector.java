package bombermantest.game.module.injectors;

import bombermantest.game.module.network.client.entityencoders.BoosterEncoder;
import bombermantest.game.module.network.client.entityencoders.CrateEncoder;
import bombermantest.game.module.network.client.entityencoders.GClientEncoder;
import bombermantest.game.module.network.client.entityencoders.NormalBombEncoder;
import bombermantest.game.module.network.client.entityencoders.PlayerEncoder;
import bombermantest.game.module.network.client.entityencoders.UnbreakableBlockEncoder;
import bombermantest.network.entities.Entities;

public final class EntityEncoderInjector {

	public static final void inject(){
		Entities.GClient.setEncoder(new GClientEncoder());
		Entities.Booster.setEncoder(new BoosterEncoder());
		Entities.BPlayer.setEncoder(new PlayerEncoder());
		Entities.NormalBomb.setEncoder(new NormalBombEncoder());
		Entities.UnbreakableBlock.setEncoder(new UnbreakableBlockEncoder());
		Entities.Crate.setEncoder(new CrateEncoder());
	}
	
	
}
