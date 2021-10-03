package junittest.phonenumbervalidator;

import java.util.LinkedList;
import java.util.List;
import java.util.stream.IntStream;

public class PhoneNumberValidator {

    private List<CountryPhoneNumberDesc> listCountryPhoneNumberDesc;

    public PhoneNumberValidator() {
        this.listCountryPhoneNumberDesc = new LinkedList<CountryPhoneNumberDesc>();
    }

    public String validateNumber(String number, String countryAlpha3Code) {
        int index = IntStream.range(0, listCountryPhoneNumberDesc.size())
                .filter(i -> listCountryPhoneNumberDesc.get(i).getAlpha3code().equals(countryAlpha3Code))
                .findFirst()
                .orElse(-1);
        if(index < 0) {
            return null;
        }
        else
        {
            CountryPhoneNumberDesc countryPhoneNumberDesc = listCountryPhoneNumberDesc.get(index);
            String formattedNumber = replaceFirstNumberIfPossible(number, countryPhoneNumberDesc);
            if(isPhoneNumberShorterThan(formattedNumber, countryPhoneNumberDesc) && doesPhoneNumberContainValidPrefix(formattedNumber, countryPhoneNumberDesc) &&
                !doesPhoneNumberContainSpecialCharacters(formattedNumber)) {
                return formattedNumber;
            }
            else {
                return null;
            }
        }
    }

    public void addCountryDesc(CountryPhoneNumberDesc countryPhoneNumberDesc) {
        this.listCountryPhoneNumberDesc.add(countryPhoneNumberDesc);
    }

    public List<CountryPhoneNumberDesc> getListCountryPhoneNumberDesc() {
        return listCountryPhoneNumberDesc;
    }

    private boolean isPhoneNumberShorterThan(String phoneNumber, CountryPhoneNumberDesc countryPhoneNumberDesc) {
        if(removeFirstPlusIfPresent(phoneNumber).length() > countryPhoneNumberDesc.getLength()) {
            return false;
        } else {
            return true;
        }
    }

    private boolean doesPhoneNumberContainSpecialCharacters(String phoneNumber) {
        int count = 0;
        for(char c: phoneNumber.toCharArray()) {
            if(!Character.isDigit(c) && c > 0) {
                return false;
            } else {
                if(count == 0 && !(c == '+')) {
                    return false;
                }
            }
            count++;
        }
        return true;
    }

    private boolean doesPhoneNumberContainValidPrefix(String phoneNumber, CountryPhoneNumberDesc countryPhoneNumberDesc) {
        return (phoneNumber.substring(0, countryPhoneNumberDesc.getCode().length()).equals(countryPhoneNumberDesc.getCode()));
    }

    private String replaceFirstNumberIfPossible(String phoneNumber, CountryPhoneNumberDesc countryPhoneNumberDesc) {
        if(countryPhoneNumberDesc.getTrunkPrefix() == null) {
            return countryPhoneNumberDesc.getCode() + phoneNumber;
        } else {
            if(phoneNumber.substring(0, countryPhoneNumberDesc.getTrunkPrefix().length()).equals(countryPhoneNumberDesc.getTrunkPrefix())) {
                return countryPhoneNumberDesc.getCode() + phoneNumber.substring(countryPhoneNumberDesc.getTrunkPrefix().length());
            }
            else {
                return phoneNumber;
            }
        }
    }

    private String removeFirstPlusIfPresent(String phoneNumber) {
        if(phoneNumber.toCharArray()[0] == '+') {
            return phoneNumber.substring(1);
        } else {
            return phoneNumber;
        }
    }

    @Override
    public String toString() {
        return "PhoneNumberValidator{" +
                "listCountryPhoneNumberDesc=" + listCountryPhoneNumberDesc +
                '}';
    }
}
