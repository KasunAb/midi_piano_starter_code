package music;

import piano.PianoMachine;

public class BeginNote extends NoteEvent {
    public BeginNote(Pitch pitch) {
        super(pitch);
    }

    public BeginNote(Pitch pitch, int delay) {
        super(pitch, delay);
    }

    @Override
    public NoteEvent delayed(int delay) {
        return null;
    }

    @Override
    public void execute(PianoMachine m) {

    }
}
