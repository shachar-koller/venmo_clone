public class UserAccount {
    private String username;
    private int userid;
    private int accountBalance;
    private AccountManager accountManager;

    public UserAccount(String username, int userid, int accountBalance, AccountManager accountManager) {
        this.username = username;
        this.userid = userid;
        this.accountBalance = accountBalance;
        this.accountManager = accountManager;
    }

    public UserAccount(String username,  AccountManager accountManager) {
     this.username = username;
     this.accountManager = accountManager;
     this.userid = this.accountManager.getNewUserId();
     this.accountBalance = 0;
    }

    public UserAccount(String username) {
        this.username = username;
        this.accountManager = new AccountManager();
        this.userid = this.accountManager.getNewUserId();
        this.accountBalance = 0;
    }

    public String getUsername() {
        return username;
    }

    public int getUserid() {
        return userid;
    }

    public int getAccountBalance() {
        return accountBalance;
    }

    public boolean setAccountBalance(int accountBalance) {
        if(accountBalance < 0){
            return false;
        }
        else {
            this.accountBalance = accountBalance;
            return true;
        }
    }

    public boolean depositFunds(int amount) {
        this.accountBalance = this.accountBalance + amount;
        return true;
    }

    public boolean setUserid(int userid) {
        if(userid < 0){
            return false;
        }
        else {
            this.userid = userid;
            return true;
        }
    }

    public void receiveMoney(int amount){
        if(amount < 0){
            throw new IllegalArgumentException("amount cannot be negative");
        }
        else {
            this.accountBalance += amount;
        }
    }

    public boolean sendMoney(int amount, UserAccount receiver){
        if(amount < 0){
            throw new IllegalArgumentException("amount cannot be negative");
        }
        if(receiver == null){
            throw new IllegalArgumentException("receiver cannot be null");
        }
        if(accountBalance < amount){
            throw new IllegalArgumentException("amount cannot be less than accountBalance");
        }
        else {
            this.accountBalance -= amount;
            receiver.receiveMoney(amount);
            return true;
        }
    }

    @Override
    public String toString(){
        return this.userid + ":" + this.username + ":" + this.accountBalance;
    }
}