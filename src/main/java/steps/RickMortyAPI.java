package steps;

import chars.Character;
import io.cucumber.java.ru.Если;
import io.cucumber.java.ru.И;
import io.cucumber.java.ru.Тогда;
import io.restassured.path.json.JsonPath;
import org.junit.jupiter.api.Assertions;
import java.util.List;

import static java.lang.System.getProperty;
import static requests.RequestSteps.getRequest;

public class RickMortyAPI {
    private Character char1 = new Character();
    private Character char2 = new Character();
    private JsonPath char1Json;
    private JsonPath char2Json;
    String lastEpisodeChar1Link;

    @Если("получить и сохранить информацию о расе и местоположении Morty Smith")
    public void saveChar() {
        char1Json = getRequest(getProperty("morty.smith.url"), 200).jsonPath();
        char1.setSpecies(char1Json.getString("species"));
        char1.setLocation(char1Json.getString("location.name"));
    }

    @И("получить последний эпизод где появлялся Morty")
    public void lastEpisode() {
        List<String> episodesChar1 = char1Json.getList("episode");
        lastEpisodeChar1Link = episodesChar1.get(episodesChar1.size() - 1);
    }

    @И("в эпизоде найти последнего появлявшегося персонажа")
    public void searchChar2() {
        JsonPath episode = getRequest(lastEpisodeChar1Link, 200).jsonPath();
        List<String> charsInEpisode = episode.getList("characters");
        char2.setLink(charsInEpisode.get(charsInEpisode.size() - 1));
    }

    @И("сохранить информацию о его расе и местоположении")
    public void saveChar2() {
        char2Json = getRequest(char2.getLink(), 200).jsonPath();
        char2.setSpecies(char2Json.getString("species"));
        char2.setLocation(char2Json.getString("location.name"));
    }

    @Тогда("проверить совпадение расы персонажей")
    public void compareSpecies() {
        Assertions.assertEquals(char1.getSpecies(), char2.getSpecies());
    }

    @И("проверить несовпадение локаций персонажей")
    public void compareLocation() {
        Assertions.assertNotEquals(char1.getLocation(), char2.getLocation());
    }
}
