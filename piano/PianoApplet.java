/**
 * Author: dnj
 * Date: August 29, 2008
 * 6.005 Elements of Software Construction
 * (c) 2008, MIT and Daniel Jackson
 */
package piano;
import java.awt.event.*;
import java.applet.*;
import java.util.HashMap;
import java.util.Map;

import midi.Instrument;
import midi.Midi;
import music.*;

/**
 * A skeletal applet that shows how to bind methods to key events
 */
public class PianoApplet extends Applet {
	Instrument currentInstrument = Midi.DEFAULT_INSTRUMENT;
	Map<Character, Integer> map=new HashMap<>();
	PianoPlayer player= new PianoPlayer();
	public void init() {
		map.put('1',0);
		map.put('2',1);
		map.put('3',2);
		map.put('4',3);
		map.put('5',4);
		map.put('6',5);
		map.put('7',6);
		map.put('8',7);
		map.put('9',8);
		map.put('=',9);

		Runnable play = () -> player.processQueue();
		(new Thread(play)).start();

		Runnable rePlay = () -> player.processDelayQueue();
		(new Thread(rePlay)).start();
		// this is a standard pattern for associating method calls with GUI events
		// the call to the constructor of KeyAdapter creates an object of an
		// anonymous subclass of KeyAdapter, whose keyPressed method is called
		// when a key is pressed in the GUI

		addKeyListener(new KeyAdapter() {
			public void keyPressed(KeyEvent e) {
				char key = (char) e.getKeyCode();
				if(map.containsKey(key)){
					player.request(new BeginNote(new Pitch(map.get(key)),currentInstrument));
				}
				if(key == 'I'){
					currentInstrument = currentInstrument.next();
					System.out.println("instrument changed");
				}
				if(key=='R')
					player.toggleRecording();
				if(key=='P'){
					player.requestPlayback();
					System.out.println("start playback");}
			}
		});

		addKeyListener(new KeyAdapter() {
			public void keyReleased(KeyEvent e) {
				char key = (char) e.getKeyCode();
				if(map.containsKey(key)){
					player.request(new EndNote(new Pitch(map.get(key)),currentInstrument));
				}
			}
		});

	}
    
}