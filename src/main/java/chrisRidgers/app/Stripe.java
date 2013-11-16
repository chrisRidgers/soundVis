package chrisRidgers.app;
import processing.core.*;

public class Stripe
{
  float x, speed, w;
  boolean mouse;
  PApplet parent;
  int color;

  Stripe(PApplet p)
  {
    parent=p;
    x=0;
    speed=parent.random(1);
    w=parent.random(10,30);
    mouse=false;
    color = 
      parent.color(parent.random(0,255),
	  parent.random(0,255),
	  parent.random(0,255));
  }

  void display()
  {
    parent.fill(color,100);
    parent.noStroke();
    parent.rect(x,0,w,parent.height);
  }
  void move()
  {
    x+=speed;
    if(x>parent.width+20)x=-20;
  }
}
