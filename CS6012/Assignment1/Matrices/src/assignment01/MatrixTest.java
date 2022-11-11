package assignment01;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class MatrixTest {
    int[][] data0 = {{1, 1}, {1, 1}};
    int[][] data1 = {{1, 2}, {3, 4}};
    int[][] data2 = {{1, 2}, {3, 4}, {5, 6}};
    int[][] data3 = {{4, 6}, {4, 6}};
    int[][] data4 = {{1, 2, 3}, {4, 5, 6}};
    int[][] data5 = {{0, 0}, {0, 0}};
    int[][] data6 = {{-1, -1}, {-1, -1}};
    int[][] data7 = {{1, 0}, {0, 1}};
    int[][] data8 = {{1, 0}, {0, 1}, {0, 0}};
    Matrix matrix0;
    Matrix matrix1;
    Matrix matrix2;
    Matrix matrix3;
    Matrix matrix4;
    Matrix matrix5;
    Matrix matrix6;
    Matrix matrix7;
    Matrix matrix8;

    @BeforeEach
    void setUp() {
        matrix0 = new Matrix(data0);
        matrix1 = new Matrix(data1);
        matrix2 = new Matrix(data2);
        matrix3 = new Matrix(data3);
        matrix4 = new Matrix(data4);
        matrix5 = new Matrix(data5);
        matrix6 = new Matrix(data6);
        matrix7 = new Matrix(data7);
        matrix8 = new Matrix(data8);
    }

    @AfterEach
    void tearDown() {
        matrix0 = null;
        matrix1 = null;
        matrix2 = null;
        matrix3 = null;
        matrix4 = null;
        matrix5 = null;
    }

    @Test
    void testEquals() {
        // don't simplify assertion - want to test the overridden equals method
        assertEquals(false, matrix0.equals(matrix1));
        assertEquals(false, matrix1.equals(matrix2));
        int[][] data = {{1, 2}, {3, 4}, {5, 6}}; // has the same data as matrix2
        Matrix matrix = new Matrix(data); // put into setup()
        assertEquals(true, matrix2.equals(matrix));
        assertEquals(true, matrix0.plus(matrix6).equals(matrix5)); // test both equals() and plus()
        assertEquals(false, matrix7.equals(matrix8));
    }

    @Test
    void testToString() {
        assertEquals("1 2 \n3 4 \n", matrix1.toString());
        assertEquals("1 2 \n3 4 \n5 6 \n", matrix2.toString());
        assertEquals("0 0 \n0 0 \n", matrix5.toString());
        assertEquals(matrix5.toString(), matrix0.plus(matrix6).toString()); // test both toString() and plus()
        assertEquals(matrix5.toString(), matrix5.times(matrix5).toString()); // test both toString() and times()
    }

    @Test
    void times() {
        assertEquals(null, matrix0.times(matrix2));
        assertEquals(matrix3, matrix0.times(matrix1));
        int[][] data = {{9, 12, 15}, {19, 26, 33}, {29, 40, 51}}; // matrix2 * matrix4
        Matrix matrix = new Matrix(data);
        assertEquals(matrix, matrix2.times(matrix4));
        assertEquals(matrix5, matrix5.times(matrix5)); // {{0, 0}, {0, 0}}
        assertEquals(matrix5, matrix5.times(matrix6)); // {{0, 0}, {0, 0}}
        assertEquals(null, matrix7.times(matrix8)); // null
    }

    @Test
    void plus() {
        int[][] data = {{2, 3}, {4, 5}};
        Matrix matrix = new Matrix(data);
        assertEquals(matrix, matrix0.plus(matrix1));
        assertEquals(matrix0, matrix0.plus(matrix5));
        assertEquals(null, matrix0.plus(matrix2));
        assertEquals(null, matrix2.plus(matrix4));
        assertEquals(matrix5, matrix5.plus(matrix5)); // {{0, 0}, {0, 0}}
        assertEquals(matrix5, matrix5.times(matrix5)); // test both plus() and times()
    }
}