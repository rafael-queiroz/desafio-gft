package com.gft.demo.exception;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;

import java.io.Serializable;
import java.time.LocalDateTime;

@Getter
@Builder
@AllArgsConstructor
public class ExceptionResponse implements Serializable {

	static final long serialVersionUID = 1L;

	@JsonFormat(pattern="yyyy-MM-dd HH:mm:ss")
	LocalDateTime timestamp;
	String message;
	String details;

}
