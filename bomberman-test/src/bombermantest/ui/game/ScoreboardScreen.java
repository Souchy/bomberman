package bombermantest.ui.game;

import java.util.ArrayList;
import java.util.List;

import com.badlogic.gdx.scenes.scene2d.ui.Cell;
import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.kotcrab.vis.ui.widget.VisTable;
import com.mygdx.engine.configs.AConstants;
import com.mygdx.engine.screens.Screen3d;

import bombermantest.main.TestGame;
import bombermantest.network.objects.GClient;

public class ScoreboardScreen extends Screen3d {

	private static ScoreboardScreen singleton;
	public static ScoreboardScreen get(){
		if(singleton == null){
			singleton = new ScoreboardScreen(); 
			singleton.create(null);
		}
		return singleton;
	}
	
	private VisTable table;
	
	@Override
	public void postCreateHook() {
		hud.setDebugAll(true);
		
        // Table
		table = new VisTable(true);
		hud.addActor(table);
		
	}
	
	public void show(){
		super.show();
		updateClientList();
	}
	
	@Override
	public void preDrawHook(float delta) { }

	@Override
	public void postDrawHook(float delta) { }

	public void updateClientList() {
		clearList();
		TestGame.get().getClientList().forEach(c -> add(c));
	}

	public void clearList() {
		table.clear();
		table.add("Name");
		table.add("Wins");
		table.add("Kills");
		table.add("Deaths");
		table.add("State");
		table.row();
		table.pack();
	}
	
	public void add(GClient client) {
		List<Cell<Label>> row = new ArrayList<>();
        
		row.add(table.add(client.name));
		row.add(table.add(Integer.toString(client.wins)));
		row.add(table.add(Integer.toString(client.kills)));
		row.add(table.add(Integer.toString(client.deaths)));
		row.add(table.add(client.state.name()));
		
		table.row();
		
		table.pack();
		
		row.forEach(c -> c.getActor().addListener(AConstants.hoverEffect));
	}

}
