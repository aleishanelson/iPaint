import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;

public class Icons {
	
	ImageIcon undoII, redoII, clearII, rectangleII, squareII, circleII, lineII, textII, ellipseII, triangleII, saveII;
	
	public Icons() {
	//DEFINE ICONS
	 try {
     	
		 
		 
		//Rectangle Icon
  		rectangleII = new ImageIcon(ImageIO.read(new URL("http://www.clker.com/cliparts/0/c/c/4/11954218501581414379ryanlerch_worldlabel.com_border_dark_blue_Black_checkered_4x2.svg.hi.png")));
  		Image rectangleImage = rectangleII.getImage(); // transform it 
  		Image newRectangleimg = rectangleImage.getScaledInstance(30, 20,  java.awt.Image.SCALE_SMOOTH); // scale it the smooth way  
  		rectangleII = new ImageIcon(newRectangleimg);  // transform it back
  		
     	//Square Icon
     		squareII = new ImageIcon(ImageIO.read(new URL("http://www.clker.com/cliparts/e/e/e/b/1194987026467993789fc02.svg.hi.png")));
     		Image squareImage = squareII.getImage(); // transform it 
     		Image newSquareimg = squareImage.getScaledInstance(30, 30,  java.awt.Image.SCALE_SMOOTH); // scale it the smooth way  
     		squareII = new ImageIcon(newSquareimg);  // transform it back
     		
     	//Ellipse Icon
     		ellipseII = new ImageIcon(ImageIO.read(new URL("http://www.susannorris.org/wp-content/uploads/2015/01/Circle.gif")));
     		Image ellipseImage = ellipseII.getImage(); // transform it 
     		Image newEllipseimg = ellipseImage.getScaledInstance(30, 20,  java.awt.Image.SCALE_SMOOTH); // scale it the smooth way  
     		ellipseII = new ImageIcon(newEllipseimg);  // transform it back
     		
     //Line Icon
     		lineII = new ImageIcon(ImageIO.read(new URL("http://www.clker.com/cliparts/k/S/p/m/B/C/straight-line-hi.png")));
     		Image lineImage = lineII.getImage(); // transform it 
     		Image newLineimg = lineImage.getScaledInstance(30, 30,  java.awt.Image.SCALE_SMOOTH); // scale it the smooth way  
     		lineII = new ImageIcon(newLineimg);  // transform it back
     		
     		
    //Line Icon
     		textII = new ImageIcon(ImageIO.read(new URL("http://www.pvhc.net/img155/dlhsvwzcognquriuvdwg.png")));
     		Image textImage = textII.getImage(); // transform it 
     		Image newTextimg = textImage.getScaledInstance(30, 30,  java.awt.Image.SCALE_SMOOTH); // scale it the smooth way  
     		textII = new ImageIcon(newTextimg);  // transform it back
     		
    //Clear Icon
     		clearII = new ImageIcon(ImageIO.read(new URL("http://www.clker.com/cliparts/E/4/i/3/E/z/trash-can-hi.png")));
     		Image clearImage = clearII.getImage(); // transform it 
     		Image newClearimg = clearImage.getScaledInstance(30, 30,  java.awt.Image.SCALE_SMOOTH); // scale it the smooth way  
     		clearII = new ImageIcon(newClearimg);  // transform it back
     		
   //Undo Icon
     		undoII = new ImageIcon(ImageIO.read(new URL("http://www.clker.com/cliparts/G/E/v/g/w/T/undo-hi.png")));
     		Image undoImage = undoII.getImage(); // transform it 
     		Image newUndoimg = undoImage.getScaledInstance(30, 30,  java.awt.Image.SCALE_SMOOTH); // scale it the smooth way  
     		undoII = new ImageIcon(newUndoimg);  // transform it back
     		
    //Redo Icon
     		redoII = new ImageIcon(ImageIO.read(new URL("http://www.clker.com/cliparts/a/0/e/9/11949947322096947487redo.svg.hi.png")));
     		Image redoImage = redoII.getImage(); // transform it 
     		Image newRedoimg = redoImage.getScaledInstance(30, 30,  java.awt.Image.SCALE_SMOOTH); // scale it the smooth way  
     		redoII = new ImageIcon(newRedoimg);  // transform it back
     //circle Icon
     		circleII = new ImageIcon(ImageIO.read(new URL("http://www.susannorris.org/wp-content/uploads/2015/01/Circle.gif")));
     		Image circleImage = circleII.getImage(); // transform it 
     		Image newCircleimg = circleImage.getScaledInstance(30, 30,  java.awt.Image.SCALE_SMOOTH); // scale it the smooth way  
     		circleII = new ImageIcon(newCircleimg);  // transform it back
     		
     //Triangle Icon
     		triangleII = new ImageIcon(ImageIO.read(new URL("http://www.clker.com/cliparts/d/4/7/0/11949870361565969806fc17.svg.hi.png")));
     		Image triangleImage = triangleII.getImage(); // transform it 
     		Image newTriangleimg = triangleImage.getScaledInstance(30, 30,  java.awt.Image.SCALE_SMOOTH); // scale it the smooth way  
     		triangleII = new ImageIcon(newTriangleimg);  // transform it back
     		
     		
     //Save Icon
     		saveII = new ImageIcon(ImageIO.read(new URL("http://www.clker.com/cliparts/0/9/0/f/1194994554718174540filesave.svg.hi.png")));
     		Image saveImage = saveII.getImage(); // transform it 
     		Image newSaveimg = saveImage.getScaledInstance(30, 30,  java.awt.Image.SCALE_SMOOTH); // scale it the smooth way  
     		saveII = new ImageIcon(newSaveimg);  // transform it back
     		   		
     }
     catch(MalformedURLException mue) {
     		mue.printStackTrace();
     }
     catch(IOException ioe) {
 			ioe.printStackTrace();
     }
} //end of Icons Constructor

} //end of Icons Class
