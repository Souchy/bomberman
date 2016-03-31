package bombermantest.network.handlers;

import org.apache.mina.core.session.IdleStatus;
import org.apache.mina.core.session.IoSession;

import com.badlogic.gdx.Gdx;

import Handlers.MinaHandler;

public abstract class ClientHandler extends MinaHandler {

	@Override
	public void exceptionCaught(IoSession session, Throwable mess) throws Exception {
		session.close(true);
		System.out.println("client exceptionCaught " + session.getId() + " : ");
		mess.printStackTrace();
	}
	
	@Override
	public void sessionClosed(IoSession session) throws Exception {
		System.out.println("client sessionClosed " + session.getId());
		Gdx.app.exit();
	}

	@Override
	public void sessionIdle(IoSession session, IdleStatus mess) throws Exception {
		System.out.println("client sessionIdle " + session.getId());
		session.close(true);
	}


}
