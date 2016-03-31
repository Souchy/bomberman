package bombermantest.network.entities;

import org.apache.mina.core.buffer.IoBuffer;

@SuppressWarnings("rawtypes")
public enum Entities implements EntityEncoder, EntityDecoder {
	
	GClient(false),

	// Player
	BPlayer(true),
	
	// Projectiles 
	NormalBomb(true),
	
	// Items
	Booster(true),
	
		// Scenery Items
		UnbreakableBlock(true),
		Crate(true),
	;
	
	
	private EntityEncoder encoder;
	private EntityDecoder decoder;
	private boolean isBox2dObject;
	
	/*private Entities(EntityEncoder encod, EntityDecoder decod){
		encoder = encod;
		decoder = decod;
	}*/
	private Entities(boolean isbox2dobject){
		isBox2dObject = isbox2dobject;
	}

	@Override
	public Object decode(IoBuffer buf) {
		return decoder.decode(buf);
	}

	@SuppressWarnings("unchecked")
	@Override
	public void encode(Object object, IoBuffer buf) {
		encoder.encode(object, buf);
	}
	
	public void setEncoder(EntityEncoder encoder){
		this.encoder = encoder;
	}
	
	public void setDecoder(EntityDecoder decoder){
		this.decoder = decoder;
	}
	
	public boolean isBox2dObject(){
		return isBox2dObject;
	}
	
}
