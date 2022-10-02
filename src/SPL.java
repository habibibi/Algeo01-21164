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
    
}
