package junittest.emailvalidator;

public class EmailValidator {

    private static final String LOCAL_PART_ALLOWED_SPECIAL_CHARS = "!#$%&'*+-/=?^_`{|}~.";
    private static final String GLOBAL_PART_ALLOWED_SPECIAL_CHARS = ".-";
    private static  final int LOCAL_PART_MAX_LENGTH = 64;
    private static final int GLOBAL_PART_MAX_LENGTH = 63;

    public EmailValidator() {
    }

    public boolean validateEmail(String email) {
        return doesEmailContainOnlyOneAtSymbol(email) && validateLocalPart(email) && validateGlobalPart(email);
    }

    private boolean validateLocalPart(String email) {
        String localPart = email.substring(0, email.lastIndexOf("@"));
        return localPart.length() <= LOCAL_PART_MAX_LENGTH && !doesLocalPartStartOrEndWithDot(localPart) &&
                doesPartHaveAllowedChars(localPart, LOCAL_PART_ALLOWED_SPECIAL_CHARS) && !doesPartHaveContinuousDots(localPart);
    }

    private boolean validateGlobalPart(String email) {
        String globalPart = email.substring(email.lastIndexOf("@") + 1, email.lastIndexOf("."));
        return globalPart.length() <= GLOBAL_PART_MAX_LENGTH && !doesDomainStartOrEndWithHyphen(globalPart) &&
                doesPartHaveAllowedChars(globalPart, GLOBAL_PART_ALLOWED_SPECIAL_CHARS) && doesEmailHaveTopLevelDomain(email);
    }

    private boolean doesEmailContainOnlyOneAtSymbol(String email) {
        long atCount = email.chars().filter(ch -> ch == '@').count();
        return atCount == 1;
    }

    private boolean doesPartHaveAllowedChars(String part, String allowedSpecChars) {
        if(part.isEmpty()) {
            return false;
        } else {
            for(char c: part.toCharArray()) {
                if(!(Character.isLetterOrDigit(c) || allowedSpecChars.contains(Character.toString(c)))) {
                    return false;
                }

            }
        }
        return true;
    }

    private boolean doesEmailHaveTopLevelDomain(String email) {
        String tld = email.substring(email.lastIndexOf("."));
        boolean onlyNumbers = true;
        for(char c: tld.toCharArray()) {
            if(Character.isLetter(c)) {
                onlyNumbers = false;
            }
        }
        return tld.length() > 1 && !onlyNumbers;
    }

    private boolean doesLocalPartStartOrEndWithDot(String localPart) {
        if(localPart.charAt(0) == '.' || localPart.charAt(localPart.length()-1) == '.') {
            return true;
        }
        return false;
    }

    private boolean doesPartHaveContinuousDots(String part) {
        int dotConsecutiveCount = 0;
        for(char c: part.toCharArray()) {
            if(dotConsecutiveCount >= 2) {
                return true;
            }
            if(c == '.') {
                dotConsecutiveCount += 1;
            } else {
                dotConsecutiveCount = 0;
            }
        }
        return false;
    }

    private boolean doesDomainStartOrEndWithHyphen(String globalPart) {
        return globalPart.contains("-.") || globalPart.contains(".-");
    }
}
