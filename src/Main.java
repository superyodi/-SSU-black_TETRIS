public class Main {

    public Main(){}
    public static void main(String[] args) throws Exception{ main3(args); }

    public static void main1(String[] args) throws Exception {
        Matrix m = new Matrix();
        for (int i=0; i<999; i++)
            m = new Matrix();//객체 할당
        System.gc();
        System.out.println("nAlloc=" + m.get_nAlloc());
        System.out.println("nFree=" + m.get_nFree());




    }
    public static void main3(String[] args) throws Exception {
        int [][] arrayScreen = {
                { 1, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 1 },
                { 1, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 1 },
                { 1, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 1 },
                { 1, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 1 },
                { 1, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 1 },
                { 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1 },
        };
        //테트리스 테두리, 가이드라인

        int[][] arrayBlk = {
                {0, 1, 0},
                {1, 1, 1},
                {0, 0, 0},
        };

        Matrix oScreen = new Matrix(arrayScreen);
        System.out.println("oScreen");
        drawMatrix(oScreen); System.out.println();

        Matrix currBlk = new Matrix(arrayBlk);
        System.out.println("currBlk");
        drawMatrix(currBlk); System.out.println();
    }

    public static void drawMatrix(Matrix m) {
        int[][] array = m.get_array();
        for(int y = 0; y < m.get_dy(); y++){
            for(int x = 0; x < m.get_dx(); x++){
                if(array[y][x] == 1) {
                    System.out.print("■");
                }else if(array[y][x] == 0) {
                    System.out.print("□");
                }
                else {
                    System.out.print("X");
                }
            }
            System.out.println();
        }
    }
}
