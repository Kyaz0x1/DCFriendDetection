package net.kyaz0x1.dcfrienddetection.loader;

import javax.imageio.ImageIO;
import java.awt.AWTException;
import java.awt.Image;
import java.awt.MenuItem;
import java.awt.PopupMenu;
import java.awt.SystemTray;
import java.awt.TrayIcon;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.IOException;

public class SystemTrayLoader {

    private static TrayIcon trayIcon;

    public static void load() {
        try {
            final Image image = ImageIO.read(new File("src\\main\\resources\\discord.png")).getScaledInstance(15, 15, Image.SCALE_SMOOTH);

            final TrayIcon trayIcon = new TrayIcon(image);
            trayIcon.setToolTip("Clique aqui para mais informações!");

            final PopupMenu popupMenu = new PopupMenu();

            final MenuItem exitItem = new MenuItem("Sair");
            exitItem.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    trayIcon.displayMessage("DCFriendDetection v1.0.0", "Fechando programa...", TrayIcon.MessageType.ERROR);
                    System.exit(0);
                }
            });

            popupMenu.add(exitItem);
            trayIcon.setPopupMenu(popupMenu);

            SystemTray.getSystemTray().add(trayIcon);
            SystemTrayLoader.trayIcon = trayIcon;

            trayIcon.displayMessage("DCFriendDetection v1.0.0", "O programa agora está em execução na bandeja do windows!", TrayIcon.MessageType.INFO);
        }catch(IOException | AWTException e) {
            e.printStackTrace();
        }
    }

    public static TrayIcon getTrayIcon() {
        return trayIcon;
    }

}