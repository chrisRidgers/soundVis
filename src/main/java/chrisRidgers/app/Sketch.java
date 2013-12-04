package chrisRidgers.app;
import processing.core.*;
import ddf.minim.*;
import ddf.minim.analysis.*;
import java.util.*;

public class Sketch extends PApplet
{
  ArrayList ripples = new ArrayList();
  String track; 
  Minim minim;
  AudioPlayer player;
  FFT fft;

  public void setup()
  {              
    track 	= this.args[0];
    minim 	= new Minim(this);
    player 	= minim.loadFile(track, 2048);
    fft 	= new FFT(player.bufferSize(), player.sampleRate());

    player.loop();
    fft.window(FFT.HAMMING);
    fft.logAverages(20,20);

    size(400,400);
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
    background(0);
    stroke(128);

    fft.forward(player.mix);
    float w = width/(float)fft.avgSize();

    for(int i=0;i<fft.avgSize();i++)
    {
      rect(i*w, height, i*w+w, height/1-Math.round(2*20*Math.log10(100*fft.getAvg(i))));
      //if(fft.getAvg(i)>260)System.out.println(fft.getAvg(i));
      if(fft.getAvg(i)>10)ripples.add(new Ripple(this, i, fft.getAvg(i)));
    }

    for(int i=0;i<ripples.size()-1;i++)
    {
      Ripple test = (Ripple) ripples.get(i);
      test.display();
      test.move();

      if(test.o<=0)
      {
	ripples.remove(i);
	ripples.trimToSize();
      }
    }
  }
}
