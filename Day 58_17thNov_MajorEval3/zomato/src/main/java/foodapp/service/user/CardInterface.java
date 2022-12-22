package foodapp.service.user;

import foodapp.exception.SessionIdExpiredException;
import foodapp.model.Cards;

import java.util.List;

public interface CardInterface {

    String addCards(long cardNumber) throws SessionIdExpiredException;

    String removeCard(long cardNumber) throws SessionIdExpiredException;

    List<Cards> viewAllCards() throws SessionIdExpiredException;

}
