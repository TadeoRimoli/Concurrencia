public class Stream {
    int id;
    int maxUsers;
    int currentUsers;

    public Stream() {
    }

    public Stream(int id, int maxUsers) {
        this.id = id;
        this.maxUsers = maxUsers;
        this.currentUsers=0;
    }

    public int getId() {
        return id;
    }

    public int getMaxUsers() {
        return maxUsers;
    }

    public int getCurrentUsers() {
        return currentUsers;
    }
    public void setCurrentUsers(int param) {
        currentUsers=param;
    }
}
