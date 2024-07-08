package ihar.shyn;

public interface UrlShortener {
    String getShortUrl(String longUrl);
    String getLongUrl(String shortUrl);
}
