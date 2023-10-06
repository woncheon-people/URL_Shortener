package shortnerURL.shortener.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import shortnerURL.shortener.entity.Url;

@Repository
public interface UrlRepository extends JpaRepository<Url, Long> {

}