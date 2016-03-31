package bombermantest.network.packets;

import java.util.Collection;

import org.apache.mina.core.buffer.IoBuffer;
import org.apache.mina.core.session.IoSession;

public interface IPacket extends Composer2, Parser {
	
	/**
	 * allocate en utilisant le nombre de players comme multiplicateur de longueur de packet
	 */
	public IoBuffer allocate();

	/**
	 * allocate en utilisant nb comme multiplicateur de longueur de packet
	 */
	public IoBuffer allocate(int nb);

	/**
	 * allocate en mettant autoExpand() à true <p>
	 * you must set the packet length with buf.putInt(0, buf.limit() - 5) after filling it tho. (5 = 4 du int PacketLength + 1 de l'Enum) 
	 */
	public IoBuffer allocateAutoExpand();
	
	/**
	 * Pour vérifier un packet reçu
	 * @param size La longueur du packet qu'on a reçu lors d'un messageReceived sans la longueur du prefix. Size = prefix en fait.
	 * @return
	 */
	public boolean isLengthCorrect(int size);
	
	public void setParser(Parser p);
	public void setComposer(Composer c);
	
	public static int fullPosLength(){
		 return 2 * 4 + 3 * 4; // 2*4 pour la pos (x,y) et 3*4 pour les angles (x,y,z) 
	}

	public void broadcast(Collection<IoSession> sessions, Object... objects);

}
