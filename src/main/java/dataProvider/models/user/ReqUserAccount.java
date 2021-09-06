package dataProvider.models.user;

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
public class ReqUserAccount {
	public String userName;
	public String password;
	@JsonIgnore
	private static ConfigFileReader configFileReaderAPI = new ConfigFileReader();

	public static ReqUserAccount getDefaultRequest() {//подготовленная конструкция для реквестов
		return new ReqUserAccount(configFileReaderAPI.getUser(), configFileReaderAPI.getPassword());


	}
}