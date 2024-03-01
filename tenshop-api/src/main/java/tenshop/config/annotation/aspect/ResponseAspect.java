package tenshop.config.annotation.aspect;

import static tenshop.config.annotation.aspect.dto.Response.*;
import static tenshop.config.annotation.aspect.dto.ResponseCommon.*;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.springframework.stereotype.Component;

import lombok.extern.slf4j.Slf4j;
import tenshop.config.annotation.aspect.dto.Response;

@Slf4j
@Aspect
@Component
public class ResponseAspect {

    @Around("@within(tenshop.config.annotation.ResponseAnnotation)")
    public Response doResponse(ProceedingJoinPoint joinPoint) {
        log.info("[ApiResponse] {}", joinPoint.getSignature());

        try {
            Object proceed = joinPoint.proceed();

            if (proceed instanceof Response) {
                Response response = (Response) proceed;
                // response.commonUpdate(success());
                if (response.getCommon() == null || response.getCommon().isSuccess()) {
                    response.commonUpdate(success());
                }
                return response;
            }

            throw new IllegalStateException("반환 값이 올바르지 않습니다.");
        } catch (RuntimeException e) {
            return create(notFoundError(e.getMessage()));
        } catch (Throwable e) {
            log.error("Error occurred while processing request: {}", e.getMessage());
            return create(internalServerError());
        }
    }
}


