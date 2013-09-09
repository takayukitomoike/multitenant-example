package multi;

public class MultiMain {

    private ActionFactory factory;

    private CustomerAction customerAction;
    private HistoryAction historyAction;

    public MultiMain() {
        factory = new ActionFactory();

        customerAction = factory.getCustomerAction();
        historyAction = factory.getHistoryAction();
    }

    public static void main(String[] args) {
        MultiMain main = new MultiMain();
        main.execute();
    }

    private void execute() {
        Customer customer = customerAction.get("cherry", 1);
        System.out.println("Customer name: " + customer.getName());

        factory.close();
    }

}
