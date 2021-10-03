package junittest.passwordvalidator;

public class PasswordValidator {

    private String specialChars;
    private int minimumLength;

    public PasswordValidator(int minimumLength, String specialChars) {
        this.minimumLength = minimumLength;
        this.specialChars = specialChars;
    }

    public boolean validatePassword(String password) {
        return doesPasswordContainSpecialCharacter(password) && doesPasswordHaveDigit(password) && doesPasswordHaveLowercase(password) &&
                doesPasswordHaveUppercase(password) && isPasswordRequiredLength(password);
    }

    public String getSpecialChars() {
        return specialChars;
    }

    public void setSpecialChars(String specialChars) {
        this.specialChars = specialChars;
    }

    public int getMinimumLength() {
        return minimumLength;
    }

    public void setMinimumLength(int minimumLength) {
        this.minimumLength = minimumLength;
    }

    private boolean isPasswordRequiredLength(String password) {
        return password.length() >= this.minimumLength;
    }

    private boolean doesPasswordHaveUppercase(String password) {
        for(char c: password.toCharArray()) {
            if(Character.isUpperCase(c)) {
                return true;
            }
        }
        return false;
    }

    private boolean doesPasswordHaveLowercase(String password) {
        for(char c: password.toCharArray()) {
            if(Character.isLowerCase(c)) {
                return true;
            }
        }
        return false;
    }

    private boolean doesPasswordHaveDigit(String password) {
        for(char c: password.toCharArray()) {
            if(Character.isDigit(c)) {
                return true;
            }
        }
        return false;
    }

    private boolean doesPasswordContainSpecialCharacter(String password) {
        for(char c: this.specialChars.toCharArray()) {
            if(password.contains(Character.toString(c))) {
                return true;
            }
        }
        return false;
    }

    @Override
    public String toString() {
        return "PasswordValidator{" +
                "specialChars='" + specialChars + '\'' +
                ", minimumLength=" + minimumLength +
                '}';
    }
}
