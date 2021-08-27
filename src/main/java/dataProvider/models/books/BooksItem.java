package dataProvider.models.books;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data//без геттеров и сеттеров
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class BooksItem{
	private String website;
	private int pages;
	private String subTitle;
	private String author;
	private String isbn;
	private String publisher;
	private String description;
	private String title;
	@JsonProperty("publish_date")
	private String publishDate;
}

/*	public static Response getDefaultResponse(){
		new Response().setData(website,pages.........);// данные для пост методов
	}
	а после when .body(Response.getDefaultResponse()).post
	А если надо считывать отдельный джейсон файл, то в utils положить ридер-клас для него */