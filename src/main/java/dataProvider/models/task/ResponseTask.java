package dataProvider.models.task;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data//без геттеров и сеттеров, при компиляции
@AllArgsConstructor
@NoArgsConstructor
public class ResponseTask{
	private MRData mRData;
}