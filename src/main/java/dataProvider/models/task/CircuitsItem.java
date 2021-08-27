package dataProvider.models.task;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data//без геттеров и сеттеров, при компиляции
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class CircuitsItem{
	private String circuitId;
	private String url;
	private String circuitName;
	private Location location;
}