import java.util.HashSet;
import java.util.Random;
import java.util.Set;

public class AccountManager {

    private int numUsers;
    private Set<Integer> allIds = new HashSet<>();
    private Set<UserAccount> userAccounts;
    Random random = new Random();

    public AccountManager() {
        this.numUsers = 0;
        this.allIds =  new HashSet<>();
        this.userAccounts = new HashSet<>();
    }

    public int getNewUserId(){
        int id;
        do {
            id = random.nextInt(Integer.MAX_VALUE);
        }
        while(allIds.contains(id));
        allIds.add(id);
        return id;
    }

    public boolean addUser(UserAccount userAccount){
        this.numUsers++;
        this.userAccounts.add(userAccount);
        return true;
    }
}