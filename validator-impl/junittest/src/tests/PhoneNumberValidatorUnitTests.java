package tests;

import static org.junit.jupiter.api.Assertions.*;

import junittest.phonenumbervalidator.CountryPhoneNumberDesc;
import junittest.phonenumbervalidator.PhoneNumberValidator;
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
		ltuPhoneNumber.setCode("+370");
		ltuPhoneNumber.setTrunkPrefix("8");
		ltuPhoneNumber.setLength(11);
		ltuPhoneNumber.setAlpha3code("ltu");
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
		lvaPhoneNumber.setCode("+371");
		lvaPhoneNumber.setTrunkPrefix(null);
		lvaPhoneNumber.setLength(11);
		lvaPhoneNumber.setAlpha3code("lva");
		validator.addCountryDesc(lvaPhoneNumber);
		
		String result = validator.validateNumber(number, "lva");
		assertEquals(result, "+37123456789");
	}
}
