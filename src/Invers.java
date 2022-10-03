public class Invers {
    public static boolean isExistInv(Matrix m)
   // Cek apakah matriks m memiliki invers
   // Prekondisi : m matriks persegi
    {
        if (Determinan.detReductionRow(m) != 0){
            return true;
        } else return false;
    }

    public static Matrix invGaussJordan(Matrix m)
    // Mengembalikan matriks invers dari m menggunakan metode OBE
    // Prekondisi : m matriks persegi
    {
        int size = m.getRow();
        if (isExistInv(m)){
            Matrix tmp = new Matrix(size,size*2);
            for (int i = 0;i < size;i++){
                for (int j = 0;j < size;j++){
                    tmp.mem[i][j] = m.mem[i][j];
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
            return eche.getSubMatrix(0, size, eche.getLastIdxRow(), eche.getLastIdxCol());
        } else return null;
    }
}
