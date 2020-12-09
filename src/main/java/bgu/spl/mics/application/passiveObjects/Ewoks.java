package bgu.spl.mics.application.passiveObjects;


import java.util.Vector;

/**
 * Passive object representing the resource manager.
 * <p>
 * This class must be implemented as a thread-safe singleton.
 * You must not alter any of the given public methods of this class.
 * <p>
 * You can add ONLY private methods and fields to this class.
 */
public class Ewoks {
    private Vector<Ewok> ewoks;

    private static class EwoksHolder{
        private static Ewoks instance;
    }

    public static void initialize(int numOfEwoks){
        EwoksHolder.instance=new Ewoks(numOfEwoks);
    }
    public  static Ewoks getInstance(){
        return EwoksHolder.instance;
    }
    private Ewoks(int numOfEwoks){
        for (int i = 1; i <= numOfEwoks; i++) {
            ewoks.add(new Ewok(i));
        }
    }
}
