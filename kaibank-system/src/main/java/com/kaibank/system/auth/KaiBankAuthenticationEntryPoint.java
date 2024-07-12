package com.kaibank.system.auth;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.kaibank.system.constants.ApplicationConstants;
import com.kaibank.system.dto.ApplicationExceptionResponse;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.security.core.AuthenticationException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * Represents custom KaiBankAuthenticationEntryPoint for handling invalid events.
 *
 * @author Pabasara
 * @version 1.0
 * @since 1.0
 */
public class KaiBankAuthenticationEntryPoint
    implements org.springframework.security.web.AuthenticationEntryPoint {

  /** objectMapper */
  private ObjectMapper objectMapper;

  /**
   * Initializes objectMapper.
   *
   * @param objectMapper ObjectMapper instance
   * @since 1.0
   */
  public KaiBankAuthenticationEntryPoint(ObjectMapper objectMapper) {
    this.objectMapper = objectMapper;
  }

  /**
   * Observes invalid events and returns custom error codes respectively.
   *
   * @param httpServletRequest HttpServletRequest instance
   * @param httpServletResponse HttpServletResponse instance
   * @param e authentication exception
   * @throws IOException in case io operation
   * @throws ServletException in case of Servlet unexpected error.
   * @since 1.0
   */
  @Override
  public void commence(
      HttpServletRequest httpServletRequest,
      HttpServletResponse httpServletResponse,
      AuthenticationException e)
      throws IOException, ServletException {

    var applicationExceptionResponse = new ApplicationExceptionResponse();
    applicationExceptionResponse.setReturnCode(HttpStatus.UNAUTHORIZED.value());
    httpServletResponse.setStatus(401);
    applicationExceptionResponse.setErrorMessage(e.getLocalizedMessage());

    httpServletResponse.setHeader(
        ApplicationConstants.HEADER_CONTENT_TYPE, MediaType.APPLICATION_JSON_VALUE);
    httpServletResponse
        .getWriter()
        .print(objectMapper.writeValueAsString(applicationExceptionResponse));
  }
}
