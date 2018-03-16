import java.awt.Color;
import java.awt.Graphics;
import javax.swing.JPanel;
import javax.swing.JLabel;
import java.awt.BorderLayout;
import java.awt.event.MouseEvent;
import java.awt.event.MouseAdapter;
import java.util.ArrayList;

public class CanvasPanel extends JPanel
{
    private LinkedList<Shape> myShapes; //dynamic stack of shapes
    private LinkedList<Shape> clearedShapes; //dynamic stack of cleared shapes from undo
    
    //current Shape variables
    private int currentShapeType; //0 for line, 1 for rect, 2 for oval
    private Shape currentShapeObject; //stores the current shape object
    private Color currentShapeColor; //current shape color
    private boolean currentShapeFilled; //determine whether shape is filled or not
    
    JLabel statusLabel; //status label for mouse coordinates
    
    /**
     * This constructor initializes the dynamic stack for myShapes and clearedShapes.
     * It sets the current shape variables to default values.
     * It initalizes statusLabel from JLabel passed in.
     * Sets up the panel and adds event handling for mouse events.
     */
    public CanvasPanel(JLabel statusLabel){
        
        myShapes = new LinkedList<Shape>(); //initialize myShapes dynamic stack
        clearedShapes = new LinkedList<Shape>(); //initialize clearedShapes dynamic stack
        
        //Initialize current Shape variables
        currentShapeType=1;
        currentShapeObject=null;
        currentShapeColor=Color.BLACK;
        currentShapeFilled=false;
        
        this.statusLabel = statusLabel; //Initialize statusLabel
        
        setLayout(new BorderLayout()); //sets layout to border layout; default is flow layout
        setBackground( Color.WHITE ); //sets background color of panel to white
        add( statusLabel, BorderLayout.SOUTH );  //adds a statuslabel to the south border
        
        // event handling for mouse and mouse motion events
        MouseHandler handler = new MouseHandler();                                    
        addMouseListener( handler );
        addMouseMotionListener( handler ); 
    }
    
    //PaintComponent method() to call the draw() method for shapes
    public void paintComponent( Graphics g )
    {
        super.paintComponent( g );
        
        // draw the shapes
        ArrayList<Shape> shapeArray=myShapes.getArray();
        for ( int counter=shapeArray.size()-1; counter>=0; counter-- )
           shapeArray.get(counter).draw(g);
        
        //draws the current Shape Object if it is not null
        if (currentShapeObject!=null)
            currentShapeObject.draw(g);
    }
    
    
    
    //MUTATORS  for currentShapeType, currentShapeColor and currentShapeFilled

    //Sets the currentShapeType variable to type 
    //(0 for Line, 1 for Rect, 2 for Oval, 3 for ... and so on according to shape array)
    public void setCurrentShapeType(int type)
    {
        currentShapeType=type;
    }
    

    //method to set the CurrentShapeColor variable to the color that is passed in from the JColorChooser
    public void setCurrentShapeColor(Color color)
    {
        currentShapeColor=color;
    }

    //method to set booled 'filled' variable to true if shape should be filled with color, or false if it shouldn't.
    public void setCurrentShapeFilled(boolean filled)
    {
        currentShapeFilled=filled;
    }
    
   
    //method to clear the last shape drawn, it calls the repatin() method to redraw the panel without that shape  
    public void clearLastShape()
    {
        if (! myShapes.isEmpty())
        {
            clearedShapes.addFront(myShapes.removeFront());
            repaint();
        }
    }
    

    //method to repaint() the last shape that was cleared from the canvas. upon clicking it redraws the panel
    public void redoLastShape()
    {
        if (! clearedShapes.isEmpty())
        {
            myShapes.addFront(clearedShapes.removeFront());
            repaint();
        }
    }
    

    //method to clear the entire canvas. It empties the clearedShapes array so you cannot do "redo" after clearing it
    public void clearDrawing()
    {
        myShapes.makeEmpty();
        clearedShapes.makeEmpty();
        repaint();
    }
    

    //method to handle all of the mouse events
    private class MouseHandler extends MouseAdapter 
    {

    	//When mouse is pressed down, draw the shape based on the variables passed in (shapeType, color, and filled)
    	//x1, y1, x2, y2 are the coordinates for the drawn shape and are set to the same position
        public void mousePressed( MouseEvent event )
        {
            switch (currentShapeType) //0 for line, 1 for rect, 2 for oval
            {
                case 0:
                    currentShapeObject= new Line( event.getX(), event.getY(), 
                                                   event.getX(), event.getY(), currentShapeColor);
                    break;
                case 1:
                    currentShapeObject= new Rectangle( event.getX(), event.getY(), 
                                                        event.getX(), event.getY(), currentShapeColor, currentShapeFilled);
                    break;
                case 2:
                    currentShapeObject= new Ellipse( event.getX(), event.getY(), 
                                                   event.getX(), event.getY(), currentShapeColor, currentShapeFilled);
                    break;
                /*case 3:
                    currentShapeObject= new Text();
                    break; */
                case 4:
                    currentShapeObject= new Circle( event.getX(), event.getY(), 
                                                   event.getX(), event.getY(), currentShapeColor, currentShapeFilled);
                    break;
                case 5:
                    currentShapeObject= new Square( event.getX(), event.getY(), 
                                                   event.getX(), event.getY(), currentShapeColor, currentShapeFilled);
                    break;
               
                    
                case 6:
                    currentShapeObject= new Triangle( event.getX(), event.getY(), 
                                                   event.getX(), event.getY(), currentShapeColor, currentShapeFilled);
                    break;
                    
                    
            }// end switch case
        } // end method mousePressed
        

        /*
         Method to handle events that should happen when the mouse is released
        
        
             Upon releasing the mouse, we set the current Shape's x2 and y2 coordinates to that specific mouse position
             then we add to the front of the currentShapeObject stack and set currentShapeObject back to null (which clears the shape  object that was drawn)
             then is clears ALL of the shape objects in 'clearedShapes" because we don't want to be able to do "redo" after drawing it. 
             Lastly, it calls repaint() to draw the panel with the new shape  
        */
        
        public void mouseReleased( MouseEvent event )
        {
            //sets currentShapeObject x2 & Y2
            currentShapeObject.setX2(event.getX());
            currentShapeObject.setY2(event.getY());
            
            myShapes.addFront(currentShapeObject); //addFront currentShapeObject onto myShapes
            
            currentShapeObject=null; //sets currentShapeObject to null
            clearedShapes.makeEmpty(); //clears clearedShapes
            repaint();
            
        } // end method mouseReleased
        
        /**
         * This method gets the mouse pos when it is moving and sets it to statusLabel.
         */
        public void mouseMoved( MouseEvent event )
        {
            statusLabel.setText(String.format("Mouse Coordinates X: %d Y: %d",event.getX(),event.getY()));
        } // end method mouseMoved
        
        /**
         * This method gets the mouse position when it is dragging and sets x2 & y2 of current shape to the mouse pos
         * It also gets the mouse position when it is dragging and sets it to statusLabel
         * Then it calls repaint() to redraw the panel
         */
        public void mouseDragged( MouseEvent event )
        {
            //sets currentShapeObject x2 & Y2
            currentShapeObject.setX2(event.getX());
            currentShapeObject.setY2(event.getY());
            
            //sets statusLabel to current mouse position
            statusLabel.setText(String.format("Mouse Coordinates X: %d Y: %d",event.getX(),event.getY()));
            
            repaint();
            
        } // end method mouseDragged
        
    }// end MouseHandler
    
} // end class DrawPanel