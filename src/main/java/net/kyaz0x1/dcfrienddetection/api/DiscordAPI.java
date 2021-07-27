package net.kyaz0x1.dcfrienddetection.api;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import net.kyaz0x1.dcfrienddetection.api.models.Friend;
import net.kyaz0x1.dcfrienddetection.api.service.WebService;

import java.util.List;

public final class DiscordAPI {

    private static DiscordAPI INSTANCE;

    private final WebService webService;
    private final String API_ENDPOINT = "https://discord.com/api/v9";

    private final Gson gson;

    private DiscordAPI(){
        this.webService = WebService.getInstance();
        this.gson = new Gson();
    }

    public List<Friend> findFriends(){
        final String url = API_ENDPOINT + "/users/@me/relationships";
        final String content = webService.get(url);

        final List<Friend> friends = gson.fromJson(content, new TypeToken<List<Friend>>(){}.getType());
        return friends;
    }

    public static DiscordAPI getInstance(){
        if(INSTANCE == null){
            synchronized(DiscordAPI.class){
                if(INSTANCE == null){
                    INSTANCE = new DiscordAPI();
                }
            }
        }
        return INSTANCE;
    }

}