package chrisRidgers.app;
import processing.core.*;

public class Ripple
{
  PApplet 	parent;								//Passes drawing window to object so that it knows where the paper is
  int 		x, y, r, o, color;						//Defines integer variables to calculate objects

  Ripple(PApplet p, int freq, float intensity)					//Constructor for object of Ripple
  {
    parent	= p;                                                            //Insert very basic magic here:  what makes it work.
    x		= (int)parent.map(						//Takes information from drawing window and FFT object to define rippple objects
	freq,0,128,0+parent.width/100*10,parent.width-parent.width/100*40);
    y		= (int)parent.height/2;
    r		= (int)parent.map(intensity, 0, 255, 0, parent.height/2);
    o		= (int)parent.map(intensity, 0, 255, 128, 255);
    color	= parent.color(freq, 255, 255);					//Color defined as HSB variables, FFT octave controls Hue value
  }

  void move()									//Checks to ensure that the Ripple object is still valid, updates r and o
  {
    if(o>0)o-=30;r++;
  }

  void display()								//Redraw ripple object on the parent window
  {
    parent.noFill();
    parent.stroke(color, o);
    parent.ellipse(x, y, r, r);
  }
}

