package telran.java52.forum.dto;

import java.time.LocalDateTime;
import java.util.List;

import lombok.Getter;

@Getter
public class PostDto {
	String id;
	String title;
	String content;
	String author;
	LocalDateTime dateCreated;
	List<String> tags;
	Integer likes;
	List<CommentDto> comments;
}
