package dataProvider.models.books;

import dataProvider.models.user.ResUserProvider;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import net.minidev.json.annotate.JsonIgnore;
import utils.ConfigFileReader;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class ReqPutBook {
	private String userId;
	private String isbn;

	@JsonIgnore
	//private static ConfigFileReader configFileReader= new ConfigFileReader();
	private static String checkedIsbn1 = ConfigFileReader.getIsbn1();

	public static ReqPutBook getDefaultRequest() {//подготовленная конструкция для реквестов
		return new ReqPutBook(ResUserProvider.getSessionUserId(),checkedIsbn1);
	}
}