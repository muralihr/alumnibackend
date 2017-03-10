package org.servelots.alumni.repository.search;

import org.servelots.alumni.domain.Alumni;
import org.springframework.data.elasticsearch.repository.ElasticsearchRepository;

/**
 * Spring Data Elasticsearch repository for the Alumni entity.
 */
public interface AlumniSearchRepository extends ElasticsearchRepository<Alumni, Long> {
}
