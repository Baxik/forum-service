package telran.java41.forum.dao;

import org.springframework.data.mongodb.repository.MongoRepository;

import telran.java41.forum.model.Post;

public interface PostRepository extends MongoRepository<Post, String> {

}
