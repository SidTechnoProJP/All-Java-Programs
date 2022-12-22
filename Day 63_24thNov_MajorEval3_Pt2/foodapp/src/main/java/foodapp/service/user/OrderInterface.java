package foodapp.service.user;

import foodapp.customexceptions.InvalidCardNumberException;
import foodapp.model.MyOrderItems;
import foodapp.model.MyOrders;

import java.util.List;
import java.util.Map;

public interface OrderInterface {

    List<MyOrders> addOrders(String restaurantId, Map<String, Map<String, Integer>> orders, String paymentType, String deliveryAddress);

    String makePayment(String restaurantId, String orderId, String paymentType, Long cardNumber, String deliveryAddress) throws InvalidCardNumberException;

    List<MyOrders> viewOrders(int pageNumber, int pageSize);

    List<MyOrderItems> viewItemsOfOrder(String orderId, int pageNumber, int pageSize);

    MyOrders getOrderDetails(String orderId);

    String cancelOrder(String orderId);

    String cancelItem(String orderId);

    String changeDeliverAddress(String deliveryAddress, String orderId);
}
