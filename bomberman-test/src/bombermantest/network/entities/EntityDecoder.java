package bombermantest.network.entities;

import org.apache.mina.core.buffer.IoBuffer;

@FunctionalInterface
public interface EntityDecoder<T> {

	public T decode(IoBuffer buf);
	
}
