package bombermantest.network.packets;

import java.util.Collection;

import org.apache.mina.core.buffer.IoBuffer;
import org.apache.mina.core.session.IoSession;

import bombermantest.main.TestGame;

public class Packet implements IPacket {

	private Enum<?> enu;
	private Parser parser; 
	private Composer composer; 
	private final int baseLength;
	private final int mult;	
	private final boolean autoexpand;
	
	/**
	 * L'header est de 5 avec l'enum, mais faut pas la compter dans l'header en fait. L'enum fait plutot parti du packet lui-même et influence donc la packetlength.<p>
	 * L'header au final c'est juste le int packetlength 
	 */
	public static final int headerLength = 5 - 1; 
	

	public Packet(Enum<?> enu, Parser p, Composer c, boolean autoexpand, int baseLength, int playerMult){
		this.enu = enu;
		this.parser = p;
		this.composer = c;
		this.baseLength = baseLength + 1; // + 1 pour l'id du packet (enum)
		this.mult = playerMult;
		this.autoexpand = autoexpand;
	}

	/**
	 * Pour envoyer un packet avec un multiplicateur précis
	 * @return la longueur du packet AVEC le prefix.
	 */
	private int getPacketLength(int nb){
		return baseLength + mult * nb; // 4 = le prefix qui est unt int et qui contient la longueur du packet
	}

	@Override
	public IoBuffer allocateAutoExpand(){
		IoBuffer buf = IoBuffer.allocate(0);
		buf.setAutoExpand(true);
		return putHeader(buf, 0);
	}

	
	@Override
	public IoBuffer allocate(){
		if(mult == 0) return allocate(0);
		return allocate(TestGame.get().getClientCount());
	}

	@Override
	public IoBuffer allocate(int nb){
		IoBuffer buf = IoBuffer.allocate(headerLength + getPacketLength(nb));
		return putHeader(buf, nb);
	}
	
	private IoBuffer putHeader(IoBuffer buf, int nb){
		buf.putInt(getPacketLength(nb)); // 4
		buf.putEnum(enu); // 1
		return buf;
	}

	@Override
	public boolean isLengthCorrect(int size){
		//System.out.println("isSize correct : received = ["+size+"], baseLength = ["+baseLength+"], playerMult = ["+playerMult+"], expected = ["+(getPacketLength()-4)+"]");
		//if(this == ENTITY_LIST || this == ENTITY) return true;
		if(autoexpand) return true;
		if(mult == 0) return size == baseLength;
		return (size - baseLength) % mult == 0;
	}

	@Override
	public void parse(IoSession sess, IoBuffer mess) {
		parser.parse(sess, mess);
	}

	@Override
	public IoBuffer compose(IoSession session, Object...objects) {
		IoBuffer buf = autoexpand ? allocateAutoExpand() : allocate();
		
		buf = composer.compose(buf, session, objects);
		
		write(buf, session);
		
		return buf;
	}
	
	@Override
	public void broadcast(Collection<IoSession> collection, Object... objects){
		collection.forEach(s -> compose(s, objects));
	}

	@Override
	public void setParser(Parser p) {
		parser = p;
	}

	@Override
	public void setComposer(Composer c) {
		composer = c;
	}
	

	public void write(IoBuffer buf, IoSession session){
		if(session != null){
			buf.flip(); // XXX buf.flip avant buf.putInt(0, buf.limit() - headerLength) dans Packet.write. Not sure if ok or not.
				//System.out.println("Sending1 limit ["+buf.limit()+"]");
			buf.putInt(0, buf.limit() - headerLength);
				//System.out.println("Sending2 limit ["+buf.limit()+"]");
				//buf.flip();
				//System.out.println("Sending3 limit ["+buf.limit()+"]");
			session.write(buf);
				//System.out.println("Sending4 limit ["+buf.limit()+"]");
		}
	}
	
}
