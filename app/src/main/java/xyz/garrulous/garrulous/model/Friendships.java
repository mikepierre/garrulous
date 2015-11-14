package xyz.garrulous.garrulous.model;

/**
 * Created by michaelpierre on 11/14/15.
 */
public class Friendships {
    int id;
    int friendshipId;
    int friendshipWithId;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getFriendshipId() {
        return friendshipId;
    }

    public void setFriendshipId(int friendshipId) {
        this.friendshipId = friendshipId;
    }

    public int getFriendshipWithId() {
        return friendshipWithId;
    }

    public void setFriendshipWithId(int friendshipWithId) {
        this.friendshipWithId = friendshipWithId;
    }
}
