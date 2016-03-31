package bombermantest.network.packets;

import java.nio.charset.Charset;
import java.nio.charset.CharsetDecoder;

import org.apache.mina.core.buffer.IoBuffer;
import org.apache.mina.core.session.IoSession;

@FunctionalInterface 
public interface Parser {

	public static CharsetDecoder decoder = Charset.forName("UTF-8").newDecoder();
	
	public void parse(IoSession session, IoBuffer buf);
	
}
