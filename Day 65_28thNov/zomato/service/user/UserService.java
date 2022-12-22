package zomatomodified.zomato.service.user;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;
import zomatomodified.zomato.coustomexcptions.*;
import zomatomodified.zomato.entity.Categories;
import zomatomodified.zomato.entity.Users;
import zomatomodified.zomato.model.*;
import zomatomodified.zomato.repository.CategoryRepository;
import zomatomodified.zomato.repository.UserRepository;
import zomatomodified.zomato.security.userdetails.JWTUserDetailsService;
import zomatomodified.zomato.security.utility.JWTUtility;
import zomatomodified.zomato.service.*;
import zomatomodified.zomato.service.system.SystemInterface;
import zomatomodified.zomato.service.system.TokenInterface;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.time.*;
import java.util.*;

@Service
public class UserService implements LoginInterface, HomeInterface, FilterInterface, CategoriesInterface, DishInterface, CardInterface, OrderInterface, RatingInterface, SearchInterface, RoleInterface {

    @Autowired
    private JdbcTemplate jdbcTemplate;

    @Autowired
    private JavaMailSender javaMailSender;

    @Autowired
    private SystemInterface systemInterface;

    @Autowired
    private TokenInterface tokenInterface;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private CategoryRepository categoryRepository;

    @Autowired
    private AuthenticationManager authenticationManager;

    @Autowired
    private JWTUtility jwtUtility;

    @Autowired
    private JWTUserDetailsService jwtUserDetailsService;

    @Autowired
    private BCryptPasswordEncoder passwordEncoder;

    private static final String userProfileFilePath = "C:\\Users\\Chandan H U\\Desktop\\userProfilePhoto";


    /**
     * ******Login Interface******
     */

