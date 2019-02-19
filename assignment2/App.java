package assignment2;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;


class AppKeyListener implements KeyListener {
    // KeyListener for Main Application.
    @Override
    public void keyTyped(KeyEvent e) {
    }
    @Override
    public void keyPressed(KeyEvent e) {
    }
    @Override
    public void keyReleased(KeyEvent e) {
    }
}

class AppMouseListener implements MouseListener {
    // MouseListener for Level Panel.
    private LevelPanel _levelPanel;
    public LevelPanel getLevelPanel() {
        return _levelPanel;
    }
    public void setLevelPanel(LevelPanel levelPanel) {
        this._levelPanel = levelPanel;
    }

    // MouseListener for LevelGridPanel.
    private LevelGridPanel _levelGridPanel;
    public LevelGridPanel getLevelGridPanel() {
        return _levelGridPanel;
    }
    public void setLevelGridPanel(LevelGridPanel levelGridPanel) {
        this._levelGridPanel = levelGridPanel;
    }

    // Method for mouse clicked by user.
    @Override
    public void mouseClicked(MouseEvent e) {
        // Check if the current panel is being shown to the user.
        if (this.getLevelGridPanel().isShowShape()) {
            // Update the scoreboard.
            this.getLevelPanel().collectBlock();
        }
    }

    // Method for mouse pressed by user (unused).
    @Override
    public void mousePressed(MouseEvent e) {
    }

    // Method for mouse released by user (unused).
    @Override
    public void mouseReleased(MouseEvent e) {
    }

    // Method for mouse entered (unused).
    @Override
    public void mouseEntered(MouseEvent e) {
    }

    // Method for mouse exited (unused).
    @Override
    public void mouseExited(MouseEvent e) {
    }

    // Method for mouse exited (unused).
    public AppMouseListener(LevelPanel levelPanel, LevelGridPanel levelGridPanel) {
        this.setLevelPanel(levelPanel);
        this.setLevelGridPanel(levelGridPanel);
    }
}

class AppStartButtonListener implements ActionListener {
    private GameFrame _gameFrame;
    public GameFrame getGameFrame() {
        return _gameFrame;
    }
    public void setGameFrame(GameFrame gameFrame) {
        this._gameFrame = gameFrame;
    }

    // Method for user performing action (starting game).
    @Override
    public void actionPerformed(ActionEvent e) {
        this.getGameFrame().startGame();
    }

    public AppStartButtonListener(GameFrame gameFrame) {
        this.setGameFrame(gameFrame);
    }
}
