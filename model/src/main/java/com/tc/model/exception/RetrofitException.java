package com.tc.model.exception;

import java.io.IOException;
import java.lang.annotation.Annotation;

import okhttp3.Response;
import okhttp3.ResponseBody;
import retrofit2.Converter;
import retrofit2.Retrofit;

/**
 * Created by tianchao on 2017/8/3.
 */

public class RetrofitException extends RuntimeException {

    public static RetrofitException networkError(Throwable exception, String url, Response response, Retrofit retrofit) {
        return new RetrofitException(exception.getMessage(), url, response, Kind.NETWORK, exception, retrofit, null, -1);
    }

    public static RetrofitException serverError(Throwable exception) {
        if (!(exception instanceof ServerInnerErrorException) && !(exception.getCause() instanceof ServerInnerErrorException)) {
            return unexpectedError(exception);
        }
        if (exception instanceof ServerInnerErrorException) {
            return new RetrofitException(exception.getMessage(), null, null, Kind.SERVER, exception, null,
                    ((ServerInnerErrorException) exception).getInfo(), ((ServerInnerErrorException) exception).getCode());
        } else {
            if (exception.getCause() == null) {
                return unexpectedError(exception);
            }
            return new RetrofitException(exception.getCause().getMessage(), null, null, Kind.SERVER, exception.getCause(), null,
                    ((ServerInnerErrorException) exception.getCause()).getInfo(), ((ServerInnerErrorException) exception.getCause()).getCode());
        }
    }

    public static RetrofitException unexpectedError(Throwable exception) {
        return new RetrofitException(exception.getMessage(), null, null, Kind.UNEXPECTED, exception, null, null, -1);
    }

    /**
     * Identifies the event kind which triggered a {@link RetrofitException}.
     */
    public enum Kind {
        /**
         * An {@link IOException} occurred while communicating to the server.
         */
        NETWORK,

        /**
         * Server exception.
         */
        SERVER,
        /**
         * An internal error occurred while attempting to execute a request. It is best practice to
         * re-throw this exception so your application crashes.
         */
        UNEXPECTED
    }

    private final String url;
    private final Response response;
    private final Kind kind;
    private final Retrofit retrofit;
    private final String info;
    private final int code;

    RetrofitException(String message, String url, Response response, Kind kind, Throwable exception, Retrofit retrofit, String info, int code) {
        super(message);
        this.url = url;
        this.response = response;
        this.kind = kind;
        this.retrofit = retrofit;
        this.info = info;
        this.code = code;
    }

    /**
     * The request URL which produced the error.
     */
    public String getUrl() {
        return url;
    }

    /**
     * Response object containing status code, headers, body, etc.
     */
    public Response getResponse() {
        return response;
    }

    /**
     * The event kind which triggered this error.
     */
    public Kind getKind() {
        return kind;
    }

    /**
     * The Retrofit this request was executed on
     */
    public Retrofit getRetrofit() {
        return retrofit;
    }

    /**
     * Some cases the server inner error will take information
     **/
    public String getInfo() {
        return info;
    }

    /**
     * Error code of exception
     **/
    public int getCode() {
        return code;
    }

    /**
     * HTTP response body converted to specified {@code type}. {@code null} if there is no
     * response.
     *
     * @throws IOException if unable to convert the body to the specified {@code type}.
     */
    public <T> T getErrorBodyAs(Class<T> type) throws IOException {
        if (response == null || response.body() == null) {
            return null;
        }
        Converter<ResponseBody, T> converter = retrofit.responseBodyConverter(type, new Annotation[0]);
        return converter.convert(response.body());
    }
}