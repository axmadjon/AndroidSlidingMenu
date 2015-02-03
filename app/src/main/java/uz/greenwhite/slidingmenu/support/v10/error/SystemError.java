package uz.greenwhite.slidingmenu.support.v10.error;

public class SystemError extends RuntimeException {
    public SystemError() {
    }

    public SystemError(String detailMessage) {
        super(detailMessage);
    }

    public SystemError(String detailMessage, Throwable throwable) {
        super(detailMessage, throwable);
    }

    public SystemError(Throwable throwable) {
        super(throwable);
    }
}
