import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import ru.netology.Ticket.AviaSouls;
import ru.netology.Ticket.Ticket;
import ru.netology.Ticket.TicketTimeComparator;

import java.util.Comparator;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class AviaSoulsTest {

    AviaSouls manager = new AviaSouls();
    Ticket ticket1 = new Ticket("City1", "City2", 5_000, 3, 5); // 2 часа
    Ticket ticket2 = new Ticket("City2", "City3", 7_000, 6, 9); // 3 часа
    Ticket ticket3 = new Ticket("City1", "City3", 10_000, 14, 17); // 3 часа
    Ticket ticket4 = new Ticket("City1", "City2", 6_000, 18, 22); // 4 часа
    Ticket ticket5 = new Ticket("City3", "City1", 10_000, 19, 22); // 3 часа
    Ticket ticket6 = new Ticket("City1", "City2", 15_000, 17, 22); // 5 часов
    Ticket ticket7 = new Ticket("City3", "City2", 8_000, 10, 12); // 2 часа
    Ticket ticket8 = new Ticket("City1", "City2", 4_000, 1, 2); // 1 час
    Ticket ticket9 = new Ticket("City4", "City5", 3_000, 23, 3); // 4 часа

    @BeforeEach
    public void setup() {
        manager.add(ticket1);
        manager.add(ticket2);
        manager.add(ticket3);
        manager.add(ticket4);
        manager.add(ticket5);
        manager.add(ticket6);
        manager.add(ticket7);
        manager.add(ticket8);
        manager.add(ticket9);
    }

    @Test
    public void test1() { // сравнивает цены билетов (меньше)
        System.out.println(ticket1.compareTo(ticket2));

        int expected = -1;
        int actual = ticket1.compareTo(ticket2);

        assertEquals(expected, actual);
    }

    @Test
    public void test2() { // сравнивает цены билетов (больше)
        System.out.println(ticket3.compareTo(ticket2));

        int expected = 1;
        int actual = ticket3.compareTo(ticket2);

        assertEquals(expected, actual);
    }

    @Test
    public void test3() { // сравнивает цены билетов (равно)
        System.out.println(ticket3.compareTo(ticket5));

        int expected = 0;
        int actual = ticket3.compareTo(ticket5);

        assertEquals(expected, actual);
    }

    @Test
    public void test4() {  // сортирует билеты по цене
        Ticket[] expected = {ticket8, ticket1, ticket4, ticket6};
        Ticket[] actual = manager.search("City1", "City2");

        Assertions.assertArrayEquals(expected, actual);
    }

    @Test
    public void test5() {  // ищет подходящий билет
        Ticket[] expected = {ticket7};
        Ticket[] actual = manager.search("City3", "City2");

        Assertions.assertArrayEquals(expected, actual);
    }

    @Test
    public void test6() {  // подходящих билетов не найдено
        Ticket[] expected = {};
        Ticket[] actual = manager.search("City1", "City4");

        Assertions.assertArrayEquals(expected, actual);
    }

    @Test
    public void test7() { // сравнивает билеты по времени (меньше)
        Comparator<Ticket> comparator = new TicketTimeComparator();

        assertEquals(-1, comparator.compare(ticket1, ticket2));
    }

    @Test
    public void test8() { // сравнивает билеты по времени (больше)
        Comparator<Ticket> comparator = new TicketTimeComparator();

        assertEquals(1, comparator.compare(ticket6, ticket9));
    }

    @Test
    public void test9() { // сравнивает билеты по времени (равно)
        Comparator<Ticket> comparator = new TicketTimeComparator();

        assertEquals(0, comparator.compare(ticket3, ticket5));
    }

    @Test
    public void test10() {
        Comparator<Ticket> comparator = new TicketTimeComparator();
        Ticket[] expected = {ticket8, ticket1, ticket4, ticket6};
        Ticket[] actual = manager.searchAndSortBy("City1", "City2", comparator);
    }

}
