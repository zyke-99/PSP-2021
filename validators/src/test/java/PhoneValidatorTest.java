import org.junit.jupiter.api.Test;
import phonevalidator.PhoneValidator;


import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class PhoneValidatorTest {

    PhoneValidator phoneValidator = new PhoneValidator("confPath");

    @Test
    void Validate_PhoneNumberContainsNonDigits_ShouldReturnFalse() {
        assertFalse(phoneValidator.validate("85321ab42", "LT"));
    }

    @Test
    void Validate_PhoneNumberDoesNotHaveCorrectPrefixOrFirstChangeableDigit_ShouldReturnFalse() {
        assertFalse(phoneValidator.validate("754321331", "LT"));
    }

    @Test
    void Validate_PhoneNumberLongerThanRuleLengthLimit_ShouldReturnFalse() {
        assertFalse(phoneValidator.validate("+37097979975939431", "LT"));
    }

    @Test
    void Validate_PhoneNumberLongerThanRuleLengthLimitAfterFirstDigitSwap_ShouldReturnFalse() {
        assertFalse(phoneValidator.validate("8699999999", "LT"));
    }

    @Test
    void Validate_PhoneNumberAdheresToTheRulesAndHasCorrectPrefix_ShouldReturnTrue() {
        assertTrue(phoneValidator.validate("+37065532122", "LT"));
    }

    @Test
    void Validate_PhoneNumberAdheresToRulesAndHasFirstChangeableDigitAvailable_ShouldReturnTrue() {
        assertTrue(phoneValidator.validate("869999999", "LT"));
    }

}
