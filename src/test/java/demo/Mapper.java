package demo;

class Mapper {

    static class Point {
        private double x;
        private double y;

        Point(double x, double y) {
            this.x = x;
            this.y = y;
        }

        double getX() {
            return x;
        }

        double getY() {
            return y;
        }
    }

    private Point viewStart;
    private Point viewEnd;
    private Point envStart;
    private Point envEnd;

    Mapper(Point viewStart, Point viewEnd, Point envStart, Point envEnd) {
        this.viewStart = viewStart;
        this.viewEnd = viewEnd;
        this.envStart = envStart;
        this.envEnd = envEnd;
    }

    Point mapEnvToView(Point envPoint) {
        double viewX = map(envPoint.getX(), envStart.getX(), envEnd.getX(), viewStart.getX(), viewEnd.getX());
        double viewY = map(envPoint.getY(), envStart.getY(), envEnd.getY(), viewStart.getY(), viewEnd.getY());
        return new Point(viewX, viewY);
    }

    /*
     * maps x from [xmin, xmax] to [ymin, ymax]
     */
    private double map(double x, double xmin, double xmax, double ymin, double ymax) {
        return (x - xmin)/(xmax - xmin) * (ymax - ymin) + ymin;
    }

}
