package telran.java41.forum.service;

import java.util.List;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import telran.java41.forum.dao.PostRepository;
import telran.java41.forum.dto.CommentDto;
import telran.java41.forum.dto.DatePeriodDto;
import telran.java41.forum.dto.NewCommentDto;
import telran.java41.forum.dto.NewPostDto;
import telran.java41.forum.dto.PostDto;
import telran.java41.forum.dto.exceptions.PostNotFoundException;
import telran.java41.forum.model.Post;

@Service
public class ForumServiceImpl implements ForumService {

	PostRepository postRepository;
	ModelMapper modelMapper;
	
	@Autowired
	public ForumServiceImpl(PostRepository postRepository, ModelMapper modelMapper) {
		this.postRepository = postRepository;
		this.modelMapper = modelMapper;
	}

	@Override
	public PostDto addNewPost(NewPostDto newPost, String author) {
		Post post = modelMapper.map(newPost, Post.class);
		post.setAuthor(author);
		post = postRepository.save(post);
		return modelMapper.map(post, PostDto.class);
	}

	@Override
	public PostDto getPost(String id) {
		Post post = postRepository.findById(id).orElseThrow(() -> new PostNotFoundException(id));
		return modelMapper.map(post, PostDto.class);
	}

	@Override
	public PostDto removePost(String id) {
		Post post = postRepository.findById(id).orElseThrow(() -> new PostNotFoundException(id));
		postRepository.delete(post);
		return modelMapper.map(post, PostDto.class);
	}

	@Override
	public PostDto updatePost(NewPostDto postUpdateDto, String id) {
		Post post = postRepository.findById(id).orElse(null);
		if(post == null) {
			return null;
		}
		if (updatePost(postUpdateDto, id).getTitle() != null) {
			post.setTitle(postUpdateDto.getTitle());
		}
		if (updatePost(postUpdateDto, id).getContent() != null) {
			post.setContent(postUpdateDto.getContent());
		}
		postRepository.save(post);
		return modelMapper.map(post, PostDto.class);
	}

	@Override
	public void addLike(String id) {
		Post post = postRepository.findById(id).orElseThrow(() -> new PostNotFoundException(id));
		post.addLike();
		postRepository.save(post);
	}

	@Override
	public PostDto addComment(String id, String author, NewCommentDto newCommentDto) {
		if(postRepository.existsById(newCommentDto.getMessage())) {
			return null;
		}
		Post post = modelMapper.map(newCommentDto, Post.class);
		postRepository.save(post);
		return null;
	}

	@Override
	public Iterable<PostDto> findPostsByAuthor(String author) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Iterable<PostDto> findPostsByTags(List<String> tags) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Iterable<PostDto> findPostsByDates(DatePeriodDto datePeriodDto) {
		// TODO Auto-generated method stub
		return null;
	}

}
