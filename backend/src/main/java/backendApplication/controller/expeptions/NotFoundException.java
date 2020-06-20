package backendApplication.controller.expeptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(value = HttpStatus.NOT_FOUND, reason="Resource not found in the server")
public class NotFoundException extends RuntimeException {}