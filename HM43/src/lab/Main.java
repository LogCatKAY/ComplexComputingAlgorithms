package lab;

/**
 * Лабораторная работа №3
 * <p>
 * HM43.
 * Модифицированный метод Гаусса.
 * {1.0, x, x, x},
 * {0.0, 1.0, x, x},
 * {0.0, 0.0, 1.0, x},
 * {0.0, 0.0, 0.0, 1.0},
 */
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

        gaussM(x, a, b, N);

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

    public static void gaussM(double[] x, double[][] z, double[] y, int n) {
        double[][] a = new double[n][n];
        double[] b = new double[n];
        a = cloneArray(z);
        System.arraycopy(y, 0, b, 0, y.length);

        printMV("GaussM", a,b,n);

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

        printMV("GaussM", a,b,n);

        // Часть 2
        // перевернули матрицу так, что левый верхний элемент оказался справа снизу
        x[n - 1] = b[n - 1];
        for (int i = n - 2; i >= 0; i--) {
            double s = 0.0;
            for (int j = i + 1; j < n; j++) {
                s += a[i][j] * x[j];
            }
            x[i] = b[i] - s;
        }
    }

    private static double[][] cloneArray(double[][] source) {
        int length = source.length;
        double[][] clonedArray = new double[length][source[0].length];

        for (int i = 0; i < length; i++) {
            System.arraycopy(source[i], 0, clonedArray[i], 0, source[i].length);
        }

        return clonedArray;
    }
}
