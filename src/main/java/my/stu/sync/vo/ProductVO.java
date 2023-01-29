package my.stu.sync.vo;

public class ProductVO {
    int id;
    String name;
    int price;
    int quantity;

    public ProductVO() {

    }

    @Override
    public String toString() {
        return "id : " + this.id + " quantity : " + this.quantity;
    }

    public ProductVO(int id, String name, int price, int quantity) {
        this.id = id;
        this.name = name;
        this.price = price;
        this.quantity = quantity;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getPrice() {
        return price;
    }

    public void setPrice(int price) {
        this.price = price;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public void decreaseQuantity(int quantity){
        if(this.quantity - quantity < 0)
            throw new RuntimeException("수량이 0개 이하");
        this.quantity = this.quantity - quantity;
    }
}
