package piano;

import music.NoteEvent;

import java.util.List;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.LinkedBlockingQueue;

public class PianoPlayer {
    private final BlockingQueue<NoteEvent> queue, delayQueue;
    private final PianoMachine machine;

    public PianoPlayer() {
        queue= new LinkedBlockingQueue<NoteEvent>();
        delayQueue = new LinkedBlockingQueue<NoteEvent>();
        machine = new PianoMachine(this);
    }
    public void request (NoteEvent e){
        try {
            queue.put(e);
        } catch (InterruptedException interruptedException) {
            interruptedException.printStackTrace();
        }
    }
    public void requestPlayback(){
        machine.requestPlayback();
    }
    public void toggleRecording(){
        machine.toggleRecording();
    }
    public void playbackRecording(List<NoteEvent> recording){
        for (NoteEvent e:recording) {
            try {
                delayQueue.put(e);
            } catch (InterruptedException interruptedException) {
                interruptedException.printStackTrace();
            }
        }
    }
    public  void processQueue(){
        while (true){
            NoteEvent e = null;
            try {
                e = queue.take();
            } catch (InterruptedException interruptedException) {
                interruptedException.printStackTrace();
            }
            e.execute(machine);
        }
    }
    public void processDelayQueue(){
        while (true){
            NoteEvent e = null;
            try {
                e =delayQueue.take();
            } catch (InterruptedException interruptedException) {
                interruptedException.printStackTrace();
            }
            midi.Midi.wait(e.getDelay());
            try {
                queue.put(e);
            } catch (InterruptedException interruptedException) {
                interruptedException.printStackTrace();
            }
        }
    }
}
