import java.io.*;

public class SPL {
    public static boolean isUniqueSol(Matrix augM)
    // Cek apakah solusi dari SPL adalah unik atau tidak.
    {
        Matrix echeM = new Matrix();
        Matrix.echeForm(augM, echeM, false);
        if (augM.getCol()-1 > augM.getRow()) return false;
        for (int i = 0;i < echeM.getCol()-1;i++){

            if (echeM.mem[i][i] != 1) return false;
        }
        if (augM.getRow() >= augM.getCol() && augM.mem[augM.getLastIdxCol()][augM.getLastIdxCol()] != 0) return false;
        return true;
    }

    public static boolean isThereSol(Matrix augM)
    // Cek apakah ada solusi dari SPL
    {
        Matrix echeM = new Matrix();
        Matrix.echeForm(augM,echeM,false);
        for (int i = 0;i < echeM.getRow();i++){
            boolean isAllZero = true;
            for (int j = 0;j < echeM.getCol()-1;j++){
                if (echeM.mem[i][j] != 0) {
                    isAllZero = false;
                    break;
                }
            }
            if (!isAllZero && echeM.mem[i][echeM.getLastIdxCol()] == 0){
                return false;
            }
        }
        return true;
    }

    public static double[] solGaussJordan(Matrix augM)
    // Mengembalikan array yang berisi solusi dari SPL jika solusinya unik, null jika tidak
    {
        if (isUniqueSol(augM)){
            double[] sol = new double[augM.getRow()];
            Matrix eche = new Matrix();
            Matrix.echeForm(augM, eche, true);
            for (int i = 0;i < augM.getRow();i++){
                sol[i] = eche.mem[i][eche.getCol()-1];
            }
            return sol;
        } else return null;
    }
    
    public static void printParaToScr(Matrix augM)
    // I.S augM terdefinisi dan sudah dicek memiliki solusi tak hingga, fileDir terdefinisi dan valid
    // F.S Solusi parametrik augM ditulis ke file yang dituju
    {
        Matrix echeform = new Matrix(augM);
        Matrix.echeForm(augM, echeform, true);
        boolean[] ada = new boolean[echeform.getCol()-1]; //default false
        int[] angka = new int[echeform.getCol()-1];
        for (int i = 0;i < echeform.getRow();i++){
            boolean isAllZero = true;
            for (int j = 0;j < echeform.getCol()-1;j++){
                if (echeform.mem[i][j] == 1){
                    ada[j] = true;
                    isAllZero = false;
                    break;
                }
            }
            if (isAllZero) break;
        }
        int now = 0;
        for (int i = 0;i < angka.length;i++){
            if (!ada[i]) {
                angka[i] = now;
                now++;
            }
        }
        int befAda = -1;
        for (int i = 0;i < echeform.getRow();i++){
            boolean isAllZero = true;
            for (int j = 0;j < echeform.getCol()-1;j++){
                if (echeform.mem[i][j] == 1){
                    isAllZero = false;
                    for (int k = befAda+1;k < j;k++){
                        System.out.printf("x%d = a%d\n",k+1,angka[k]);
                    }
                    System.out.printf("x%d =",j+1);
                    boolean first = true;
                    for (int k = j+1;k < echeform.getCol()-1;k++){
                        if (echeform.mem[i][k] != 0.0){
                            if (first) {
                                System.out.printf(" %.2fa%d",echeform.mem[i][k],angka[k]);
                                first = false;
                            } else if (-echeform.mem[i][j] > 0){
                                System.out.printf(" + %.2fa%d",-echeform.mem[i][k],angka[k]);
                            } else {
                                System.out.printf(" - %.2fa%d",echeform.mem[i][k],angka[k]);
                            }
                        }
                    }
                    int lastidx = echeform.getLastIdxCol();
                    if (first) System.out.printf(" %f",echeform.mem[i][lastidx]);
                    else if (echeform.mem[i][lastidx] < 0) System.out.printf(" - %.2f",-echeform.mem[i][lastidx]);
                    else if (echeform.mem[i][lastidx] > 0) System.out.printf(" + %.2f",echeform.mem[i][lastidx]);
                    System.out.println();
                    befAda = j;
                    break;
                }
            }
            if (isAllZero) break;
        }
        for (int k = befAda+1;k < echeform.getCol()-1;k++){
            System.out.printf("x%d = a%d\n",k+1,angka[k]);
        }
        
    }

