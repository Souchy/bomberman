package bombermantest.objects.buffs;

import com.mygdx.engine.objects.buffs.ABuffType;
import com.mygdx.engine.objects.buffs.Buff;

import bombermantest.objects.characters.playables.BPlayer;
import bombermantest.objects.characters.playables.BombermanStats;

public class BombsBuff extends Buff {

	protected BombsBuff(BPlayer player, boolean infinite) {
		super(player, infinite);
		if(!infinite) apply();
	}

	@Override
	public ABuffType getType() {
		return BuffType.Bombs;
	}

	@Override
	public long getDuration() {
		return 0;
	}

	@Override
	public void buffEnded() {
		((BombermanStats)player.getStats()).nbBombsMax -= 1;
		((BombermanStats)player.getStats()).nbBombs -= 1;
	}

	@Override
	public void prolong(){
		super.prolong();
		if(infinite) apply();
	}
	
	
	private void apply(){
		((BombermanStats)player.getStats()).nbBombsMax += 1;
		((BombermanStats)player.getStats()).nbBombs += 1;
	}

}
