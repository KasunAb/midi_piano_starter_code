package music;

import midi.Instrument;
import piano.PianoMachine;

public class BeginNote extends NoteEvent {
    public BeginNote(Pitch pitch, Instrument instrument) {
        super(pitch,instrument);
    }

    public BeginNote(Pitch pitch, int delay,Instrument instrument) {
        super(pitch, delay,instrument);
    }

    @Override
    public NoteEvent delayed(int delay) {
        return null;
    }

    @Override
    public void execute(PianoMachine m) {
        m.playNote(this);
    }
}
