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
    int delay=0;

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
        Pitch pitch = event.getPitch();
        int delayTime = event.getDelay();
        Instrument instrument = event.getInstrument();
        if(event instanceof BeginNote)
            beginNote(pitch,delayTime,instrument,event);
        else
            endNote(pitch,instrument,event);
    }

    public void beginNote(Pitch pitch,int delayTime,Instrument instrument,NoteEvent event){
        if(pitchesPlaying.contains(pitch)) {
            delay+=delayTime;
            return;
        }
        pitchesPlaying.add(pitch);
        midi.beginNote(pitch.toMidiFrequency(),instrument);
        event.setDelay(delay);
        delay=0;
        if(isRecording) recording.add(event);
    }
    public void endNote(Pitch pitch,Instrument instrument,NoteEvent event){
        midi.endNote(pitch.toMidiFrequency(),instrument);
        pitchesPlaying.remove(pitch);
        if(isRecording) recording.add(event);
    }

    public void requestPlayback(){
        player.playbackRecording(lastRecording);
    }


}
