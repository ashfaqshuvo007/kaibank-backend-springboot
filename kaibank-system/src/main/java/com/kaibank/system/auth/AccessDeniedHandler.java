package com.kaibank.system.auth;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.kaibank.system.constants.ApplicationConstants;
import com.kaibank.system.dto.ApplicationExceptionResponse;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.security.access.AccessDeniedException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * Represents custom AccessDeniedHandler for handling access denied events in case of insufficient
 * role permission.
 *
 * @author Pabasara
 * @version 1.0
 * @since 1.0
 */
public class AccessDeniedHandler
    implements org.springframework.security.web.access.AccessDeniedHandler {

  /** objectMapper */
  private ObjectMapper objectMapper;

  /**
   * Initializes objectMapper.
   *
   * @param objectMapper ObjectMapper instance
   * @since 1.0
   */
  public AccessDeniedHandler(ObjectMapper objectMapper) {
    this.objectMapper = objectMapper;
  }

  /**
   * Handles access denied exception and returns error code.
   *
   * @param httpServletRequest HttpServletRequest instance
   * @param httpServletResponse HttpServletResponse instance
   * @param e exception
   * @throws IOException in case io operation
   * @throws ServletException in case of Servlet unexpected error.
   * @since 1.0
   */
  @Override
  public void handle(
      HttpServletRequest httpServletRequest,
      HttpServletResponse httpServletResponse,
      AccessDeniedException e)
      throws IOException, ServletException {

    var applicationExceptionResponse = new ApplicationExceptionResponse();
    applicationExceptionResponse.setReturnCode(HttpStatus.FORBIDDEN.value());
    httpServletResponse.setStatus(403);
    applicationExceptionResponse.setErrorMessage(e.getLocalizedMessage());

    httpServletResponse.setHeader(
        ApplicationConstants.HEADER_CONTENT_TYPE, MediaType.APPLICATION_JSON_VALUE);
    httpServletResponse
        .getWriter()
        .print(objectMapper.writeValueAsString(applicationExceptionResponse));
  }
}
