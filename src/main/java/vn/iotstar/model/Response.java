package vn.iotstar.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * Chuan hoa du lieu tra ve cho moi API:
 * status: true/false, message: thong bao, body: du lieu (object hoac list)
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Response {

	private Boolean status;
	private String message;
	private Object body;
}
