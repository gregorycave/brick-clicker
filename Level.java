package brick-clicker;

import javax.swing.*;
import java.awt.*;
import java.util.Random;
import java.util.TimerTask;
import java.util.ArrayList;
import java.awt.event.MouseListener;

class Level extends TimerTask {                                                         // ---------------------------------------
    private GameFrame _gameFrame;                                                       // Initialise variable for GameFrame.
    public GameFrame getGameFrame() { return _gameFrame; }                              // Get current value for GameFrame.
    public void setGameFrame(GameFrame gameFrame) { this._gameFrame = gameFrame; }      // Update ScoreboardPanel within the game.
                                                                                        // ---------------------------------------

    private void updateShapePosition() { this.getGameFrame().getGamePanel().getLevelPanel().getLevelGrid().UpdateShapePosition(); } // Update the position of the shape.

    public Level(GameFrame gameFrame) { this.setGameFrame(gameFrame); }     // Update the GameFrame.

    @Override
    public void run() {                                                     // ---------------------------------------
        this.updateShapePosition();                                         // Update the ShapePosition.
        this.getGameFrame().getGamePanel().getLevelPanel().repaintGrid();   // Repaint the Grid.
    }                                                                       // ---------------------------------------
}

class LevelGrid extends ArrayList<ArrayList<LevelGridPanel>> {                                                                      // ---------------------------------------
    private LevelPosition _currentShapePosition;                                                                                    // Initialise variable for currentShapePosition.
    public LevelPosition getCurrentShapePosition() { return _currentShapePosition; }                                                // Get current value for currentShapePosition.
    public void setCurrentShapePosition(LevelPosition currentShapePosition) { this._currentShapePosition = currentShapePosition; }  // Update currentShapePosition within the game.
                                                                                                                                    // ---------------------------------------

    private void changeShapeVisibility(boolean showShape) { this.get(this.getCurrentShapePosition().getX()).get(this.getCurrentShapePosition().getY()).setShowShape(showShape); }

    private void hideCurrentShape() {
        this.changeShapeVisibility(false);
    }

    public void UpdateShapePosition() {                                         // ---------------------------------------
        this.hideCurrentShape();                                                // Hide the current shape.
        this.setCurrentShapePosition(LevelPosition.generateRandomPosition());   // Set a new shape position.
        this.changeShapeVisibility(true);                            // Reveal the shape again.
    }                                                                           // ---------------------------------------

    public LevelGridPanel getCurrentBlock() { return this.get(this.getCurrentShapePosition().getX()).get(this.getCurrentShapePosition().getY()); }  // Update the Shape Position.
}

class LevelGridPanel extends JPanel {                                                // ---------------------------------------
    private boolean _showShape;                                                      // Initialise variable for showShape.
    public boolean isShowShape() { return _showShape; }                              // Get current value for showShape (check if visible or not).
    public void setShowShape(boolean showShape) { this._showShape = showShape; }     // Update showShape within the game.
                                                                                     // ---------------------------------------
    private Shape _shape;                                                            // Initialise variable for Shape.
    public Shape getShape() { return _shape; }                                       // Get current value for Shape.
    public void setShape(Shape shape) { this._shape = shape; }                       // Update the Shape within the game.
                                                                                     // ---------------------------------------
    private boolean _collected;                                                      // Initialise variable for collected.
    public boolean isCollected() {
        return _collected;
    }                              // Get current value for collected.
    public void setCollected(boolean collected) { this._collected = collected; }     // Update the collected variable within the game.
                                                                                     // ---------------------------------------
                                                                                                                                // ---------------------------------------
    public void initialiseMouseEvent(LevelPanel levelPanel) { this.addMouseListener(new AppMouseListener(levelPanel, this)); }  // Adds a mouse listener to the game.
                                                                                                                                // ---------------------------------------
    private void initialisePanel() { this.setShape(new Squares(Constants.BLOCKWIDTH, Constants.BLOCKHEIGHT, 0, 0)); }           // Sets the properties of the shape.
                                                                                                                                // ---------------------------------------
    public LevelGridPanel() { this.initialisePanel(); }                                                                         // Initialises the panel.
                                                                                                                                // ---------------------------------------
    @Override
    public void paintComponent(Graphics g) {                                            // ---------------------------------------
        if (this.isCollected()) { this.getShape().setColour(Color.red); }               // If the shape has been clicked: change the color to red.
                                                                                        // ---------------------------------------
        else { this.getShape().setColour(Color.blue); }                                 // If the shape has NOT been clicked: change the color to blue.
                                                                                        // ---------------------------------------
        if (this.isShowShape()) { this.getShape().Draw(g); }                            // If the shape is to be shown: draw the shape in the game.
                                                                                        // ---------------------------------------
        else { g.clearRect(0, 0, Constants.BLOCKWIDTH, Constants.BLOCKHEIGHT); }  // If the shape is NOT to be shown: hide it from the user.
    }                                                                                   // ---------------------------------------
}

