package bombermantest.network.packets;

import java.nio.charset.Charset;
import java.nio.charset.CharsetEncoder;

import org.apache.mina.core.buffer.IoBuffer;
import org.apache.mina.core.session.IoSession;

@FunctionalInterface 
public interface Composer {
	
	public static CharsetEncoder encoder = Charset.forName("UTF-8").newEncoder();
	
	public IoBuffer compose(IoBuffer buf, IoSession session, Object...objects);
	
}
