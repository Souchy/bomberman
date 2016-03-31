package bombermantest.network.entities;

import org.apache.mina.core.buffer.IoBuffer;

@FunctionalInterface
public interface EntityEncoder<T> {
	
	public void encode(T object, IoBuffer buf);
	
}
