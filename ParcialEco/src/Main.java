

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;

import com.google.gson.Gson;

import processing.core.PApplet;

public class Main extends PApplet{
	
	public static void main(String[] args) {
		PApplet.main("Main");
		
	}
	
	private Socket socket; 
	private BufferedReader reader;
	private int x,y,r,g,b;
	
	@Override
	public void settings() {
		size(700,700);
		 
		 
	}
	
	ArrayList<Grupos>lista = new ArrayList();
	private ArrayList<Particle> particulas = new ArrayList();
	Particle prueba;
	
	
	@Override
	public void setup() {
		int[] colorprueba = new int[] {0,0,0};
		
		serveriniciar();
		prueba = new Particle("prueba",350,350,colorprueba);
		x=350;
		y=350;
		textAlign(CENTER,CENTER);
		ellipseMode(CENTER);
	}
	
	@Override
	public void draw() {
	background(255);
	
	if(lista.size()>0) {
	
			//dibujar lista
			 for(int i = 0; i < particulas.size();i++) {
	    		 particulas.get(i).drawParticle(this);
	    	 }
			
		
			
		
		
		//verificar mouse
		
		for(int j=0; j < particulas.size();j++) {
			if (mouseX>particulas.get(j).getX()-25 
			&&mouseX<particulas.get(j).getX()+25
			&& mouseY>particulas.get(j).getY()-25
			&&mouseY<particulas.get(j).getY()+25) {
				particulas.get(j).setHold(true);}
			else {particulas.get(j).setHold(false);}
			
		}
	}
	  
     
     

	
    
	}

	
		
		
		

	public void serveriniciar() {
		new Thread(
				()->{
					try {
						ServerSocket server = new ServerSocket(5000);
						System.out.println("Esperando...");
						socket = server.accept();
						System.out.println("conectado");
						InputStream is = socket.getInputStream();
						InputStreamReader isr = new InputStreamReader(is);
						reader = new BufferedReader(isr);
						
						while(true) {
							String line = reader.readLine();
							System.out.println(line);
							if(line.equals("Borrar")) {
								particulas.clear();
								lista.clear();
								
							}else {
								Gson gson = new Gson();
								Grupos grupo = gson.fromJson(line, Grupos.class);
								lista.add(grupo);
								  for(int i = 0; i < grupo.getCantidad();i++) {
							        	Particle newP = new Particle(grupo.getNombre(), grupo.getX(), grupo.getY(), grupo.getColor());
							        	particulas.add(newP);
							        }
							
							}
							for(int i=0;i< lista.size();i++) {
								System.out.println(lista.get(i).getNombre());
								for(int j=0; j < particulas.size();j++) {
									System.out.println(particulas.get(j).getX());
								}
							}
							
						}
						
					} catch (IOException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
				}).start();
	}

 }

