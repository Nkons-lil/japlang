package org.japlang.controller;

import org.japlang.model.Word;
import org.japlang.service.WordService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/words")
public class WordController {

    private final WordService wordService;

    public WordController(WordService wordService) {
        this.wordService = wordService;
    }

    @GetMapping
    public List<Word> getAllWords() {
        return wordService.findAll();
    }

    @GetMapping("/random")
    public Word getRandomWord() {
        return wordService.findRandom();
    }

    @GetMapping("/{id}")
    public Word getWord(@PathVariable Long id) {
        return wordService.findById(id);
    }

    @PostMapping
    public void createWord(@RequestBody Word word) {
        wordService.save(word);
    }

    @PutMapping
    public void updateWord(@RequestBody Word word) {
        wordService.update(word);
    }

    @DeleteMapping("/{id}")
    public void deleteWord(@PathVariable Long id) {
        wordService.delete(id);
    }
}
