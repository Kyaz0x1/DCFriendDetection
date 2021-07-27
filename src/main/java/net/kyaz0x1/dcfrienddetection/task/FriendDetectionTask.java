package net.kyaz0x1.dcfrienddetection.task;

import net.kyaz0x1.dcfrienddetection.api.DiscordAPI;
import net.kyaz0x1.dcfrienddetection.api.models.Friend;
import net.kyaz0x1.dcfrienddetection.loader.SystemTrayLoader;
import net.kyaz0x1.dcfrienddetection.manager.FriendManager;

import java.awt.TrayIcon;
import java.util.List;
import java.util.TimerTask;
import java.util.stream.Collectors;

public class FriendDetectionTask extends TimerTask {

    private final FriendManager manager;
    private final DiscordAPI api;

    public FriendDetectionTask(){
        this.manager = FriendManager.getInstance();
        this.api = DiscordAPI.getInstance();
    }

    @Override
    public void run() {
        final List<Friend> friends = api.findFriends();
        manager.setFriends(friends);

        final List<Friend> friendsRemoved = manager.getFriendsOlds()
                .stream()
                .filter(friend -> !friends.stream().anyMatch(f -> f.getId().equals(friend.getId())))
                .collect(Collectors.toList());

        manager.setFriendsOlds(friends);

        if(friendsRemoved.isEmpty()){
            System.out.println("[DCFriendDetection] No momento, não há nenhum amigo removido!");
            return;
        }

        final TrayIcon trayIcon = SystemTrayLoader.getTrayIcon();
        for(Friend friendRemove : friendsRemoved){
            final String message = String.format("%s não está mais na sua lista de amigos!", friendRemove.getUser().getUsername());
            if(trayIcon != null){
                trayIcon.displayMessage("DCFriendDetection v1.0.0", message, TrayIcon.MessageType.ERROR);
            }else{
                System.out.println("[DCFriendDetection] " + message);
            }
        }
    }

}