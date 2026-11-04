
import java.util.ArrayList;

public class Invoice {
    private String title;
    private String customerAddress;
    private ArrayList<LineItem> lineItems;

    public Invoice(String title, String customerAddress) {
        this.title = title;
        this.customerAddress = customerAddress;
        lineItems = new ArrayList<>();
    }

    public void addLineItem(LineItem item) {
        lineItems.add(item);
    }

    public double getTotal() {
        double total = 0;

        for (LineItem item : lineItems) {
            total += item.getTotal();
        }

        return total;
    }

    public String displayInvoice() {
        StringBuilder output = new StringBuilder();

        output.append(title).append("\n");
        output.append(customerAddress).append("\n\n");
        output.append("Product\tQuantity\tUnit Price\tTotal\n");

        for (LineItem item : lineItems) {
            Product product = item.getProduct();

            output.append(product.getName()).append("\t");
            output.append(item.getQuantity()).append("\t");
            output.append(String.format("$%.2f", product.getUnitPrice())).append("\t");
            output.append(String.format("$%.2f", item.getTotal())).append("\n");
        }

        output.append("\nTotal Amount Due: ");
        output.append(String.format("$%.2f", getTotal()));

        return output.toString();
    }
}
