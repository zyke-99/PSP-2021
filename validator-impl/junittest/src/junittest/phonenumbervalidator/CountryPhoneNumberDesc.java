package junittest.phonenumbervalidator;

public class CountryPhoneNumberDesc {
    private String code;
    private String trunkPrefix;
    private int length;
    private String alpha3code = "ltu";

    public CountryPhoneNumberDesc() {
    }

    public CountryPhoneNumberDesc(String code, String trunkPrefix, int length, String alpha3code) {
        this.code = code;
        this.trunkPrefix = trunkPrefix;
        this.length = length;
        this.alpha3code = alpha3code;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        if(code == null) {
            this.code = "";
        } else {
            this.code = code;
        }
    }

    public String getTrunkPrefix() {
        return trunkPrefix;
    }

    public void setTrunkPrefix(String trunkPrefix) {
        this.trunkPrefix = trunkPrefix;
    }

    public int getLength() {
        return length;
    }

    public void setLength(int length) {
        this.length = length;
    }

    public String getAlpha3code() {
        return alpha3code;
    }

    public void setAlpha3code(String alpha3code) {
        this.alpha3code = alpha3code;
    }

    @Override
    public String toString() {
        return "CountryPhoneNumberDesc{" +
                "code='" + code + '\'' +
                ", trunkPrefix='" + trunkPrefix + '\'' +
                ", length=" + length +
                ", alpha3code='" + alpha3code + '\'' +
                '}';
    }
}
