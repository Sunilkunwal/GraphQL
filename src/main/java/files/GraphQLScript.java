package files;

import static io.restassured.RestAssured.*;
import org.testng.Assert;

import io.restassured.path.json.JsonPath;

public class GraphQLScript {
	
	public static void main(String[] args) {
		//Query
		
		int characterId = 6418;
		String response = given().log().all().header("Content-Type","application/json")
		.body("{\"query\":\"query($LocationId: Int!,$CharacterId: Int!,$EpisodeId: Int!)\\n{\\n character(characterId: $CharacterId) \\n  {\\n    name\\n    gender\\n    status\\n    id\\n  }\\n  location(locationId:$LocationId)\\n  {\\n    name\\n    dimension\\n  }\\n  episode(episodeId: $EpisodeId)\\n  {\\n    name\\n    air_date\\n    episode\\n  }\\n  \\n}\",\"variables\":{\"LocationId\":7313,\"CharacterId\":"+characterId+",\"EpisodeId\":5243}}")
		.when().post("https://rahulshettyacademy.com/gq/graphql")
		.then().extract().response().asString();
		
		System.out.println(response);
		JsonPath js = new JsonPath(response);
		String characterName = js.getString("data.character.name");
		Assert.assertEquals(characterName, "AJAYJAGA");
		
		
		//Mutations
		
		String newCharacter = "JAGA";
		String mutationresponse = given().log().all().header("Content-Type","application/json")
		.body("{\"query\":\"mutation ($locationName: String!, $characterName: String!, $episodeName: String!) {\\n  createLocation(location: {name: $locationName, type: \\\"NorthZone\\\", dimension: \\\"155\\\"}) {\\n    id\\n  }\\n  createCharacter(character: {name: $characterName, type: \\\"Chu\\\", status: \\\"Alive\\\", species: \\\"Human\\\", gender: \\\"Male\\\", image: \\\"Not\\\", originId: 7307, locationId: 7307}) {\\n    id\\n  }\\n  createEpisode(episode: {name: $episodeName, air_date: \\\"1999 Jun\\\", episode: \\\"Netfix\\\"}) {\\n    id\\n  }\\n \\n  }\\n\\n\",\"variables\":{\"locationName\":\"USA\",\"characterName\":\""+newCharacter+"\",\"episodeName\":\"Manifest\"}}")
		.when().post("https://rahulshettyacademy.com/gq/graphql")
		.then().extract().response().asString();
		
		System.out.println(mutationresponse);
		JsonPath js1 = new JsonPath(mutationresponse);
		System.out.println(mutationresponse);
		
		
	}

}
