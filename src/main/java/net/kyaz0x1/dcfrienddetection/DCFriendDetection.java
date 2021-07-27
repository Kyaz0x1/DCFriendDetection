package net.kyaz0x1.dcfrienddetection;

import net.kyaz0x1.dcfrienddetection.api.auth.AuthCredentials;
import net.kyaz0x1.dcfrienddetection.loader.SystemTrayLoader;
import net.kyaz0x1.dcfrienddetection.task.FriendDetectionTask;

import javax.swing.JOptionPane;
import java.awt.SystemTray;
import java.util.Timer;
import java.util.concurrent.TimeUnit;

public class DCFriendDetection {

    private static final long TIME_TO_VERIFY = TimeUnit.SECONDS.toMillis(15);

    public static void main(String[] args){
        if(!SystemTray.isSupported()){
            JOptionPane.showMessageDialog(null, "O sistema não tem suporte para o system tray!", "DCFriendDetection", JOptionPane.ERROR_MESSAGE);
            return;
        }

        if(AuthCredentials.TOKEN.isEmpty()){
            JOptionPane.showMessageDialog(null, "Você esqueceu de informar um token!", "DCFriendDetection", JOptionPane.ERROR_MESSAGE);
            return;
        }

        SystemTrayLoader.load();

        final Timer timer = new Timer();
        timer.scheduleAtFixedRate(new FriendDetectionTask(), TIME_TO_VERIFY, TIME_TO_VERIFY);
    }

}