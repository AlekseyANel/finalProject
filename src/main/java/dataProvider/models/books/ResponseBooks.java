package dataProvider.models.books;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data//вместо нас прописует геттеры и сеттеры
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class ResponseBooks{
	public List<BooksItem> books;
}