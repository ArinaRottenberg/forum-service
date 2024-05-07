package telran.java52.forum.service;

import java.time.LocalDate;
import java.util.List;

import telran.java52.forum.dto.PostAddDto;
import telran.java52.forum.dto.PostDto;

public interface ForumService {
	PostDto addPost(String user, PostAddDto postAddDto);
	
	PostDto findPost(String postId);
	
	void addLike(String postId);
	
	List<PostDto> findPostsByAuthor(String user);
	
	PostDto addComment(String postId, String message);
	
	PostDto deletePost(String postId);
	
	List<PostDto> findPostsByTags(List<String> tags);
	
    List<PostDto> findPostsByPeriod(LocalDate dateFrom, LocalDate dateTo);

    PostDto updatePost(String postId, PostAddDto postAddDto);
	
}
