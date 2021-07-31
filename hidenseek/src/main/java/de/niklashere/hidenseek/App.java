package de.niklashere.hidenseek;

import java.io.File;
import java.util.List;

import org.bukkit.plugin.java.JavaPlugin;

import de.niklashere.hidenseek.gamestates.Gamestate;
import de.niklashere.hidenseek.gamestates.GamestateManager;
import de.niklashere.hidenseek.libary.Fileaccess;

public class App extends JavaPlugin {

    private RegisterManager registerManager;
    public static App instance;

    @Override
    public void onEnable() {
        instance = this;
        this.saveDefaultConfig();
        if (Fileaccess.getBoolean("download-new-languages", Fileaccess.getConfig())) {
            Fileaccess.createConfig();
            List<String> lang = Fileaccess.getStringList("supported-languages", Fileaccess.getConfig());
            int i = 0;
            while (lang.size()-1 >= i) {
                saveResource("languages/" + lang.get(i) + ".yml", true);
                i++;
            }
        }
        Layouts.getBanner();
        Fileaccess.loadLanguages();
        Layouts.getLanguage();
        this.registerManager = RegisterManager.init(this);
        registerManager.registerEvents();
        GamestateManager.startLobbyCD();

    }

    @Override
    public void onDisable() {
        Layouts.getBanner();
        Layouts.getBye();
    }
}
