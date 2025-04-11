package lab7;

import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

class MainTest {

    @Test
    void testGetQuickRecipesReturnsEmptyListIfNoData() {
        var recipes = Main.getQuickRecipes(List::of);
        assertEquals(0, recipes.size());
    }

    @Test
    void testGetQuickRecipesReturnsEmptyListIfNoQuickRecipes() {
        var recipes = Main.getQuickRecipes(() -> List.of(
                new Recipe(0, "", "", "", 4, 10, 10, 16),
                new Recipe(1, "", "", "", 4, 10, 10, 20),
                new Recipe(2, "", "", "", 4, 10, 10, 200)
        ));
        assertEquals(0, recipes.size());
    }

    @Test
    void testGetQuickRecipesReturnsAllRecipesIfAllQuick() {
        var recipes = Main.getQuickRecipes(() -> List.of(
                new Recipe(0, "", "", "", 4, 10, 10, 15),
                new Recipe(1, "", "", "", 4, 10, 10, 1),
                new Recipe(2, "", "", "", 4, 10, 10, 10)
        ));

        assertEquals(3, recipes.size());
    }

    @Test
    void testGetQuickRecipesWorksOnTypicalData() {
        var recipes = Main.getQuickRecipes(() -> List.of(
                new Recipe(0, "", "", "", 4, 10, 10, 10),
                new Recipe(1, "", "", "", 4, 10, 10, 15),
                new Recipe(2, "", "", "", 4, 10, 10, 16),
                new Recipe(3, "", "", "", 4, 10, 10, 20),
                new Recipe(4, "", "", "", 4, 10, 10, 2343)
        ));

        assertEquals(2, recipes.size());

        // Verify that the two recipes we expected are in fact in the list
        assertEquals(0, recipes.get(0).id());
        assertEquals(1, recipes.get(1).id());
    }

    // Tests for the searchRecipes method

    @Test
    void testSearchRecipes_MatchByName() {
        DataService mockService = () -> List.of(
                new Recipe(1, "Chicken Soup", "Tasty chicken broth", "", 2, 10, 20, 30),
                new Recipe(2, "Beef Stew", "Rich and hearty", "", 4, 15, 45, 60)
        );

        List<Recipe> result = Main.searchRecipes("chicken", mockService);
        assertEquals(1, result.size());
        assertEquals("Chicken Soup", result.get(0).name());
    }

    @Test
    void testSearchRecipes_MatchByDescription() {
        DataService mockService = () -> List.of(
                new Recipe(1, "Veggie Mix", "Contains fresh chicken and herbs", "", 2, 10, 10, 20),
                new Recipe(2, "Fruit Salad", "Cool and sweet", "", 2, 5, 0, 5)
        );

        List<Recipe> result = Main.searchRecipes("chicken", mockService);
        assertEquals(1, result.size());
        assertEquals("Veggie Mix", result.get(0).name());
    }

    @Test
    void testSearchRecipes_NoMatch() {
        DataService mockService = () -> List.of(
                new Recipe(1, "Pasta", "With cheese", "", 2, 10, 10, 20)
        );

        List<Recipe> result = Main.searchRecipes("chicken", mockService);
        assertTrue(result.isEmpty());
    }

    @Test
    void testSearchRecipes_CaseInsensitive() {
        DataService mockService = () -> List.of(
                new Recipe(1, "CHICKEN Curry", "Spicy and tasty", "", 2, 10, 15, 25)
        );

        List<Recipe> result = Main.searchRecipes("chicken", mockService);
        assertEquals(1, result.size());
        assertEquals("CHICKEN Curry", result.get(0).name());
    }
}
