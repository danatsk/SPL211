package bgu.spl.mics.application.services;

import bgu.spl.mics.MicroService;
import bgu.spl.mics.application.messages.DeactivationEvent;

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
    }

    @Override
    protected void initialize() {
       subscribeEvent(DeactivationEvent.class,(long duration)->(try {
               Thread.sleep(duration);
           } catch (InterruptedException e) {}
        ));

    }
}
