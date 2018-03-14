import java.awt.Color;
import java.awt.Graphics;
import java.awt.Polygon;

//Triangle class inherits methods from ShapeBounds in order to draw a traingle with the correct bounds 
public class Triangle extends ShapeBounds
{   
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
    		int x[] = {getX1(),  getX2(), getX2()};
    		int y[] = { getY1(), getY1(), getY2()};
    		int n = 3;
    		Polygon p = new Polygon(x, y, n);
    	
        g.setColor( getColor() ); //sets the color
        if (getFill()) { //determines whether fill is true or false
        		g.fillPolygon(p);
	}
    		else {
        		g.drawPolygon(p);  		
		} 
    
    }
}