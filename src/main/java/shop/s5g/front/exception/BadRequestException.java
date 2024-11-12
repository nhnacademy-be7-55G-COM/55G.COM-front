package shop.s5g.front.exception;

public class BadRequestException extends RuntimeException {

    public BadRequestException(String message) {
        super(message);
    }

    public BadRequestException() {
        super("잘못된 요청입니다.");
    }

    @Override
    public synchronized Throwable fillInStackTrace() {
        return this;
    }
}
