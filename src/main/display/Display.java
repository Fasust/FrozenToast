package main.display;
import java.awt.Canvas;
import java.awt.Dimension;
import javax.swing.JFrame;

public class Display {
	
	private JFrame frame;
	private Canvas canvas;
	private String title;
	private int hoehe,breite;
	
	public Display(String title,int breite,int hoehe){
		this.title = title;
		this.hoehe = hoehe;
		this.breite = breite;
		
		creatDisplay();
	}
	private void creatDisplay(){
		frame = new JFrame(title);
		frame.setSize(breite, hoehe);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setResizable(false);
		frame.setLocationRelativeTo(null);
		frame.setVisible(true);
		
		canvas = new Canvas();
		canvas.setPreferredSize(new Dimension(breite,hoehe));
		canvas.setMaximumSize(new Dimension(breite,hoehe));
		canvas.setMinimumSize(new Dimension(breite,hoehe));
		canvas.setFocusable(false);
		
		frame.add(canvas);
		frame.pack();
	}
	
	//getters and setters below
	public Canvas getCanvas(){
		return canvas;
	}
	public JFrame getFrame(){
		return frame;
	}
}
