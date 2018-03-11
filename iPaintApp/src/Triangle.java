import java.awt.Color;
import java.awt.Graphics;

/**
 * This class inherits from MyBoundedShape and is responsible for drawing a rectangle
 */
public class Triangle extends ShapeBounds
{ 
	
	private int triangleWidth;
	private int triangleHeight;
    
	//Constructor
    public Triangle()
    {
        super();
    }
    
    //overloaded constructor with parameters
    public Triangle( int x1, int y1, int x2, int y2, Color color, boolean fill )
    {
        super(x1, y1, x2, y2, color,fill);
 
    } 
    
    //overridden draw() method in order to define how to draw a square
    @Override
    public void draw( Graphics g )
    {
    	/*
    		triangleWidth = getWidth();
		triangleHeight = getHeight();
		triangleWidth = triangleHeight;   //set height and width equal to eachother to create a symmetric rectangle - aka a square
    		*/
    	
        g.setColor( getColor() ); //sets the color
        if (getFill()) { //determines whether fill is true or false
        		//g.fillPolygon(getUpperLeftX(), getUpperLeftY(), 3);
           // g.fillRect( getUpperLeftX(), getUpperLeftY(), getWidth(), getHeight() ); //draws a filled rectangle
    			}
    		else {
        		//g.drawPolyline(xPoints, yPoints, 3);  		
		} 
    
    }
}