    public static void printParaToFile(Matrix augM, String fileDir) throws IOException {
    // I.S augM terdefinisi dan sudah dicek memiliki solusi tak hingga, fileDir terdefinisi dan valid
    // F.S Solusi parametrik augM ditulis ke file yang dituju
        FileWriter writer = new FileWriter(fileDir);
        Matrix echeform = new Matrix(augM);
        Matrix.echeForm(augM, echeform, true);
        boolean[] ada = new boolean[echeform.getCol()-1]; //default false
        int[] angka = new int[echeform.getCol()-1];
        for (int i = 0;i < echeform.getRow();i++){
            boolean isAllZero = true;
            for (int j = 0;j < echeform.getCol()-1;j++){
                if (echeform.mem[i][j] == 1){
                    ada[j] = true;
                    isAllZero = false;
                    break;
                }
            }
            if (isAllZero) break;
        }
        int now = 0;
        for (int i = 0;i < angka.length;i++){
            if (!ada[i]) {
                angka[i] = now;
                now++;
            }
        }
        int befAda = -1;
        for (int i = 0;i < echeform.getRow();i++){
            boolean isAllZero = true;
            for (int j = 0;j < echeform.getCol()-1;j++){
                if (echeform.mem[i][j] == 1){
                    isAllZero = false;
                    for (int k = befAda+1;k < j;k++){
                        writer.write(String.format("x%d = a%d\n",k+1,angka[k]));
                    }
                    writer.write(String.format("x%d =",j+1));
                    boolean first = true;
                    for (int k = j+1;k < echeform.getCol()-1;k++){
                        if (echeform.mem[i][k] != 0.0){
                            if (first) {
                                writer.write(String.format(" %.2fa%d",echeform.mem[i][k],angka[k]));
                                first = false;
                            } else if (-echeform.mem[i][j] > 0){
                                writer.write(String.format(" + %.2fa%d",-echeform.mem[i][k],angka[k]));
                            } else {
                                writer.write(String.format(" - %.2fa%d",echeform.mem[i][k],angka[k]));
                            }
                        }
                    }
                    int lastidx = echeform.getLastIdxCol();
                    if (first) writer.write(String.format(" %f",echeform.mem[i][lastidx]));
                    else if (echeform.mem[i][lastidx] < 0) writer.write(String.format(" - %.2f",-echeform.mem[i][lastidx]));
                    else if (echeform.mem[i][lastidx] > 0) writer.write(String.format(" + %.2f",echeform.mem[i][lastidx]));
                    System.out.println();
                    befAda = j;
                    break;
                }
            }
            if (isAllZero) break;
        }
        for (int k = befAda+1;k < echeform.getCol()-1;k++){
            writer.write(String.format("x%d = a%d\n",k+1,angka[k]));
        }
        writer.close();
    }

    public static double[] solInverse(Matrix A, Matrix B){
        // Mencari solusi Ax = B dengan metode inverse x = A^-1 B
        // Mengembalikan null jika A tidak memiliki inverse
        if (Invers.isExistInv(A)){
            Matrix x = Matrix.mult(Invers.invGaussJordan(A), B);
            double[] sol = new double[x.getRow()];
            for (int i = 0;i < x.getRow();i++) sol[i] = x.mem[i][0];
            return sol;
        } else {
            return null;
        }

    }

