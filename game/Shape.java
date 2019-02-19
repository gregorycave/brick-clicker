package brick-clicker;

import java.awt.*;
import java.util.Random;
import java.awt.image.BufferedImage;

public abstract class Shape {                                           // ---------------------------------------
    private Color _colour;                                              // Initialise variable for colour.
    public Color getColour() {
        return _colour;
    }                        // Get current value for colour.
    public void setColour(Color colour) {
        this._colour = colour;
    }      // Update colour of the Shape.
                                                                        // ---------------------------------------
    public Shape() {
        this.setColour(new Color(0, 0, 255));
    }  // Set the colour of the shape to blue.
    public abstract void Draw(Graphics g);                              // Draw the shape into the frame.
}                                                                       // ---------------------------------------

class PieShape extends Shape {                                                          // ---------------------------------------
    private int _posX;                                                                  // Initialise variable for posX.
    public int getPosX() {
        return _posX;
    }                                              // Get current value for posX.
    public void setPosX(int posX) { this._posX = posX; }                                // Update colour of the PieShape.
                                                                                        // ---------------------------------------
    private int _posY;                                                                  // Initialise variable for posY.
    public int getPosY() {
        return _posY;
    }                                              // Get current value for posY.
    public void setPosY(int posY) { this._posY = posY; }                                // Update posY of the PieShape.
                                                                                        // ---------------------------------------
    private int _width;                                                                 // Initialise variable for width.
    public int getWidth() {
        return _width;
    }                                            // Get current value for width.
    public void setWidth(int width) { this._width = width; }                            // Update width of the PieShape.
                                                                                        // ---------------------------------------
    private int _height;                                                                // Initialise variable for height.
    public int getHeight() {
        return _height;
    }                                          // Get current value for height.
    public void setHeight(int height) { this._height = height; }                        // Update height of the PieShape.
                                                                                        // ---------------------------------------
    private int _startAngle;                                                            // Initialise variable for startAngle.
    public int getStartAngle() { return _startAngle; }                                  // Get current value for startAngle.
    public void setStartAngle(int startAngle) { this._startAngle = startAngle; }        // Update startAngle of the PieShape.
                                                                                        // ---------------------------------------
    private int _arcAngle;                                                              // Initialise variable for arcAngle.
    public int getArcAngle() { return _arcAngle; }                                      // Get current value for arcAngle.
    public void setArcAngle(int arcAngle) { this._arcAngle = arcAngle; }                // Update arcAngle of the PieShape.
                                                                                        // ---------------------------------------
    public PieShape(int width, int height, int posX, int posY) {
        super();
        this.setPosX(posX);
        this.setPosY(posY);
        this.setWidth(width);
        this.setHeight(height);
    }

    @Override
    public void Draw(Graphics g) {
        g.setColor(this.getColour());
        g.fillArc(this.getPosX(), this.getPosY(), this.getWidth(), this.getHeight(), this.getStartAngle(), this.getArcAngle());
    }
}

class Rectangles extends Shape {                                    // ---------------------------------------
    private int _posX;                                              // Initialise variable for posX.
    public int getPosX() {
        return _posX;
    }                          // Get current value for posX.
    public void setPosX(int posX) {
        this._posX = posX;
    }            // Update posX of the Rectangle.
                                                                    // ---------------------------------------
    private int _posY;                                              // Initialise variable for posY.
    public int getPosY() {
        return _posY;
    }                          // Get current value for posY.
    public void setPosY(int posY) {
        this._posY = posY;
    }            // Update posY of the Rectangle.
                                                                    // ---------------------------------------
    private int _width;                                             // Initialise variable for width.
    public int getWidth() {
        return _width;
    }                        // Get current value for width.
    public void setWidth(int width) {
        this._width = width;
    }        // Update width of the Rectangle.
                                                                    // ---------------------------------------
    private int _height;                                            // Initialise variable for height.
    public int getHeight() {
        return _height;
    }                      // Get current value for height.
    public void setHeight(int height) {
        this._height = height;
    }    // Update height of the Rectangle.
                                                                    // ---------------------------------------
    public Rectangles(int width, int height, int posX, int posY) {
        super();
        this.setWidth(width);
        this.setHeight(height);

        this.setPosX(posX);
        this.setPosY(posY);
    }

    @Override
    public void Draw(Graphics g) {
        g.setColor(this.getColour());
        g.drawRect(this.getPosX(), this.getPosY(), this.getWidth(), this.getHeight());
    }
}

