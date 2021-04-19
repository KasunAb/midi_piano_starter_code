/**
 * Author: dnj
 * Date: August 29, 2008
 * 6.005 Elements of Software Construction
 * (c) 2008, MIT and Daniel Jackson
 */
package piano;
import java.awt.*;
import java.awt.event.*;
import javax.sound.midi.MidiUnavailableException;
import java.applet.*;
import midi.*;
import music.*;

/**
 * A skeletal applet that shows how to bind methods to key events
 */
public class PianoApplet extends Applet {
	
	public void init() {
		final Midi midi;
		try {
			midi = new Midi();
		} catch (MidiUnavailableException e1) {
			e1.printStackTrace();
			return;
		}
		
		// this is a standard pattern for associating method calls with GUI events
		// the call to the constructor of KeyAdapter creates an object of an
		// anonymous subclass of KeyAdapter, whose keyPressed method is called
		// when a key is pressed in the GUI
		addKeyListener(new KeyAdapter() {
			public void keyPressed(KeyEvent e) {
				char key = (char) e.getKeyCode();
				switch (key) {
				case 'A':
			        midi.beginNote(new Pitch('A').toMidiFrequency());
					setBackground(Color.red);
			        return;
				case 'B':
			        midi.beginNote(new Pitch('B').toMidiFrequency());
			        setBackground(Color.green);
			        return;
				case 'C':
			        midi.beginNote(new Pitch('C').toMidiFrequency());
			        setBackground(Color.blue);
			        return;
				}
			}
		});

		addKeyListener(new KeyAdapter() {
			public void keyReleased(KeyEvent e) {
				char key = (char) e.getKeyCode();
				switch (key) {
				case 'A':
			        midi.endNote(new Pitch('A').toMidiFrequency());
			        return;
				case 'B':
			        midi.endNote(new Pitch('B').toMidiFrequency());
			        return;
				case 'C':
			        midi.endNote(new Pitch('C').toMidiFrequency());
			        return;
				}
			}
		});
	}	
    
}