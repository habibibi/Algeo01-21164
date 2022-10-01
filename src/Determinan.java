public class Determinan {   
    public static double cofactorExpansion(Matrix m){
        // Mengembalikan nilai determinan dari m dengan menggunkan metode ekspansi kofaktor
        /* !!!!! kompleksitas O(n!), 11x11 aja udh meninggoy !!!!! */
        double det = 0;
        if (m.getRow() == 2){
            det = m.mem[0][0]*m.mem[1][1] - m.mem[1][0]*m.mem[0][1];
        } else {
            for (int j = 0;j < m.getCol();j++){
                Matrix tmp = new Matrix();
                if (j == 0){
                    tmp = m.getSubMatrix(1,1,m.getLastIdxRow(),m.getLastIdxCol());
                } else if (j == m.getCol()-1){
                    tmp = m.getSubMatrix(1,0,m.getLastIdxRow(),m.getLastIdxCol()-1);
                } else {
                    tmp = Matrix.combine(m.getSubMatrix(1,0,m.getLastIdxRow(),j-1),m.getSubMatrix(1,j+1,m.getLastIdxRow(),m.getLastIdxCol()));
                }
                double subDet = cofactorExpansion(tmp);
                if (j %2 == 0)det += subDet * m.mem[0][j];
                else det -= subDet * m.mem[0][j];
            }
        }
        return det;
    }

}