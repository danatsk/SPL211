package bgu.spl.mics;

import java.util.HashMap;
import java.util.Queue;
import java.util.Vector;
/**
 * The {@link MessageBusImpl class is the implementation of the MessageBus interface.
 * Write your implementation here!
 * Only private fields and methods can be added to this class.
 */
public class MessageBusImpl implements MessageBus {
	private static class MessageBussHolder{
		private static MessageBusImpl instance=new MessageBusImpl();
	}
	public static MessageBusImpl getInstance() {
		return MessageBussHolder.instance;
	}

	private HashMap<MicroService, Vector<Message>> messagesQs;
	private HashMap<Class<? extends Message>,Vector<MicroService>> subscriptions;
	private HashMap<Message,Future> expectations;

	private MessageBusImpl(){
		messagesQs = new HashMap<MicroService, Vector<Message>>();
		subscriptions=new HashMap<Class<? extends Message>,Vector<MicroService>>();
		expectations=new HashMap<Message,Future>();
	}

	@Override
	public <T> void subscribeEvent(Class<? extends Event<T>> type, MicroService m) {
		subscriptions.get(type).add(m);
	}

	@Override
	public void subscribeBroadcast(Class<? extends Broadcast> type, MicroService m) {
		subscriptions.get(type).add(m);
    }

	@Override @SuppressWarnings("unchecked")
	public <T> void complete(Event<T> e, T result) {
		expectations.get(e).resolve(result);
	}

	@Override
	public void sendBroadcast(Broadcast b) {
		Vector<MicroService> q=subscriptions.get(b.getClass());
		for (MicroService m:q) {
			messagesQs.get(m).add(b);
		}
	}

	
	@Override
	public <T> Future<T> sendEvent(Event<T> e) {
		Vector<MicroService> q=subscriptions.get(e.getClass());
		for (MicroService m:q) {
			messagesQs.get(m).add(e);
		}
		Future<T> f=new Future<>();
		expectations.put(e,f);
        return f;
	}

	@Override
	public void register(MicroService m) {
		messagesQs.put(m, new Vector<Message>());

	}

	@Override
	public void unregister(MicroService m) {
		
	}

	@Override
	public Message awaitMessage(MicroService m) throws InterruptedException {
		
		return null;
	}
}
