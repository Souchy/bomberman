package bombermantest.ui.game;

import java.util.Optional;
import java.util.concurrent.Executors;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input.Keys;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.g3d.Environment;
import com.badlogic.gdx.graphics.g3d.ModelBatch;
import com.badlogic.gdx.graphics.g3d.attributes.ColorAttribute;
import com.badlogic.gdx.graphics.g3d.environment.DirectionalLight;
import com.badlogic.gdx.graphics.g3d.particles.ParticleSystem;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.math.Vector3;
import com.badlogic.gdx.physics.box2d.Box2DDebugRenderer;
import com.badlogic.gdx.scenes.scene2d.actions.Actions;
import com.google.common.eventbus.Subscribe;
import com.mygdx.engine.actors.MyLabel;
import com.mygdx.engine.configs.Test;
import com.mygdx.engine.game.Universe;
import com.mygdx.engine.objects.Box2dObject;
import com.mygdx.engine.screens.Screen3d;
import com.mygdx.engine.services.FontsLoader;

import bombermantest.enums.ClientState;
import bombermantest.events.SpectatingClickListener;
import bombermantest.events.TextPopupEvent;
import bombermantest.main.TestGame;
import bombermantest.network.objects.GClient;
import bombermantest.objects.characters.playables.BPlayer;

public class GameScreen extends Screen3d {

	private static GameScreen singleton;
	public static GameScreen get(){
		if(singleton == null){
			singleton = new GameScreen(); 
			singleton.create(null);
		}
		return singleton;
	}

    private Box2DDebugRenderer renderer;
	private ModelBatch modelBatch;
	private Environment environment;
	public Box2dObject camTarget; 
    public int visibleCount;
	

	@Override
	public void show() {
		super.show();
		resetCamTarget();
        System.out.println("GameScreen.show, camtarget = ["+GameScreen.get().camTarget+"]");
	}
	
	@Override
	public void postCreateHook() {
		Universe.bus.register(this);
		
		renderer = new Box2DDebugRenderer();
        
        // Model Environment
		modelBatch = new ModelBatch();
		environment = new Environment();
		environment.set(new ColorAttribute(ColorAttribute.AmbientLight, 0.4f, 0.4f, 0.4f, 1f));
		environment.add(new DirectionalLight().set(0.8f, 0.8f, 0.8f, -1f, -0.8f, -0.2f));
		
		// Hud setup
		//setupHud();
		
		// Keyboard
        /*hud.addListener(new InputListener() {
			@Override
			public boolean keyDown(InputEvent event, int keycode) {
				//if (Gdx.input.isKeyPressed(Keys.ESCAPE)) game.setScreen(PauseScreen.get());
				if (Gdx.input.isKeyPressed(Keys.TAB)) game.setScreen(ScoreboardScreen.get());
				return false;
			}
		});*/
        
        // Mouse (choosing target to spectate after dying)
        hud.addListener(new SpectatingClickListener());
		
	}
	
	public void resetCamTarget(){
       	if(ClientState.state == ClientState.PLAYING) {
       		camTarget = TestGame.get().universe.player;
       	} else {
       		TestGame.get().getClientList().stream()
       				.filter(c -> c.state == ClientState.PLAYING) // SI T'ES PLAYING, ÇA IMPLIQUE QUE T'AS UN BPLAYER
       				.filter(c -> c.player != null)
       				.filter(c -> c.player.body != null) // il est ptete pas encore build() par contre
       				.findFirst()
       				.ifPresent(c -> camTarget = c.player);
       		
       		/*for(GClient target : TestGame.get().getClientList()){
       			if(target.state == ClientState.PLAYING && target.player != null && target.player.body != null){
    				camTarget = target.player;
    			}
    		}*/
       	}
       	
		System.out.println("spectate = ["+GameScreen.get().camTarget+"], "
				+ "body = ["+(GameScreen.get().camTarget != null ? GameScreen.get().camTarget.body : "null target")+"]");
       	if(GameScreen.get().camTarget != null){
       		if(GameScreen.get().camTarget instanceof BPlayer){
       			BPlayer p = (BPlayer) GameScreen.get().camTarget;
       			if(p.client != null){
       				System.out.println("p.client = ["+p.client+"]");
       				if(p.client.player != null){
       					System.out.println("p.client.player.client = ["+p.client+"]");
       				}
       			}else{
       				System.out.println("client null");
       			}
       		}
       	}
	}
	
