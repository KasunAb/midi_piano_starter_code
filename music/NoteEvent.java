package music;

import piano.PianoMachine;

public abstract class NoteEvent {
    protected final Pitch pitch;
    protected final int delay;

    public NoteEvent(Pitch pitch){
        this(pitch,0);
    }

    public NoteEvent(Pitch pitch, int delay) {
        this.pitch = pitch;
        this.delay = delay;
    }
    abstract public  NoteEvent delayed(int delay);
    abstract public void execute(PianoMachine m);

    public Pitch getPitch() {
        return pitch;
    }

    public int getDelay() {
        return delay;
    }
}
