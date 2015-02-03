package uz.greenwhite.slidingmenu.support.v10.error;

public class RequestException extends RuntimeException {

    public RequestException() {
    }

    public RequestException(String detailMessage) {
        super(detailMessage);
    }

    public RequestException(String detailMessage, Throwable throwable) {
        super(detailMessage, throwable);
    }

    public RequestException(Throwable throwable) {
        super(throwable);
    }
}
