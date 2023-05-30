public class Stream {
    int id;
    int maxUsers;

    public Stream() {
    }

    public Stream(int id, int maxUsers) {
        this.id = id;
        this.maxUsers = maxUsers;
    }

    public int getId() {
        return id;
    }

    public int getMaxUsers() {
        return maxUsers;
    }
}
