package shortnerURL.shortener.controller;

import jakarta.servlet.http.HttpServletResponse;
import org.json.JSONObject;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import shortnerURL.shortener.dto.UrlRequest;
import shortnerURL.shortener.entity.Url;
import shortnerURL.shortener.service.UrlService;


@RestController
@RequestMapping("/api/v1")
public class UrlController {

    private final UrlService urlService;

    public UrlController(UrlService urlService) {
        this.urlService = urlService;
    }

    @PostMapping("/shorten")
    public String convertToShortUrl(@RequestBody UrlRequest request) {
        return urlService.getShortUrl(request);
    }

    @GetMapping("/{shorturl}")
    public ResponseEntity<String> redirect(@PathVariable String shorturl ){
        String longurl= urlService.getOriginalUrl(shorturl.replace("http://localhost:8080/",""));
        if(longurl != null){
            return ResponseEntity.ok("redirect:"+longurl);
        }
        return ResponseEntity.badRequest().body("sorry"+longurl);
    }
    }

