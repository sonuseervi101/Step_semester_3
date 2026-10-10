package encapsulation.assigment_problems;

class Cart {
    private final String cartId;      // fixed at creation
    private final double[] prices;    // private: never returned
    private int itemCount;            // how many prices are stored so far

    public Cart(String cartId, int maxItems) {
        this.cartId = cartId;
        this.prices = new double[maxItems];
        this.itemCount = 0;
    }

    public void addItem(double price) {
        if (itemCount >= prices.length) {
            System.out.println("Rejected: cart is full");
            return;
        }
        prices[itemCount] = price;
        itemCount++;
    }

    // Computed fresh every time: loops over the private array
    public double getTotal() {
        double total = 0;
        for (int i = 0; i < itemCount; i++) {
            total = total + prices[i];
        }
        return total;
    }

    public int getItemCount() {
        return itemCount;
    }

    public String getCartId() {
        return cartId;
    }
}

public class CartDemo {
    public static void main(String[] args) {
        Cart cart = new Cart("CART-5", 20);

        cart.addItem(250);
        cart.addItem(99);
        cart.addItem(151);

        System.out.println("Cart " + cart.getCartId());
        System.out.println("getTotal()     -> " + cart.getTotal());
        System.out.println("getItemCount() -> " + cart.getItemCount());
    }
}