class Squares extends Shape {                                       // ---------------------------------------
    private int _posX;                                              // Initialise variable for posX.
    public int getPosX() {
        return _posX;
    }                          // Get current value for posX.
    public void setPosX(int posX) {
        this._posX = posX;
    }            // Update posX of the Square.
                                                                    // ---------------------------------------
    private int _posY;                                              // Initialise variable for posY.
    public int getPosY() {
        return _posY;
    }                          // Get current value for posY.
    public void setPosY(int posY) {
        this._posY = posY;
    }            // Update posY of the Square.
                                                                    // ---------------------------------------
    private int _width;                                             // Initialise variable for width.
    public int getWidth() {
        return _width;
    }                        // Get current value for width.
    public void setWidth(int width) {
        this._width = width;
    }        // Update width of the Square.
                                                                    // ---------------------------------------
    private int _height;                                            // Initialise variable for height.
    public int getHeight() {
        return _height;
    }                      // Get current value for height.
    public void setHeight(int height) {
        this._height = height;
    }    // Update height of the Square.
                                                                    // ---------------------------------------
    public Squares(int width, int height, int posX, int posY) {
        super();
        this.setWidth(width);
        this.setHeight(height);

        this.setPosX(posX);
        this.setPosY(posY);
    }

    @Override
    public void Draw(Graphics g) {
        g.setColor(this.getColour());
        g.fillRect(this.getPosX(), this.getPosY(), this.getWidth(), this.getHeight());
    }
}

class Triangles extends Shape {                                         // ---------------------------------------
    private int[] _xCoords;                                             // Initialise variable for xCoords.
    public int[] getXCoords() {
        return _xCoords;
    }                      // Get current value for xCoords.
    public void setXCoords(int[] xCoords) {
        this._xCoords = xCoords;
    }  // Update xCoords of the Triangle.
                                                                        // ---------------------------------------
    private int[] _yCoords;                                             // Initialise variable for yCoords.
    public int[] getYCoords() {
        return _yCoords;
    }                      // Get current value for yCoords.
    public void setYCoords(int[] yCoords) {
        this._yCoords = yCoords;
    }  // Update yCoords of the Triangle.
                                                                        // ---------------------------------------
    private int _corners;                                               // Initialise variable for corners.
    public int getCorners() {
        return _corners;
    }                        // Get current value for corners.
    public void setCorners(int corners) {
        this._corners = _corners;
    }   // Update corners of the Triangle.
                                                                        // ---------------------------------------
    public Triangles(int[] xCoords, int[] yCoords) {
        super();
        if (xCoords.length == yCoords.length) {
            this.setXCoords(xCoords);
            this.setYCoords(yCoords);

            this.setCorners(this.getXCoords().length);
        }
        else {

        }
    }

    @Override
    public void Draw(Graphics g) {
        g.setColor(this.getColour());
        g.fillPolygon(this.getXCoords(), this.getYCoords(), this.getCorners());
    }
}

class Circles extends Shape {                                       // ---------------------------------------
    private int _posX;                                              // Initialise variable for posX.
    public int getPosX() {
        return _posX;
    }                          // Get current value for posX.
    public void setPosX(int posX) {
        this._posX = posX;
    }            // Update posX of the Circle.
                                                                    // ---------------------------------------
    private int _posY;                                              // Initialise variable for posY.
    public int getPosY() {
        return _posY;
    }                          // Get current value for posY.
    public void setPosY(int posY) {
        this._posY = posY;
    }            // Update posY of the Circle.
                                                                    // ---------------------------------------
    private int _radius;                                            // Initialise variable for radius.
    public int getRadius() {
        return _radius;
    }                      // Get current value for radius.
    public void setRadius(int radius) {
        this._radius = radius;
    }    // Update radius of the Circle.
                                                                    // ---------------------------------------
    public int getDiameter() {
        return this.getRadius() * 2;
    }       // Initialise variable for diameter.
                                                                    // ---------------------------------------
    public Circles(int radius, int posX, int posY) {
        super();
        this.setPosX(posX);
        this.setPosY(posY);
        this.setRadius(radius);
    }

    @Override
    public void Draw(Graphics g) {
        g.setColor(this.getColour());
        g.fillOval(this.getPosX(), this.getPosY(), this.getDiameter(), this.getDiameter());
    }
}
