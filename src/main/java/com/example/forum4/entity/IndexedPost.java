package com.example.forum4.entity;

import org.springframework.data.annotation.Id;
import org.springframework.data.elasticsearch.annotations.Document;
import org.springframework.data.elasticsearch.annotations.Field;
import org.springframework.data.elasticsearch.annotations.FieldType;

@Document(indexName = "forum", createIndex = true)
public class IndexedPost extends Post {

    @Id
    private String id;

    @Field(type = FieldType.Text)
    private String content;

    // Getters and setters
}
