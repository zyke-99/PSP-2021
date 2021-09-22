import emailvalidator.EmailValidator;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.junit.jupiter.api.Assertions.assertFalse;

public class EmailValidatorTest {

    EmailValidator emailValidator = new EmailValidator("confPath");

    @Test
    void Validate_EmailDoesNotContainAtSign_ShouldReturnFalse() {
        assertFalse(emailValidator.validate("inter.dom.tld"));
    }


    @Test
    void Validate_EmailContainsSpecialCharacters_ShouldReturnFalse() {
        assertFalse(emailValidator.validate("%$#ab$@dom.tld"));
    }


    @Test
    void Validate_EmailDoesNotHaveTopLevelDomain_ShouldReturnFalse() {
        assertFalse(emailValidator.validate("someone@dom"));
    }

    @Test
    void Validate_EmailDoesNotHaveDomainBeforeTopLevelDomain_ShouldReturnFalse() {
        assertFalse(emailValidator.validate("someone@.tld"));
    }

    @Test
    void Validate_EmailAdheresToTheRules_ShouldReturnTrue() {
        assertTrue(emailValidator.validate("someone@dom.tld"));
    }

}
