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
}
