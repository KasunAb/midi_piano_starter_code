package music;

import midi.Instrument;
import piano.PianoMachine;

public abstract class NoteEvent {
    protected final Pitch pitch;
    protected int delay;
    protected final Instrument instrument;

    public NoteEvent(Pitch pitch,Instrument instrument){
        this(pitch,0,instrument);
    }

    public NoteEvent(Pitch pitch, int delay, Instrument instrument) {
        this.pitch = pitch;
        this.delay = delay;
        this.instrument=instrument;
    }
    abstract public  NoteEvent delayed(int delay);
    abstract public void execute(PianoMachine m);

    public Pitch getPitch() {
        return pitch;
    }

    public int getDelay() {
        return delay;
    }

    public Instrument getInstrument() {
        return instrument;
    }

    public void setDelay(int delay) {
        this.delay = delay;
    }
}
