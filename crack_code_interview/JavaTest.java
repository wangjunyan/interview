import java.lang.reflect.*;

public class JavaTest {
    public JavaTest() {}

    public static void main(String[] args) {
        try {
            Class c = Class.forName("java.sql.Connection");
            Method m[] = c.getDeclaredMethods();
            for(int i = 0; i < 5; i++) {
                System.out.println(m[i].toString());
            }
        } catch (Throwable e){
            System.err.println(e);
        }

    }
}
