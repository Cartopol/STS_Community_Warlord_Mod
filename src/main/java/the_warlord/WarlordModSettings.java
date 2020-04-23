package the_warlord;

import basemod.ModButton;
import basemod.ModLabel;
import basemod.ModPanel;
import com.badlogic.gdx.graphics.Color;
import com.evacipated.cardcrawl.modthespire.lib.SpireConfig;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.core.Settings;
import com.megacrit.cardcrawl.helpers.FontHelper;
import com.megacrit.cardcrawl.helpers.Prefs;
import com.megacrit.cardcrawl.screens.stats.CharStat;
import the_warlord.characters.Warlord;

import java.util.Properties;

import static the_warlord.WarlordMod.makeID;

public class WarlordModSettings {
    private static Properties DEFAULT_SETTINGS = new Properties();
    static {
    }

    private static final String MOD_SETTINGS_FILE = "stswarlordmod_config";

    private static SpireConfig config;


    public static void initialize() {
        try {
            config = new SpireConfig(WarlordMod.MODNAME, MOD_SETTINGS_FILE, DEFAULT_SETTINGS);
            config.load();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static ModPanel createSettingsPanel() {
        String[] MOD_SETTINGS_PANEL_TEXT = CardCrawlGame.languagePack.getUIString(makeID("ModSettingsPanel")).TEXT;
        ModPanel settingsPanel = new ModPanel();

        // Buttons will set the text on this when pressed
        ModLabel messageLabel = new ModLabel("",
                400.0F,
                300.0F,
                Color.GREEN,
                FontHelper.tipHeaderFont,
                settingsPanel,
                (label) -> {});
        settingsPanel.addUIElement(messageLabel);

        ModButton unlockA20Button = new ModButton(
                370.0F,
                370.0F,
                settingsPanel,
                (b) -> {
                    unlockA20(Warlord.Enums.WARLORD);
                    messageLabel.text = MOD_SETTINGS_PANEL_TEXT[2];
                }
        );
        settingsPanel.addUIElement(unlockA20Button);
        ModLabel unlockA20Label = new ModLabel(
                MOD_SETTINGS_PANEL_TEXT[1],
                500.0F,
                425.0F,
                Color.WHITE,
                FontHelper.tipHeaderFont,
                settingsPanel,
                (label) -> {});
        settingsPanel.addUIElement(unlockA20Label);

        return settingsPanel;
    }

    private static void unlockA20(AbstractPlayer.PlayerClass clz) {
        Prefs prefs = CardCrawlGame.characterManager.getCharacter(clz).getPrefs();
        if (prefs.getInteger(CharStat.WIN_COUNT, 0) == 0) {
            prefs.putInteger(CharStat.WIN_COUNT, 1);
        }
        prefs.putInteger(CharStat.LAST_ASCENSION_LEVEL, Settings.MAX_ASCENSION_LEVEL);
        prefs.putInteger(CharStat.ASCENSION_LEVEL, Settings.MAX_ASCENSION_LEVEL);
        prefs.flush();
    }
}
