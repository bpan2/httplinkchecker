//LinkStatus is implemented with enum for the most common list status codes 

package httplinkchecker;

//https://developer.mozilla.org/en-US/docs/Web/HTTP/Status
public enum LinkStatus {
	DEFAULT_VALUE(1, "Default Value", "NONE"),
	
	CONTINUE(100, "Continue", "INFO"), 
	SWITCHING_PROTOCOL(101, "Switching Protocol", "INFO"), 
	EARLY_HINTS(103, "Early Hints", "INFO"),

	HTTP_OK(200, "OK", "SUCCESS"), 
	CREATED(201, "Created", "SUCCESS"), 
	ACCEPTED(202, "Accepted", "SUCCESS"),
	NO_CONTENT(204, "No Content", "SUCCESS"), 
	RESET_CONTENT(205, "Reset Content", "SUCCESS"),
	PARTIAL_CONTENT(206, "Partial Content", "SUCCESS"),

	MOVED_PERMANENTLY(301, "Moved Permanently", "SUCCESS"), 
	FOUND(302, "Found", "SUCCESS"),
	SEE_OTHER(303, "See Other", "SUCCESS"),
	NOT_MODIFIED(304, "Not modified", "SUCCESS"),
	USE_PROXY(305, "Use Proxy", "SUCCESS"), 
	TEMPORARY_REDIRECT(307, "Temporary Redirect", "SUCCESS"),
	PERMANENT_REDIRECT(308, "Permanent Redirect", "SUCCESS"),
	
	BAD_REQUEST(400, "Bad Request", "ERROR"),
	UNAUTHORIZED(401, "Unauthorized", "ERROR"),
	FORBIDDEN(403, "Forbidden", "ERROR"),
	NOT_FOUND(404, "Not Found", "ERROR"),
	METHOD_NOT_ALLOWED(405, "Method Not Allowed", "ERROR"),
	NOT_ACCEPTABLE(406, "Not Acceptable", "ERROR"),
	REQUEST_TIMEOUT(408, "Request Timeout", "ERROR"),
	CONFLICT(409, "Conflict", "ERROR"),
	GONE(410, "Gone", "ERROR"),
	TOO_MANY_REQUESTS(429, "Too Many Requests", "ERROR"),
	
	INTERNAL_SERVER_ERROR(500, "Internal Server Error", "ERROR"),
	NOT_IMPLEMENTED(501, "Not Implemented", "ERROR"),
	BAD_GATEWAY(502, "Bad Gateway", "ERROR"),
	SERVICE_UNAVAILABLE(503, "Service Unavailable", "ERROR"),
	GATEWAY_TIMEOUT(504, "Gateway Timeout", "ERROR"),
	HTTP_VERSION_NOT_SUPPORTED(505, "HTTP Version Not Supported", "ERROR"),
	NOT_EXTENDED(510, "Not Extended", "ERROR"),
	NETWORK_AUTHENTICATION_REQUIRED(511, "Network Authentication Required", "ERROR");

	private int statusCode;
	private String statusMessage;
	private String statusType;
	

	private LinkStatus(int code, String message, String statusType) {
		this.statusCode = code;
		this.statusMessage = message;
		this.statusType = statusType;
	}

	public static String getCompleteStatusMsg(int code) {
		String responseMsg = null;
		for (LinkStatus ls : LinkStatus.values()) {
			if (ls.statusCode == code) {
				responseMsg = "(" + ls.statusCode + " | " + ls.statusMessage + " | " + ls.statusType + " )";
			}
		}
		return responseMsg;
	}

	public int getStatusCode() {
		return statusCode;
	}
	
	public String getStatusMessage() {
		return statusMessage;
	}

	public String getStatusType() {
		return statusType;
	}

}
