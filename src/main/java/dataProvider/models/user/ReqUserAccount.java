package dataProvider.models.user;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import utils.ConfigFileReader;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class ReqUserAccount {
	public String password;
	public String userName;

	public static ReqUserAccount getDefaultRequest() {//подготовленная конструкция для реквестов
		return new ReqUserAccount(ConfigFileReader.getPassword(),ConfigFileReader.getUser());


	}
}