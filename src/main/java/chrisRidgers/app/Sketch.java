package chrisRidgers.app;
import processing.core.*;
import ddf.minim.*;

public class Sketch extends PApplet
{
  Stripe[] stripes = new Stripe[50];
  Minim minim;
  AudioPlayer player;

  public void setup()
  {
    minim = new Minim(this);
    player = minim.loadFile(this.args[0]);
    player.play();
    frameRate(60);
    size(400, 400);
    if(frame!=null)
    {
      frame.setResizable(true);
    }
    background(100);
    for(int i=0;i<stripes.length;i++)
    {
      stripes[i]=new Stripe(this);
    }
  }

  public void draw()
  {
    background(100);
    for(int i=0;i<stripes.length;i++)
    {
      stripes[i].move();
      stripes[i].display();
    }
  }
}
