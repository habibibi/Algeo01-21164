public class Invers {
    public static boolean isExistInv(Matrix M)
   // Cek apakah matriks m memiliki invers
   // Prekondisi : m matriks persegi
    {
        if (Determinan.detReductionRow(M) != 0){
            return true;
        } else return false;
    }

    public static Matrix invGaussJordan(Matrix M)
    // Mengembalikan matriks invers dari m menggunakan metode OBE
    // Prekondisi : M matriks persegi
    {
        int size = M.getRow();
        if (isExistInv(M)){
            Matrix tmp = new Matrix(size,size*2);
            for (int i = 0;i < size;i++){
                for (int j = 0;j < size;j++){
                    tmp.mem[i][j] = M.mem[i][j];
                }
            }
            for (int i = 0;i < size;i++){
                for (int j = 0;j < size;j++){
                    if (j == i) tmp.mem[i][j+size] = 1;
                    else tmp.mem[i][j+size] = 0;
                }
            }
            Matrix eche = new Matrix();
            Matrix.echeForm(tmp, eche, true);
            return eche.getSubMatrix(0, eche.getLastIdxRow(), size, eche.getLastIdxCol());
        } else return null;
    }

    public static Matrix invAdjoint(Matrix M)
    // Mengembalikan matriks invers dari m menggunakan metode matriks adjoint
    // Prekondisi : M matriks persegi
    {
        double det = Determinan.detReductionRow(M);
        if (det == 0) return null;
        int n = M.getRow();
        Matrix mCof = new Matrix(M.getRow(),M.getCol());
        for (int i = 0;i < n;i++){
            for (int j = 0;j < n;j++){
                Matrix sub = new Matrix(n-1,n-1);
                int cntData = 0;
                for (int i2 = 0;i2 < n;i2++){
                    for (int j2 = 0;j2 < n;j2++){
                        if (i2 == i || j2 == j) continue;
                        sub.mem[cntData/(n-1)][cntData%(n-1)] = M.mem[i2][j2];
                        cntData++;
                    }
                }
                mCof.mem[i][j] = Determinan.detReductionRow(sub);
                if ((i + j)%2 == 1) mCof.mem[i][j] *= -1;
            }
        }
        mCof.printToScr();
        Matrix.transpose(mCof);
        mCof.printToScr();
        mCof.timesConst(1/det);
        return mCof;
    }
}
