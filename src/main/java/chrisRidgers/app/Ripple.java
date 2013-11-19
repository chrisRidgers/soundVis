package chrisRidgers.app;
import processing.core.*;

public class Ripple
{
  int x, y, r, o;
  PApplet parent;
  int color;

  Ripple(PApplet p)
  {
    parent=p;
    x=(int)parent.random(0, parent.width);
    y=(int)parent.random(0, parent.height);
    r=(int)parent.random(2,200);
    o=255;
    color=parent.color
      ( parent.random(0,255),
	parent.random(0,255),
	parent.random(0,255));
  }

  void move()
  {
    if(o>0)
    {
      r+=1;
      o--;
    }
  }

  void display()
  {
    parent.noFill();
    parent.stroke(color, o);
    parent.ellipse(x, y, r, r);
  }
}

