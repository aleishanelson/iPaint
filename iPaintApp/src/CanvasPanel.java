import java.awt.Color;
import java.awt.Graphics;
import javax.swing.JPanel;
import javax.swing.Timer;
import javax.swing.JLabel;
import java.awt.BorderLayout;
import java.awt.event.MouseEvent;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.util.ArrayList;

public class CanvasPanel extends JPanel
{
    private LinkedList<Shape> myShapes; //dynamic stack of shapes
    private LinkedList<Shape> clearedShapes; //dynamic stack of cleared shapes from undo
    private LinkedList<Shape> animatedShapes;  //dynamic stack of animated shapes
    
    //current Shape variables
    private int currentShapeType; //0 for line, 1 for rect, 2 for oval
    private Shape currentShapeObject; //stores the current shape object
    private Color currentShapeColor; //current shape color
    private boolean currentShapeFilled; //determine whether shape is filled or not
    private boolean currentShapeAnimated; //determine whether shape is animated or not
    public boolean stop = false;
    private int fontSize = 10;
    
    JLabel statusLabel; //status label for mouse coordinates
    
 
    //CONSTRUCTOR to initialize the stack for shapes and clearedShapes
    public CanvasPanel(JLabel statusLabel){
        
        myShapes = new LinkedList<Shape>(); //initialize myShapes dynamic stack
        clearedShapes = new LinkedList<Shape>(); //initialize clearedShapes dynamic stack
        animatedShapes = new LinkedList<Shape>(); //initialize animatedShapes dynamic stack
        
        //Initialize current Shape variables
        currentShapeType=1;
        currentShapeObject=null;
        currentShapeColor=Color.BLACK;
        currentShapeFilled=false;
        currentShapeAnimated = false;
        
        this.statusLabel = statusLabel; //Initialize statusLabel
        
        setLayout(null); //sets layout to border layout; default is flow layout
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
    
    
    
    public void animateShapes() {
		ArrayList<Shape> shapeArray = animatedShapes.getArray();
		for(int counter = shapeArray.size() - 1; counter >= 0; counter--) {
			Thread shapeThread = new Thread(new Animation());
			Shape currentShape = shapeArray.get(counter);
			int originalX1 = shapeArray.get(counter).getX1();
			int originalX2 = shapeArray.get(counter).getX2();
			//int animateX1 = originalX1;
			//int animatex2 = originalX2;
			Timer time = new Timer(5, (ActionListener) new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					animate(originalX1, originalX2, currentShape);
				}
			});
			time.start();
			shapeThread.run();
		}
    }  //end of animateShape()
    
    
    public void animate(int originalX1, int originalX2, Shape shape) {
		int X1 = shape.getX1();
		int X2 = shape.getX2();
		int vel = 2;
		if(!stop) {
			if(X1 < originalX1 || X2 > 1350) {
				shape.reverse = !shape.reverse;
				//vel = -vel;
			}
			if(shape.reverse) {
				X1 = X1 - vel;
    			X2 = X2 - vel;
			}
			else {
				X1 = X1 + vel;
				X2 = X2 + vel;
			}
			shape.setX1(X1);
			shape.setX2(X2);
			repaint();
		}
		else {
			shape.setX1(originalX1);
			shape.setX2(originalX2);
			repaint();
		}
		
    } //end of animate()
    

    
    
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
    
    public void setFontSize(int size) {
		fontSize = size;
    }

    //method to set booled 'filled' variable to true if shape should be filled with color, or false if it shouldn't.
    public void setCurrentShapeFilled(boolean filled)
    {
        currentShapeFilled=filled;
    }
    
    public void setCurrentShapeAnimate(boolean animated)
    {
    		currentShapeAnimated = animated;
    }
    
   
    //method to clear the last shape drawn, it calls the repatin() method to redraw the panel without that shape  
    public void clearLastShape()
    {
        if (! myShapes.isEmpty())
        {
        	if(myShapes.getFront().getData() instanceof TextBox) {
        			System.out.println(((TextBox)myShapes.getFront().getData()).getTextBox());
        			ApplicationFrame.canvas.remove(((TextBox)myShapes.getFront().getData()).getTextBox());
        	}
        		ApplicationFrame.canvas.revalidate();
            clearedShapes.addFront(myShapes.removeFront());
            System.out.println(myShapes.getArray());
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
                case 3:
                    currentShapeObject = new TextBox(event.getX(), event.getY(),
                										event.getX(), event.getY(), currentShapeColor, fontSize);
                		
                    break; 
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
            
	if(currentShapeObject != null && currentShapeType == 3) {
        		ApplicationFrame.canvas.add(((TextBox)currentShapeObject).getTextBox());
        		
        	}
            myShapes.addFront(currentShapeObject); //addFront currentShapeObject onto myShapes
            System.out.println(myShapes.getArray());
            
            if(currentShapeAnimated) {
            		animatedShapes.addFront(currentShapeObject);
            }
            
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