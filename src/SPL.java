public class SPL {

    public static boolean isUniqueSol(Matrix augM)
    // Cek apakah solusi dari SPL adalah unik atau tidak.
    {
        Matrix echeM = new Matrix();
        Matrix.echeForm(augM, echeM, false);
        if (augM.getCol()-1 > augM.getRow()) return false;
        for (int i = 0;i < augM.getCol()-1;i++){

            if (echeM.mem[i][i] != 1) return false;
        }
        if (augM.getRow() >= augM.getCol() && augM.mem[augM.getLastIdxCol()][augM.getLastIdxCol()] != 0) return false;
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
    
    public static Matrix solInverse(Matrix augM){
        // Mencari solusi Ax = B dengan metode inverse x = A^-1 B
        // Asumsi awal : matriks A ada inversenya
        Matrix A = new Matrix();
        Matrix B = new Matrix();
        for (int i = 0; i < augM.getRow(); i++){
            for (int j = 0; j < augM.getCol()-1; j++){
                A.mem[i][j] = augM.mem[i][j];
            }
            B.mem[i][0] = augM.mem[i][augM.getCol()-1];
        }
        Matrix x = new Matrix();
        if (Invers.isExistInv(A)){
            x = Matrix.mult(Invers.OBE(A), B);
        }
        return x;
    }

    public static double[] solKaidahCramer(Matrix A,double[] B){
        double detA = Determinan.reductionRow(A.mem,A.getRow());
        if (detA == 0) return null;
        double[] sol = new double[B.length];
        for (int k = 0;k < B.length;k++){
            Matrix tmp = new Matrix(A);
            for (int i = 0;i < tmp.getRow();i++){
                tmp.mem[i][k] = B[i];
            }
            double detK = Determinan.reductionRow(tmp.mem,tmp.getRow());
            sol[k] = detK/detA; 
        }
        return sol;
    }
    public static void solGauss(Matrix augM){
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
        // Ditaro di main.java aja ini
        // // Menampilkan matriks eselon baris yang terbentuk
        // printEselonBaris(Persamaan, Hasil, augM.getRow(), augM.getCol());
        // // Substitusi mundur
        // validasi(Persamaan, Hasil);
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
    
    public static void validasi(Matrix Persamaan, Matrix Hasil){
        if ((Persamaan.mem[Persamaan.getRow()-1][Persamaan.getCol()-2] != 0)){
            Matrix solusi = new Matrix();
            for (int i = Persamaan.getRow() - 1; i >= 0; i--){
                double jumlah = 0.0;
                for (int j = i + 1; j < Persamaan.getCol()+1; j++){
                    jumlah += Persamaan.mem[i][j] * solusi.mem[j][0];
                }
                solusi.mem[i][0] = (Hasil.mem[i][0] - jumlah) / Persamaan.mem[i][i];
            }
            /** Menampilkan solusi yang terbentuk **/
            printSolusi(solusi);
        } else if
            ((Persamaan.mem[Persamaan.getRow()-1][Persamaan.getCol()-2] == 0) && (Hasil.mem[Hasil.getRow()-1][Hasil.getCol()-1] != 0)){
            System.out.println("Tidak ada Solusi yang Memenuhi"); 
        } else{
            System.out.println("Persamaan Memiliki Tak Terhingga Solusi"); 
        }
    }
}
