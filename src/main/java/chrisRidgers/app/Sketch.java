package chrisRidgers.app;
import processing.core.*;
import ddf.minim.*;
import java.util.*;

public class Sketch extends PApplet
{
  ArrayList ripples = new ArrayList();
  String track; 
  Minim minim;
  AudioPlayer player;

  public void setup()
  {              
    ellipseMode(CENTER);
    track = this.args[0];
    minim = new Minim(this);
    player = minim.loadFile(track);
    player.play();
    frameRate(60);
    size(400, 400);
    noFill();
    background(0);

    if(frame!=null)
    {
      frame.setResizable(true);
    }
  }

  public void draw()
  {
    background(0);
    if(ripples.size()<255)ripples.add(new Ripple(this)); 
    System.out.println(ripples.size());
    for(int i=0;i<ripples.size()-1;i++){
      Ripple test = (Ripple) ripples.get(i);
      test.move();
      test.display();
      if(test.o<=0)
      {
	ripples.remove(i);
	ripples.trimToSize();
      }
    }
  }
}
