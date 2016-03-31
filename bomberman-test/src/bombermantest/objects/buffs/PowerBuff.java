package bombermantest.objects.buffs;

import com.mygdx.engine.objects.buffs.ABuffType;
import com.mygdx.engine.objects.buffs.Buff;
import com.mygdx.engine.objects.characters.playables.APlayer;

import bombermantest.objects.characters.playables.BombermanStats;

public class PowerBuff extends Buff {

	protected PowerBuff(APlayer player, boolean infinite) {
		super(player, infinite);
		if(!infinite) apply();
	}

	@Override
	public ABuffType getType() {
		return BuffType.Power;
	}

	@Override
	public long getDuration() {
		return 0;
	}

	@Override
	public void buffEnded() {
		((BombermanStats)player.getStats()).power -= 1;
	}

	@Override
	public void prolong(){
		super.prolong();
		if(infinite) apply();
	}
	
	private void apply(){
		((BombermanStats)player.getStats()).power += 1;
	}

}
