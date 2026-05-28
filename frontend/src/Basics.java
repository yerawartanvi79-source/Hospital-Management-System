public class Basics {
    int x = 10;

    public class Abc extends Basics {
        int Y = x + 10;
    }

    public static void main(String[] args) {
        Basics outer = new Basics();
        Basics.Abc inner = outer.new Abc();
        System.out.print(inner.Y);
    }
}