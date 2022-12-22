package foodapp.controller.user;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import foodapp.exception.SessionIdExpiredException;
import foodapp.model.Cards;
import foodapp.service.user.CardInterface;

import javax.validation.Valid;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/card")
public class CardController {

    @Autowired
    private CardInterface cardInterface;

    @PostMapping("/addCard")
    public ResponseEntity<String> addCards(@RequestParam @Valid long cardNumber) throws SessionIdExpiredException {
        return ResponseEntity.of(Optional.of(cardInterface.addCards(cardNumber)));
    }

    @GetMapping("/showCard")
    public ResponseEntity<List<Cards>> viewAllCards() throws SessionIdExpiredException {
        return ResponseEntity.of(Optional.of(cardInterface.viewAllCards()));
    }

    @DeleteMapping("/removeCard")
    public ResponseEntity<String> removeCard(@RequestParam long cardNumber) throws SessionIdExpiredException {
        return ResponseEntity.of(Optional.of(cardInterface.removeCard(cardNumber)));
    }

}
