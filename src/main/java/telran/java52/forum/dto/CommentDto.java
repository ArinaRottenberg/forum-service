package telran.java52.forum.dto;

import java.time.LocalDateTime;

import lombok.Getter;

@Getter
public class CommentDto {
	String user;
	String message;
	LocalDateTime dateCreated;
	Integer likes;
}
