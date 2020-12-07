package bgu.spl.mics;

import java.util.HashMap;
import java.util.Map;
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

	private MicroService roundRobin(Class<? extends Event> type){
		MicroService microService = subscriptions.get(type).get(0);
		subscriptions.get(type).remove(0);
		while (!isRegistered(microService)){
			microService = subscriptions.get(type).get(0);
			subscriptions.get(type).remove(0);
		}
		subscriptions.get(type).add(microService);
		return microService;
	}

	private boolean isRegistered(MicroService microService){
		return messagesQs.containsKey(microService);
	}

	@Override
	public <T> Future<T> sendEvent(Event<T> e) {
		MicroService m = roundRobin(e.getClass());
		messagesQs.get(m).add(e);
		Future<T> f = new Future<>();
		if(isRegistered(m)){
			messagesQs.get(m).add(e);
			expectations.put(e,f);
		}
        return f;
	}

	@Override
	public void register(MicroService m) {
		messagesQs.put(m, new Vector<Message>());

	}

	@Override
	public void unregister(MicroService m) {
		messagesQs.remove(m);
		for (Map.Entry<Class<? extends Message>,Vector<MicroService>>pair:subscriptions.entrySet()){
			for (int i = 0; i < pair.getValue().size(); i++) {
				pair.getValue().remove(m);
			}
		}
	}

	@Override
	public Message awaitMessage(MicroService m) throws InterruptedException {
		while(messagesQs.get(m).isEmpty())
			wait();
		return messagesQs.get(m).remove(0);
	}
}
