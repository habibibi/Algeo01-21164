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

    public static double reductionRow(double[][] M, int N){
        double temp[][] = new double[N][N];
        double result = 0;
        for (int row = 0; row < N; ++row){
            for (int col = 0; col < N; ++col){
                temp[row][col] = M[row][col];
            }
        }
        int count = rowReduction(M, N);
        double det = M[0][0];
        for (int i = 1; i < N; i++){
            det *= M[i][i];
        }
        for (int row = 0; row < N; ++row){
            for (int col = 0; col < N; ++col){
                M[row][col] = temp[row][col];
            }
        }
        if (count % 2 == 0){
            result = det;
        }else{
            result = -det;
        }
        return result;
    }

    public static int rowReduction(double[][] M, int N){
        int i = 0, l = 0, idx;
        double pereduksi;
        int count = 0;
        for (int j = 1; j < N; j++){
            for (int h = j; h < N; h++){
                idx = i+1;
                while ((M[i][l] == 0) && (idx < N)){
                    for (int k = 0; k < N; k++){
                        double tmp = M[i][k];
                        M[i][k] = M[idx][k];
                        M[idx][k] = tmp;
                    }
                    count++;
                    idx++;
                }
                if (idx == N){
                    continue;}
                pereduksi = M[h][l] / M[i][l];
                for (int k = l; k < N; k++){
                    M[h][k] -= M[i][k] * pereduksi;
                }
            }
            i++;
            l++;
        }
        return count;
    }
}