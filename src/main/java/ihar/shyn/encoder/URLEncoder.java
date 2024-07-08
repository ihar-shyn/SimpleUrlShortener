package ihar.shyn.encoder;

public interface URLEncoder {
    String encode(Long id);

    Long decode(String url);
}
