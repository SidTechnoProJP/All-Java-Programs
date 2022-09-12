public class Invoice {
    private String partNumber;
    private String partDescription;
    private int quantity;
    private double price;
    public Invoice(String pNum, String pDesc, int qty, double prc) {
        if (pNum != null) partNumber=pNum;
        else partNumber="0";

        if (pDesc != null) partDescription=pDesc;
        else partDescription="0";

        if (qty > 0) quantity=qty;
        else quantity=0;

        if (prc > 0.0) price=prc;
        else price=0;
    }

    public String getPartNum(){
        return partNumber;
    }

    public String getPartDesc(){
        return partDescription;
    }

    public int getQuantity(){
        return quantity;
    }

    public double getPrice(){
        return price;
    }

    public void setPartNum(String pNum){
        if (pNum != null) {partNumber=pNum;}
        else {partNumber="0";}
    }

    public void setPartDesc(String pDesc){
        if (pDesc != null) {partDescription=pDesc;}
        else {partDescription="0";}
    }

    public void setQuantity(int qty){
        if (qty > 0) {quantity=qty;}
        else {quantity=0;}
    }

    public void setPrice(double prc){
        if (prc > 0.0) {price=prc;}
        else {price=0.0;}
    }

    public double getInvoiceAmount(){
        return (double)quantity*price;
    }

    public class InvoiceTest {
        public void main (String args[]){
            Invoice invoice1=new Invoice ("A5544","Hammer",500, 250);
            Invoice invoice2=new Invoice ("A5542", "Spade", 300, 50);

            System.out.printf("Invoice 1: %s\t%s\t%d\t$%.2f\n", invoice1.getPartNum(),
                    invoice1.getPartDesc(), invoice1.getQuantity(), invoice1.getPrice());
            System.out.printf("Invoice 2: %s\t%s\t%d\t$%.2f\n", invoice2.getPartNum(),
                    invoice2.getPartDesc(), invoice2.getQuantity(), invoice2.getPrice());
        }
    }
}

