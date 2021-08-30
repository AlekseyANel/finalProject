package dataProvider.models.books;

import com.fasterxml.jackson.annotation.JsonIgnore;
import dataProvider.models.user.ResUserProvider;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

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
private static ConfigFileReader configFileReaderAPI = new ConfigFileReader();


	public static ReqAddBookToUserList getDefaultRequest() {//подготовленная конструкция для реквестов
		return new ReqAddBookToUserList(ResUserProvider.getSessionUserId(),
			       new ArrayList(List.of("\n"+
						"    {\n" +
						"      \"isbn\": \""+ configFileReaderAPI.getIsbn()+"\"\n" +
						"    }\n"+
						"")));

	}
}

