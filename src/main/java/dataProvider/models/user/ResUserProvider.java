package dataProvider.models.user;

public class ResUserProvider {
	private static String sessionUserId;
	private static String sessionToken;

/*    public static ResUserAccount getDefaultResponseUserId() {
        return ResUserAccount.getDefaultResponse();
    };
	public static ResGenToken getDefaultRequestToken() {
		return ResGenToken.getDefaultResponse();
	};*/


	public static void setSessionUserId(String userId) {
		sessionUserId = userId;
	}
	public static String getSessionUserId() {
		return sessionUserId;
	}

	public static void setSessionToken(String token) {
		sessionToken = token;
	}
	public static String getSessionToken() {
		return sessionToken;
	}
}