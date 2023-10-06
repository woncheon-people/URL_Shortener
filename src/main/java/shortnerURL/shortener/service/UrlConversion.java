package shortnerURL.shortener.service;
import org.springframework.stereotype.Service;

@Service
public class UrlConversion {
    private static final String possibleString = "abcdefghijklmnopqrstuvwxyzABCDEFGHIJKLMNOPQRSTUVWXYZ0123456789";
    private char[] possibleChar = possibleString.toCharArray();
    private int base62 = possibleChar.length;

    public String encode(long input) {
        var encodeString = new StringBuilder();

        if (input == 0) {
            return String.valueOf(possibleChar[0]);
        }
        while (input > 0) {
            encodeString.append(possibleChar[(int) (input % base62)]);
            input = input / base62;
        }
        return encodeString.reverse().toString();
    }

    public long decode(String input) {
        char[] characters = input.toCharArray();
        int length = characters.length;
        int decode = 0;

        int counter = 1;
        for (int i = 0; i < length; i++) {
            decode += possibleString.indexOf(characters[i]) * Math.pow(base62, length - counter);
            counter++;
        }
        return decode;
    }


}

