package Model;

public class ErrorClass
{
    private String ErrorCode;
    private String ErrorMessage;
    private ErrorClass[] Result;

    public String getErrorCode() {
        return ErrorCode;
    }

    public void setErrorCode(String errorCode) {
        this.ErrorCode = errorCode;
    }

    public String getErrorMessage() {
        return ErrorMessage;
    }

    public void setErrorMessage(String errorMessage) {
        ErrorMessage = errorMessage;
    }

    public ErrorClass[] getResult() {
        return Result;
    }

    public void setResult(ErrorClass[] result) {
        Result = result;
    }
}
