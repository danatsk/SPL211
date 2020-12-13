package bgu.spl.mics.application.passiveObjects;


import com.google.gson.*;
import com.sun.tools.javac.util.List;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Vector;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * Passive data-object representing a Diary - in which the flow of the battle is recorded.
 * We are going to compare your recordings with the expected recordings, and make sure that your output makes sense.
 * <p>
 * Do not add to this class nothing but a single constructor, getters and setters.
 */
public class Diary {
    private String jsonString="";
    private AtomicInteger totalAttacks;
    private Vector<Task> data;

    private static class DiaryHolder {
        private static Diary instance = new Diary();
    }
    private Diary(){
        totalAttacks=new AtomicInteger(0);
        data=new Vector<Task>();
    }

    public static Diary getInstance() {
        return DiaryHolder.instance;
    }

/*    private long HanSoloFinish=0;
    private long C3POFinish=0;
    private long R2D2Deactivate=0;
    private long LeiaTerminate=0;
    private long HanSoloTerminate=0;
    private long C3POTerminate=0;
    private long R2D2Terminate=0;
    private long LandoTerminate=0;



    public void updateTotalAttacks() {
        while (!totalAttacks.compareAndSet(totalAttacks.intValue(), totalAttacks.intValue() + 1)) {
        }
        ;
    }

    public void HanSoloFinished(long time) {
        HanSoloFinish = time;
    }

    public void C3POFinished(long time) {
        C3POFinish = time;
    }

    public void R2D2Deactivated(long time) {
        R2D2Deactivate = time;
    }

    public void LeiaTerminated(long time) {
        LeiaTerminate = time;
    }

    public void HanSoloTerminated(long time) {
        HanSoloTerminate = time;
    }

    public void C3POTerminated(long time) {
        C3POTerminate = time;
    }

    public void LandoTerminated(long time) {
        LandoTerminate = time;
    }

    public void R2D2Terminated(long time) {
        R2D2Terminate = time;
    }

//    public int getTotalAttacks() {
//        return totalAttacks.intValue();
//    }
//
//    public long getC3POFinish() {
//        return C3POFinish;
//    }
//
//    public long getHanSoloFinish() {
//        return HanSoloFinish;
//    }
//
//    public long getR2D2Deactivate() {
//        return R2D2Deactivate;
//    }
//
//    public long getLeiaTerminate() {
//        return LeiaTerminate;
//    }
//
//    public long getHanSoloTerminate() {
//        return HanSoloTerminate;
//    }
//
//    public long getC3POTerminate() {
//        return C3POTerminate;
//    }
//
//    public long getR2D2Terminate() {
//        return R2D2Terminate;
//    }
//
//    public long getLandoTerminate() {
//        return LandoTerminate;
//    }

    public void writeOutput(String filename) {
        JsonArray jsonDiaryArray = new JsonArray();
        jsonDiaryArray.add("Total attacks: " + totalAttacks.intValue());
        jsonDiaryArray.add("Han-Solo finished his attacks after: " + HanSoloFinish + " milliseconds");
        jsonDiaryArray.add("C3PO finished his attacks after: " + C3POFinish + " milliseconds");
        jsonDiaryArray.add("R2D2 deactivated the shield generator after: " + R2D2Deactivate + " milliseconds");
        jsonDiaryArray.add("Leia was terminated after " + LeiaTerminate + "milliseconds");
        jsonDiaryArray.add("Han-Solo was terminated after " + HanSoloTerminate + " milliseconds");
        jsonDiaryArray.add("C3PO was terminated after " + C3POTerminate + " milliseconds");
        jsonDiaryArray.add("R2D2 was terminated after " + R2D2Terminate + "milliseconds");
        jsonDiaryArray.add("Lando was terminated after " + " milliseconds");
        try {
            FileWriter fileWriter = new FileWriter("../output.txt");
            fileWriter.write(jsonDiaryArray.toString());
        } catch (IOException e) {
            e.printStackTrace();
        }
    }*/
}
