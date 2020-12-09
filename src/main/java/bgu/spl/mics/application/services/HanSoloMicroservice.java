package bgu.spl.mics.application.services;


import bgu.spl.mics.MicroService;
import bgu.spl.mics.application.messages.AttackEvent;
import bgu.spl.mics.application.passiveObjects.Ewoks;

import java.util.concurrent.TimeUnit;

/**
 * HanSoloMicroservices is in charge of the handling {@link AttackEvent}.
 * This class may not hold references for objects which it is not responsible for:
 * {@link AttackEvent}.
 *
 * You can add private fields and public methods to this class.
 * You MAY change constructor signatures and even add new public constructors.
 */
public class HanSoloMicroservice extends MicroService {

    public HanSoloMicroservice() {
        super("Han");
    }


    @Override
    protected void initialize() {
        Ewoks ewoks = Ewoks.getInstance();
        subscribeEvent(AttackEvent.class, (attack) ->
        {ewoks.acquire(attack.getSerials()); long duration = TimeUnit.MILLISECONDS.toMillis(attack.getDuration());
            try {
                Thread.sleep(duration);
            } catch (InterruptedException e) {};
            ewoks.realse(attack.getSerials());
        });
    }
}
