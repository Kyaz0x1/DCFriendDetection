package net.kyaz0x1.dcfrienddetection.manager;

import net.kyaz0x1.dcfrienddetection.api.models.Friend;

import java.util.ArrayList;
import java.util.List;

public final class FriendManager {

    private static FriendManager INSTANCE;

    private List<Friend> friends;
    private List<Friend> friendsOlds;

    private FriendManager(){
        this.friends = new ArrayList<>();
        this.friendsOlds = new ArrayList<>();
    }

    public List<Friend> getFriends() {
        return friends;
    }

    public void setFriends(List<Friend> friends) {
        this.friends = friends;
    }

    public List<Friend> getFriendsOlds() {
        return friendsOlds;
    }

    public void setFriendsOlds(List<Friend> friendsOlds) {
        this.friendsOlds = friendsOlds;
    }

    public static FriendManager getInstance(){
        if(INSTANCE == null){
            synchronized(FriendManager.class){
                if(INSTANCE == null){
                    INSTANCE = new FriendManager();
                }
            }
        }
        return INSTANCE;
    }

}