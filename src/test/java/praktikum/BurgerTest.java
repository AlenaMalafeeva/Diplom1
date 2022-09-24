package praktikum;

import com.github.javafaker.Faker;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.MockitoJUnitRunner;

import java.util.List;

@RunWith(MockitoJUnitRunner.class)
public class BurgerTest {
    private final Faker faker = new Faker();
    private Burger burger;

    @Mock
    Bun bun;
    @Mock
    Ingredient firstIngredient;
    @Mock
    Ingredient secondIngredient;


    @Before
    public void setUp() {
        burger = new Burger();
    }

    @Test
    public void setBunsTest() {
        burger.setBuns(bun);
        Assert.assertEquals("the bun doesn't match the expected value", bun, burger.bun);
    }

    @Test
    public void addIngredientTest() {
        burger.addIngredient(firstIngredient);
        Assert.assertEquals("the ingredient is not added to the list", firstIngredient, burger.ingredients.get(0));
    }

    @Test
    public void removeIngredientTest() {
        List<Ingredient> expectedList = List.of(firstIngredient);

        burger.addIngredient(firstIngredient);
        burger.addIngredient(secondIngredient);

        burger.removeIngredient(1);
        Assert.assertEquals("the ingredient has not been removed from the list", expectedList, burger.ingredients);
    }

    @Test
    public void moveIngredientTest() {
        List<Ingredient> expectedList = List.of(firstIngredient, secondIngredient);

        burger.addIngredient(secondIngredient);
        burger.addIngredient(firstIngredient);

        burger.moveIngredient(0, 1);
        Assert.assertEquals("the list of ingredients does not match the expected", expectedList, burger.ingredients);
    }

    @Test
    public void getPriceTest() {
        float bunPrice = faker.number().numberBetween(100, 500);
        float firstIngredientPrice = faker.number().numberBetween(100, 500);
        float secondIngredientPrice = faker.number().numberBetween(100, 500);

        burger.setBuns(bun);
        Mockito.when(bun.getPrice()).thenReturn(bunPrice);

        burger.addIngredient(firstIngredient);
        Mockito.when(firstIngredient.getPrice()).thenReturn(firstIngredientPrice);

        burger.addIngredient(secondIngredient);
        Mockito.when(secondIngredient.getPrice()).thenReturn(secondIngredientPrice);

        float expectedPrice = 2 * bunPrice + firstIngredientPrice + secondIngredientPrice;
        Assert.assertEquals("price is incorrect", expectedPrice, burger.getPrice(), 0.0);
    }

    @Test
    public void getReceiptTest() {
        float bunPrice = faker.number().numberBetween(100, 500);
        String bunName = faker.color().name() + " bun";

        float firstIngredientPrice = faker.number().numberBetween(100, 500);
        String firstIngredientName = faker.food().ingredient();

        float secondIngredientPrice = faker.number().numberBetween(100, 500);
        String secondIngredientName = faker.food().ingredient();

        float expectedPrice = 2 * bunPrice + firstIngredientPrice + secondIngredientPrice;

        burger.setBuns(bun);
        Mockito.when(bun.getName()).thenReturn(bunName);
        Mockito.when(bun.getPrice()).thenReturn(bunPrice);

        burger.addIngredient(firstIngredient);
        Mockito.when(firstIngredient.getName()).thenReturn(firstIngredientName);
        Mockito.when(firstIngredient.getPrice()).thenReturn(firstIngredientPrice);
        Mockito.when(firstIngredient.getType()).thenReturn(IngredientType.FILLING);

        burger.addIngredient(secondIngredient);
        Mockito.when(secondIngredient.getName()).thenReturn(secondIngredientName);
        Mockito.when(secondIngredient.getPrice()).thenReturn(secondIngredientPrice);
        Mockito.when(secondIngredient.getType()).thenReturn(IngredientType.SAUCE);

        String expectedReceipt = String.format("(==== %s ====)%n" +
                "= filling %s =%n" +
                "= sauce %s =%n" +
                "(==== %s ====)%n" +
                "%nPrice: %f%n",
                bunName, firstIngredientName, secondIngredientName, bunName, expectedPrice);

        Assert.assertEquals("Receipt is incorrect", expectedReceipt, burger.getReceipt());
    }
}