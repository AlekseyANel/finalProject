package dataProvider.models.books;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;



//import java.util.List;



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
