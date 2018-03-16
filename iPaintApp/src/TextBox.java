import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Point;

import javax.swing.JTextField;

public class TextBox extends Shape{
	JTextField textbox;
	int fontSize;
	
	public TextBox() {
		super();
	}
	
	public TextBox(int x1, int y1, int x2, int y2, Color color, int fontSize) {
		super(x1, y1, x2, y2, color);
		this.fontSize = fontSize;
	}

	@Override
	public void draw(Graphics g) {
		// TODO Auto-generated method stub
		Graphics2D g2 = (Graphics2D)g;
		this.textbox = new JTextField("");
		//this.textbox.setLocation(new Point(getX1(), getX2()));
		this.textbox.setBounds(getX1(), getY1(), Math.abs(getX2() - getX1()), Math.abs(getY2() - getY1()));
		//this.textbox.setSize(new Dimension(Math.abs(getX2() - getX1()), Math.abs(getY2() - getY1())));
		this.textbox.setFont(new Font("SansSerif", Font.PLAIN, fontSize));
		
	}
	public JTextField getTextBox() {
		return this.textbox;
	}
	
	public void setFontSize(int fontSize) {
		this.textbox.setFont(new Font("SansSerif", Font.PLAIN, fontSize));
	}

}
