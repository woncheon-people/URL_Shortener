package shortnerURL.shortener.controller;

import org.json.JSONObject;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import shortnerURL.shortener.service.UrlService;


@RestController
@RequestMapping("/api/v1")
public class UrlController {

    private final UrlService urlService;

    public UrlController(UrlService urlService) {
        this.urlService = urlService;
    }

    @PostMapping(value = "/shorten")
    public ResponseEntity<String> convertToUrl(@RequestBody String url) {
        System.out.println(url);
        JSONObject jsonObj = new JSONObject(url);
        String longUrl = jsonObj.getString("longUrl");
        String shortUrl = urlService.getShortUrl(longUrl);
        return new ResponseEntity<>(shortUrl, HttpStatus.CREATED);
    }
    @GetMapping("/{shorturl}")
    public String redirect(@PathVariable String shorturl){
        String longurl= urlService.getOriginalUrl(shorturl.replace("http://localhost:8080/",""));
        if(longurl != null){
            return "redirect:"+longurl;
        }
        return "wrong_shortening";
    }


   @GetMapping()
    public String home() {
        return "index";
}
    }

