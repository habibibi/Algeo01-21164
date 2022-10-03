public class Determinan {   
    public static double detCofactorExpansion(Matrix m)
    // Mengembalikan nilai determinan dari m dengan menggunakan metode ekspansi kofaktor
    { 
        double det = 0;
        if (m.getRow() == 2){
            det = m.mem[0][0]*m.mem[1][1] - m.mem[1][0]*m.mem[0][1];
        } else {
            for (int j = 0;j < m.getCol();j++){
                Matrix tmp = new Matrix();
                if (j == 0){
                    tmp = m.getSubMatrix(1,m.getLastIdxRow(),1,m.getLastIdxCol());
                } else if (j == m.getCol()-1){
                    tmp = m.getSubMatrix(1,m.getLastIdxRow(),0,m.getLastIdxCol()-1);
                } else {
                    tmp = Matrix.combine(m.getSubMatrix(1,m.getLastIdxRow(),0,j-1),m.getSubMatrix(1,m.getLastIdxRow(),j+1,m.getLastIdxCol()));
                }
                double subDet = detCofactorExpansion(tmp);
                if (j %2 == 0)det += subDet * m.mem[0][j];
                else det -= subDet * m.mem[0][j];
            }
        }
        return det;
    }

    public static double detReductionRow(Matrix M)
    // Mengembalikan nilai determinan dari m dengan menggunakan metode reduksi baris
    {
        Matrix temp = new Matrix(M);
        int N = M.getRow();
        double result = 0;
        int count = rowReduction(M);
        double det = M.mem[0][0];
        for (int i = 1; i < N; i++){
            det *= M.mem[i][i];
        }
        for (int row = 0; row < N; ++row){
            for (int col = 0; col < N; ++col){
                M.mem[row][col] = temp.mem[row][col];
            }
        }
        if (count % 2 == 0){
            result = det;
        }else{
            result = -det;
        }
        return result;
    }

    private static int rowReduction(Matrix M)
    {
        int i = 0, l = 0, idx, N = M.getCol();
        double pereduksi;
        int count = 0;
        for (int j = 1; j < N; j++){
            for (int h = j; h < N; h++){
                idx = i+1;
                while ((M.mem[i][l] == 0) && (idx < N)){
                    for (int k = 0; k < N; k++){
                        double tmp = M.mem[i][k];
                        M.mem[i][k] = M.mem[idx][k];
                        M.mem[idx][k] = tmp;
                    }
                    count++;
                    idx++;
                }
                if (idx == N){
                    continue;}
                pereduksi = M.mem[h][l] / M.mem[i][l];
                for (int k = l; k < N; k++){
                    M.mem[h][k] -= M.mem[i][k] * pereduksi;
                }
            }
            i++;
            l++;
        }
        return count;
    }
}