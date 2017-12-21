// This file contains: a Constructor, Inheritance
import java.awt.*;
import javax.swing.*;

public class Quadrant1 extends DisplayCircle{
	public Quadrant1(){
		super();
		unitCircle = Toolkit.getDefaultToolkit().getImage("unit1.gif");
	}

	public void showImage(){
		JFrame frame = new JFrame("Quadrant1");
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
   		frame.setSize(900,750);
		Quadrant1 panel = new Quadrant1();
		frame.setContentPane(panel);
		frame.setVisible(true);
	}
}