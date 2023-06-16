package pro.sky.Collections.Service;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;
import org.springframework.boot.test.context.SpringBootTest;
import pro.sky.Collections.Exception.EmployeeAlreadyAddedException;
import pro.sky.Collections.Exception.EmployeeInvalidNameException;
import pro.sky.Collections.Exception.EmployeeNotFoundException;

import java.util.stream.Stream;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class EmployeeServiceImplTest {
    EmployeeServiceImpl out = new EmployeeServiceImpl();

    @ParameterizedTest
    @MethodSource("paramsForNameTest")
    void correctFullName(String firstName, String lastName, String result) {//проверка на добавление с правильными именами
        assertEquals(result, out.addEmployee(firstName, lastName, 1, 40000));

    }

    public static Stream<Arguments> paramsForNameTest() {
        return Stream.of(
                Arguments.of("Иван", "Иванов", "Иван Иванов"),
                Arguments.of("василий", "репин", "Василий Репин"),
                Arguments.of("вАсИлий", "ивАноВ", "Василий Иванов")
        );
    }

    @ParameterizedTest
    @MethodSource("paramsForInvalidNameTest")
    void invalidNameTest(String firstName, String lastName) {//проверка на ввод неверных значений имени
        assertThrows(EmployeeInvalidNameException.class, () -> out.addEmployee(firstName, lastName, 2, 30000));
    }

    public static Stream<Arguments> paramsForInvalidNameTest() {
        return Stream.of(
                Arguments.of("Иван1", "Иванов"),
                Arguments.of("василий", "репин1"),
                Arguments.of("вАсИлий!", "ивАноВ"),
                Arguments.of("", "ивАноВ"),
                Arguments.of("Иван", ""),
                Arguments.of("   ", "ивАноВ")
        );
    }


    @Test
    void addSameEmployee() {//проверка на повторное добавление сотрудника
        out.addEmployee("Иван", "Антонов", 2, 30000);
        assertThrows(EmployeeAlreadyAddedException.class, () -> out.addEmployee("Иван", "Антонов", 2, 30000));
    }
    @Test
    void addEmployee() {//проверка добавление сотрудника
        out.addEmployee("Иван", "Крылов", 2, 30000);
    }
    @Test
    void deleteNonExistentEmployee() {//проверка на удаление сотрудника которого нет
        assertThrows(EmployeeNotFoundException.class, () -> out.deleteEmployee("Иван", "Носов"));

    }
    @Test
    void deleteEmployee() {//проверка добавление сотрудника
        out.addEmployee("Иван", "Мочкин", 2, 30000);
        out.deleteEmployee("Иван", "Мочкин");
    }
    @Test
    void findEmployee() {//проверка поиска сотрудника
        out.addEmployee("Иван", "Восницов", 2, 30000);
        out.findEmployee("Иван", "Восницов");
    }
    @Test
    void findNonExistentEmployee() {//проверка на поиск сотрудника которого нет
        assertThrows(EmployeeNotFoundException.class, () -> out.deleteEmployee("Иван", "Носов"));
    }

}