package dev.facturador.exception;

import lombok.extern.slf4j.Slf4j;
import org.springframework.lang.Nullable;
import org.springframework.stereotype.Component;
import org.springframework.validation.BindException;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.DefaultHandlerExceptionResolver;
import javax.servlet.http.*;
import java.io.IOException;

@Component
@ControllerAdvice
@Slf4j
public class CustomExceptionHandlerResolver extends DefaultHandlerExceptionResolver {

    @ExceptionHandler(value = BindException.class)
    @Override
    protected ModelAndView handleBindException
            (BindException ex, HttpServletRequest request, HttpServletResponse response, @Nullable Object handler)
            throws IOException {
        log.debug("In CustomExceptionHandlerResolver");
        response.sendError(HttpServletResponse.SC_BAD_REQUEST);
        return new ModelAndView();
    }
}
