public class Nested {



private int dy;
private static int dx;
public int get_dy(){ return dy; }
public static int get_dx(){ return dx; }
public Nested(int cy,int cx){ dx=cx; dy=cy; }
public class InnerD {
    public int get_dy() { return dy; }
    public int sum() { return dy+dx; }
    }
    public static class InnerS { public int get_dx() {return dx; } }
}







