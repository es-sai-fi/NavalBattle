package org.example.navalbattle.model;

import javax.swing.JPanel;
import java.awt.Graphics;
import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseMotionAdapter;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.RenderingHints;
import java.util.ArrayList;
import java.util.List;

public class ShipPanel extends JPanel {

    private static final int GRID_SIZE = 50;
    private static final int GRID_ROWS = 10;
    private static final int GRID_COLS = 10;

    private List<ShipDrawing> ships;
    private ShipDrawing selectedShip;
    private int offsetX, offsetY;

    public ShipPanel() {
        ships = new ArrayList<>();
        // Crear y añadir los barcos
        ships.add(new ShipDrawing(50, 50, 200, 50, "Portaaviones"));
        ships.add(new ShipDrawing(50, 150, 150, 50, "Submarino"));
        ships.add(new ShipDrawing(250, 150, 150, 50, "Submarino"));
        ships.add(new ShipDrawing(50, 250, 100, 50, "Destructor"));
        ships.add(new ShipDrawing(200, 250, 100, 50, "Destructor"));
        ships.add(new ShipDrawing(350, 250, 100, 50, "Destructor"));
        ships.add(new ShipDrawing(50, 350, 50, 50, "Fragata"));
        ships.add(new ShipDrawing(150, 350, 50, 50, "Fragata"));
        ships.add(new ShipDrawing(250, 350, 50, 50, "Fragata"));
        ships.add(new ShipDrawing(350, 350, 50, 50, "Fragata"));

        addMouseListener(new MouseAdapter() {
            @Override
            public void mousePressed(MouseEvent e) {
                for (ShipDrawing ship : ships) {
                    if (ship.contains(e.getX(), e.getY())) {
                        selectedShip = ship;
                        offsetX = e.getX() - ship.getX();
                        offsetY = e.getY() - ship.getY();
                        break;
                    }
                }
            }

            @Override
            public void mouseReleased(MouseEvent e) {
                if (selectedShip != null) {
                    snapToGrid(selectedShip);
                    selectedShip = null;
                    repaint();
                }
            }
        });

        addMouseMotionListener(new MouseMotionAdapter() {
            @Override
            public void mouseDragged(MouseEvent e) {
                if (selectedShip != null) {
                    selectedShip.setPosition(e.getX() - offsetX, e.getY() - offsetY);
                    repaint();
                }
            }
        });

        addKeyListener(new KeyAdapter() {
            @Override
            public void keyPressed(KeyEvent e) {
                if (selectedShip != null) {
                    if (e.getKeyCode() == KeyEvent.VK_R) {
                        selectedShip.rotate();
                        snapToGrid(selectedShip);
                        repaint();
                    }
                }
            }
        });

        setFocusable(true);
        requestFocusInWindow();
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        Graphics2D g2d = (Graphics2D) g;
        g2d.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);

        // Dibujar los barcos
        for (ShipDrawing ship : ships) {
            ship.dibujar(g2d);
        }
    }

    private void snapToGrid(ShipDrawing ship) {
        int newX = ((ship.getX() + GRID_SIZE / 2) / GRID_SIZE) * GRID_SIZE;
        int newY = ((ship.getY() + GRID_SIZE / 2) / GRID_SIZE) * GRID_SIZE;

        // Verificar que el barco no esté fuera del panel
        if (newX < 0) newX = 0;
        if (newY < 0) newY = 0;
        if (newX + ship.getWidth() > getWidth()) {
            newX = getWidth() - ship.getWidth();
        }
        if (newY + ship.getHeight() > getHeight()) {
            newY = getHeight() - ship.getHeight();
        }

        ship.setPosition(newX, newY);
    }
}

