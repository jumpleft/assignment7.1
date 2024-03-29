package merit.assignment7.ErrorHandling;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(value = HttpStatus.NOT_FOUND, reason = "Account Not Found")
public class FileNotFoundError extends RuntimeException{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public FileNotFoundError() {
		super();
	}
}
