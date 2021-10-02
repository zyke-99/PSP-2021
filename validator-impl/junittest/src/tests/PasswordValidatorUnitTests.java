package tests;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;

@TestInstance(TestInstance.Lifecycle.PER_CLASS)
class PasswordValidatorUnitTests
{
	private PasswordValidator validator;
	
	@BeforeAll
	void setUp()
	{
		validator = new PasswordValidator(8, "!@#$%^&*()_-=+");
	}
	
	@Test
	void testValidPassword()
	{
		String password = "Password123!@#$%";
		boolean result = validator.validatePassword(password);
		assertTrue(result);
	}
	
	@Test
	void testInvalidPassword_insufficientLength()
	{
		String password = "Pa123!";
		boolean result = validator.validatePassword(password);
		assertFalse(result);
	}
	
	@Test
	void testInvalidPassword_noUppercase()
	{
		String password = "password123!@#$%";
		boolean result = validator.validatePassword(password);
		assertFalse(result);
	}
	
	@Test
	void testInvalidPassword_noLowercase()
	{
		String password = "PASSWORD123!@#$%";
		boolean result = validator.validatePassword(password);
		assertFalse(result);
	}
	
	@Test
	void testInvalidPassword_noNumbers()
	{
		String password = "Password!@#$%";
		boolean result = validator.validatePassword(password);
		assertFalse(result);
	}
	
	@Test
	void testInvalidPassword_noSpecialChars()
	{
		String password = "Password123<>";
		boolean result = validator.validatePassword(password);
		assertFalse(result);
	}
}
