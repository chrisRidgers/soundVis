package chrisRidgers.app;
import processing.core.*;

public class Sketch extends PApplet
{
  Stripe[] stripes = new Stripe[50];

  public void setup()
  {
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
  
