package dataProvider.models.task;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data//без геттеров и сеттеров, при компиляции
@AllArgsConstructor
@NoArgsConstructor
public class Location{
	private String country;
	private String locality;
	private String lat;
	private String jsonMemberLong;
}