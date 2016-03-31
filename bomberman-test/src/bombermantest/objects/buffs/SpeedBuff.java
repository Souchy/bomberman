package bombermantest.objects.buffs;

import com.mygdx.engine.objects.buffs.ABuffType;
import com.mygdx.engine.objects.buffs.Buff;
import com.mygdx.engine.objects.characters.playables.APlayer;

import bombermantest.objects.characters.playables.BombermanStats;

public class SpeedBuff extends Buff {

	protected SpeedBuff(APlayer player, boolean infinite) {
		super(player, infinite);
		if(!infinite) apply();
	}

	@Override
	public ABuffType getType() {
		return BuffType.Speed;
	}

	@Override
	public long getDuration() {
		return 0;
	}

	@Override
	public void buffEnded() {
		//player.getStats().accelForce.add(-0.01f, -0.01f);
		((BombermanStats)player.getStats()).speed -= 0.1f;
	}

	@Override
	public void prolong(){
		super.prolong();
		if(infinite) apply();
	}
	
	private void apply(){
		//player.getStats().accelForce.add(0.01f, 0.01f);
		((BombermanStats)player.getStats()).speed += 0.1f;
	}

}
