package com.kaibank.system.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

/**
 * The ApplicationExceptionResponse class will be used to respond exceptions occurring in
 * application
 * @author Pabasara
 * @version 1.0
 * @since 1.0
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class ApplicationExceptionResponse implements Serializable {

  /** errorMessage */
  private String errorMessage;

  /** error return code. */
  private int returnCode;
}
