package tests;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;

@TestInstance(TestInstance.Lifecycle.PER_CLASS)
class EmailValidatorUnitTests
{
	private EmailValidator validator;
	
	@BeforeAll
	void setUp()
	{
		validator = new EmailValidator();
	}
	
	@Test
	void testValidEmail()
	{
		String email = "Bob!#$%&'*+-/=?^_`{|}~bob.joe.2@Mif.5t-ud.vu.8.lt";
		boolean result = validator.validateEmail(email);
		assertTrue(result);
	}

	@Test
	void testInvalidEmail_hasMultipleAt()
	{
		String email = "bob.joe2@mif@stud.vu.lt";
		boolean result = validator.validateEmail(email);
		assertFalse(result);
	}

	@Test
	void testInvalidEmail_localPart_hasInvalidChars()
	{
		String email = "bob.joe[]<>2@mif.stud.vu.lt";
		boolean result = validator.validateEmail(email);
		assertFalse(result);
	}
	
	@Test
	void testInvalidEmail_localPart_hasConsecutiveDots()
	{
		String email = "bob..joe2@mif.stud.vu.lt";
		boolean result = validator.validateEmail(email);
		assertFalse(result);
	}
	
	@Test
	void testInvalidEmail_localPart_hasDotsAtTheStart()
	{
		String email = ".bob.joe2@mif.stud.vu.lt";
		boolean result = validator.validateEmail(email);
		assertFalse(result);
	}
	
	@Test
	void testInvalidEmail_localPart_hasDotsAtTheEnd()
	{
		String email = "bob.joe2.@mif.stud.vu.lt";
		boolean result = validator.validateEmail(email);
		assertFalse(result);
	}
	
	@Test
	void testInvalidEmail_localPart_lengthExceeds64()
	{
		String email = "0123456789012345678901234567890123456789012345678901234567891234_@mif.stud.vu.lt";
		boolean result = validator.validateEmail(email);
		assertFalse(result);
	}
	
	@Test
	void testInvalidEmail_domain_hasSpecialChars()
	{
		String email = "bob.joe2@m!f.st&d.vu.lt";
		boolean result = validator.validateEmail(email);
		assertFalse(result);
	}
	
	@Test
	void testInvalidEmail_domain_topLevelDomainOnlyNumbers()
	{
		String email = "bob.joe2@mif.stud.vu.123";
		boolean result = validator.validateEmail(email);
		assertFalse(result);
	}
	
	@Test
	void testInvalidEmail_domain_startsWithHyphen()
	{
		String email = "bob.joe2@mif.stud.-vu.lt";
		boolean result = validator.validateEmail(email);
		assertFalse(result);
	}
	
	@Test
	void testInvalidEmail_domain_endsWithHyphen()
	{
		String email = "bob.joe2@mif.stud-.vu.lt";
		boolean result = validator.validateEmail(email);
		assertFalse(result);
	}
	
	@Test
	void testInvalidEmail_domain_labelLengthExceeds63()
	{
		String email = "bob.joe2@mif.stud.vu.012345678901234567890123456789012345678901234567890123456789123X.lt";
		boolean result = validator.validateEmail(email);
		assertFalse(result);
	}

}
