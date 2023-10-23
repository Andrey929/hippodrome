import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.*;
import org.mockito.MockedStatic;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.junit.jupiter.api.Assertions.*;

@ExtendWith(MockitoExtension.class)
public class HorseTest {
    Horse horse = new Horse("Test",50,1000);

    @ParameterizedTest
    @CsvSource(
{"'',4,5",
"' ',6,7",
"'    ',2,7"})
    public void HorseConstrTest(String name, double speed, double distance) {
        assertAll("Тест конструктора",
                () -> assertThrows(IllegalArgumentException.class, ()
                        -> new Horse(null, 4)),
                () -> {
                    Exception exception = assertThrows(IllegalArgumentException.class, ()
                            -> new Horse(null, 4));
                    assertEquals("Name cannot be null.", exception.getMessage());
                },
                () -> assertThrows(IllegalArgumentException.class, ()
                        -> new Horse(name, speed,distance)),
                () -> {
                    Exception exception = assertThrows(IllegalArgumentException.class, ()
                            -> new Horse(name, speed,distance));
                    assertEquals("Name cannot be blank.", exception.getMessage());},
                () -> assertThrows(IllegalArgumentException.class, ()
                -> new Horse("Test",-1,4)),
                () -> {
                    Exception exception = assertThrows(IllegalArgumentException.class, ()
                            -> new Horse("Test",-1,4));
                    assertEquals("Speed cannot be negative.", exception.getMessage());},
                () -> assertThrows(IllegalArgumentException.class, ()
                -> new Horse("Test",1,-4)),
                () -> {
                    Exception exception = assertThrows(IllegalArgumentException.class, ()
                            -> new Horse("Test",1,-4));
                    assertEquals("Distance cannot be negative.", exception.getMessage());}
        );
    }
    @Test
    public void getNameTest(){
        assertEquals("Test",horse.getName());
    }
    @Test
    public void  getSpeedTest(){
        assertEquals(50,horse.getSpeed());
    }
    @Test
    public void getDistance(){
        assertAll("Тест дистанции",
                ()-> assertEquals(1000,horse.getDistance()),
                ()-> {
                    Horse horse1 = new Horse("Test2",45);
                    assertEquals(0,horse1.getDistance());
                });
    }

    @ParameterizedTest
    @CsvSource({
            "0.2",
            "0.4",
            "0.7,"
    })
    public void moveTest(double test){
        assertAll(
                ()->{
            try (MockedStatic<Horse> mockedStatic = Mockito.mockStatic(Horse.class)){
                horse.move();
                mockedStatic.verify(() -> Horse.getRandomDouble(0.2,0.9));
            }
        },
                () ->{
                    try (MockedStatic<Horse> mockedStatic = Mockito.mockStatic(Horse.class)){
                        mockedStatic.when(()-> Horse.getRandomDouble(0.2,0.9)).thenReturn(test);
                        double resTest = horse.getDistance() + horse.getSpeed() * Horse.getRandomDouble(0.2,0.9);
                        horse.move();
                        assertEquals(resTest,horse.getDistance());

                    }

                    });

    }


}
