import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Random;

public class Main {
    private static int iScreenDy = 15;
    private static int iScreenDx = 10;
    private static int iScreenDw = 4;
    //왜 3이 아닐까??
    //짝대기는 4*4 배열, 이것까지 커버해야한다.
    private static Random random = new Random();
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
    //for "key input" methods
    private static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    private static String line = null;
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
    private static Matrix setOfBlockObjects (int blockType, int blockDegree) throws MatrixException {
        int idxBlockType = blockType;
        int idxBlockDegree = blockDegree%4;
        int[][] temp_array;
        Matrix temp;
        switch (idxBlockType){
            case 0:
                temp_array = new int[][]{
                        {1, 0, 0},
                        {1, 0, 0},
                        {1, 1, 0}
                };
                switch (idxBlockDegree) {
                    case 0:
                        temp_array = new int[][] {
                                {1, 0, 0},
                                {1, 0, 0},
                                {1, 1, 0}
                        }; break;
                    case 1:
                        temp_array = new int[][] {
                                {0, 0, 1},
                                {1, 1, 1},
                                {0, 0, 0},
                        }; break;
                    case 2:
                        temp_array = new int[][] {
                                {0, 1, 1},
                                {0, 0, 1},
                                {0, 0, 1}
                        }; break;
                    case 3:
                        temp_array = new int[][] {
                                {1, 1, 1},
                                {1, 0, 0},
                                {0, 0, 0}
                        }; break;
                }
                temp = new Matrix(temp_array);
                return temp;
            case 1:
                temp_array = new int[][]{
                        {0, 0, 1},
                        {0, 0, 1},
                        {0, 1, 1}
                };
                switch (idxBlockDegree) {
                    case 0:
                        temp_array = new int[][] {
                                {0, 0, 1},
                                {0, 0, 1},
                                {0, 1, 1}
                        }; break;
                    case 1:
                        temp_array = new int[][] {
                                {1, 0, 0},
                                {1, 1, 1},
                                {0, 0, 0},
                        }; break;
                    case 2:
                        temp_array = new int[][] {
                                {0, 1, 1},
                                {0, 1, 0},
                                {0, 1, 0}
                        }; break;
                    case 3:
                        temp_array = new int[][] {
                                {1, 1, 1},
                                {0, 0, 1},
                                {0, 0, 0}
                        }; break;
                }
                temp = new Matrix(temp_array);
                return temp;
            case  2:
            temp_array = new int[][]{
                    {0, 1, 0},
                    {1, 1, 1},
                    {0, 0, 0},
            };
                switch (idxBlockDegree) {
                    case 0:
                        temp_array = new int[][] {
                                {0, 1, 0},
                                {1, 1, 1},
                                {0, 0, 0}
                        }; break;
                    case 1:
                        temp_array = new int[][] {
                                {0, 1, 0},
                                {0, 1, 1},
                                {0, 1, 0}
                        }; break;
                    case 2:
                        temp_array = new int[][] {
                                {1, 1, 1},
                                {0, 1, 0},
                                {0, 0, 0}
                        }; break;
                    case 3:
                        temp_array = new int[][] {
                                {0, 0, 1},
                                {0, 1, 1},
                                {0, 0, 1}
                        }; break;
                }
            temp = new Matrix(temp_array);
            return temp;
            case 3:
            temp_array = new int[][]{
                    {0, 1, 1},
                    {1, 1, 0},
                    {0, 0, 0},
            };
            if(idxBlockDegree%2==0){
                temp_array = new int[][]{
                        {0, 1, 1},
                        {1, 1, 0},
                        {0, 0, 0},
                };
            }else{
                temp_array = new int[][]{
                        {1, 0, 0},
                        {1, 1, 0},
                        {0, 1, 0},
                };
            }
            temp = new Matrix(temp_array);
            return temp;
            case 4:
                temp_array = new int[][]{
                        {1, 1, 0},
                        {0, 1, 1},
                        {0, 0, 0},
                };
                if(idxBlockDegree%2==0){
                    temp_array = new int[][]{
                            {1, 1, 0},
                            {0, 1, 1},
                            {0, 0, 0},
                    };
                }else{
                    temp_array = new int[][]{
                            {0, 0, 1},
                            {0, 1, 1},
                            {0, 1, 0},
                    };
                }
                temp = new Matrix(temp_array);
                return temp;
            case 5:
                temp_array = new int[][]{
                        {0, 0, 1, 0},
                        {0, 0, 1, 0},
                        {0, 0, 1, 0},
                        {0, 0, 1, 0},
                };
                if(idxBlockDegree%2==0){
                    temp_array = new int[][]{
                            {0, 0, 1, 0},
                            {0, 0, 1, 0},
                            {0, 0, 1, 0},
                            {0, 0, 1, 0},
                    };
                }else{
                    temp_array = new int[][]{
                            {1, 1, 1, 1},
                            {0, 0, 0, 0},
                            {0, 0, 0, 0},
                            {0, 0, 0, 0},
                    };
                }
                temp = new Matrix(temp_array);
                return temp;
            case 6:
            temp_array = new int[][]{
                    {1, 1},
                    {1, 1},
            };
            temp = new Matrix(temp_array);
            return temp;
           default:
               temp_array = new int[][]{
                       {0, 0, 0},
                       {0, 0, 0},
                       {0, 0, 0},
               };
               temp = new Matrix(temp_array);
               return temp;
        }
    }
   private  static  void deleteFullLine(Matrix obj, int dw) throws MatrixException {
        Matrix aLine;
        boolean full_line = false;
        int fulled_y ;

        //search full line
        for (int y = 0; y < obj.get_dy()-dw; y++) {
            aLine = obj.clip(y, dw, y+1,obj.get_dx()-dw );
            if (aLine.sum() >= aLine.get_dx()) {
                fulled_y = y;
                for( int x = dw; x < obj.get_dx()-dw; x++)
                    obj.get_array()[fulled_y][x] = 0;

                //0부터 full line 이전까지 clip
                Matrix tmp = obj.clip(0, dw, fulled_y , obj.get_dx()-dw);
                obj.paste(tmp, 1, dw);
                //맨 윗라인 초기화
                for( int x = dw; x < obj.get_dx()-dw; x++)
                    obj.get_array()[0][x] = 0;
            }
        }
    }


