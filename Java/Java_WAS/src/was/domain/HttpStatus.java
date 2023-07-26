package was.domain;

public enum HttpStatus {

    OK(200),
    BAD_REQUEST(400),
    NOT_FOUND(404)
    ;

    private final int value;

    HttpStatus(int value) {
        this.value = value;
    }

    public int getValue() {
        return value;
    }
}