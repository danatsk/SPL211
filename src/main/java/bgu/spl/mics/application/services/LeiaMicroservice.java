package bgu.spl.mics.application.services;

import java.awt.event.ActionEvent;
import java.util.ArrayList;
import java.util.List;

import bgu.spl.mics.MicroService;
import bgu.spl.mics.application.messages.ActivationEvent;
import bgu.spl.mics.application.messages.AttackEvent;
import bgu.spl.mics.application.messages.TerminationBroadcast;
import bgu.spl.mics.application.passiveObjects.*;

/**
 * LeiaMicroservices Initialized with Attack objects, and sends them as  {@link AttackEvent}.
 * This class may not hold references for objects which it is not responsible for:
 * {@link AttackEvent}.
 *
 * You can add private fields and public methods to this class.
 * You MAY change constructor signatures and even add new public constructors.
 */
public class LeiaMicroservice extends MicroService {
	private Attack[] attacks;

    public LeiaMicroservice(Attack[] attacks, int ewoks) {
        super("Leia");
        Ewoks.initialize(ewoks);
        this.attacks = attacks;
		initialize();
    }
    private void sendAttacks(){
        for (Attack a:attacks) {
            sendEvent(new AttackEvent(a.getSerials(),a.getDuration()));
        }
    }
    @Override
    protected void initialize() {
        Task init = new Task(name, "init", System.currentTimeMillis());
        mb.register(this);
        diary.addTask(init);
//        try {
//            Thread.sleep(300);
//        } catch (InterruptedException e) { }
        subscribeEvent(ActivationEvent.class,(bool)->sendAttacks());
        sendEvent(new ActivationEvent());
        subscribeBroadcast(TerminationBroadcast.class,(bool)->{terminate();});
    }
}
