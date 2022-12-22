package foodapp.controller.user;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import foodapp.customexceptions.InvalidCardNumberException;
import foodapp.model.Cards;
import foodapp.service.user.CardInterface;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/user/card")
public class CardController {

    /**
     * Cards Controller
     */

    @Autowired
    private CardInterface cardInterface;

    @PostMapping("/addCard")
    public ResponseEntity<String> addCards(@RequestHeader long cardNumber) throws InvalidCardNumberException {
        return ResponseEntity.of(Optional.of(cardInterface.addCards(cardNumber)));
    }

    @GetMapping("/showCard")
    public ResponseEntity<List<Cards>> viewAllCards() {
        return ResponseEntity.of(Optional.of(cardInterface.viewAllCards()));
    }

    @DeleteMapping("/deleteCard")
    public ResponseEntity<String> removeCard(@RequestHeader long cardNumber) throws InvalidCardNumberException {
        return ResponseEntity.of(Optional.of(cardInterface.removeCard(cardNumber)));
    }
}
