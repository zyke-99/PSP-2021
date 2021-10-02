package tests;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;

@TestInstance(TestInstance.Lifecycle.PER_CLASS)
class PhoneNumberValidatorUnitTests
{
	private PhoneNumberValidator validator;
	
	@BeforeAll
	void setUp()
	{
		validator = new PhoneNumberValidator();
		CountryPhoneNumberDesc ltuPhoneNumber = new CountryPhoneNumberDesc();
		ltuPhoneNumber.code = "+370";
		ltuPhoneNumber.trunkPrefix = "8";
		ltuPhoneNumber.length = 8;
		ltuPhoneNumber.alpha3code = "ltu";
		validator.addCountryDesc(ltuPhoneNumber);
	}
	
	@Test
	void testValidNumber()
	{
		String number = "823456789";
		String result = validator.validateNumber(number, "ltu");
		assertEquals(result, "+37023456789");
	}
	
	@Test
	void testInvalidNumber_hasOtherCharacters()
	{
		String number = "+123bad456789";
		String result = validator.validateNumber(number, "ltu");
		assertNull(result);
	}
	
	@Test
	void testInvalidNumber_incorrectLength()
	{
		String number = "8234567890";
		String result = validator.validateNumber(number, "ltu");
		assertNull(result);
	}
	
	@Test
	void testValidNumber_latvia()
	{
		String number = "23456789";
		CountryPhoneNumberDesc lvaPhoneNumber = new CountryPhoneNumberDesc();
		lvaPhoneNumber.code = "+371";
		lvaPhoneNumber.trunkPrefix = null;
		lvaPhoneNumber.length = 8;
		lvaPhoneNumber.alpha3code = "lva";
		validator.addCountryDesc(lvaPhoneNumber);
		
		String result = validator.validateNumber(number, "lva");
		assertEquals(result, "+37123456789");
	}
}
