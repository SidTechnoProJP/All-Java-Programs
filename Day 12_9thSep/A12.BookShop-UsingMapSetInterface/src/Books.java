public class Books {
    private int serialNo;
    private String bookName;
    private int price;
    private int quantity;

    public Books(int serialNo, String bookName, int price, int quantity) {
        this.serialNo = serialNo;
        this.bookName = bookName;
        this.price = price;
        this.quantity = quantity;
    }

    //return the number of books present
    public int getQuantity() {
        return this.quantity;
    }

    public void setQuantity(int qty) {
        this.quantity -= qty;
    }

    public String getBookName() {
        return this.bookName;
    }

    public int getSerialNo() {
        return serialNo;
    }

    public int getPrice() {
        return this.price;
    }
}
