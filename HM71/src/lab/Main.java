package lab;

/**
 * Лабораторная работа №6.
 * HM71
 * <p>
 * Метод Фаддеева.
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

        printM("Матрица A", a, N);

        double[][] R = new double[N][N];

        faddeev(R, a, N);
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

    public static void printM(String text, double[][] a, int n) {
        System.out.println(text);

        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                if (j == 0) System.out.print("(");
                System.out.print (String.format("%8.2f", a[i][j]));
                if (j == (n-1)) System.out.print(")\n");
            }
        }
        System.out.println(String.format("%24s", "----------------------------------"));
    }

    public static boolean faddeev(double[][] R, double[][] A, int n) {
        double[][] B = new double[n][n];

        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                B[i][j] = 0.0;
            }
            B[i][i] = 1.0;
        }
        printM("Matrix B", B, n);

        double[][] U = new double[n][n];
        double p, s;
        p = 0;

        for (int k = 1; k <= n; k++) {
            System.out.println("k = " + k);
            //multiply matrix
            for (int i = 0; i < n; i++) {
                for (int j = 0; j < n; j++) {
                    double z = 0.0;
                    for (int m = 0; m < n; m++) {
                        z += A[i][m] * B[m][j];
                    }
                    U[i][j] = z;
                }
            }

            printM("Matrix U", U, n);

            s = 0.0;
            for (int i = 0; i < n; i++) {
                s += U[i][i];
            }

            System.out.println("s = " + s);

            p = s/k;

            System.out.println("p = " + p);

            if (k == n) {
                break;
            }

            for (int i = 0; i < n; i++) {
                for (int j = 0; j < n; j++) {
                    B[i][j] = U[i][j];
                }
                B[i][i] -= p;
            }

            printM("Matrix B", B, n);
        } //k

        if (p == 0.0) {
            return false;
        }

        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                R[i][j] = B[i][j] / p;
            }
        }

        printM("Matrix R", R, n);
        return true;
    }
}
