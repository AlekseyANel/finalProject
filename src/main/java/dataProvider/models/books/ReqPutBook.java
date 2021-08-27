package dataProvider.models.books;

import com.fasterxml.jackson.annotation.JsonIgnore;
import dataProvider.models.user.ResUserProvider;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import utils.ConfigFileReader;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class ReqPutBook {
	private String userId;
	private String isbn;

	@JsonIgnore
	private static ConfigFileReader configFileReader= new ConfigFileReader();


	public static ReqPutBook getDefaultRequest() {//подготовленная конструкция для реквестов
		return new ReqPutBook(ResUserProvider.getSessionUserId(),configFileReader.getIsbn1());
	}
}