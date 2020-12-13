package bgu.spl.mics.application.services;

import bgu.spl.mics.MicroService;
import bgu.spl.mics.application.messages.DeactivationEvent;
import bgu.spl.mics.application.messages.TerminationBroadcast;
import bgu.spl.mics.application.passiveObjects.Diary;
import bgu.spl.mics.application.passiveObjects.Task;

import java.util.concurrent.TimeUnit;

/**
 * LandoMicroservice
 * You can add private fields and public methods to this class.
 * You MAY change constructor signatures and even add new public constructors.
 */
public class LandoMicroservice  extends MicroService {
    private long duration;
    public LandoMicroservice(long duration) {
        super("Lando");
        this.duration= TimeUnit.MILLISECONDS.toMillis(duration);
        initialize();
    }

    @Override
    protected void initialize() {
        Task init = new Task(name, "init", System.currentTimeMillis());
        diary.addTask(init);
        Diary diary = Diary.getInstance();
       subscribeEvent(DeactivationEvent.class,(duration)->{try {
               Thread.sleep(this.duration);
           } catch (InterruptedException e) {}
        });
        subscribeBroadcast(TerminationBroadcast.class,(bool)->{terminate();});
    }
}
