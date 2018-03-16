
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
import javax.swing.JColorChooser;

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
    private JButton undo, redo, clear;// selectShape; // buttons to undo, redo last shape drawn and to clear the canvas
    private JButton jbRect, jbEllipse, jbLine, jbText, jbSquare, jbCircle, jbTriangle, jbSave, jbColorChooser;  //buttons for selecting which shape the user wants to draw
    private JCheckBox fillCheckBox; //checkbox to select whether or not to fill a shape with color
    private Color c;  //color variable to hold color value for shape fill    
    private JPanel toolboxPanel; //panel to hold buttons for drawing shapes and selecting colors
    private JPanel toolboxPadding; //adds padding around the toolbox panel
    private CanvasPanel canvas; //canvas panel for drawing shapes
    private Icons JBicons;
   
    //array of strings containing shape options for JComboBox shapes
    private String shapeOptions[]={"Line","Rectangle","Ellipse", "Text", "Circle", "Square", "Triangle"};

//CONSTRUCTOR For Paint Application Frame    
    public ApplicationFrame()
    {
        super("iPaint Application");  //Set label at the top of the application frame
            
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
        jbRect.setIcon(JBicons.rectangleII);
        jbSquare = new JButton("Square");
        jbSquare.setIcon(JBicons.squareII);
        jbEllipse = new JButton("Ellipse");
        jbEllipse.setIcon(JBicons.ellipseII);
        jbCircle = new JButton("Circle");
        jbCircle.setIcon(JBicons.circleII);
        jbLine = new JButton("Line");
        jbLine.setIcon(JBicons.lineII);
        jbText = new JButton("Text");
        jbText.setIcon(JBicons.textII);
        //selectShape = new JButton("Recolor Shape");
        	jbTriangle = new JButton("Triangle");
        	jbTriangle.setIcon(JBicons.triangleII);
        	jbSave = new JButton("Save");
        	jbSave.setIcon(JBicons.saveII);
        	
        
        jbColorChooser = new JButton("Select Fill Color");
        
        //create checkbox
        fillCheckBox = new JCheckBox( "Filled" );
        
        //creates toolboxJPanel with a grid layout for canvas buttons
        toolboxPanel = new JPanel();
        toolboxPanel.setLayout( new GridLayout( 2, 3, 5, 5 ) ); //sets padding between widgets in gridlayout
        
        //JPanel object, widgetPadder, with flowlayout to encapsulate and pad the widgetJPanel
        toolboxPadding = new JPanel();
        toolboxPadding.setLayout(new FlowLayout(FlowLayout.LEADING, 20, 5)); //sets padding around the edges
            
        // add widgets to widgetJPanel
        toolboxPanel.add(jbSave);
        toolboxPanel.add( undo );   
        toolboxPanel.add( jbRect );
        toolboxPanel.add(jbSquare);
        toolboxPanel.add(jbEllipse);
        toolboxPanel.add(jbCircle);
        toolboxPanel.add(jbTriangle); 
        toolboxPanel.add( clear );
        toolboxPanel.add( redo ); 
        toolboxPanel.add(jbLine);
        toolboxPanel.add(jbText);
        toolboxPanel.add( jbColorChooser );
        toolboxPanel.add( fillCheckBox );
       
        
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
        
        
        //Action Listener for color selection button to set fill color for a shape
        jbColorChooser.addActionListener(new ActionListener() {
        		public void actionPerformed(ActionEvent e) {
        			c = JColorChooser.showDialog(null, "Select Fill Color", Color.BLUE);
        			canvas.setCurrentShapeColor(c);
        			
        		}
        });
        
        
        //create handlers for color combobox and filled checkbox
        ItemListenerHandler handler = new ItemListenerHandler();
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
                      
        } // end method itemStateChanged
    }
    

	public void savePaint() {
		try
        {
            BufferedImage image = new BufferedImage(getWidth(), getHeight(), BufferedImage.TYPE_INT_RGB);
            Graphics2D graphics2D = image.createGraphics();
            canvas.paint(graphics2D);
            ImageIO.write(image,"jpeg", new File("/Users/aleishanelson/Desktop/SavePaintTest.jpeg"));
        }
        catch(Exception exception)
        {
            System.out.println(exception);
        }
		
	}
    
} // end class DrawFrame