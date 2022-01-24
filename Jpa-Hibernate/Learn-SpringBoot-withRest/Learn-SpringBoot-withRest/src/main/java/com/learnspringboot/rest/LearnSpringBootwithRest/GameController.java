package com.learnspringboot.rest.LearnSpringBootwithRest;

import java.util.Arrays;
import java.util.List;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class GameController {

	@GetMapping("/games")
	public List<Game> getGamesList() {
		return Arrays.asList(new Game(1l, "The Witcher 3", "CDPR"),
				new Game(2l, "Grand Theft Auto: V", "Rockstar North"));
	}
}
