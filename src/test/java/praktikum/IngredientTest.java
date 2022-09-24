package praktikum;

import com.github.javafaker.Faker;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;

@RunWith(Parameterized.class)
public class IngredientTest {
    private final IngredientType ingredientType;
    private final String ingredientName;
    private final float ingredientPrice;
    private Ingredient ingredient;
    private static final Faker faker = new Faker();

    public IngredientTest(IngredientType ingredientType, String ingredientName, float ingredientPrice) {
        this.ingredientType = ingredientType;
        this.ingredientName = ingredientName;
        this.ingredientPrice = ingredientPrice;
    }

    @Parameterized.Parameters(name = "test data: {0} {1} {2}")
    public static Object[][] getData() {
        return new Object[][] {
                {IngredientType.SAUCE, faker.food().ingredient(), faker.number().numberBetween(100, 1000)},
                {IngredientType.FILLING, faker.food().ingredient(), faker.number().numberBetween(100, 1000)}
        };
    }

    @Before
    public void setUp() {
        ingredient = new Ingredient(ingredientType, ingredientName, ingredientPrice);
    }

    @Test
    public void getPriceTest() {
        Assert.assertEquals("Price doesn't match the expected value", ingredientPrice, ingredient.getPrice(), 0.0);
    }

    @Test
    public void getNameTest() {
        Assert.assertEquals("Name doesn't match the expected value", ingredientName, ingredient.getName());
    }

    @Test
    public void getTypeTest() {
        Assert.assertEquals("Type doesn't match the expected value", ingredientType, ingredient.getType());
    }
}