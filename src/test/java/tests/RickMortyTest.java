package tests;

import io.restassured.path.json.JsonPath;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import webhooks.WebHook;

import java.util.List;

import static steps.RequestSteps.getRequest;

public class RickMortyTest extends WebHook {
    @DisplayName("Сравнение персонажей")
    @Test
    public void compareCharacters(){
        // Расса и местположение первого персонажа для сравнения
        // Получение ссылки на последний эпизод
        JsonPath char1 = getRequest("https://rickandmortyapi.com/api/character/2", 200).jsonPath();
        List<String> episodesChar1 = char1.getList("episode");
        String lastEpisodeChar1Link = episodesChar1.get(episodesChar1.size() - 1);
        String speciesChar1 = char1.getString("species");
        String locationChar1 = char1.getString("location.name");

        //Поиск последнего песронажа в эпизоде
        JsonPath episode = getRequest(lastEpisodeChar1Link, 200).jsonPath();
        List<String> charsInEpisode = episode.getList("characters");
        String lastCharLink = charsInEpisode.get(charsInEpisode.size() - 1);

        //Расса и местположение второго персонажа для сравнения
        JsonPath char2 = getRequest(lastCharLink, 200).jsonPath();
        String speciesChar2 = char2.getString("species");
        String locationChar2 = char2.getString("location.name");

        Assertions.assertEquals(speciesChar1, speciesChar2);
        Assertions.assertNotEquals(locationChar1, locationChar2);
    }
}
