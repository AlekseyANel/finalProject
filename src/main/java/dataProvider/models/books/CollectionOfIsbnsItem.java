package dataProvider.models.books;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import net.minidev.json.JSONArray;
import net.minidev.json.JSONUtil;
import org.json.JSONObject;


import java.util.List;

import static net.minidev.json.JSONValue.toJSONString;


/*{
  "userId": "string",
  "collectionOfIsbns": [   {"isbn": "string"}  ]
}*/
@Data
@AllArgsConstructor
//@NoArgsConstructor
@Builder
public class CollectionOfIsbnsItem {
	public static String isbn;

	public CollectionOfIsbnsItem(String isbn) {
		this.isbn = isbn;
	}
}



/*public static List<Object> collectionOfIsbnsItem ;

	public CollectionOfIsbnsItem(List<Object> collectionOfIsbnsItem) {
		this.collectionOfIsbnsItem = collectionOfIsbnsItem;
	}
	public static CollectionOfIsbnsItem getInstance(){
		return  new CollectionOfIsbnsItem(List.of("9781449337711"));
	}*/