    @Override
    public Map<String, String> signIn(String email, String password) throws LoginFailedException {
        try {
            Map<String, String> result = new HashMap<>();
            List<Users> user = userRepository.findByEmailAndIsDeleted(email, "false");
            if (user.isEmpty()) {
                result.put("message", "Invalid credentials");
                return result;
            } else if (user.get(0).getAccountStatus().equalsIgnoreCase(AccountStatus.NOTVERIFIED.toString())) {
                result.put("message", "verify the email before login");
                return result;
            } else {
                authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(email, password));
                final UserDetails userDetail = jwtUserDetailsService.loadUserByUsername(email);
                result.put("message", "login successful");
                result.put("username", email);
                result.put("token", jwtUtility.generateToken(userDetail));
                return result;
            }
        } catch (BadCredentialsException exception) {
            throw new LoginFailedException("Invalid credentials");
        }
    }

    @Override
    public String signUp(Users user, MultipartFile profilePhoto) throws SignupException {
        try {
            String userId = systemInterface.generateId();
            if (userRepository.findById(userId).isPresent())
                signUp(user, profilePhoto);

            user.setUserId(userId);
            user.setCreatedDate(LocalDate.now());
            user.setAccountStatus(AccountStatus.NOTVERIFIED.toString());

            if (user.getGstNumber() != null && user.getGstNumber().length() == 15)
                user.setRole(Role.ADMIN.toString());
            else
                user.setRole(Role.CUSTOMER.toString());

            if (profilePhoto.getOriginalFilename() != null) {
                user.setProfilePhoto(userProfileFilePath + File.separator + profilePhoto.getOriginalFilename());
                profilePhoto.transferTo(Path.of(userProfileFilePath + File.separator + profilePhoto.getOriginalFilename()));
            } else user.setProfilePhoto(null);

            if (systemInterface.checkForDuplicateRegistration(user.getEmail(), user.getPhoneNumber())) {
                userRepository.save(user);
                return "Account successfully created";
            }
            return "Email or Phone number already registered";

        } catch (Exception exception) {
            throw new SignupException("Failed to register.Please provide valid details");
        }
    }

    @Override
    public String forgotPassword(String email) throws UsernameNotFoundException {
        if (!systemInterface.checkAvailabilityOfEmail(email)) {
            String token = tokenInterface.generate();
            if (!tokenInterface.verifyToken(token))
                forgotPassword(email);

            SimpleMailMessage emailContent = new SimpleMailMessage();
            emailContent.setFrom("raspberrypi001025@gmail.com");
            emailContent.setTo(userRepository.findByUserId(systemInterface.getUserId()).get(0).getEmail());
            emailContent.setSubject("Password Forgot Request");
            emailContent.setText("Click on below link to Reset password.");
            emailContent.setText("\nhttp://localhost:8080/reset-password/token=" + token);
            javaMailSender.send(emailContent);
            tokenInterface.save(token, email, LocalDateTime.now(), LocalDateTime.now().plusMinutes(30L), TokenStatus.ACTIVE.toString());
            return "If the mail address entered matches your account , you will receive an email with a link to reset your password";
        }
        throw new UsernameNotFoundException("username not found");
    }

    @Override
    public String signOut() throws SignOutException {
        try {
            return "sign out successful";
        } catch (Exception exception) {
            throw new SignOutException("sign out failed.try again after sometime.");
        }
    }

    @Override
    public String resetPassword(String token, String newPassword, String confirmPassword) throws SessionIdExpiredException, TokenExpiredException {
        List<Tokens> tokens = tokenInterface.getTokenDetail(token);
        if (!tokens.isEmpty()) {
            if (newPassword.equals(confirmPassword)) {
                if (tokens.get(0).getExpireAt().compareTo(LocalDateTime.now()) >= 0) {
                    jdbcTemplate.update("update users set password = ? where username = ? and is_deleted = 'false' ", newPassword, tokens.get(0).getUserId());
                    tokenInterface.remove(token, tokens.get(0).getUserId());
                    return "Password updated successfully";
                }
                throw new TokenExpiredException("token expired");
            }
            return "password not matched";
        }
        throw new SessionIdExpiredException("token already used");
    }

    @Override
    public String changePassword(String newPassword, String confirmPassword) {
        try {
            if (newPassword.equals(confirmPassword)) {
                jdbcTemplate.update("update users set password = ? where user_id = ? and is_deleted = 'false'",
                        passwordEncoder.encode(newPassword), systemInterface.getUserId());
                return "Password updated successfully";
            }
            throw new Exception("password miss matched");
        } catch (Exception exception) {
            throw new NullPointerException("login to perform action");
        }
    }

    @Override
    public String changeProfilePhoto(MultipartFile profilePhoto) throws IOException, UpdateFailedException {
        if (profilePhoto.getOriginalFilename() != null) {
            deleteOldProfilePhoto();
            String setProfilePhoto = userProfileFilePath + File.separator + profilePhoto.getOriginalFilename();
            jdbcTemplate.update("update users set profile_photo = ? where user_id = ? and is_deleted = 'false'",
                    setProfilePhoto, systemInterface.getUserId());
            profilePhoto.transferTo(Path.of(setProfilePhoto));
            return "Profile photo uploaded successfully";
        }
        throw new UpdateFailedException("Profile photo uploading failed");
    }

    @Override
    public byte[] viewProfilePhoto() throws IOException {
        Users user = userRepository.findByUserId(systemInterface.getUserId()).get(0);
        if (user.getProfilePhoto() != null) {
            String photo = user.getProfilePhoto();
            return Files.readAllBytes(Path.of(photo));
        }
        throw new NullPointerException("Photo not uploaded");
    }

    @Override
    public String deleteOldProfilePhoto() throws IOException {
        Users user = userRepository.findByUserId(systemInterface.getUserId()).get(0);
        if (user.getProfilePhoto() != null) {
            String photo = user.getProfilePhoto();
            Files.deleteIfExists(Path.of(photo));
            jdbcTemplate.update("update users set profile_photo = ? where user_id = ? and is_deleted = 'false'",
                    null, systemInterface.getUserId());
            return "photo deleted successfully";
        }
        throw new NullPointerException("Photo not uploaded");
    }

    /**
     * ******Home Interface******
     */

    @Override
    public List<Restaurants> topRatedRestaurants(int pageNumber, int pageSize) {
        int offset = pageSize * (pageNumber - 1);
        return jdbcTemplate.query("select * from restaurants where isDeleted = 'false' order by ratings DESC limit ? offset ? ",
                new BeanPropertyRowMapper<>(Restaurants.class), pageSize, offset);
    }

    @Override
    public List<Categories> viewCategories(int pageNumber, int pageSize) {

        int offset = pageSize * (pageNumber - 1);
        return jdbcTemplate.query("Select * from categories where is_deleted = 'false' limit ? offset ?",
                new BeanPropertyRowMapper<>(Categories.class), pageSize, offset);

    }

    @Override
    public byte[] viewRestaurantPhoto(String restaurantId) throws IOException {

        Restaurants restaurant = jdbcTemplate.query("select * from restaurants where restaurant_id = ? and isDeleted = 'false'",
                new BeanPropertyRowMapper<>(Restaurants.class), restaurantId).get(0);
        if (restaurant.getRestaurantPhoto() != null) {
            String photo = restaurant.getRestaurantPhoto();
            return Files.readAllBytes(Path.of(photo));
        }
        throw new NullPointerException("Photo not uploaded");
    }


    /**
     * ******Filter Interface******
     */

    @Override
    public List<String> viewSortOptions() {

        List<String> sortOptions = new ArrayList<>();
        sortOptions.add("Top Rated");
        sortOptions.add("Cost Low To High");
        sortOptions.add("Cost High To Low");
        return sortOptions;

    }

    @Override
    public List<String> otherFilters() {

        List<String> filters = new ArrayList<>();
        filters.add("Open Now");
        filters.add("Free Delivery");
        filters.add("CreditCard");
        return filters;

    }

    @Override
    public List<String> showPaymentType() {
        List<String> paymentTypes = new ArrayList<>();
        for (PaymentTypes types : PaymentTypes.values())
            paymentTypes.add(types.toString());
        return paymentTypes;
    }

    @Override
    public List<Restaurants> filterAll(FilterModel filterModel, int pageSize, int pageNumber) {
        int offset = pageSize * (pageNumber - 1);

        String query = "Select * from Restaurants where isDeleted = 'false' and ";
        String categoryFilter = "restaurant_id in(select restaurant_id from restaurantCategory where category_id = ";
        String freeDeliveryFilter = "restaurant_id in( select restaurant_id from restaurants where delivery_charge = 0 and isDeleted = 'false')";
        String open = "restaurant_id in(select restaurant_id from restaurants where close_at >= CURRENT_TIME() and isDeleted = 'false')";
        String payment = "restaurant_id in(select restaurant_id from restaurantPayments where paymentType = 'CREDITCARD' and isDeleted = 'false')";
        String lowToHigh = " restaurant_id in(select restaurant_id from dish where itemPrice <= ? and isDeleted = 'false' order by itemPrice ASC)";
        String highToLow = " restaurant_id in(select restaurant_id from dish where itemPrice <= ? and isDeleted = 'false' order by itemPrice DESC)";
        String topRated = " order by ratings";
        String paging = " limit ? offset ?";
        String separator = " and ";

        if (filterModel.getCategoryId() != null)
            query += categoryFilter + "'" + filterModel.getCategoryId() + "' and isDeleted = 'false')" + separator;

        if (filterModel.isOpenNow())
            query += open + separator;
        if (filterModel.isCreditCard())
            query += payment + separator;
        if (filterModel.isFreeDelivery())
            query += freeDeliveryFilter + separator;

        if (query.endsWith(" and "))
            query = query.substring(0, (query.length() - 5));

        if (filterModel.getPriceRange() > 0)
            if (filterModel.getSortBy().equalsIgnoreCase("Cost High To Low"))
                return jdbcTemplate.query(query + separator + highToLow + paging, new BeanPropertyRowMapper<>(Restaurants.class),
                        filterModel.getPriceRange(), pageSize, offset);
            else if (filterModel.getSortBy().equalsIgnoreCase("Cost Low To High"))
                return jdbcTemplate.query(query + separator + lowToHigh + paging, new BeanPropertyRowMapper<>(Restaurants.class),
                        filterModel.getPriceRange(), pageSize, offset);
            else if (filterModel.getSortBy().equalsIgnoreCase("Top Rated"))
                return jdbcTemplate.query(query + topRated + paging, new BeanPropertyRowMapper<>(Restaurants.class),
                        pageSize, offset);
            else
                return jdbcTemplate.query(query + separator + lowToHigh + paging, new BeanPropertyRowMapper<>(Restaurants.class),
                        filterModel.getPriceRange(), pageSize, offset);

        if (filterModel.getSortBy().equalsIgnoreCase("Cost High To Low"))
            return jdbcTemplate.query(query + separator + highToLow + paging, new BeanPropertyRowMapper<>(Restaurants.class),
                    "itemPrice", pageSize, offset);
        else if (filterModel.getSortBy().equalsIgnoreCase("Cost Low To High"))
            return jdbcTemplate.query(query + separator + lowToHigh + paging, new BeanPropertyRowMapper<>(Restaurants.class),
                    "itemPrice", pageSize, offset);
        else if (filterModel.getSortBy().equalsIgnoreCase("Top Rated"))
            return jdbcTemplate.query(query + topRated + paging, new BeanPropertyRowMapper<>(Restaurants.class),
                    pageSize, offset);


        if (query.length() > 60)
            return jdbcTemplate.query(query + paging, new BeanPropertyRowMapper<>(Restaurants.class), pageSize, offset);

        else return null;
    }

    /**
     * ******Categories Interface******
     */


    @Override
    public List<Categories> showAllCategoriesOfRestaurant(String restaurantId, int pageSize, int pageNumber) {

        int offset = pageSize * (pageNumber - 1);
        List<RestaurantCategory> restaurantCategories = jdbcTemplate.query("Select * from restaurantCategory where " +
                        "restaurant_id = ? and isDeleted = 'false' limit ? offset ?",
                new BeanPropertyRowMapper<>(RestaurantCategory.class), restaurantId, pageSize, offset);
        List<Categories> categories = new ArrayList<>();
        for (RestaurantCategory restaurantCategory : restaurantCategories)
            categories.add(categoryRepository.findByCategoryIdAndIsDeleted(restaurantCategory.getCategoryId(), "false").get(0));

        return categories;
    }


    /**
     * ******Dish Interface******
     */

    @Override
    public List<Dish> viewDishOfRestaurant(String restaurantId, int pageSize, int pageNumber) {

        int offset = pageSize * (pageNumber - 1);
        return jdbcTemplate.query("Select * from Dish where restaurant_id = ? and isDeleted = 'false' limit ? offset ?",
                new BeanPropertyRowMapper<>(Dish.class), restaurantId, pageSize, offset);

    }

    @Override
    public List<Dish> viewDishOfCategory(String restaurantId, String categoryId, int pageSize, int pageNumber) {

        int offset = pageSize * (pageNumber - 1);

        return jdbcTemplate.query("Select * from Dish where restaurant_id = ? and category_id = ? and isDeleted = 'false' limit ? offset ?",
                new BeanPropertyRowMapper<>(Dish.class), restaurantId, categoryId, pageSize, offset);

    }

    @Override
    public List<Dish> getDishDetails(String itemId) {
        List<Dish> dishes = jdbcTemplate.query("Select * from Dish where itemId = ? and isDeleted = 'false'",
                new BeanPropertyRowMapper<>(Dish.class), itemId);
        return dishes.isEmpty() ? null : dishes;
    }

    /**
     * ******Cards Interface******
     */

    @Override
    public String addCards(long cardNumber) throws InvalidCardNumberException {

        if (String.valueOf(cardNumber).length() != 12)
            throw new InvalidCardNumberException("Invalid card number");
        if (systemInterface.verifyCard(cardNumber, systemInterface.getUserId())) {
            jdbcTemplate.update("Insert into cards values(?,?)", systemInterface.getUserId(), cardNumber);
            return "card added successfully";
        }
        throw new NullPointerException("Card already added");

    }

    @Override
    public String removeCard(long cardNumber) throws InvalidCardNumberException {
        if (String.valueOf(cardNumber).length() != 12)
            throw new InvalidCardNumberException("Invalid card number");
        if (!systemInterface.verifyCard(cardNumber, systemInterface.getUserId())) {
            jdbcTemplate.update("delete from cards where cardNumber = ? and user_id = ?",
                    cardNumber, systemInterface.getUserId());
            return "card Removed Successfully";
        }
        throw new NullPointerException("Card not found");

    }

    @Override
    public List<Cards> viewAllCards() {

        return jdbcTemplate.query("Select * from cards where user_id = ?",
                new BeanPropertyRowMapper<>(Cards.class), systemInterface.getUserId());
    }

    /**
     * ******Order Interface******
     */

    @Override
    public List<MyOrders> addOrders(String restaurantId, Map<String, Map<String, Integer>> orders, String paymentType, String deliveryAddress) {
        systemInterface.verifyOrderItems(orders);


        String orderId = systemInterface.generateId();

        if (!systemInterface.verifyOrderId(orderId))
            addOrders(restaurantId, orders, paymentType, deliveryAddress);

        jdbcTemplate.update("Insert into myOrders values(?,?,?,?,?,?,?,?)", orderId, systemInterface.getUserId(), restaurantId,
                LocalDateTime.now(), 0, deliveryAddress, paymentType, OrderStatus.PAYMENTPENDING.toString());

        int totalBill = placeOrder(orderId, orders);

        jdbcTemplate.update("update myOrders set totalBill = ? where orderId = ?", totalBill, orderId);
        return jdbcTemplate.query("select * from myOrders where orderId = ?",
                new BeanPropertyRowMapper<>(MyOrders.class), orderId);
    }

    private int placeOrder(String orderId, Map<String, Map<String, Integer>> orders) {
        int totalBill = 0;
        for (String categoryId : orders.keySet()) {

            List<RestaurantCategory> category = jdbcTemplate.query("select * from restaurantCategory where category_id = ? " +
                    "and isDeleted = 'false'", new BeanPropertyRowMapper<>(RestaurantCategory.class), categoryId);

            if (category.isEmpty()) {
                systemInterface.removeOrder(orderId);
                throw new RuntimeException("Invalid category selection");

            }
            Map<String, Integer> items = orders.get(categoryId);

            for (String itemId : items.keySet()) {

                List<Dish> itemDetails = getDishDetails(itemId);

                if (itemDetails.isEmpty()) {
                    systemInterface.removeOrder(orderId);
                    throw new RuntimeException("Invalid item selection");
                }

                totalBill += items.get(itemId) * itemDetails.get(0).getItemPrice();
                jdbcTemplate.update("Insert into myOrderItems values(?,?,?,?,?,?)", orderId, itemId,
                        itemDetails.get(0).getItemName(), categoryId, category.get(0).getCategoryName(), items.get(itemId));
            }
        }
        return totalBill;
    }

    @Override
    public String makePayment(String restaurantId, String orderId, String paymentType, Long cardNumber, String deliveryAddress) throws InvalidCardNumberException {
        if (systemInterface.verifyOrderId(orderId))
            return "make order before making payment";

        if (systemInterface.verifyPaymentAvailability(restaurantId, paymentType)) {
            updatePaymentType(paymentType, orderId);
            if (cardNumber != null) {
                if (Long.toString(cardNumber).length() == 16) {
                    jdbcTemplate.update("update myOrders set orderDateTime = ? where orderId = ?", LocalDateTime.now(),
                            orderId);
                    return bookOrderToHotel(restaurantId, orderId, deliveryAddress);
                }
                throw new InvalidCardNumberException("Invalid card number");
            } else {
                jdbcTemplate.update("update myOrders set orderDateTime = ? where orderId = ?", LocalDateTime.now(),
                        orderId);
                return bookOrderToHotel(restaurantId, orderId, deliveryAddress);
            }
        }
        return "Select other payment type";
    }

    @Override
    public List<MyOrders> viewOrders(int pageNumber, int pageSize) {
        int offset = pageSize * (pageNumber - 1);
        List<MyOrders> myOrders = jdbcTemplate.query("select * from myOrders where user_id = ? limit ? offset ?",
                new BeanPropertyRowMapper<>(MyOrders.class), systemInterface.getUserId(), pageSize, offset);
        return myOrders.isEmpty() ? null : myOrders;
    }

    @Override
    public List<MyOrderItems> viewItemsOfOrder(String orderId, int pageNumber, int pageSize) {
        int offset = pageSize * (pageNumber - 1);
        List<MyOrderItems> myOrderItems = jdbcTemplate.query("select * from myOrderItems where orderId = ? limit ? offset ?",
                new BeanPropertyRowMapper<>(MyOrderItems.class), orderId, pageSize, offset);
        return myOrderItems.isEmpty() ? null : myOrderItems;
    }

    @Override
    public MyOrders getOrderDetails(String orderId) {
        if (systemInterface.verifyOrderId(orderId))
            throw new NullPointerException("order not found");
        return jdbcTemplate.query("select * from myOrders where orderId = ?",
                new BeanPropertyRowMapper<>(MyOrders.class), orderId).get(0);
    }

    @Override
    public String cancelOrder(String orderId) {
        List<MyOrders> myOrders = jdbcTemplate.query("select * from myOrders where orderId = ?",
                new BeanPropertyRowMapper<>(MyOrders.class), orderId);
        Period dateElapsed = Period.between(myOrders.get(0).getOrderDateTime().toLocalDate(), LocalDate.now());
        Duration timeElapsed = Duration.between(myOrders.get(0).getOrderDateTime().toLocalTime(), LocalTime.now());
        if (myOrders.isEmpty())
            return "Invalid Cancellation of order";
        else if (myOrders.get(0).getOrderStatus().equalsIgnoreCase(OrderStatus.PAYMENTPENDING.toString()))
            return systemInterface.cancelOrder(myOrders.get(0));
        else if (myOrders.get(0).getOrderStatus().equalsIgnoreCase(OrderStatus.CONFIRMED.toString()))
            if (dateElapsed.getDays() < 1 && timeElapsed.toMinutes() < 3)
                return systemInterface.cancelOrder(myOrders.get(0));
            else return "Time Exceed for order cancellation";
        else return "Unable to cancel order";
    }

    @Override
    public String cancelItem(String orderId) {
        return null;
    }

    @Override
    public String changeDeliverAddress(String deliveryAddress, String orderId) {
        if (systemInterface.verifyOrderId(orderId))
            return "Invalid order";
        jdbcTemplate.update("update myOrders set deliveryAddress = ? where orderId = ?", deliveryAddress, orderId);
        return "address changed successfully";
    }

    private void updatePaymentType(String paymentType, String orderId) {
        jdbcTemplate.update("update myOrders set paymentMethod = ? where orderId = ?", paymentType, orderId);
    }

    private String bookOrderToHotel(String restaurantId, String orderId, String deliveryAddress) {

        int totalBill = jdbcTemplate.query("select * from myOrders where orderId = ?",
                new BeanPropertyRowMapper<>(MyOrders.class), orderId).get(0).getTotalBill();
        System.out.println(totalBill);
        jdbcTemplate.update("update myOrders set orderStatus = ? where orderId = ?", OrderStatus.CONFIRMED.toString(),
                orderId);
        jdbcTemplate.update("insert into restaurantOrders values(?,?,?,?,?,?)", restaurantId, orderId, totalBill,
                LocalDate.now(), deliveryAddress, OrderStatus.CONFIRMED.toString());
        return "your order confirmed.Thank you";

    }


    /**
     * ******Rating Interface******
     */

    @Override
    public String rating(String orderId, int numberOfStarRated, String feedback) {
        if (numberOfStarRated <= 5 && numberOfStarRated > 0) {
            List<MyOrders> myOrder = verifyOrderConformation(orderId);
            int numberOfRatings = jdbcTemplate.query("select * from restaurants where restaurant_id = ? and isDeleted = 'false'",
                    new BeanPropertyRowMapper<>(Restaurants.class), myOrder.get(0).getRestaurantId()).get(0).getNumberOfRatings() + 1;

            if (jdbcTemplate.query("select * from ratingDetails where orderId = ?",
                    new BeanPropertyRowMapper<>(RatingDetails.class), orderId).isEmpty())
                return rateTheRestaurant(orderId, numberOfStarRated, feedback, myOrder.get(0).getRestaurantId(), numberOfRatings);
            else
                return alterRatedTheRestaurant(orderId, numberOfStarRated, feedback, myOrder.get(0).getRestaurantId(), numberOfRatings - 1);
        }
        return "select rating in between 1 to 5";
    }

    private String alterRatedTheRestaurant(String orderId, int numberOfStarRated, String feedback, String restaurantId, int numberOfRatings) {
        jdbcTemplate.update("update ratingDetails set numberOfStarRated = ? , feedback = ? where orderId = ?",
                numberOfStarRated, feedback, orderId);
        double ratings = systemInterface.calculateRatings(restaurantId);
        jdbcTemplate.update("update restaurants set numberOfRatings = ? , ratings = ? where restaurant_id = ?",
                numberOfRatings, ratings, restaurantId);
        return "thank you for rating";
    }

    private String rateTheRestaurant(String orderId, int numberOfStarRated, String feedback, String restaurantId, int numberOfRatings) {
        jdbcTemplate.update("insert into ratingDetails values(?,?,?,?)", restaurantId, orderId, numberOfStarRated, feedback);
        double ratings = systemInterface.calculateRatings(restaurantId);
        jdbcTemplate.update("update restaurants set numberOfRatings = ? , ratings = ? where restaurant_id = ?",
                numberOfRatings, ratings, restaurantId);
        return "thank you for rating";
    }

    private List<MyOrders> verifyOrderConformation(String orderId) {
        List<MyOrders> myOrder = jdbcTemplate.query("Select * from myOrders where orderId = ? and orderStatus = 'CONFIRMED'",
                new BeanPropertyRowMapper<>(MyOrders.class), orderId);
        if (myOrder.isEmpty())
            throw new NullPointerException("you cannot rate now");
        return myOrder;
    }

    @Override
    public String addToFavourite(String restaurantId) {
        List<Restaurants> restaurants = jdbcTemplate.query("select * from restaurants where restaurant_id = ? and isDeleted = 'false'",
                new BeanPropertyRowMapper<>(Restaurants.class), restaurantId);
        if (restaurants.isEmpty())
            return "restaurant not found";
        long favouriteCount = restaurants.get(0).getFavouriteCount() + 1;
        List<Favourites> favourites = jdbcTemplate.query("select * from favourites where restaurant_id = ? and user_id = ?",
                new BeanPropertyRowMapper<>(Favourites.class), restaurantId, systemInterface.getUserId());
        if (favourites.isEmpty()) {
            jdbcTemplate.update("insert into favourites values(?,?)", systemInterface.getUserId(), restaurantId);
            updateFavouriteCount(favouriteCount, restaurantId);
            return "restaurant added to your favourite list";
        }
        return deleteFavourite(restaurantId);
    }

    private void updateFavouriteCount(long favouriteCount, String restaurantId) {
        jdbcTemplate.update("update restaurants set favouriteCount = ? where restaurant_id = ?",
                favouriteCount, restaurantId);
    }

    @Override
    public String deleteFavourite(String restaurantId) {
        List<Restaurants> restaurants = jdbcTemplate.query("select * from restaurants where restaurant_id = ? and isDeleted = 'false'",
                new BeanPropertyRowMapper<>(Restaurants.class), restaurantId);
        if (!restaurants.isEmpty()) {
            jdbcTemplate.update("delete from favourites where restaurant_id = ?", restaurantId);
            updateFavouriteCount(restaurants.get(0).getFavouriteCount() - 1, restaurantId);
            return "removed from favourites";
        }
        return "unable to remove from favourites";
    }

    @Override
    public List<Restaurants> viewFavorites() {
        List<Favourites> favourites = jdbcTemplate.query("select * from favourites where user_id = ?",
                new BeanPropertyRowMapper<>(Favourites.class), systemInterface.getUserId());
        List<Restaurants> restaurants = new ArrayList<>();
        for (Favourites restaurantId : favourites)
            restaurants.add(jdbcTemplate.query("select * from restaurants where restaurant_id = ? and isDeleted = 'false'",
                    new BeanPropertyRowMapper<>(Restaurants.class), restaurantId.getRestaurantId()).get(0));
        return restaurants;
    }

    /**
     * ******Search Interface******
     */

    @Override
    public List<?> search(String field, int pageSize, int pageNumber) {
        int offset = pageSize * (pageNumber - 1);
        List<Restaurants> restaurants = jdbcTemplate.query("select * from restaurants where restaurant_name = ? and " +
                "isDeleted = 'false' limit ? offset ?", new BeanPropertyRowMapper<>(Restaurants.class), field, pageSize, offset);

        List<Categories> categories = jdbcTemplate.query("select * from categories where category_name = ? and " +
                "is_deleted = 'false' limit ? offset ?", new BeanPropertyRowMapper<>(Categories.class), field, pageSize, offset);

        List<Restaurants> restaurants1 = new ArrayList<>();
        if (!categories.isEmpty())
            restaurants1 = jdbcTemplate.query("select * from restaurants where restaurant_id in" +
                            "(select restaurant_id from restaurantCategory where category_id = ? and isDeleted = 'false')" +
                            " and isDeleted = 'false' limit ? offset ?", new BeanPropertyRowMapper<>(Restaurants.class),
                    categories.get(0).getCategoryId(), pageSize, offset);

        List<Restaurants> restaurants2 = jdbcTemplate.query("select * from restaurants where restaurant_id in" +
                        "(select restaurant_id from dish where itemName = ? and isDeleted = 'false') " +
                        "and isDeleted = 'false' limit ? offset ?", new BeanPropertyRowMapper<>(Restaurants.class),
                field, pageSize, offset);

        if (!restaurants.isEmpty())
            return restaurants;
        if (!restaurants1.isEmpty())
            return restaurants1;
        if (!restaurants2.isEmpty())
            return restaurants2;
        throw new NullPointerException("no data found");
    }


    /**
     * ******Role Interface******
     */

    @Override
    public String changeRole(String gstNumber) throws InvalidCardNumberException {
        if (systemInterface.verifyGstNumber(gstNumber)) {
            jdbcTemplate.update("update users set role = ? where user_id = ? ", Role.ADMIN.toString(), systemInterface.getUserId());
            return "role changed successfully.please logout and login to perform admin operation";
        }
        throw new InvalidCardNumberException("enter valid gst number");
    }

    @Override
    public String editUser(Users users) {
        jdbcTemplate.update("update users set username = ? , gender = ? , age = ? ", users.getUsername(), users.getGender(), users.getAge());
        List<Users> user = userRepository.findByEmail(users.getEmail());
        if (user.isEmpty()) {
            jdbcTemplate.update("update users set email = ? , account_status = ?", users.getEmail(), AccountStatus.NOTVERIFIED);
            return "updated successfully please verify email";
        }
        return "updated successfully";
    }

    @Override
    public String deleteAccount() {
        if (userRepository.findByUserIdAndIsDeleted(systemInterface.getUserId(), "false").isEmpty())
            return "Account already deleted";
        jdbcTemplate.update("update users set is_deleted = 'true' where user_id = ?", systemInterface.getUserId());
        return "Account deleted successfully";
    }

}

