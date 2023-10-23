import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.Timeout;

public class MainTest {
    @Disabled
    @Test
    @Timeout(22)
    public void mainTest(){
        String[] str = new String[4];
        try {
            Main.main(str);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
}
