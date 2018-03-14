package iPaintApp.src;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JLabel;
import javax.imageio.ImageIO;
import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JCheckBox;
import java.awt.event.ItemListener;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;
import java.awt.event.ItemEvent;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.BorderLayout;
import java.awt.GridLayout;
import java.awt.Image;
import java.awt.FlowLayout;
import java.awt.Graphics2D;
import java.awt.Color;
import java.awt.Dimension;

public class ApplicationFrame extends JFrame
{
	private JLabel stausLabel; //label display mouse coordinates
    private JButton undo, redo, clear, selectShape; // buttons to undo, redo last shape drawn and to clear the canvas
    private JButton jbRect, jbEllipse, jbLine, jbText, jbSquare, jbCircle, jbTriangle, jbSave;  //buttons for selecting which shape the user wants to draw
    private JComboBox colors; //combobox with color options 
    private JCheckBox fillCheckBox; //checkbox to select whether or not to fill a shape with color
    private JLabel colorLabel;
        
    private JPanel toolboxPanel; //panel to hold buttons for drawing shapes and selecting colors
    private JPanel toolboxPadding; //adds padding around the toolbox panel
    private CanvasPanel canvas; //canvas panel for drawing shapes
    
    private Icons JBicons;
    
  //array holding the different color options for filling a shape
    private Color colorsArray[]= {Color.BLACK , Color.WHITE , Color.MAGENTA ,
    		Color.BLUE , Color.CYAN , Color.GREEN, Color.YELLOW, 
    		Color.ORANGE , Color.RED , Color.PINK , Color.lightGray, Color.darkGray , Color.GRAY  
          };
    
    //array of strings containing color options for JComboBox colors
    private String colorMenu[]=
    {"Black", "White", "Magenta", "Blue","Cyan", "Green", "Yellow", 
    		 "Orange", "Red", "Pink", "Light Gray","Dark Gray","Gray"};

    //array of strings containing shape options for JComboBox shapes
    private String shapeOptions[]={"Line","Rectangle","Ellipse", "Text", "Circle", "Square", "Triangle"};
    
    /**
     * CONSTRUCTOR for JFrame/Application
     * Creates the canvas JPanel object for drawing shapes/textfields, adds event handlers, and initializes variables
     */
    
    public ApplicationFrame()
    {
    		
        super("iPaint Application"); 
            
        JLabel statusLabel = new JLabel( "" ); //create an empty JLabel to pass in to the canvas Panel
        
        canvas = new CanvasPanel(statusLabel); //creates the canvas panel and passes in the statusLabel param
        canvas.setBorder(BorderFactory.createLineBorder(Color.GRAY , 15)); 
       
        Icons JBicons = new Icons();   //instantiates Icons object which defines images to use for icons
          
        //CREATE BUTTONS AND ADD ICONS    
        undo = new JButton( "Undo" );
        undo.setIcon(JBicons.undoII);
        redo = new JButton( "Redo" );
        redo.setIcon(JBicons.redoII);
        clear = new JButton("Clear");
        clear.setIcon(JBicons.clearII);
        jbRect = new JButton("Rectangle");
        jbRect.setIcon(JBicons.squareII);
        jbEllipse = new JButton("Ellipse");
        jbEllipse.setIcon(JBicons.circleII);
        jbLine = new JButton("Line");
        jbLine.setIcon(JBicons.lineII);
        jbText = new JButton("Text");
        jbText.setIcon(JBicons.textII);
        selectShape = new JButton("Recolor Shape");
        jbCircle = new JButton("Circle");
        jbSquare = new JButton("Square");
        	jbTriangle = new JButton("Triangle");
        	jbSave = new JButton("Save");
        
        //create color combobox and label for it
        colorLabel = new JLabel("<html>Select Shape<br>Fill Color:</html>");
        colors = new JComboBox( colorMenu );      
        
        //create checkbox
        fillCheckBox = new JCheckBox( "Filled" );
        
        //creates toolboxJPanel with a grid layout for canvas buttons
        toolboxPanel = new JPanel();
        toolboxPanel.setLayout( new GridLayout( 2, 3, 5, 5 ) ); //sets padding between widgets in gridlayout
        
        //JPanel object, widgetPadder, with flowlayout to encapsulate and pad the widgetJPanel
        toolboxPadding = new JPanel();
        toolboxPadding.setLayout(new FlowLayout(FlowLayout.LEADING, 20, 5)); //sets padding around the edges
            
        // add widgets to widgetJPanel
        toolboxPanel.add( undo );
        toolboxPanel.add( redo );  
        toolboxPanel.add( jbRect );
        toolboxPanel.add(jbEllipse);
        toolboxPanel.add(jbLine);
        toolboxPanel.add(jbText);
        toolboxPanel.add(jbCircle);
        toolboxPanel.add( clear );
        toolboxPanel.add(colorLabel);
        toolboxPanel.add( colors );
        toolboxPanel.add( fillCheckBox );
        toolboxPanel.add(selectShape);
        toolboxPanel.add(jbSquare);
        toolboxPanel.add(jbTriangle);
        toolboxPanel.add(jbSave);
        
        // add toolbox to its padding panel
        toolboxPadding.add( toolboxPanel );
        
        //add toolbox padding panel and canvas to the main JFrame
        add( toolboxPadding, BorderLayout.NORTH);
        add( canvas, BorderLayout.CENTER);
        
        // create new ButtonHandler for handing events when buttons are clicked
        ButtonHandler buttonHandler = new ButtonHandler();
        undo.addActionListener( buttonHandler );
        redo.addActionListener( buttonHandler );
        clear.addActionListener( buttonHandler );
        jbRect.addActionListener( buttonHandler );
        jbEllipse.addActionListener( buttonHandler );
        jbLine.addActionListener( buttonHandler );
        jbText.addActionListener( buttonHandler );
        jbSquare.addActionListener(buttonHandler);
        jbCircle.addActionListener(buttonHandler);
        jbTriangle.addActionListener(buttonHandler);
        
        jbSave.addActionListener(buttonHandler);
        //create handlers for color combobox and filled checkbox
        ItemListenerHandler handler = new ItemListenerHandler();
        colors.addItemListener( handler );
        fillCheckBox.addItemListener( handler );
        
        setDefaultCloseOperation( JFrame.EXIT_ON_CLOSE );
        setSize( 500, 500 );
        setVisible( true );
        
    } // end DrawFrame constructor
    
