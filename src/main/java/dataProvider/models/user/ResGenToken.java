package dataProvider.models.user;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class ResGenToken{
	public String result;
	public String expires;
	public String token;
	public String status;

	public static ResGenToken getDefaultResponse() {
		return new ResGenToken();
	};
}