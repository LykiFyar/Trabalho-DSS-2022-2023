import java.util.HashSet;

public class Main {
    public static void main(String[] args) {
        String x = "Aa", y = "BB";
        System.out.println("\"" + x + "\":" + x.hashCode());
        System.out.println("\"" + y + "\":" + y.hashCode());
        HashSet<String> set = new HashSet<String>();
        set.add(x);
        set.add(y);
        for (String s : set) {
            System.out.println(s);
        }
    }
}
