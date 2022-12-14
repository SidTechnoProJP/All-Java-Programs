package zomatomodified.zomato.service.user;

import zomatomodified.zomato.coustomexcptions.InvalidCardNumberException;
import zomatomodified.zomato.model.MyOrderItems;
import zomatomodified.zomato.model.MyOrders;

import java.util.List;
import java.util.Map;

public interface OrderInterface {

    List<MyOrders> addOrders(String restaurantId, Map<String, Map<String, Integer>> orders, String paymentType) ;

    String makePayment(String restaurantId, String orderId, String paymentType, Long cardNumber) throws InvalidCardNumberException;

    List<MyOrders> viewOrders();

    List<MyOrderItems> viewItemsOfOrder(String orderId);

}
