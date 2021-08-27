package dataProvider.models.task;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data//без геттеров и сеттеров, при компиляции
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class MRData {
	private String xmlns;
	private String total;
	private String offset;
	private String series;
	private String limit;
	private CircuitTable circuitTable;
	private String url;
}