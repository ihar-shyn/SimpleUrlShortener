package ihar.shyn.encoder;

public class BasicUrlEncoder implements URLEncoder {
    private final String POSSIBLE_CHARS = "0123456789abcdefghijklmnopqrstuvwxyzABCDEFGHIJKLMNOPQRSTUVWXYZ";
    private final int BASE = POSSIBLE_CHARS.length();

    @Override
    public String encode(Long id) {
        StringBuilder sb = new StringBuilder();
        while (id > 0) {
            sb.append(POSSIBLE_CHARS.charAt((int) (id % BASE)));
            id = id / BASE;
        }

        return sb.reverse().toString();
    }

    @Override
    public Long decode(String url) {
        long id = 0L;
        for (char c : url.toCharArray()) {
            id = id * BASE + POSSIBLE_CHARS.indexOf(c);
        }

        return id;
    }
}
