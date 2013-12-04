package chrisRidgers.app;
import processing.core.*;
import ddf.minim.*;
import ddf.minim.analysis.*;
import java.util.*;

public class Sketch extends PApplet
{
  ArrayList ripples = new ArrayList()					//Allows non-fixed number of elements
  String track; 							//Provides a reference to the track submitted on the command line
  Minim minim;								//Creates an object of the Minim object to provide access to sound method calls
  AudioPlayer player;							//Loads a sound file into memory, provides features such as navigation and audio out
  FFT fft;								//Creats FFT object providing access to STFT functions.

  public void setup()
  {              
    track 	= this.args[0];						//Initialises all variables to different values
    minim 	= new Minim(this);
    player 	= minim.loadFile(track, 2048);				//Defines a buffer size for player object of 2048 bytes
    fft 	= new FFT(player.bufferSize(), player.sampleRate());	//Defines the FFT object to have a buffer size equal to that of the player object

    player.loop();							//Instructs player to repeatedly play the file defined by the track variable
    fft.window(FFT.HAMMING);						//Instructs FFT object to use a 'Hamming Window', reducing noise pickup in samples
    fft.logAverages(20,20);						//Defines a minimum octave of 20Hz, and a divides each  octave into 20 bands.

    size(400,400);							//Sets up visual element, defines initial size, frame rate, and color settings.
    frameRate(30);
    colorMode(HSB);
    background(0);
    noFill();
    stroke(128);
    ellipseMode(CENTER);
    rectMode(CORNERS);

    if(frame!=null)
    {
      frame.setResizable(true);
    }
  }

  public void draw()
  {
    background(0);							//Overwrites previous frame
    stroke(128);

    fft.forward(player.mix);
    float w = width/(float)fft.avgSize();

    for(int i=0;i<fft.avgSize();i++)
    {
      rect(								//Defines the rectangle objects used to draw out the frequency spectograph
	  i*w, height,
	  i*w+w,
	  height/1-Math.round(2*20*Math.log10(100*fft.getAvg(i)))
	  );
      if(fft.getAvg(i)>10){						//Defines sensitivity of frequency detection and creates Ripple objects accordingly
	ripples.add(new Ripple(this,i,fft.getAvg(i)));
      }
    }

    for(int i=0;i<ripples.size()-1;i++)					//Carry's out ripple functions to modify and update visuals
    {
      Ripple test = (Ripple) ripples.get(i);
      test.display();
      test.move();

      if(test.o<=0)							//Detects whether ripple objects are in use and deletes them if not
      {
	ripples.remove(i);
	ripples.trimToSize();
      }
    }
  }
}
