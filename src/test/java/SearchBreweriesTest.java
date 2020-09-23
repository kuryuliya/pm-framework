import org.assertj.core.api.SoftAssertions;
import org.testng.annotations.Test;

import java.util.Arrays;

import static io.restassured.RestAssured.when;
import static org.hamcrest.MatcherAssert.assertThat;

public class SearchBreweriesTest extends BaseTest {
    private static final String SEARCH_REQUEST = "/breweries/search?query=";
    private final String param = "dog";

    /**
     * Positive
     */
    @Test
    public void when_performSearch_expect_statusCode200() {
        when()
                .get(SEARCH_REQUEST + param)
                .then()
                .log()
                .ifValidationFails()
                .statusCode(200);
    }

    @Test
    public void when_performSearchByValidWord_expect_responseListIsNotEmpty() {
        var result =
                when()
                        .get(SEARCH_REQUEST + param)
                        .then()
                        .extract()
                        .as(Object[].class);

        assertThat("The response body is empty", result.length > 0);
    }

    @Test
    public void when_performSearchByValidWord_expect_wordIsPresentInEachElementOfResult() {
        var result =
                when()
                        .get(SEARCH_REQUEST + param)
                        .then()
                        .log()
                        .body()
                        .extract()
                        .as(Object[].class);

        SoftAssertions.assertSoftly(s ->
                Arrays.stream(result)
                        .forEach(el ->
                                s.assertThat(el).as("One element of the result").asString().contains(param)
                        ));
    }
    /**
     * Negative
     */
    @Test
    public void when_performSearchWithEmptyParameter_expect_statusCode200AndEmptyResult() {
        var result = when()
                .get(SEARCH_REQUEST + "")
                .then()
                .assertThat()
                .log()
                .ifValidationFails()
                .statusCode(200)
                .and()
                .extract()
                .as(Object[].class);

        assertThat("The response should be empty", result.length == 0);
    }

    @Test
    public void when_performSearchWithChineseCharacters_expect_statusCode200AndEmptyResult() {
        var result = when()
                .get(SEARCH_REQUEST + "你好")
                .then()
                .assertThat()
                .log()
                .body()
                .statusCode(200)
                .and()
                .extract()
                .as(Object[].class);

        assertThat("The response should be empty", result.length == 0);
    }
}
