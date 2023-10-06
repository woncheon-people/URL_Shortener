package shortnerURL.shortener.service;

import jakarta.persistence.EntityNotFoundException;
import org.springframework.stereotype.Service;
import shortnerURL.shortener.dto.UrlRequest;
import shortnerURL.shortener.entity.Url;
import shortnerURL.shortener.repository.UrlRepository;

@Service
public class UrlService {

    private final UrlRepository urlRepository;
    private final UrlConversion urlConversion;

    public UrlService(UrlRepository urlRepository, UrlConversion conversion) {
        this.urlRepository = urlRepository;
        this.urlConversion = conversion;
    }

    public String getShortUrl(UrlRequest request) {
        Url url = new Url();
        url.setLongUrl(request.getLongUrl());
        Url entity = urlRepository.save(url);

        return urlConversion.encode(entity.getId());
    }

    public String getOriginalUrl(String shortUrl) {

        long id = urlConversion.decode(shortUrl);
        Url entity = urlRepository.findById(id).orElseThrow(()
                -> new EntityNotFoundException("찾을 수 없습니다." + shortUrl));
        urlRepository.save(entity);
        return entity.getLongUrl();
    }
}



