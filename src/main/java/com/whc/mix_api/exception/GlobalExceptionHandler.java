package com.whc.mix_api.exception;

import com.whc.mix_api.api.ApiResult;
import com.whc.mix_api.api.ApiResultBuilder;
import org.apache.tomcat.util.http.fileupload.impl.FileSizeLimitExceededException;
import org.apache.tomcat.util.http.fileupload.impl.SizeLimitExceededException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.support.DefaultMessageSourceResolvable;
import org.springframework.ui.Model;
import org.springframework.validation.BindException;
import org.springframework.web.HttpMediaTypeNotSupportedException;
import org.springframework.web.HttpRequestMethodNotSupportedException;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.MissingServletRequestParameterException;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.method.annotation.MethodArgumentTypeMismatchException;
import org.springframework.web.multipart.MaxUploadSizeExceededException;
import org.springframework.web.multipart.MultipartException;
import org.springframework.web.multipart.support.MissingServletRequestPartException;
import org.springframework.web.servlet.NoHandlerFoundException;

import java.util.stream.Collectors;

/**
 * 全局异常处理器
 *
 * @author bianxinhuan
 */
@RestControllerAdvice
public class GlobalExceptionHandler {

    private static Logger logger = LoggerFactory.getLogger(GlobalExceptionHandler.class);

    /**
     * 应用到所有@RequestMapping注解方法，在其执行之前初始化数据绑定器
     *
     * @param binder
     */
    @InitBinder
    public void initBinder(WebDataBinder binder) {
    }

    /**
     * 把值绑定到Model中，使全局@RequestMapping可以获取到该值
     *
     * @param model
     */
    @ModelAttribute
    public void addAttributes(Model model) {
    }

    /**
     * 处理通用异常
     *
     * @param e
     * @return
     */
    @ExceptionHandler(value = Exception.class)
    public ApiResult handleException(Exception e) {
        logger.error(e.getMessage(), e);
        return ApiResultBuilder.fail("系统繁忙，请稍后重试");
    }

    /**
     * 处理404异常
     *
     * @param e
     * @return
     */
    @ExceptionHandler(NoHandlerFoundException.class)
    public ApiResult handlerNoHandlerFoundException(NoHandlerFoundException e) {
        return ApiResultBuilder.fail("接口" + e.getRequestURL() + "不存在");
    }

//    /**
//     * 系统业务异常处理器
//     *
//     * @param ex
//     * @return
//     */
//    @ResponseBody
//    @ExceptionHandler(value = {BusinessException.class})
//    public ApiResult handleBusinessException(BusinessException ex) {
//        return ApiResultBuilder.fail(ex.getMessage());
//    }

    /**
     * MediaType不支持异常
     *
     * @param e
     * @return
     */
    @ExceptionHandler(HttpMediaTypeNotSupportedException.class)
    public ApiResult handleHttpMediaTypeNotSupportedException(HttpMediaTypeNotSupportedException e) {
        return ApiResultBuilder.fail("不支持ContentType'" + e.getContentType() + "'");
    }

    /**
     * 请求方法不支持
     *
     * @param e
     * @return
     */
    @ExceptionHandler(HttpRequestMethodNotSupportedException.class)
    public ApiResult handleHttpRequestMethodNotSupportedException(HttpRequestMethodNotSupportedException e) {
        return ApiResultBuilder.fail("不支持请求方法'" + e.getMethod() + "'");
    }

    /**
     * 参数类型不匹配
     *
     * @param e
     * @return
     */
    @ExceptionHandler(MethodArgumentTypeMismatchException.class)
    public ApiResult handleMethodArgumentTypeMismatchException(MethodArgumentTypeMismatchException e) {
        return ApiResultBuilder.fail("参数'" + e.getName() + "'格式不正确");
    }

    /**
     * 处理缺失参数异常
     *
     * @param e
     * @return
     */
    @ExceptionHandler(MissingServletRequestParameterException.class)
    public ApiResult handleMissingServletRequestParameterException(MissingServletRequestParameterException e) {
        return ApiResultBuilder.fail("缺少参数'" + e.getParameterName() + "'");
    }

    /**
     * 处理缺失上传参数异常
     *
     * @param e
     * @return
     */
    @ExceptionHandler(MissingServletRequestPartException.class)
    public ApiResult handleMissingServletRequestPartException(MissingServletRequestPartException e) {
        return ApiResultBuilder.fail("缺少参数'" + e.getRequestPartName() + "'");
    }

    /**
     * 处理缺失上传参数异常
     *
     * @param e
     * @return
     */
    @ExceptionHandler(MultipartException.class)
    public ApiResult handleMultipartException(MultipartException e) {
        return ApiResultBuilder.fail("当前请求不是'multipart'请求");
    }

    /**
     * 处理上传文件过大异常
     *
     * @param e
     * @return
     */
    @ExceptionHandler(MaxUploadSizeExceededException.class)
    public ApiResult handleMaxUploadSizeExceededException(MaxUploadSizeExceededException e) {
        logger.error("上传文件错误", e);
        if (e.getCause() != null && e.getCause().getCause() != null) {
            Throwable cause = e.getCause().getCause();
            if (cause instanceof FileSizeLimitExceededException) {
                FileSizeLimitExceededException ex = (FileSizeLimitExceededException) cause;
                return ApiResultBuilder.fail("单文件最大不能超过" + (ex.getPermittedSize() / 1024) + "KB");
            }
            if (cause instanceof SizeLimitExceededException) {
                SizeLimitExceededException ex = (SizeLimitExceededException) cause;
                return ApiResultBuilder.fail("文件最大不能超过" + (ex.getPermittedSize() / 1024) + "KB");
            }
        }
        return ApiResultBuilder.fail("文件大小超过限制");
    }

    /**
     * 参数未验证异常。由于异常会影响到拦截器，导致提示未登录。在Spring Boot BasicErrorController默认拦截器之前对异常进行拦截
     *
     * @param e
     * @return
     */
    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ApiResult handleMethodArgumentNotValidException(MethodArgumentNotValidException e) {
        String message = e.getBindingResult().getAllErrors().iterator().next().getDefaultMessage();
        logger.error(e.getMessage(), e);
        return ApiResultBuilder.fail(message);
    }

//    /**
//     * 处理参数校验异常
//     *
//     * @param e
//     * @return
//     */
//    @ExceptionHandler(value = ConstraintViolationException.class)
//    public ApiResult handleConstraintViolationException(ConstraintViolationException e) {
//        Set<ConstraintViolation<?>> constraintViolations = e.getConstraintViolations();
//        String message = "";
//        // 只获取第1个异常
//        for (ConstraintViolation<?> constraint : constraintViolations) {
//            message = constraint.getMessageTemplate();
//            break;
//        }
//        return ApiResultBuilder.fail(message);
//    }

    /**
     * 处理参数校验异常
     *
     * @param e
     * @return
     */
    @ExceptionHandler(value = BindException.class)
    public ApiResult handleBindException(BindException e) {
        String message = e.getBindingResult()
                .getAllErrors()
                .stream()
                .map(DefaultMessageSourceResolvable::getDefaultMessage)
                .collect(Collectors.joining());
        return ApiResultBuilder.fail(message);
    }
}
