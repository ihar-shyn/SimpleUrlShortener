package ihar.shyn;

import ihar.shyn.encoder.BasicUrlEncoder;
import ihar.shyn.encoder.URLEncoder;
import ihar.shyn.generator.ConcurrentIdGenerator;
import ihar.shyn.generator.IdGenerator;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

public class UrlShortenerTests {

    @Test
    void whenPutLongLink_thenGetShortLink() {
        IdGenerator idGenerator = new ConcurrentIdGenerator();
        URLEncoder encoder = new BasicUrlEncoder();
        MapBasedUrlShortener urlShortener = new MapBasedUrlShortener(encoder, idGenerator, "test.com");
        String shortUrl1 = urlShortener.getShortUrl("www.mycustomsite/very/very/long/path");
        Assertions.assertEquals(shortUrl1, "test.com/1");
        String shortUrl2 = urlShortener.getShortUrl("www.mycustomsite/very/very/long/path2");
        Assertions.assertEquals(shortUrl2, "test.com/2");
        String shortUrl3 = urlShortener.getShortUrl("www.mycustomsite/very/very/long/path");
        Assertions.assertEquals(shortUrl3, "test.com/1");
    }

    @Test
    void whenGettingByInvalidShortURL_thenRaiseException() {
        IdGenerator idGenerator = new ConcurrentIdGenerator();
        URLEncoder encoder = new BasicUrlEncoder();
        MapBasedUrlShortener urlShortener = new MapBasedUrlShortener(encoder, idGenerator, "test.com");
        String shortUrl1 = urlShortener.getShortUrl("www.mycustomsite/very/very/long/path");
        Assertions.assertEquals(shortUrl1, "test.com/1");
        Assertions.assertThrows(IllegalArgumentException.class, () -> urlShortener.getLongUrl("test.com/2"));
    }
}
