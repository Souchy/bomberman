package bombermantest.ui.game;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.math.Vector3;
import com.mygdx.engine.actors.MyLabel;
import com.mygdx.engine.configs.AConstants.Platform;
import com.mygdx.engine.objects.characters.playables.APlayer;
import com.mygdx.engine.screens.Screen3d;
import com.mygdx.engine.services.FontsLoader;

import bombermantest.main.TestGame;
import bombermantest.objects.characters.playables.BPlayer;
import bombermantest.objects.characters.playables.BombermanStats;

@SuppressWarnings("rawtypes")
public class StatusHud extends Screen3d {

	private static StatusHud singleton;
	
	@SuppressWarnings("unchecked")
	public static StatusHud get(){
		if(singleton == null){
			singleton = new StatusHud(); 
			singleton.create(null);
		}
		return singleton;
	}

	// Hud
	//private MyLabel scoreLbl;
	private MyLabel timeLbl;
	private MyLabel fpsLbl;
	private MyLabel playerPosLbl;
	private MyLabel visibleCountLbl;
	private MyLabel lifeLbl;
	private MyLabel buffTestLbl;
	private MyLabel playerListLbl;
	private MyLabel ammoLbl;
	private MyLabel powerLbl;
	private MyLabel speedLbl;
	private float time = 0;
	
	@Override
	public void postCreateHook() {
		setupHud();
	}

	@Override
	public void preDrawHook(float delta) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void postDrawHook(float delta) {
		Vector3 pos = new Vector3(GameScreen.get().camTarget.getPos(), 0);
		Vector2 vel = GameScreen.get().camTarget.body.getLinearVelocity();
		updateHud(delta, pos, vel);
	}
	

	private void setupHud(){
		// Labels
		fpsLbl = new MyLabel("", FontsLoader.singleton.hongkong, Color.WHITE, 20);
		fpsLbl.setPosition(30, 30);
		hud.addActor(fpsLbl);

		visibleCountLbl = new MyLabel("", FontsLoader.singleton.hongkong, Color.WHITE, 20);
		visibleCountLbl.setPosition(30, 45);
		hud.addActor(visibleCountLbl);
		
		playerPosLbl = new MyLabel("", FontsLoader.singleton.hongkong, Color.WHITE, 20);
		playerPosLbl.setPosition(30, 75);
		hud.addActor(playerPosLbl);
		
		lifeLbl = new MyLabel("", FontsLoader.singleton.hongkong, Color.WHITE, 20);
		lifeLbl.setPosition(30, 105);
		hud.addActor(lifeLbl);

		buffTestLbl = new MyLabel("", FontsLoader.singleton.hongkong, Color.WHITE, 20);
		buffTestLbl.setPosition(30, 135);
		hud.addActor(buffTestLbl);

		playerListLbl = new MyLabel("", FontsLoader.singleton.hongkong, Color.WHITE, 20);
		playerListLbl.setPosition(30, 165);
		hud.addActor(playerListLbl);
		
		ammoLbl = new MyLabel("", FontsLoader.singleton.hongkong, Color.WHITE, 20);
		ammoLbl.setPosition(30, 195);
		hud.addActor(ammoLbl);

		speedLbl = new MyLabel("", FontsLoader.singleton.hongkong, Color.WHITE, 20);
		speedLbl.setPosition(30, 225);
		hud.addActor(speedLbl);

		powerLbl = new MyLabel("", FontsLoader.singleton.hongkong, Color.WHITE, 20);
		powerLbl.setPosition(30, 255);
		hud.addActor(powerLbl);

		timeLbl = new MyLabel("", FontsLoader.singleton.hongkong, Color.WHITE, 20);
		timeLbl.setPosition(hudCam.viewportWidth - 175, 30);
		hud.addActor(timeLbl);
		
		//scoreLbl = new MyLabel("", FontsLoader.singleton.hongkong, Color.WHITE, 20);
		//scoreLbl.setPosition(hudCam.viewportWidth - 175, 45);
		//hud.addActor(scoreLbl);
	}
	
	private void updateHud(float delta, Vector3 pos, Vector2 vel){
		APlayer player = TestGame.get().universe.player; 
		if(GameScreen.get().camTarget instanceof BPlayer)
			player = (APlayer) GameScreen.get().camTarget;  
		
		// Buff
		if(player.buffs.size > 0) {
			buffTestLbl.setText("Buff : " + player.buffs.get(0).getTimeRemaining());
		}
		BombermanStats stats = ((BombermanStats) player.getStats());
		// Ammo
		ammoLbl.setText("Bombs : " + stats.nbBombs + "/" + stats.nbBombsMax);
		// Power
		powerLbl.setText("Power : " + stats.power);
		// Speed
		speedLbl.setText("Speed : " + stats.getTotSpeed());
		// Life
		lifeLbl.setText("Life : " + stats.life + "/" + stats.maxLife);
		// FPS
		fpsLbl.setText(Gdx.graphics.getFramesPerSecond() + " fps");
		// Score
		// scoreLbl.setText("Score : " + HighScore.current.update(delta, player));
		// Time
		timeLbl.setText("Time : " + (Math.round((time += delta) * 100) / 100f));
		// Position
		Vector3 dir = player.dirAngles; // cam.direction;
		playerPosLbl.setText("Pos : [" + Math.round(pos.x) + ", " + Math.round(pos.y) + ", " + Math.round(pos.z) + "]\n"
						   + "Vel : [" + Math.round(vel.x) + ", " + Math.round(vel.y) + ", 0] " 
						   + "Dir : [" + Math.round(dir.x) + ", " + Math.round(dir.y) + ", " + Math.round(dir.z) + "]");
		// Visible count
		visibleCountLbl.setText("Visible : " + GameScreen.get().visibleCount + " , instances.size : " + TestGame.get().universe.instances.size());
		
		// Player ID list
		String id = "null";
		if(TestGame.platform == Platform.Server){
			id = "Server";
		} else if(TestGame.get().universe.player != null){
			id = ((BPlayer)TestGame.get().universe.player).client.id + "";
		}
		playerListLbl.setText("Players ["+TestGame.get().getClientCount()+"] {"+TestGame.get().getClientIDList()+"}, MyId = "+id);
	}
	

}
