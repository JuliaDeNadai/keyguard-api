package com.keyguard.infra.database.mongodb;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.mongodb.MongoDatabaseFactory;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.SimpleMongoClientDatabaseFactory;

@Configuration
public class MongoDbConfig {

  @Value("${api.mongodb.url}")
  private String ConnectionUrl;

  @Bean
  public MongoDatabaseFactory mongoConfigure() {
    return new SimpleMongoClientDatabaseFactory(ConnectionUrl);
  }
  @Bean
  public MongoTemplate mongoTemplate() {
    return new MongoTemplate(mongoConfigure());
  }


  
}
