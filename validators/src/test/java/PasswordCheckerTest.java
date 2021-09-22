import org.junit.jupiter.api.Test;
import passwordchecker.PasswordChecker;

import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.junit.jupiter.api.Assertions.assertFalse;

public class PasswordCheckerTest {

    PasswordChecker passwordChecker = new PasswordChecker("confPath");

    @Test
    void Validate_PasswordIsShorterThanRequiredLength_ShouldReturnFalse() {
        assertFalse(passwordChecker.validate("Pass@"));
    }

    @Test
    void Validate_PasswordDoesNotContainUppercaseCharacters_ShouldReturnFalse() {
        assertFalse(passwordChecker.validate("pass@"));
    }

    @Test
    void Validate_PasswordDoesNotContainSpecialCharacter_ShouldReturnFalse() {
        assertFalse(passwordChecker.validate("Pass"));
    }

    @Test
    void Validate_PasswordAdheresToTheRules_ReturnsTrue() {
        assertTrue(passwordChecker.validate("Pass@"));
    }

}