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
	private static ConfigFileReader configFileReaderAPI = new ConfigFileReader();

	public static ReqDeleteBook getDefaultRequest() {//подготовленная конструкция для реквестов
		return new ReqDeleteBook(configFileReaderAPI.getIsbn1(), ResUserProvider.getSessionUserId());
	}
}