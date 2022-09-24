package praktikum;

import com.github.javafaker.Faker;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

public class BunTest {
    private String bunName;
    private float bunPrice;
    private Bun bun;
    private final Faker faker = new Faker();

    @Before
    public void setUp() {
        bunName = faker.color().name() + " bun";
        bunPrice = faker.number().numberBetween(50, 500);
        bun = new Bun(bunName, bunPrice);
    }

    @Test
    public void getNameTest() {
        Assert.assertEquals("Name doesn't match the expected value", bunName, bun.getName());
    }

    @Test
    public void getPriceTest() {
        Assert.assertEquals("Price doesn't match the expected value", bunPrice, bun.getPrice(), 0.0);
    }

}