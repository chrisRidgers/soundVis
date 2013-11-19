package chrisRidgers.app;
import processing.core.*;
import ddf.minim.*;

public class Sketch extends PApplet
{
  Ripple[] ripples = new Ripple[20];
  boolean rippdone = false;
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
    
    for(int i=0;i<ripples.length;i++)
    {
      ripples[i]=new Ripple(this);
    }
  }

  public void draw()
  {
    background(0);
    for(int i=0;i<ripples.length;i++)
    {
      ripples[i].move();
      ripples[i].display();
    }
    if(ripples[ripples.length-1].o==0)rippdone=true;
    if(rippdone)
    {
    for(int i=0;i<ripples.length;i++)
    {
      ripples[i]=new Ripple(this);
    }
      rippdone = false;
    }
  }
}
