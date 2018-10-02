import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Main {
    static int[][] arrayBlk = {
            {1, 0, 0, 0},
            {1, 0, 0, 0},
            {1, 0, 0, 0},
            {1, 0, 0, 0},
    };
    private static int iScreenDy = 15;
    private static int iScreenDx = 10;
    private static int iScreenDw = 4;
    //왜 3이 아닐까??
    //짝대기는 4*4 배열, 이것까지 커버해야한다.
    private static int[][] createArrayScreen(int dy,int dx, int dw) {
        int x,y;
        int[][] array = new int[dy+dw][dx+2*dw];
        //배열 크기 여유롭게 설정
        for(y= 0; y < array.length; y++)
            for (x = 0; x < dw; x++)
                array[y][x] = 1;
            //좌측부터 두께(dw)의 크기까지 1
        for(y = 0; y < array.length; y++)
            for(x = dx+dw; x < array[0].length; x++ )
                array[y][x] = 1;
            //우측부터 두께의 크기까지 1
        for(y = dy; y < array.length; y++)
            for(x = 0; x < array[0].length; x++)
                array[y][x] = 1;
            //바닥 테두리
        return array;
    }
    public static void printScreen(Matrix screen) {
        int dy = screen.get_dy();
        int dx = screen.get_dx();
        int dw = iScreenDw;

        //dy: 19
        //dx: 18
        int[][] array = screen.get_array();
        //**Screen 배열은 널널하지만 화면에는 테두리 두께가 1만 보이도록 한다.**
        for(int y = 0; y < dy - dw+1; y++){
            for(int x = dw -1; x < dx-dw+1; x++){
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

//    public static void printMatrix(Matrix m) {
//        int[][] array = m.get_array();
//        for(int y = 0; y < m.get_dy(); y++){
//            for(int x = 0; x < m.get_dx(); x++){
//                if(array[y][x] == 1) {
//                    System.out.print("■");
//                }else if(array[y][x] == 0) {
//                    System.out.print("□");
//                }
//                else {
//                    System.out.print("X");
//                }
//            }
//            System.out.println();
//        }
//    }

    //for "key input" methods
    private  static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    private  static String line = null;
    private static int nKeys = 0;
    private static char getKey() throws IOException {
        char ch;
        if (nKeys != 0){
            ch = line.charAt(line.length() - nKeys);
            nKeys--;
            return ch;
        }
        do {
           line = br.readLine();
           //read A line
           nKeys = line.length();
           //한 줄에 입력된 문자의 개수 저장.
        } while (nKeys == 0) ;
        //buffer에 들어온 값이 1도 없을 경우, 반복.
        ch = line.charAt(0);
        nKeys--;
        return ch;
    }

    public static void main(String[] args) throws Exception {
        int top = 0;
        int left = iScreenDw + iScreenDx/2 -2;
        //??
        int[][] arrayScreen = createArrayScreen(iScreenDy, iScreenDx,iScreenDw);
        char key;
        //입력받은 키

        Matrix iScreen = new Matrix(arrayScreen);
       //테트리스 배경 및 테두리(변하지 않음)
        Matrix currBlk = new Matrix(arrayBlk);
        Matrix tempBlk = iScreen.clip(top, left, top+currBlk.get_dy(), left+currBlk.get_dx());
        tempBlk = tempBlk.add(currBlk);
        Matrix oScreen = new Matrix(iScreen);
        oScreen.paste(tempBlk, top, left);
        printScreen(oScreen); System.out.println();

        while ((key = getKey()) != 'q'){
            //q를 입력하면 게임 종료
            switch (key) {
                case 'a':
                    left--;
                    break;
                case 'd':
                    left++;
                    break;
                case 's':
                    top++;
                    break;
                case 'w':
                    top--;
                    break;
                default:
                    System.out.println("UNKNOWN KEY!!!");
            }
            tempBlk = iScreen.clip(top, left, top + currBlk.get_dy(), left + currBlk.get_dx());
            //임시 공간의 위치& 크기 정하기
            tempBlk=tempBlk.add(currBlk);
            //충돌여부 판단
            if(tempBlk.anyGreaterThan(1)){
                switch (key) {
                    //원상복구
                    case 'a':
                        left++;
                        break;
                    case 'd':
                        left--;
                        break;
                    case 's':
                        top--;
                        break;
                    case 'w':
                        top++;
                        break;
                }
                tempBlk = iScreen.clip(top, left, top + currBlk.get_dy(), left + currBlk.get_dx());
                tempBlk=tempBlk.add(currBlk);
                //충돌하지 않는 위치에 다시 clip() & add()
            }
            oScreen = new Matrix(iScreen);
            oScreen.paste(tempBlk, top, left);
            printScreen(oScreen); System.out.println();
            }
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