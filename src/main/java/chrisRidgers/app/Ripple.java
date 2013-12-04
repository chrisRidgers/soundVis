package chrisRidgers.app;
import processing.core.*;

public class Ripple
{
  int x, y, o;
  float r;
  PApplet parent;
  int color;

  Ripple(PApplet p, int freq, float intensity)
  {
    parent=p;
    x=(int)parent.map(freq, 0, 128, 0+parent.width/100*10, parent.width-parent.width/100*40);
    y=(int)parent.height/2;
    r=parent.map(intensity, 0, 255, 0, parent.height/2);
    o=(int)parent.map(intensity, 0, 255, 128, 255);
    color=parent.color
      (
       freq,
       255,
       255
       );
  }

  void move()
  {
    if(o>0)
    {
      o-=30;
      r+=1;
    }
  }

  void display()
  {
    parent.noFill();
    parent.stroke(color, o);
    parent.ellipse(x, y, r, r);
  }
}