    public static double[] solKaidahCramer(Matrix augM)
    // Mengembalikan array solusi unik dari augM menggunakan Kaidah Cramer, null jika tidak ada solusi unik
    {
        Matrix A = augM.getSubMatrix(0,augM.getLastIdxRow(),0,augM.getLastIdxCol()-1);
        double detA = Determinan.detReductionRow(A);
        if (detA == 0) return null;
        double[] sol = new double[augM.getRow()];
        for (int k = 0;k < augM.getRow();k++){
            Matrix tmp = new Matrix(A);
            for (int i = 0;i < tmp.getRow();i++){
                tmp.mem[i][k] = augM.mem[i][augM.getLastIdxRow()];
            }
            double detK = Determinan.detReductionRow(tmp);
            sol[k] = detK/detA; 
        }
        return sol;
    }
    public static double[] solGauss(Matrix augM){
        Matrix Persamaan = new Matrix();
        Matrix Hasil = new Matrix();
        for (int m = 0; m < augM.getRow(); ++m){
            for (int n = 0; n < augM.getCol()-1; ++n){
                Persamaan.mem[m][n] = augM.mem[m][n];
            }
        }
        for (int m = 0; m < augM.getRow(); ++m){
            Hasil.mem[m][augM.getCol()-1] = augM.mem[m][augM.getCol()-1];
        }
        for (int k = 0; k < augM.getRow(); ++k){
            // Menerapkan OBE penukaran baris
        	// Mencari elemen mutlak terbesar dalam satu kolom
            int max = k;
            for (int i = k + 1; i <= augM.getRow()-1; ++i){
                if (Math.abs(Persamaan.mem[i][k]) > Math.abs(Persamaan.mem[max][k])) 
                    max = i;
            }
            // Menukar baris   
            double[] temp = Persamaan.mem[k]; 
            Persamaan.mem[k] = Persamaan.mem[max]; 
            Persamaan.mem[max] = temp;
            // Menukar baris hasil, mengikuti penukaran baris Persamaan.mem
            double[] tempp = Hasil.mem[k]; 
            Hasil.mem[k] = Hasil.mem[max]; 
            Hasil.mem[max] = tempp;
            // Menerapkan OBE dengan mengurangkan baris satu dengan yang lain
            // Menjadi matriks eselon baris
            for (int i = k + 1; i < augM.getRow(); i++){
                double rasio = Persamaan.mem[i][k] / Persamaan.mem[k][k];
                Hasil.mem[i][0] -= rasio * Hasil.mem[k][0];
                for (int j = k; j < augM.getCol(); j++){
                    Persamaan.mem[i][j] -= rasio * Persamaan.mem[k][j];
                }
            }
        }
        if ((Persamaan.mem[Persamaan.getRow()-1][Persamaan.getCol()-2] != 0)){
            double[] solusi = new double[Persamaan.getCol()];
            for (int i = Persamaan.getRow() - 1; i >= 0; i--){
                double jumlah = 0.0;
                for (int j = i + 1; j < Persamaan.getCol()+1; j++){
                    jumlah += Persamaan.mem[i][j] * solusi[j];
                }
                solusi[i] = (Hasil.mem[i][0] - jumlah) / Persamaan.mem[i][i];
            }
            return solusi;
        } else if
            ((Persamaan.mem[Persamaan.getRow()-1][Persamaan.getCol()-2] == 0) && (Hasil.mem[Hasil.getRow()-1][Hasil.getCol()-1] != 0)){
            System.out.println("Tidak ada Solusi yang Memenuhi");
            return null;
        } else{
            System.out.println("Persamaan Memiliki Tak Terhingga Solusi"); 
            return null;
        }
    }

    public static void printEselonBaris(Matrix Persamaan, Matrix Hasil, int M, int N){
        System.out.println("\nMatriks Eselon Baris yang Terbentuk : ");
        for (int i = 0; i < M; i++){
               for (int j = 0; j < N; j++)
                   System.out.printf("%.3f ", Persamaan.mem[i][j]);
               System.out.printf("| %.3f\n", Hasil.mem[i]);
           }
           System.out.println();
    }
    
    public static void printSolusi(Matrix solusi){
        int N = solusi.getRow();
        System.out.println("\nSolusi : ");
        for (int i = 0; i < N; i++){
            System.out.printf("%.3f ", solusi.mem[i][0]);
        }
        System.out.println();
    }
}