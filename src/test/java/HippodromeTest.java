import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;
import org.mockito.Mock;
import org.mockito.MockedStatic;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.times;

@ExtendWith(MockitoExtension.class)

public class HippodromeTest {
    ArrayList<Horse> horses = new ArrayList<>();
    Hippodrome hippodrome;
    @BeforeEach
    public void init(){
        horses.add(new Horse("Tutura",25,100));
        horses.add(new Horse("Bony",30,500));
        horses.add(new Horse("Ivan",15,75));
        hippodrome  = new Hippodrome(horses);
    }
    @Test
    public void HippodromeConstrTest() {
        assertAll("Тест конструктора",
                () -> assertThrows(IllegalArgumentException.class, ()
                        -> new Hippodrome(null)),
                () -> {
                    Exception exception = assertThrows(IllegalArgumentException.class, ()
                            -> new Hippodrome(null));
                    assertEquals("Horses cannot be null.", exception.getMessage());
                },
                () -> assertThrows(IllegalArgumentException.class, ()
                        -> new Hippodrome(new ArrayList<>())),
                () -> {
                    Exception exception = assertThrows(IllegalArgumentException.class, ()
                            -> new Hippodrome(new ArrayList<>()));
                    assertEquals("Horses cannot be empty.", exception.getMessage());
                }
        );
    }

    @Test
    public void getHorsesTest(){
        assertEquals(horses,hippodrome.getHorses());
    }

    @Test
    public void getWinnerTest(){
        assertEquals(500,hippodrome.getWinner().getDistance());
    }
    @Test
    public void moveTest(){
        ArrayList<Horse> mockHorse = new ArrayList<>();
        int count = 0;
        for (int i = 0; i < 50; i++) {
            mockHorse.add(Mockito.spy(new Horse(Integer.toString(i),50+i)));
        }
        Hippodrome hippodrome1 = new Hippodrome(mockHorse);
        hippodrome1.move();
        for (Horse horse:mockHorse) {
            Mockito.verify(horse).move();
            count += 1;

        }
        assertEquals(50,count);
    }
}
