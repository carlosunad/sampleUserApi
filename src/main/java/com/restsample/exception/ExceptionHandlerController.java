package com.restsample.exception;

import com.restsample.constants.RestSampleConstants;
import com.restsample.data.dto.ResponseDTO;
import com.restsample.data.dto.ValidationErrorDTO;
import com.restsample.message.MessageByLocaleService;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;

@ControllerAdvice
public class ExceptionHandlerController {

    @Autowired
    private MessageByLocaleService messageManagerService;

    @ExceptionHandler( RuntimeException.class)
    @ResponseBody
    public ResponseEntity<ResponseDTO> handleError(HttpServletRequest req, Exception exception) {
        ResponseDTO responseDTO =
                new ResponseDTO("400",
                        StringUtils.defaultIfBlank(
                                 messageManagerService.getMessage(RestSampleConstants.BAD_REQUEST_ERROR_MESSAGE),
                                "Bad Request"));

        exception.printStackTrace();
        return new ResponseEntity<>(responseDTO, HttpStatus.BAD_REQUEST);
    }

}
