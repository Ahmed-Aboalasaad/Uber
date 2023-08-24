public interface paymentStrategy {
    public boolean check_credentials(String x,String y);
    public boolean check_balance( double amount);
    public void deducte_balance(double amount);
    public void refund(double amount);
}
