public  abstract class person {
    short id = 0;
    String Name;
    short age;
    String Mail;
    String password;

    public person( String name, short age,String Mail, String password) {
        id++;
        Name = name;
        this.age = age;
        this.Mail = Mail;
        this.password = password;
    }
}
