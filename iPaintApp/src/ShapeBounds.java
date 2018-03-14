import java.awt.Color;
import java.awt.Graphics;

// This class contains methods for drawing all shapes and filling them with color. 
 
abstract class ShapeBounds extends Shape
{
    private boolean fill; //boolean variable that determines whether the shape is filled or not
    

    public ShapeBounds()
    {
        super();
        fill=false;
    }
    
    //overloaded constructor that takes in params
    public ShapeBounds(int x1, int y1, int x2, int y2, Color color, boolean fill)
    {
        super(x1, y1, x2, y2, color);
        this.fill=fill;
        
    }
    
    //MUTATORS
    
    //set fill color for shape
    public void setFill(boolean fill)
    {
        this.fill=fill;
    }
    
    //gets the upper left x coordinate
    public int getUpperLeftX()
    {
        return Math.min(getX1(),getX2());
    }
    
    //gets upper left y coordinate
    public int getUpperLeftY()
    {
        return Math.min(getY1(),getY2());
    }
    
    //get width of shape
    public int getWidth()
    {
        return Math.abs(getX1()-getX2());
    }
    
    //ACCESSORS
    
    //get height of shape
    public int getHeight()
    {
        return Math.abs(getY1()-getY2());
    }
    
    //return fill boolean variable
    public boolean getFill()
    {
        return fill;
    }
    
    //abstract method for draw() -- this method is overridden in each separate shape class
    abstract public void draw( Graphics g );
} // end class MyBoundedShape