class LevelPanel extends JPanel {                                                                                                   // ---------------------------------------
    private GameFrame _gameFrame;                                                                                                   // Initialise variable for GameFrame.
    public GameFrame getGameFrame() { return _gameFrame; }                                                                          // Get current value for GameFrame.
    public void setGameFrame(GameFrame gameFrame) { this._gameFrame = gameFrame; }                                                  // Update the GameFrame.
                                                                                                                                    // ---------------------------------------
    private LevelGrid _levelGrid;                                                                                                   // Initialise variable for LevelGrid.
    public LevelGrid getLevelGrid() { return _levelGrid; }                                                                          // Get current value for LevelGrid.
    public void setLevelGrid(LevelGrid levelGrid) { this._levelGrid = levelGrid; }                                                  // Update the LevelGrid.
                                                                                                                                    // ---------------------------------------
    private GridBagConstraints _gridBagConstraints;                                                                                 // Initialise variable for GridBagConstraints.
    public GridBagConstraints getGridBagConstraints() { return _gridBagConstraints; }                                               // Get current value for GridBagConstraints.
    public void setGridBagConstraints(GridBagConstraints gridBagConstraints) { this._gridBagConstraints = gridBagConstraints; }     // Update GridBagConstraints.
                                                                                                                                    // ---------------------------------------
    private void addItem(JComponent component, int x, int y) {  // Add new item to the GridBag layout.
        GridBagConstraints c = this.getGridBagConstraints();

        for (Component temp: getComponents()) {
            if (c.gridx == x && c.gridy == y) {
                remove(temp);
                break;
            }
        }

        c.fill = GridBagConstraints.BOTH;
        c.gridx = x;
        c.gridy = y;
        c.weightx = 0.5;
        this.add(component, c);
    }

    public void collectBlock() {    // Sets the block to collected.
        LevelPosition currentPosition = this.getLevelGrid().getCurrentShapePosition();
        this.getLevelGrid().getCurrentBlock().setCollected(true);
        this.getGameFrame().updateScore(1);
        this.repaintGrid();
    }

    public void repaintGrid() {     // Sets the layout of the grid.

        this.setLayout(new GridBagLayout());

        // Map the level grid to the GridBagLayout bound to the current panel.
        for (int x = 0; x < this.getLevelGrid().size() ; x++) {
            ArrayList<LevelGridPanel> currentRow = this.getLevelGrid().get(x);
            for (int y = 0; y < currentRow.size(); y++) {
                this.addItem(currentRow.get(y), x, y);
                currentRow.get(y).repaint();
            }
        }
        revalidate();
        repaint();
    }

    public void initialiseGrid() {  // Initialise the grid by creating a 20x20 arraylist of panels.

        this.setLevelGrid(new LevelGrid());
        for (int i = 0; i < 20; i++) {
            ArrayList<LevelGridPanel> row = new ArrayList<LevelGridPanel>();

            for (int j = 0; j < 20; j++) {
                row.add(new LevelGridPanel());
                row.get(j).initialiseMouseEvent(this);

            }
            this.getLevelGrid().add(row);
        }

        this.getLevelGrid().setCurrentShapePosition(LevelPosition.generateRandomPosition());    // Generate random position for block to appear.
        this.getLevelGrid()                                                                     // Set the block to appear within the game.
                .get(this.getLevelGrid().getCurrentShapePosition().getX())
                .get(this.getLevelGrid().getCurrentShapePosition().getY())
                .setShowShape(true);
    }

    private void initialisePanel(GameFrame gameFrame) {
                                                                // ---------------------------------------
        this.setGameFrame(gameFrame);                           // Initialise GameFrame.
        this.setGridBagConstraints(new GridBagConstraints());   // Initialise GridBagConstraints.
                                                                // ---------------------------------------
        this.initialiseGrid();                                  // Initialise the Grid.
    }                                                           // ---------------------------------------

    public LevelPanel(GameFrame gameFrame) {                    // ---------------------------------------
        this.initialisePanel(gameFrame);                        // Initialise the Panel.
    }                                                           // ---------------------------------------
}

class LevelPosition {   // Get the current position within the level.
    private int _x;
    public int getX() {
        return _x;
    }
    public void setX(int x) {
        this._x = x;
    }

    private int _y;
    public int getY() {
        return _y;
    }
    public void setY(int y) {
        this._y = y;
    }

    public LevelPosition(int x, int y) {
        this.setX(x);
        this.setY(x);
    }

    public static LevelPosition generateRandomPosition() {  // Generate a random position for the shape.
        Random rn = new Random();
        int x = rn.nextInt(19 - 0 + 1) + 0;
        int y = rn.nextInt(19 - 0 + 1) + 0;
        return new LevelPosition(x, y);
    }
}
