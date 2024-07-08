package ihar.shyn;

import ihar.shyn.encoder.URLEncoder;
import ihar.shyn.generator.IdGenerator;

import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ConcurrentMap;

public class MapBasedUrlShortener {
    private final ConcurrentMap<String, String> storage = new ConcurrentHashMap<>();
    private final ConcurrentMap<String, String> reverseStorage = new ConcurrentHashMap<>();
    private final URLEncoder urlEncoder;
    private final IdGenerator idGenerator;
    private final String baseUrl;

    public MapBasedUrlShortener(URLEncoder urlEncoder, IdGenerator idGenerator, String baseUrl) {
        this.urlEncoder = urlEncoder;
        this.idGenerator = idGenerator;
        this.baseUrl = baseUrl;
    }

    public String getShortUrl(String longUrl) {
        return storage.computeIfAbsent(longUrl, url -> {
            long urlId = idGenerator.generateId();
            String shortUrlId = urlEncoder.encode(urlId);
            String shortUrl = baseUrl + "/" + shortUrlId;
            reverseStorage.put(shortUrl, longUrl);
            return shortUrl;
        });
    }

    public String getLongUrl(String shortUrl) {
        return reverseStorage.computeIfAbsent(shortUrl, key -> {
            throw new IllegalArgumentException(shortUrl + " not found");
        });
    }
}
