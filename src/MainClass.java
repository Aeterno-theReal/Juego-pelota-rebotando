
//Código sacado de https://stackoverflow.com/questions/29449436/updating-a-java-canvas-that-is-a-component-of-a-jframe

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Event;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.RenderingHints;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.util.ArrayList;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.Timer;
import javax.swing.WindowConstants;

public class MainClass 
{
	public static JFrame frame;
	public static MyPanel panel;
	public static final int width=600;
	public static final int height=400;

	
	
	public static void CreateFrame() 
	{
        frame = new JFrame("Pelotas");
        panel = new MyPanel();

        frame.add(panel);
        frame.setResizable(false);

        frame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        frame.pack();
        frame.setLocationByPlatform(true);
        frame.setVisible(true);

        frame.addKeyListener(panel);
        createRepaintTimer(frame);
    }

    // Just makes calls for the frame to paint every 15 milliseconds
    private static void createRepaintTimer(final JFrame tframe) {
        final Timer timer = new Timer(1000/60, null);

        timer.addActionListener(new ActionListener() {			
			@Override
			public void actionPerformed(ActionEvent e) {
				if (!tframe.isVisible()) 	timer.stop();
	            else 						/*tframe.repaint();	*/panel.repaint();			
			}
		});        		

        timer.start();
    }	
	
	public static void main(String[] args)
	{
		CreateFrame();
	}
	
	
	private static class MyPanel extends JPanel implements KeyListener
	{
	    private ArrayList<Color> color;
	    private ArrayList<Pelota> bola;
	    private boolean borrar=true;

	    public MyPanel() {
	    	
	    	color=new ArrayList<Color>();
	    	bola=new ArrayList<Pelota>();
	    	
	    	color.add(Color.BLUE); color.add(Color.RED); color.add(Color.YELLOW); color.add(Color.GRAY); color.add(Color.BLACK); color.add(Color.GREEN); color.add(Color.PINK);
	    	
	    	for(int i=0; i<color.size(); i++)
	    	{
	    		bola.add(new Pelota(width/2, height/2, (i+3), color.get(i)));
	    	}
	        System.currentTimeMillis();
	    }

	    
	    @Override
	    protected void paintComponent(Graphics g) 
	    {
	    	if(borrar)
	    		super.paintComponent(g);
	        dibujarTexto(g);
	        
	        for(int i=0; i<bola.size(); i++)
	        {

	        	for(int j=0; j<bola.size(); j++)
	        		if(i!=j)
	        			bola.get(i).chocar(bola.get(j));
	        	
	        	bola.get(i).actualizar();	        
	        	bola.get(i).dibujar(g, color.get(i));
	        }
	    }
	    
	    private void dibujarTexto(Graphics g)
	    {
	    	if(g instanceof Graphics2D)
	    	{
		    	Graphics2D g2 = (Graphics2D)g;
		        g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING,
		        RenderingHints.VALUE_ANTIALIAS_ON);
		        
		        g2.setColor(new Color(0, 102, 153));

	        	g2.drawString("Velocidad X: "+bola.get(1).getVelX(),10,20);
	        	g2.drawString("Velocidad Y: "+bola.get(1).getVelY(),10,35);
	        
		        g2.drawString("Borrar: "+borrar,10,50);
	    	}
	    }

	    // Just setting the default size of the panel
	    @Override
	    public Dimension getPreferredSize() {
	        return new Dimension(MainClass.width, MainClass.height);
	    }

		@Override
		public void keyPressed(KeyEvent e) {
			switch (e.getKeyCode()) 
	    	{
			case KeyEvent.VK_PLUS:
			case KeyEvent.VK_ADD:
				for(int i=0; i<bola.size(); i++)
					bola.get(i).aumentarVelocidad();				
				break;
			case KeyEvent.VK_MINUS:
			case KeyEvent.VK_SUBTRACT:
				for(int i=0; i<bola.size(); i++)
					bola.get(i).decrementarVelocidad();
				break;

			case KeyEvent.VK_RIGHT:
				for(int i=0; i<bola.size(); i++)
					bola.get(i).aumentarVelocidad(true);				
				break;
			case KeyEvent.VK_LEFT:
				for(int i=0; i<bola.size(); i++)
					bola.get(i).decrementarVelocidad(true);
				break;
			case KeyEvent.VK_UP:
				for(int i=0; i<bola.size(); i++)
					bola.get(i).aumentarVelocidad(false);				
				break;
			case KeyEvent.VK_DOWN:
				for(int i=0; i<bola.size(); i++)
					bola.get(i).decrementarVelocidad(false);
				break;
				
			case KeyEvent.VK_SPACE:
				borrar=!borrar;
			default:
				break;
			}			
		}

		@Override
		public void keyReleased(KeyEvent e) {
			// TODO Auto-generated method stub
			
		}

		@Override
		public void keyTyped(KeyEvent e) {
			// TODO Auto-generated method stub
			
		}

	}

}
