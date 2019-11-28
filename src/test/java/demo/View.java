package demo;

import conrec.Render;

import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;

public class View implements Render {

    public interface DrawOperation {
        void draw(Graphics2D g);
    }

    public static class DrawablePanel extends JPanel {
        private java.util.List<DrawOperation> operations = new ArrayList<>();

        void addDrawOperation(DrawOperation drawOp) {
            operations.add(drawOp);
        }

        @Override
        public void paint(Graphics g) {
            super.paint(g);
            operations.forEach(op -> op.draw((Graphics2D)g));
        }
    }

    private final DrawablePanel panel = new DrawablePanel();
    private Mapper mapper;

    View(int dimension, Mapper mapper) {
        this.mapper = mapper;
        JFrame frame = new JFrame();
        frame.add(panel);
        frame.setVisible(true);
        frame.setSize(dimension, dimension);
        frame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        frame.setResizable(false);
    }

    @Override
    public void drawContour(double startX, double startY, double endX, double endY, double contourLevel) {
        Mapper.Point start = new Mapper.Point(startX, startY);
        Mapper.Point end = new Mapper.Point(endX, endY);

        Mapper.Point viewStart = mapper.mapEnvToView(start);
        Mapper.Point viewEnd = mapper.mapEnvToView(end);

        int x1 = (int) Math.ceil(viewStart.getX());
        int y1 = (int) Math.ceil(viewStart.getY());
        int x2 = (int) Math.ceil(viewEnd.getX());
        int y2 = (int) Math.ceil(viewEnd.getY());
        panel.addDrawOperation(g -> g.drawLine(x1, y1, x2, y2));
        panel.repaint();
    }

}
