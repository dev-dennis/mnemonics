package mnemonics.controller;

import java.util.List;

import org.springframework.web.bind.annotation.*;

import io.swagger.v3.oas.annotations.*;
import io.swagger.v3.oas.annotations.tags.Tag;
import mnemonics.model.Solution;
import mnemonics.service.MnemonicsService;

@RestController
@RequestMapping("/api")
@Tag(name = "Mnemonics API", description = "Generate mnemonic solutions from words")
public class MnemonicsRestController {

	private final MnemonicsService service;

	public MnemonicsRestController(MnemonicsService service) {

		this.service = service;
	}

	@GetMapping("/solutions")
	@Operation(summary = "Find mnemonic solutions", description = "Generate mnemonic phrases using specified number of words, excluding forbidden characters")
	public List<String> findSolutions(

			@Parameter(description = "Comma-separated words", example = "Neu,Löschen,Prüfen") @RequestParam String words,

			@Parameter(description = "Characters to exclude from solutions", example = "euöscherüfe") @RequestParam(defaultValue = "") String forbidden,

			@Parameter(description = "Number of words to use in solutions", example = "3") @RequestParam int wordsToUse) {

		return service.findSolutions(words, forbidden, wordsToUse).stream().map(Solution::toString).toList();
	}

}