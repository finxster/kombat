package io.kombat.core.servlet.exceptions;

/**
 * Created by Bruno de Queiroz<creativelikeadog@gmail.com> on 26/11/15.
 */
public class HttpException extends RuntimeException {
    private static final long serialVersionUID = 324957544347184421L;

    private static final int DEFAULT_CODE = 500;

    private int code;

    private String redirect;

    public HttpException(int code, String redirect) {
        this.code = code;
        this.redirect = redirect;
    }

    public HttpException(String redirect, Throwable cause) {
        super("", cause);
        this.redirect = redirect;
        this.code = DEFAULT_CODE;
    }

    public HttpException(Throwable cause) {
        super(cause);
        this.code = DEFAULT_CODE;
    }

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }

    public String getRedirect() {
        return redirect;
    }

    public void setRedirect(String redirect) {
        this.redirect = redirect;
    }
}
