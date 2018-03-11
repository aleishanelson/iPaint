import java.awt.Color;
import java.awt.Graphics;

// this class defines how to draw a circle 
public class Circle extends ShapeBounds
{ 
	
	private int circleWidth, circleHeight;

	//Constructor
    public Circle()
    {
        super();
    }
    
    //Overloaded Constructor with parameters
    public Circle( int x1, int y1, int x2, int y2, Color color, boolean fill )
    {
        super(x1, y1, x2, y2, color,fill);
    }
     
   //Overridden Draw() function in order to defin how to draw a circle. 
    @Override
    public void draw( Graphics g )
    {
    		circleWidth = getWidth();
    		circleHeight = getHeight();
    		circleWidth = circleHeight;
    	
        g.setColor( getColor() ); //sets the color of the shape
        if (getFill()) //determines whether fill is true or false
        		g.fillOval( getUpperLeftX(), getUpperLeftY(), circleWidth, circleHeight ); //draws a filled circle
           // g.fillOval( getUpperLeftX(), getUpperLeftY(), getWidth(), getHeight() ); //draws a filled circle
        else
            g.drawOval( getUpperLeftX(), getUpperLeftY(), circleWidth, circleHeight); //draws a regular circle
        
    }
    
} // end class Circle