package com.example.forum4.service;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.elasticsearch.annotations.Query;
import org.springframework.data.elasticsearch.repository.ElasticsearchRepository;
import com.example.forum4.entity.IndexedPost;
import org.springframework.stereotype.Repository;

@Repository
public interface PostSearchRepository extends ElasticsearchRepository<IndexedPost, String> {

    @Query("{\"bool\": {\"should\": [{\"match\": {\"content\": \"?0\"}},{\"match\": {\"title\": \"?1\"}}]}}")
    Page<IndexedPost> findByContentOrTitle(String content, String title, Pageable pageable);
}
