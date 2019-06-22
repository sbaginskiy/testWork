package jevera.testWork.exception;

public class EmployeeAlreadyExist extends RuntimeException {
    private String fullName;

    public EmployeeAlreadyExist(String fullName) {
        this.fullName = fullName;
    }
}
