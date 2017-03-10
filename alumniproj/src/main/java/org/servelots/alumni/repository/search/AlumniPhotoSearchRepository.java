package org.servelots.alumni.repository.search;

import org.servelots.alumni.domain.AlumniPhoto;
import org.springframework.data.elasticsearch.repository.ElasticsearchRepository;

/**
 * Spring Data Elasticsearch repository for the AlumniPhoto entity.
 */
public interface AlumniPhotoSearchRepository extends ElasticsearchRepository<AlumniPhoto, Long> {
}
