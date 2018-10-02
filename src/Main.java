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
        //배경 및 테두리 출력

        Matrix currBlk = new Matrix(arrayBlk);
        System.out.println("currBlk");
        drawMatrix(currBlk); System.out.println();
        //현재 블록 출력

        int top = 0;
        int left = 4;
        //5번째 사각형
        Matrix tempBlk = oScreen.clip(top, left, top+currBlk.get_dy(), left+currBlk.get_dx());
        //top,left의 좌표가 가리키는 공간에 currBlk의 가/세 길이를 더해줌--> botton, right
        System.out.println("tempBlk (after clip):");
        drawMatrix(tempBlk); System.out.println();

        tempBlk = tempBlk.add(currBlk);
        //내가 배경에서 선택한 위치의 임식 공간을 떼어내서 현재 블럭을 더한다
        System.out.println("tempBlk (after add):");
        drawMatrix(tempBlk); System.out.println();

        oScreen.paste(tempBlk, top, left);
        System.out.println("oScreen (after paste): ");
        drawMatrix(oScreen); System.out.println();

        System.out.println("currBlk.sum()=" + currBlk.sum());
        System.out.println();

        tempBlk.mulc(2);
        System.out.println("oScreen (after paste): ");
        tempBlk.print(); System.out.println();

        System.out.println("currBlk.anyGreaterThan(1)=" + currBlk.anyGreaterThan(1));
        System.out.println("tempBlk.anyGreaterThan(1)=" + tempBlk.anyGreaterThan(1));







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
