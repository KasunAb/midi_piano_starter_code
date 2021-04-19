package music;

import piano.PianoMachine;

public class EndNote extends NoteEvent{
    public EndNote(Pitch pitch) {
        super(pitch);
    }

    public EndNote(Pitch pitch, int delay) {
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
