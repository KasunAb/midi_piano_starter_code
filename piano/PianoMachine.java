package piano;

import midi.Instrument;
import midi.Midi;
import music.BeginNote;
import music.NoteEvent;
import music.Pitch;

import javax.sound.midi.MidiUnavailableException;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class PianoMachine {
    private boolean isRecording = false;
    private List<NoteEvent> recording, lastRecording;
    private Set<Pitch> pitchesPlaying;
    private final PianoPlayer player;
    private Midi midi;

    public PianoMachine(PianoPlayer player) {
        lastRecording = new ArrayList<NoteEvent>();
        pitchesPlaying = new HashSet<Pitch>();
        try {
            midi=new Midi();
        } catch (MidiUnavailableException e) {
            System.out.println("eror when creating midi in pianoMachine");
        }
        this.player=player;
    }
    public void toggleRecording(){
        if(isRecording)
            lastRecording = recording;
        else {
            recording= new ArrayList<NoteEvent>();
        }
        isRecording= !isRecording;
    }
    public void playNote(NoteEvent event){
        if(isRecording) recording.add(event);
        Pitch pitch = event.getPitch();
        Instrument instrument = event.getInstrument();
        if(event instanceof BeginNote)
            beginNote(pitch,instrument);
        else
            endNote(pitch,instrument);
    }

    public void beginNote(Pitch pitch,Instrument instrument){
        if(pitchesPlaying.contains(pitch)) return;
        pitchesPlaying.add(pitch);
        midi.beginNote(pitch.toMidiFrequency(),instrument);
    }
    public void endNote(Pitch pitch,Instrument instrument){
        midi.endNote(pitch.toMidiFrequency(),instrument);
        pitchesPlaying.remove(pitch);
    }

    public void requestPlayback(){
        player.playbackRecording(lastRecording);
    }

}
