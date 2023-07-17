package net.mscraft.game;

import net.mscraft.MSCraft;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ComponentEvent;
import java.awt.event.ComponentListener;
import java.awt.event.WindowEvent;
import java.awt.event.WindowListener;

public class Window extends Canvas implements WindowListener, ComponentListener {

    private final JFrame frame;

    private final WindowCanvas canvas;

    public Window(String title) {
        this.frame = new JFrame(title);
        this.canvas = new WindowCanvas();

        this.frame.add(canvas);

        this.frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        this.frame.setLocationRelativeTo(null);
        this.frame.setSize(800, 600);
        this.frame.setResizable(true);
        this.frame.addWindowListener(this);
        this.frame.addComponentListener(this);

        this.frame.setVisible(true);
        this.frame.pack();
    }

    public void setTitle(String title) {
        this.frame.setTitle(title);
    }

    @Override
    public void windowOpened(WindowEvent e) {

    }

    @Override
    public void windowClosing(WindowEvent e) {

    }

    @Override
    public void windowClosed(WindowEvent e) {
        MSCraft.getInstance().stop();
    }

    @Override
    public void windowIconified(WindowEvent e) {

    }

    @Override
    public void windowDeiconified(WindowEvent e) {

    }

    @Override
    public void windowActivated(WindowEvent e) {

    }

    @Override
    public void windowDeactivated(WindowEvent e) {

    }

    @Override
    public void componentResized(ComponentEvent e) {
        MSCraft.getInstance().getRenderer().setHeight((int) this.getHeight());
        MSCraft.getInstance().getRenderer().setWidth((int) this.getWidth());
    }

    public int getHeight() {
        return (int) this.getSize().getHeight();
    }

    public int getWidth() {
        return (int) this.getSize().getWidth();
    }

    public Dimension getSize() {
        return this.frame.getSize();
    }

    @Override
    public void componentMoved(ComponentEvent e) {

    }

    @Override
    public void componentShown(ComponentEvent e) {

    }

    @Override
    public void componentHidden(ComponentEvent e) {

    }
}
