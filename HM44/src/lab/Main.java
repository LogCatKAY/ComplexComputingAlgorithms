package lab;

// HM43
// Модифицированный метод Гаусса

public class Main {

    public static void main(String[] args) {
        final int N = 4;

        double[][] a = new double[][] {
                {2.0,4.0,2.0,4.0},
                {1.0,1.0,6.0,3.0},
                {-1.0,-4.0,1.0,1.0},
                {4.0,5.0,9.0,12.0}
        };

//        double[][] a = new double[][] {
//                {0.0, 0.0, 2.0, 0.0},
//                {0.0, 0.0, 6.0, 1.0},
//                {0.0, 3.0, 0.0, 1.0},
//                {4.0, 2.0, 0.0, 0.0},
//        };

        //вектор свободных членов
        double[] b = new double[]{22.0, 19.0, -10.0, 49.0};

//        double[] b = new double[]{8.0, 27.0, 6.0, 22.0};

        printMV("Matrix A, Vector B", a, b, N);

        double[] x = new double[N];

        gaussMTB(x, a, b, N);

        System.out.println("Roots");
        for (int i = 0; i < N; i++) {
            System.out.println(String.format("%8.2f", x[i]));
        }
    }


    public static void printMV(String text, double[][] a, double[] b, int n) {
        System.out.println(text);

        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                if (j == 0) System.out.print("(");
                System.out.print (String.format("%8.2f", a[i][j]));
                if (j == (n-1)) System.out.print(")");
            }
            System.out.println(String.format("%16.2f", b[i]));
        }
        System.out.println(String.format("%24s", "-------------------------------------------------------"));
    }

    public static void gaussMTB(double[] x, double[][] z, double[] y, int n) {
        double[][] a = new double[n][n];
        double[] b = new double[n];
        System.arraycopy(z, 0, a, 0, z.length);
        System.arraycopy(y, 0, b, 0, y.length);

        printMV("GaussMTB", a,b,n);

        for (int k = 0; k < n; k++) {
            double d = a[k][k];
            for (int j = k; j < n; j++) {
                a[k][j] /= d;
            }
            b[k] /= d;
            for(int i = k + 1; i < n; i++) {
                double r = a[i][k];
                for (int j = k; j < n; j++) {
                    a[i][j] -= a[k][j] * r;
                }
                b[i] -= b[k] * r;
            }
        }

        printMV("GaussMTB", a,b,n);

        // Часть 2. Нижнее преобразование.
        for (int k = n - 1; k > 0; k--) {
            for (int i = k - 1; i >= 0; i--) {
                b[i] -= b[k] * a[i][k];
                a[i][k] = 0.0;
            }
        }

        System.arraycopy(b, 0, x, 0, n);
        printMV("GaussMTB", a,b,n);
    }
}