import java.util.Random;

import processing.core.PApplet;

public class Particle {
	
	int velx,vely,x,y;
	int[] color;
	String name;
	boolean hold,up,right;
	
	public Particle(String nombregrupo, int posx, int posy, int[] colorgrupo) {
		Random r = new Random();
		up = r.nextBoolean();
		right = r.nextBoolean();
		velx = (int)(Math.random()*5 + 1);
		vely = (int)(Math.random()*5 + 1);
		x = posx;
		y = posy;
		color = colorgrupo;
		hold = false;
		name = nombregrupo;
		
	}
	
	public void drawParticle(PApplet app) {
		if(x<25) {right=true;}
		if(x>675) {right=false;}
		if(y<25) {up=false;}
		if(y>675) {up=true;}
		if(hold==false) {
		if(right==true) {x+=velx;}
		if(right==false) {x-=velx;}
		if(up==true) {y-=vely;}
		if(up==false) {y+=vely;}}
		app.fill(color[0],color[1],color[2]);
		app.ellipse(x, y, 50, 50);
		if(hold==true) {
			app.text(name, x, y+35);
		}
	}
	
	public void setHold(boolean hold) {
		this.hold = hold;
	}
	
	public int getX() {
		return x;
	}
	
	public int getY() {
		return y;
	}
}