	@SuppressWarnings("deprecation")
	@Override
	public void preDrawHook(float delta) {
		long currentTime = System.currentTimeMillis();
		TestGame.get().universe.instances.forEach(i -> {
			i.update(delta);
			i.updateHurt(currentTime);
		});
		if(camTarget == null /*|| camTarget.body == null*/) resetCamTarget();
		if(camTarget == null /*|| camTarget.body == null*/) return;
		/*if(camTarget.body == null){
			camTarget = TestGame.get().universe.player;
			System.err.println("WTF CAMTARGET BODY NULL, finalement ça l'air d'être juste pcq le camTarget en question a déconnecté");
		}*/
	}
	
	@Override
	public void draw(float delta){
		if(camTarget == null /*|| camTarget.body == null*/) resetCamTarget();
		if(camTarget == null /*|| camTarget.body == null*/) return;
		
        // Draw Models
        visibleCount = 0;
		modelBatch.begin(playCam);
		TestGame.get().universe.instances.forEach(box -> {
			if(box.isVisible(camTarget)){
				visibleCount++;
				modelBatch.render(box.gfx, environment);
			}
		});
		
		// Draw Particles
		ParticleSystem.get().update(); // technically not necessary for rendering
		ParticleSystem.get().begin();
		ParticleSystem.get().draw();
		ParticleSystem.get().end();
		modelBatch.render(ParticleSystem.get());
		modelBatch.end();
		
		// Draw Debug
		if(Test.get().drawDebug){
			renderer.render(TestGame.get().universe.world, playCam.combined); // Box2d Debug Renderer
		}

	}
	
	@Override
	public void postDrawHook(float delta) {
		
		// Box2d world physics
		TestGame.get().universe.world.step(delta, 6, 2);
		
		if(camTarget == null /*|| camTarget.body == null*/) resetCamTarget();
		if(camTarget == null /*|| camTarget.body == null*/) return;
		
		updateCam(delta);
		
		// Draw the status hud
		StatusHud.get().drawHud(delta);
		StatusHud.get().postDrawHook(delta);
		
		// Draw the scoreboard hud
		if(Gdx.input.isKeyPressed(Keys.TAB)){
			// affiche l'écran de score par dessus le reste
			ScoreboardScreen.get().drawHud(delta);
		}
		
		// Draw the chatbox Hud
		AChatHud.get().drawHud(delta);
		
	}
	
	private void updateCam(float delta){

		Vector3 pos = new Vector3(camTarget.getPos(), 0);
		Vector2 vel = camTarget.body.getLinearVelocity();
		// Update cam control
		//Gdx.input.setInputProcessor(null);
		//cam.position.x = 0;
		//cam.position.y = 0;
		//cam.zoom = .1f;
		float lerp = 3f; // following speed
		playCam.position.x += ((pos.x + 0) - playCam.position.x) * lerp * delta; //((pos.x + 15) - cam.position.x) * lerp * delta;// + 80 * delta;
		float dY = ((pos.y + 3) - playCam.position.y) * lerp * delta; //((pos.y + 3) - cam.position.y) * lerp * delta;
		//if(cam.position.y + dY < Constants.camMax && cam.position.y + dY > Constants.camMin) 
		playCam.position.y += dY;
		playCam.update();
		//camController.update();
	}
	
	@Subscribe
	public void textPopup(TextPopupEvent event){
		Executors.newSingleThreadExecutor().execute(() -> { //scheduler.execute(() -> { //Gdx.app.postRunnable(() -> {
			//System.out.println(event.getText());
			
			MyLabel popup = new MyLabel(event.getText(), FontsLoader.singleton.hongkong, Color.WHITE, 50);
			popup.setSize(popup.getPrefWidth(), popup.getPrefHeight());
			popup.setPosition(getPlayCamWidth() / 2 - popup.getWidth() / 2, getPlayCamHeight() / 2 - popup.getHeight() / 2);
			
			hud.addActor(popup);
			popup.addAction(Actions.sequence(Actions.alpha(0.0f), Actions.fadeIn(0.3f), Actions.delay(0.6f), Actions.fadeOut(0.3f), Actions.removeActor()));
		});
	}

}
