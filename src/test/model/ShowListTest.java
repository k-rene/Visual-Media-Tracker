package model;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class ShowListTest {
    private ShowList testShowList;
    private Show s1 = new Show("Star Wars: Visions", 0,"Disney+", 7);
    private Show s2 = new Show("Flying Tigers", 2,"Viu", 13);

    @BeforeEach
    void runBefore() {
        testShowList = new ShowList();
    }

    @Test
    void testAddShow() {
        testShowList.addShow(s1);
        testShowList.addShow(s2);
        assertEquals(2, testShowList.length());
    }

    @Test
    void testRemoveShow() {
        testShowList.addShow(s1);
        testShowList.addShow(s2);
        testShowList.removeShow(s1);
        assertEquals(1, testShowList.length());
    }
}
