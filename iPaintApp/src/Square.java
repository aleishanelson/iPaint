import java.awt.Color;
import java.awt.Graphics;

/**
 * This class inherits from MyBoundedShape and is responsible for drawing a rectangle
 */
public class Square extends ShapeBounds
{ 
	
	private int squareWidth;
	private int squareHeight;
    
	//Constructor
    public Square()
    {
        super();
    }
    
    //overloaded constructor with parameters
    public Square( int x1, int y1, int x2, int y2, Color color, boolean fill )
    {
        super(x1, y1, x2, y2, color,fill);
 
    } 
    
    //overridden draw() method in order to define how to draw a square
    @Override
    public void draw( Graphics g )
    {
    		squareWidth = getWidth();
		squareHeight = getHeight();
		squareWidth = squareHeight;   //set height and width equal to eachother to create a symmetric rectangle - aka a square
    		
        g.setColor( getColor() ); //sets the color
        if (getFill()) //determines whether fill is true or false
        		g.fillRect( getUpperLeftX(), getUpperLeftY(), squareWidth, squareHeight );
           // g.fillRect( getUpperLeftX(), getUpperLeftY(), getWidth(), getHeight() ); //draws a filled rectangle
        else
        		
        		   
        			g.drawRect( getUpperLeftX(), getUpperLeftY(), squareWidth, squareHeight ); //draws a regular rectangle
        	
        			//g.drawRect( getUpperLeftX(), getUpperLeftY(), getWidth(), getHeight()); //draws a regular rectangle
        		
    } 
    
}
