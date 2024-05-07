package telran.java52.forum.controller;

import java.time.LocalDate;
import java.util.List;

import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import telran.java52.forum.dto.PostAddDto;
import telran.java52.forum.dto.PostDto;
import telran.java52.forum.service.ForumService;

@RestController
public class ForumController {

		ForumService forumService;

		@PostMapping("/post/{user}")
		public PostDto addPost(@PathVariable String user, @RequestBody PostAddDto postAddDto) {
			return forumService.addPost(user, postAddDto);
		}

		@GetMapping("/post/{postId}")
		public PostDto findPost(@PathVariable("postId") String id) {
			return forumService.findPost(id);
		}

		@PutMapping ("/post/{{postId}}/like")
		public void addLike(@PathVariable("postId") String id) {
		}

		@GetMapping("/posts/author/{{user}}")
		public List<PostDto> findPostsByAuthor(@PathVariable String user) {
			return forumService.findPostsByAuthor(user);
		}

		@PutMapping ("/post/{{postId}}/comment/{{user}}")
		public PostDto addComment(@PathVariable("postId") String id, @RequestBody String message) {
			return forumService.addComment(id, message);
		}

		@DeleteMapping ("/post/{postId}")
		public PostDto deletePost(@PathVariable("postId") String id) {
			return forumService.deletePost(id);
		}

		@PostMapping("/posts/tags")
		public List<PostDto> findPostsByTags(@RequestBody List<String> tags) {
			return forumService.findPostsByTags(tags);
		}

		@PostMapping("/posts/period")
		public List<PostDto> findPostsByPeriod(@RequestBody LocalDate dateFrom, @RequestBody LocalDate dateTo) {
			return forumService.findPostsByPeriod(dateFrom, dateTo);
		}

		@PutMapping ("/post/{{postId}}")
		public PostDto updatePost(@PathVariable("postId") String id, @RequestBody PostAddDto postAddDto) {
			return forumService.updatePost(id, postAddDto);
		}
}
