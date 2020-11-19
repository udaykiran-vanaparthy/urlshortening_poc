package com.url.shortening.repository;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Repository;
import java.util.HashMap;

/**
 * @author thatsuday
 *
 */
@Repository
public class URLRepository {

	private final String idKey;
	private final String urlKey;
	private static final HashMap<String, Long> idMap = new HashMap<String, Long>();
	private static final HashMap<String, String> urlMap = new HashMap<String, String>();
	private static final Logger LOGGER = LoggerFactory.getLogger(URLRepository.class);

	public URLRepository() {
		idMap.put("ID", 100L);

		this.idKey = "id";
		this.urlKey = "url:";
	}

	public URLRepository(String idKey, String urlKey) {
		idMap.put("ID", 100L);
		this.idKey = idKey;
		this.urlKey = urlKey;
	}

	public Long incrementID() {
		Long id = idMap.get("ID") + 1;
		LOGGER.info("Incrementing ID: {}" + (id - 1L));
		idMap.put("ID", id);
		return id - 1;
	}

	public void saveUrl(String key, String longUrl) {
		LOGGER.info("Saving: {} at {}", longUrl, key);
		urlMap.put(key, longUrl);
	}

	public String getUrl(Long id) throws Exception {
		LOGGER.info("Retrieving at {}", id);
		LOGGER.info("HashMap Size " + urlMap.size());
		String url = urlMap.get("url:" + id);

		LOGGER.info("Retrieved {} at {}", url, id);
		if (url == null) {
			throw new Exception("URL at key" + id + " does not exist");
		}
		return url;
	}
}
