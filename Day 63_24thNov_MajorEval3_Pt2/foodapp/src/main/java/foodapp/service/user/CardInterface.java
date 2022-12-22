package foodapp.service.user;

import foodapp.customexceptions.InvalidCardNumberException;
import foodapp.model.Cards;

import java.util.List;

public interface CardInterface {
    String addCards(long cardNumber) throws InvalidCardNumberException;

    String removeCard(long cardNumber) throws InvalidCardNumberException;

    List<Cards> viewAllCards() ;

}
