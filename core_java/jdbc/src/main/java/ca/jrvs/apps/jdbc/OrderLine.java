package ca.jrvs.apps.jdbc;

public class OrderLine {
    private int quantity;
    private String productCode;
    private String productName;
    private int productSize;
    private String productVariety;
    private Float productPrice;

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public String getProductCode() {
        return productCode;
    }

    public void setProductCode(String productCode) {
        this.productCode = productCode;
    }

    public String getProductName() {
        return productName;
    }

    public void setProductName(String productName) {
        this.productName = productName;
    }

    public int getProductSize() {
        return productSize;
    }

    public void setProductSize(int productSize) {
        this.productSize = productSize;
    }

    public String getProductVariety() {
        return productVariety;
    }

    public void setProductVariety(String productVariety) {
        this.productVariety = productVariety;
    }

    public Float getProductPrice() {
        return productPrice;
    }

    public void setProductPrice(Float productPrice) {
        this.productPrice = productPrice;
    }

    @Override
    public String toString() {
        return "OrderLine{" +
                "quantity=" + quantity +
                ", productCode='" + productCode + '\'' +
                ", productName='" + productName + '\'' +
                ", productSize=" + productSize +
                ", productVariety='" + productVariety + '\'' +
                ", productPrice=" + productPrice +
                '}';
    }
}