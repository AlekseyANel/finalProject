package dataProvider.models.books;

import dataProvider.models.user.ResUserProvider;
import io.restassured.http.ContentType;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import net.minidev.json.JSONUtil;
import net.minidev.json.annotate.JsonIgnore;
import utils.ConfigFileReader;

import java.util.ArrayList;
import java.util.List;


/*{
  "userId": "string",
  "collectionOfIsbns": [
    {
      "isbn": "string"
    }
  ]
}*/
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class ReqAddBookToUserList {
	public String userId;
	public List<CollectionOfIsbnsItem> collectionOfIsbns;
@JsonIgnore
private static String checkedIsbn = ConfigFileReader.getIsbn();

	public static ReqAddBookToUserList getDefaultRequest() {//подготовленная конструкция для реквестов
		return new ReqAddBookToUserList(ResUserProvider.getSessionUserId(),
			       new ArrayList(List.of("\n"+
						"    {\n" +
						"      \"isbn\": \""+checkedIsbn+"\"\n" +
						"    }\n"+
						"")));

	}
}

