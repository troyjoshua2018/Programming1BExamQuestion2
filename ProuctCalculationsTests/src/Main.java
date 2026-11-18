import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.Test;

class ProductCalculationsTest {
    @Test
    void GetVat_WithValidData_ReturnsCorrectValue() {
        assertEquals(525.0, new ProductCalculations(3500).GetVat(), 0.001);
    }
}
