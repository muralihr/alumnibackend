package org.servelots.alumni.repository.search;

import org.servelots.alumni.domain.AlumniPhotoCategory;
import org.springframework.data.elasticsearch.repository.ElasticsearchRepository;

/**
 * Spring Data Elasticsearch repository for the AlumniPhotoCategory entity.
 */
public interface AlumniPhotoCategorySearchRepository extends ElasticsearchRepository<AlumniPhotoCategory, Long> {
}
