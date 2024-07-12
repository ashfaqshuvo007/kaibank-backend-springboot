package com.kaibank.system.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * This class is used as the base response dto to return the result.
 *
 * @author Pabasara
 * @version 1.0
 * @since 1.0
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class BaseResponse<T> {

  /** return code */
  private int returnCode;

  /** message */
  private String message;

  /** actual response */
  private T result;
}
