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
public class ReqDeleteBook {
	private String isbn;
	private String userId;
	@JsonIgnore
	private static String checkedIsbn1 = ConfigFileReader.getIsbn1();

	public static ReqDeleteBook getDefaultRequest() {//подготовленная конструкция для реквестов
		return new ReqDeleteBook(checkedIsbn1, ResUserProvider.getSessionUserId());
	}
}