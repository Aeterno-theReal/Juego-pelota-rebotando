import java.awt.Color;
import java.awt.Graphics;

public class Pelota 
{
	  private int x, y, velx, vely, tam=50, tam2=tam/2;
	  private Color color;
	  
	  public Pelota(int _x, int _y, int _vel, Color color){x=_x; y=_y; velx=vely=_vel; this.color=color; }   
	  
	  public void dibujar(Graphics g, Color color)
	  {
		  g.setColor(this.color);
          g.fillOval(x-tam2, y-tam2, tam, tam);
      }
	  
	  public int getVelX() {return velx;}
	  public int getVelY() {return vely;}

	  
	  
	  public void actualizar()
	  {
	    x+=velx;
	    y+=vely;
	    
	    if(y<tam2)
	    {
	      y-=vely;
	      vely*=-1;
	    }else if(y>=(MainClass.height-tam2))
	      {
	        y-=vely;
	        vely*=-1;
	      }    
	    
	    if(x<tam2)
	    {
	      x-=velx;
	      velx*=-1;
	    }else if(x>=(MainClass.width-tam2))
	      {
	        x-=velx;
	        velx*=-1;
	      }
	  }
	  
	   
	  public void aumentarVelocidad(boolean isX)
	  {
	    if(isX) velx++;
	    else    vely++;
	  }
	  
	  public void decrementarVelocidad(boolean isX)
	  {
	    if(isX)
	    {
	      velx--;
	    }else vely--;
	  }
	  
	  
	  public void aumentarVelocidad()
	  {
	    velx++; vely++;
	  }
	  
	  public void decrementarVelocidad()
	  {
	      velx--;
	      vely--;
	  }
	  
	  public void chocar(Pelota otra)
	  {
		  if(Math.sqrt(Math.pow(x-otra.x, 2) + Math.pow(y-otra.y, 2)) < tam)
		  {
			  velx*=-1;
			  vely*=-1;
		  }
	  }
}
