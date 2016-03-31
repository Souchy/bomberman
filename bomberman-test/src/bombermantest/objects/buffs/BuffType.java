package bombermantest.objects.buffs;

import com.mygdx.engine.configs.AConstants;
import com.mygdx.engine.objects.buffs.ABuffType;
import com.mygdx.engine.objects.buffs.Buff;
import com.mygdx.engine.objects.characters.playables.APlayer;

import bombermantest.objects.characters.playables.BPlayer;

public enum BuffType implements ABuffType {
	
	Speed {
		@Override
		public Buff newBuffHook(BPlayer p, boolean infinite) {
			return new SpeedBuff(p, true);
		}
	},
	Power {
		@Override
		public Buff newBuffHook(BPlayer p, boolean infinite) {
			return new PowerBuff(p, true);
		}
	},
	Bombs {
		@Override
		public Buff newBuffHook(BPlayer p, boolean infinite) {
			return new BombsBuff(p, true);
		}
	}
	
	;


	public abstract Buff newBuffHook(BPlayer p, boolean infinite);
	
	
	@Override
	public Buff newBuff(APlayer p, boolean infinite) {
		Buff buff = p.hasBuffType(this); //g.game.player3.hasBuffType(this);
		if(buff != null){
			buff.prolong();
			return buff;
		}
		return newBuffHook((BPlayer) p, infinite);
	}
	

	public static BuffType randomBuffType(){
		int id = AConstants.rnd.nextInt(BuffType.values().length);
		BuffType type = BuffType.values()[id];
		return type;
	}

}
