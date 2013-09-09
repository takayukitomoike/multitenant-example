package single;

public class SingleMain {

    private ActionFactory factory;

    private CustomerAction customerAction;
    private HistoryAction historyAction;

    public SingleMain() {
        factory = new ActionFactory();

        customerAction = factory.getCustomerAction();
        historyAction = factory.getHistoryAction();
    }

    public static void main(String[] args) {
        SingleMain main = new SingleMain();
        main.execute();
    }

    private void execute() {
        Customer customer = customerAction.get(1);
        System.out.println("Customer name: " + customer.getName());

        factory.close();
    }

}
