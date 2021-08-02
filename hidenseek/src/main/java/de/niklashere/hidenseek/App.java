package de.niklashere.hidenseek;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

import org.bukkit.plugin.java.JavaPlugin;

import de.niklashere.hidenseek.gamestates.GamestateManager;
import de.niklashere.hidenseek.gamestates.Rolemanager;
import de.niklashere.hidenseek.libary.Fileaccess;
import de.niklashere.hidenseek.libary.WorldManager;

public class App extends JavaPlugin {

    private RegisterManager registerManager;
    public static App instance;

    @Override
    public void onEnable() {
        instance = this;
        this.saveDefaultConfig();

        Fileaccess.createConfig();
        List<String> lang = Fileaccess.getStringList("supported-languages", Fileaccess.getConfig());
        List<String> maps = Fileaccess.getStringList("supported-maps", Fileaccess.getConfig());
        int i = 0;
        while (lang.size()-1 >= i) {
            saveResource("languages/" + lang.get(i) + ".yml", true);
            i++;
        }
        int a = 0;
        while (maps.size()-1 >= a) {
            saveResource("maps/" + maps.get(a) + ".yml", true);
             a++;
        }
        Fileaccess.loadFolder("languages");
        Fileaccess.loadFolder("maps");
        Layouts.getBanner();
        Layouts.getLanguage();
        this.registerManager = RegisterManager.init(this);
        registerManager.registerEvents();
        registerManager.registerCommands();
        GamestateManager.startLobbyCD();
        Rolemanager.initialize();
        ArrayList<File> file = WorldManager.rdmMap(Fileaccess.listOfFiles.get("maps"), 3);

        for (int e = 0; e <= file.size()-1; e++) {
            System.out.println(file.get(e));
        }
    }

    @Override
    public void onDisable() {
        Layouts.getBanner();
        Layouts.getBye();
    }
}
