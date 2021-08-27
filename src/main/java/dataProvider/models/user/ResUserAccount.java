package dataProvider.models.user;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreType;
import dataProvider.models.books.BooksItem;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data//вместо нас прописует геттеры и сеттеры
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class ResUserAccount  {//класс UserAccount для хранения информации, связанной с учетной записью пользователя:
	// такой как книги, связанные с пользователем, userID и userName.

	public String userID;
	public String username;
	public List<BooksItem> books;

	public static ResUserAccount getDefaultResponse() {//подготовленная конструкция для реквестов
		return new ResUserAccount();
	};

//List.of(new BooksItem())


}