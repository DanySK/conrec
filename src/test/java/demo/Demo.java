package demo;

import conrec.Conrec;

public class Demo {

    private static final int SAMPLES = 100;
    private static final int DIMENSION = 512;

    public static void main(String[] args) {

        final double envXStart = -1.5;
        final double envYStart = -1.5;
        final double envXEnd = 1.5;
        final double envYEnd = 1.5;

        final double STEP = (envXEnd - envXStart) / SAMPLES;

        final int ilb = 0;
        final int jlb = 0;
        final int iub = SAMPLES;
        final int jub = SAMPLES;

        final double[] x = new double[iub + 1];
        final double[] y = new double[jub + 1];
        for (int i = ilb; i <= iub; i++) {
            if (i == ilb) {
                x[i] = envXStart;
            } else {
                x[i] = x[i - 1] + STEP;
            }
        }
        for (int j = jlb; j <= jub; j++) {
            if (j == jlb) {
                y[j] = envYStart;
            } else {
                y[j] = y[j - 1] + STEP;
            }
        }

        double[][] d = new double[iub + 1][jub + 1];
        for (int i = ilb; i <= iub; i++) {
            for (int j = jlb; j <= jub; j++) {
                d[i][j] = f(x[i],y[j]);
            }
        }
        int nc = 5;
        double[] z = {0.0,0.25,0.5,2,2.25};
        try {
            Mapper.Point envStart = new Mapper.Point(envXStart, envYStart);
            Mapper.Point envEnd = new Mapper.Point(envXEnd, envYEnd);
            Mapper.Point viewStart = new Mapper.Point(0,0);
            Mapper.Point viewEnd = new Mapper.Point(DIMENSION,DIMENSION);
            Mapper m = new Mapper(viewStart, viewEnd, envStart, envEnd);
            new Conrec(new View(DIMENSION, m)).contour(d, ilb, iub, jlb, jub, x, y, nc, z);
        } catch(Exception e) {
            e.printStackTrace();
        }
    }

    private static double f(double x, double y) {
        final double C = 0.842;
        return 1/(Math.pow((Math.pow(x,2) + (y-C)*(y+C)),2) + Math.pow((x*(y+C) + x*(y-C)),2));
    }
}
