package org.example.navalbattle.model;

import java.awt.Graphics2D;
import java.awt.Color;
import java.awt.geom.AffineTransform;
import java.awt.geom.Rectangle2D;

public class ShipDrawing {
    private int x, y, width, height;
    private String tipo;
    private Color color;
    private double angle;

    public ShipDrawing(int x, int y, int width, int height, String tipo) {
        this.x = x;
        this.y = y;
        this.width = width;
        this.height = height;
        this.tipo = tipo;
        this.color = determinarColor(tipo);
        this.angle = 0;
    }

    private Color determinarColor(String tipo) {
        switch (tipo) {
            case "Portaaviones":
                return Color.GRAY;
            case "Submarino":
                return Color.BLUE;
            case "Destructor":
                return Color.GREEN;
            case "Fragata":
                return Color.RED;
            default:
                return Color.BLACK;
        }
    }

    public void dibujar(Graphics2D g2d) {
        AffineTransform old = g2d.getTransform();
        g2d.translate(x + width / 2, y + height / 2);
        g2d.rotate(Math.toRadians(angle));
        g2d.translate(-width / 2, -height / 2);

        // Dibujar el cuerpo del barco
        g2d.setColor(color);
        g2d.fillRoundRect(0, 0, width, height, 15, 15);

        // Dibujar detalles del barco
        g2d.setColor(Color.BLACK);
        g2d.drawRoundRect(0, 0, width, height, 15, 15);

        // Dibujar cabina o torre dependiendo del tipo de barco
        if ("Portaaviones".equals(tipo) || "Submarino".equals(tipo)) {
            g2d.fillRect(width / 2 - 10, -10, 20, 10);
        }

        // Dibujar detalles adicionales para submarinos
        if ("Submarino".equals(tipo)) {
            g2d.fillOval(width - 20, height / 2 - 5, 10, 10);
        }

        // Dibujar ventanas para destructores y fragatas
        if ("Destructor".equals(tipo) || "Fragata".equals(tipo)) {
            for (int i = 0; i < width / 20; i++) {
                g2d.fillOval(10 + i * 20, height / 4, 10, 10);
            }
        }

        g2d.setTransform(old);
    }

    public boolean contains(int mouseX, int mouseY) {
        AffineTransform at = AffineTransform.getRotateInstance(Math.toRadians(angle), x + width / 2, y + height / 2);
        Rectangle2D rect = new Rectangle2D.Double(x, y, width, height);
        return at.createTransformedShape(rect).contains(mouseX, mouseY);
    }

    public void setPosition(int x, int y) {
        this.x = x;
        this.y = y;
    }

    public void rotate() {
        this.angle += 90;
        this.angle = this.angle % 360;
        // Ajustar el tamaño después de rotar
        int temp = this.width;
        this.width = this.height;
        this.height = temp;
    }

    public int getX() {
        return x;
    }

    public int getY() {
        return y;
    }

    public int getWidth() {
        return width;
    }

    public int getHeight() {
        return height;
    }
}
