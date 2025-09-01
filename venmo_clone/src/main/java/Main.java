public class Main {
    public static void main(String[] args) {
        UserAccount shachar = new UserAccount("shachar");
        UserAccount dalya = new UserAccount("dalya");

        shachar.depositFunds(100);

        shachar.sendMoney(100, dalya);

        dalya.sendMoney(20, shachar);

        System.out.println(dalya);
        System.out.println(shachar);
    }
}