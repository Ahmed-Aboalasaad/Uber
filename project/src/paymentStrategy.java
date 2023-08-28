public interface paymentStrategy {
    public boolean checkCredentials(String username,String password);
    public boolean checkBalance(double amount);
    public void deductBalance(double amount);
    public void refund(double amount);
}
