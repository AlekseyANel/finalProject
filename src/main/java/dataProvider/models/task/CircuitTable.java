package dataProvider.models.task;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data//без геттеров и сеттеров, при компиляции
@AllArgsConstructor
@NoArgsConstructor
public class CircuitTable{
	private List<CircuitsItem> circuits;
	private String season;
}