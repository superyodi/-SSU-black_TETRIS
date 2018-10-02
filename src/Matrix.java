public class Matrix {
    private static int nAlloc = 0;
    //내가 선언한 객체 갯수
    private static int nFree = 0;
    //해제된 객체 갯수


    protected void finalize() throws Throwable {
        super.finalize();
        //부모클래스 먼저 실행하고 다음은 내가 만든거
        //파이널라이즈 하면 갯수 세준다.
        nFree++;
    }

    public static int get_nAlloc() {
        return nAlloc;
    }

    public static int get_nFree() {
        return nFree;
    }

    private int dy = 0;
    private int dx = 0;
    private int[][] array = null;

    public int get_dx() { return dx; }
    public int get_dy() { return dy; }
    public int[][] get_array() { return array; }

    private void alloc(int cy, int cx) throws MatrixException {
        if ((cx < 0) || (cy < 0)) {
            throw new MatrixException("wrong matrix size");
        }
        dx = cx;
        dy = cy;
        array = new int[dy][dx];
        //예외처리 후 할당
        nAlloc++;
    }

    public Matrix() throws MatrixException {
        alloc(0, 0);
    }

    public Matrix(int cy, int cx) throws MatrixException {
        alloc(cy, cx);
    }

    public Matrix(Matrix obj) throws MatrixException {
        alloc(obj.dy, obj.dx);
        for(int y = 0; y < dy; y++)
            for(int x = 0; x < dx; x++)
                array[y][x] = obj.array[y][x];

    }

    public Matrix(int[][] a) throws MatrixException {
        alloc(a.length, a[0].length);
        for(int y = 0; y < dy; y++){
            for(int x = 0 ;x < dx; x++){
                array[y][x] = a[y][x];
            }
        }
    }

    public Matrix clip(int top, int left, int bottom, int right) throws MatrixException {
        //bottom,right는 실제 해당 배열 index에서 +1 해준다.
        //2차원 배열을 index가 아니라 모눈종이라고 생각해야함.
        int cx = right - left;
        int cy = bottom - top;
        //가로, 세로 길이 구하기
        Matrix temp = new Matrix(cy, cx);
        for (int y = 0; y < cy; y++) {
            for (int x = 0; x < cx; x++) {
                if ((top + y >= 0) && (left + x >= 0) && (top + y < dy) && (left + x < dx))
                    temp.array[y][x] = array[top + y][left + x];
                else
                    throw new MatrixException("invalid matrix range");

            }
        }
        return temp;
    }

    public void paste(Matrix obj, int top, int left) throws MatrixException {
        if ((obj.dx <= dx) && (obj.dy <= dy) && (top >= 0) && (left >= 0)) {
            for (int y = 0; y < obj.dy; y++) {
                for (int x = 0; x < obj.dx; x++) {
                    array[top + y][left + x] = obj.array[y][x];
                }
            }
        } else
            throw new MatrixException("invalid matrix range");

    }

    public Matrix add(Matrix obj) throws MatrixException {
        if (dx != obj.dx || dy != obj.dy)
            throw new MismatchedMatrixException("matrix size mismatch");

        Matrix temp = new Matrix(dy, dx);
        for (int y = 0; y < dy; y++) {
            for (int x = 0; x < dx; x++) {
                temp.array[y][x] = array[y][x] + obj.array[y][x];
            }
        }
        return temp;
    }
       public int sum(){
        int total = 0;
        for(int y = 0; y < dy; y++)
            for (int x = 0; x < dx; x++)
                total += array[y][x];
        return total;
       }
       public  void mulc(int coef){
         for(int y = 0; y < dy; y++)
             for(int x = 0; x < dx; x++)
                 array[y][x] *= array[y][x] * coef;
       }
       public void print(){
           for (int y = 0; y < dy; y++) {
               for (int x = 0; x < dx; x++) {
                   System.out.print(array[y][x]+" ");
               }
               System.out.println();
           }
       }
       public boolean anyGreaterThan(int val){
        for(int y = 0; y < dy; y++){
            for(int x = 0; x < dx; x++){
                if(array[y][x] > val){
                    return true;
                }
            }
        }
           return false;
       }
//       Matrix int2bool() throws  Exception {
//        //transform [int] to [boolean]
//        Matrix temp = new Matrix(dy, dx);
//        int t_array[][] = temp.get_array();
//        for(int y = 0; y < dy; y++)
//            for(int x = 0; x < dx; x++)
//                t_array[y][x] = (array[y][x] != 0 ? 1: 0);
//        return temp;
//       }

//      end of Matrix
}