    public static void main(String[] args) throws Exception {
        int first_left = iScreenDw + iScreenDx/2 - 2;
        int idxBlockDegree = 0;
        int idxBlockType = random.nextInt(7);
        int top = 0;
        int left = first_left;
        int[][] arrayScreen = createArrayScreen(iScreenDy, iScreenDx, iScreenDw);
        char key;
        boolean newBlockNeeded = false;
        boolean full_line = false;


        //테트리스의 전체 배경
        Matrix iScreen = new Matrix(arrayScreen);
        //첫 화면에 난수 블럭을 세팅하고 시작함.
        Matrix currBlk =  setOfBlockObjects(idxBlockType, idxBlockDegree);
        Matrix tempBlk = iScreen.clip(top, left, top + currBlk.get_dy(), left + currBlk.get_dx());
        tempBlk = tempBlk.add(currBlk);
        Matrix oScreen = new Matrix(iScreen);
        oScreen.paste(tempBlk, top, left);
        printScreen(oScreen);
        System.out.println();


        while ((key = getKey()) != 'q') {
            //q를 입력하면 게임 종료
            switch (key) {
                case 'a': left--; break;
                case 'd': left++; break;
                case 's': top++; break;
                case 'w': idxBlockDegree++; currBlk = setOfBlockObjects(idxBlockType, idxBlockDegree); break; //rotate
                case ' ':
                    while (!tempBlk.anyGreaterThan(1)){
                        top++;
                        tempBlk = iScreen.clip(top, left, top + currBlk.get_dy(), left + currBlk.get_dx());
                        tempBlk = tempBlk.add(currBlk);
                    }
                    top--;
                    newBlockNeeded = true;
                    break; //drop the block
                default:
                    System.out.println("UNKNOWN KEY!!!");
            }
            tempBlk = iScreen.clip(top, left, top + currBlk.get_dy(), left + currBlk.get_dx());
            //임시 공간의 위치& 크기 정하기
            tempBlk = tempBlk.add(currBlk);
            //충돌여부 판단
            if (tempBlk.anyGreaterThan(1)) {
                switch (key) {
                    //원상복구
                    case 'a': left++; break;
                    case 'd': left--; break;
                    case 's': top--; newBlockNeeded = true; break;
                    case 'w': break; //rotate
                    case ' ': newBlockNeeded = true; break;
                    //s, ' ' 는 바닥과 충돌이니까 성공적 -> 새로운 난수 블럭 생성
                }
                try {
                    tempBlk = iScreen.clip(top, left, top + currBlk.get_dy(), left + currBlk.get_dx());
                    tempBlk = tempBlk.add(currBlk);
                    //충돌하지 않는 위치에 다시 clip() & add()
                } catch (MatrixException e) {
                    e.printStackTrace();
                    System.out.println(e.getMessage());
                    System.out.println("블록이 범위를 벗어났습니다.");
                }
            }
            oScreen = new Matrix(iScreen);
            oScreen.paste(tempBlk, top, left);
            printScreen(oScreen);System.out.println();
            //full line delete
           deleteFullLine(oScreen, iScreenDw);
            if(newBlockNeeded) {
                //바닥충돌 경우
                //위치 재설정
                iScreen = new Matrix(oScreen);
                top = 0; left = first_left; idxBlockDegree = 0; idxBlockType = random.nextInt(7);
                //currentBlk 재설정(난수)
                currBlk = setOfBlockObjects(idxBlockType, idxBlockDegree);
                tempBlk = iScreen.clip(top, left, top + currBlk.get_dy(), left + currBlk.get_dx());
                tempBlk = tempBlk.add(currBlk);
                if ( tempBlk.anyGreaterThan(1)) {
                    System.out.println("게임 오버");
                    System.exit(0);
                    //현재 실행 프로세스 강제 종료
                    //정상종료: 0, 비정상 종료: 0이외의 숫자

                }
                oScreen = new Matrix(iScreen);
                oScreen.paste(tempBlk, top, left);

                printScreen(oScreen);
                newBlockNeeded = false;

            }
        }
    }
}