    /**
     * private inner class for button event handling
     */
    private class ButtonHandler implements ActionListener
    {
        // handles button events
        public void actionPerformed( ActionEvent event )
        {
            if (event.getActionCommand().equals("Undo")){
                canvas.clearLastShape();
            }
            else if (event.getActionCommand().equals("Redo")){
                canvas.redoLastShape();
            }
            else if (event.getActionCommand().equals("Clear")){
                canvas.clearDrawing();
            }
            else if (event.getActionCommand().equals("Line")){
                canvas.setCurrentShapeType(0);
            }
            else if (event.getActionCommand().equals("Rectangle")){
            		canvas.setCurrentShapeType(1);
            }
            else if (event.getActionCommand().equals("Ellipse")){
                canvas.setCurrentShapeType(2);
            }
            else if (event.getActionCommand().equals("Text")){
                canvas.setCurrentShapeType(3);
            }  
            else if (event.getActionCommand().equals("Circle")){
                canvas.setCurrentShapeType(4);
            }  
            else if (event.getActionCommand().equals("Square")){
                canvas.setCurrentShapeType(5);
            }  
            else if (event.getActionCommand().equals("Triangle")){
                canvas.setCurrentShapeType(6);
            }  
            else if (event.getActionCommand().equals("Save")){
                savePaint();
            } 
             
        } // end method actionPerformed
    } // end private inner class ButtonHandler
    
    /**
     * private inner class for checkbox and combobox event handling
     */
    private class ItemListenerHandler implements ItemListener
    {
        public void itemStateChanged( ItemEvent event )
        {
            // process filled checkbox events
            if ( event.getSource() == fillCheckBox )
            {
                boolean checkFill=fillCheckBox.isSelected() ? true : false; //
                canvas.setCurrentShapeFilled(checkFill);
            }
            
            // determine whether combo box selected
            if ( event.getStateChange() == ItemEvent.SELECTED )
            {
                //if event source is combo box colors pass in colorArray at index selected.
                if ( event.getSource() == colors)
                {
                    canvas.setCurrentShapeColor
                        (colorsArray[colors.getSelectedIndex()]);
                }
     
            }
            
        } // end method itemStateChanged
    }

	public void savePaint() {
		// TODO Auto-generated method stub
		try
        {
            BufferedImage image = new BufferedImage(getWidth(), getHeight(), BufferedImage.TYPE_INT_RGB);
            Graphics2D graphics2D = image.createGraphics();
            canvas.paint(graphics2D);
            ImageIO.write(image,"jpeg", new File("/Users/surajmadkar/Desktop/SavePaintTest.jpeg"));
        }
        catch(Exception exception)
        {
            System.out.println(exception);
        }
		
	}
    
} // end class DrawFrame