package the_warlord;

import basemod.BaseMod;
import basemod.helpers.RelicType;
import basemod.interfaces.*;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.Texture;
import com.evacipated.cardcrawl.mod.stslib.Keyword;
import com.evacipated.cardcrawl.modthespire.lib.SpireInitializer;
import com.google.gson.Gson;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.core.Settings;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.localization.*;
import com.megacrit.cardcrawl.powers.AbstractPower;
import com.megacrit.cardcrawl.rooms.AbstractRoom;
import com.megacrit.cardcrawl.unlock.UnlockTracker;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.clapper.util.classutil.RegexClassFilter;
import the_warlord.cards.CustomWarlordModCard;
import the_warlord.characters.Warlord;
import the_warlord.potions.GushPotion;
import the_warlord.potions.PosturePotion;
import the_warlord.relics.CustomWarlordModRelic;
import the_warlord.util.ReflectionUtils;
import the_warlord.util.TextureLoader;
import the_warlord.variables.*;

import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.List;

@SpireInitializer
public class WarlordMod implements
        EditCardsSubscriber,
        EditRelicsSubscriber,
        EditStringsSubscriber,
        EditKeywordsSubscriber,
        EditCharactersSubscriber,
        PostInitializeSubscriber,
        OnPowersModifiedSubscriber,
        StartActSubscriber,
        StartGameSubscriber {
    public static final String MOD_ID = "the_warlord";

    public static final Logger logger = LogManager.getLogger(WarlordMod.class.getName());

    public static final String MODNAME = "The Warlord";
    private static final String AUTHOR = "";
    private static final String DESCRIPTION = "The Warlord";

    // =============== INPUT TEXTURE LOCATION =================

    //Mod Badge - A small icon that appears in the mod settings menu next to your mod.
    public static final String BADGE_IMAGE = "the_warlordResources/images/Badge.png";


    // =============== MAKE RESOURCE PATHS =================

    public static String makeCardPath(String resourcePath) {
        return MOD_ID + "Resources/images/cards/" + resourcePath;
    }

    public static String makeRelicPath(String resourcePath) {
        return MOD_ID + "Resources/images/relics/" + resourcePath;
    }

    public static String makeCharPath(String resourcePath) {
        return MOD_ID + "Resources/images/characters/" + resourcePath;
    }

    public static String makeRelicOutlinePath(String resourcePath) {
        return MOD_ID + "Resources/images/relics/outline/" + resourcePath;
    }

    public static String makePowerPath(String resourcePath) {
        return MOD_ID + "Resources/images/powers/" + resourcePath;
    }

    public static String makeImagePath(String resourcePath) {
        return MOD_ID + "Resources/images/" + resourcePath;
    }

    public static String makeLocalizedStringsPath(Settings.GameLanguage language, String resourcePath) {
        String languageFolder =
                // Disable this until we can get it back up to date
                // Settings.language == Settings.GameLanguage.FRA ? "fra" :
                language == Settings.GameLanguage.SPA ? "spa" :
                language == Settings.GameLanguage.ZHS ? "zhs" :
                /* default: */ "eng";

        return MOD_ID + "Resources/localization/" + languageFolder + "/" + resourcePath;
    }

    // =============== /MAKE RESOURCE PATHS/ =================

    // =============== /INPUT TEXTURE LOCATION/ =================


    // =============== SUBSCRIBE, CREATE THE COLOR_GRAY, INITIALIZE =================

    public WarlordMod() {
        logger.info("Subscribe to BaseMod hooks");
        BaseMod.subscribe(this);
        logger.info("Done subscribing");

        logger.info("Adding mod settings");
        WarlordModSettings.initialize();
        logger.info("Done adding mod settings");

        logger.info("Creating new card colors...");
        Warlord.ColorInfo.registerColorWithBaseMod();
        logger.info("Done creating colors");

        logger.info("Adding save fields");
        logger.info("Done adding save fields");
    }

    @SuppressWarnings("unused")
    public static void initialize() {
        logger.info("========================= Initializing WarlordMod. !dig =========================");
        WarlordMod defaultmod = new WarlordMod();
        logger.info("========================= /WarlordMod Initialized./ =========================");
    }

    // ============== /SUBSCRIBE, CREATE THE COLOR_GRAY, INITIALIZE/ =================


    // =============== LOAD THE CHARACTER =================

    @Override
    public void receiveEditCharacters() {
        logger.info("Beginning to edit characters.");

        // order matters; this will be the order they appear in the char select screen

        logger.info("Adding Warlord...");
        BaseMod.addCharacter(
                new Warlord("The Warlord", Warlord.Enums.WARLORD),
                Warlord.CHARACTER_SELECT_BUTTON_TEXTURE,
                Warlord.CHARACTER_SELECT_BG_TEXTURE,
                Warlord.Enums.WARLORD);

        logger.info("Added characters");
    }

    // =============== /LOAD THE CHARACTER/ =================


    // =============== POST-INITIALIZE =================

    private static void registerPowerInDevConsole(Class<? extends AbstractPower> warlordModPower) {
        try {
            String id = (String) warlordModPower.getField("POWER_ID").get(null);
            logger.info("Registering power: " + id);
            BaseMod.addPower(warlordModPower, id);
        } catch (IllegalAccessException | NoSuchFieldException e) {
            throw new RuntimeException(e);
        }
    }

    public void registerPowersInDevConsole() {
        logger.info("Registering powers in developer console");

        ArrayList<Class<AbstractPower>> powers = ReflectionUtils.findAllConcreteWarlordModClasses(new RegexClassFilter("^the_warlord\\.powers\\.(.+)Power$"));
        for (Class<AbstractPower> power : powers) {
            registerPowerInDevConsole(power);
        }

        logger.info("Done registering powers");
    }

    @Override
    public void receivePostInitialize() {
        logger.info("Registering dev console commands");

        logger.info("Registering mod config page");
        Texture badgeTexture = TextureLoader.getTexture(BADGE_IMAGE);
        BaseMod.registerModBadge(badgeTexture, MODNAME, AUTHOR, DESCRIPTION, WarlordModSettings.createSettingsPanel());

        logger.info("Registering potions");
        BaseMod.addPotion(PosturePotion.class, Color.SKY.cpy(), Color.SLATE.cpy(), null, PosturePotion.POTION_ID, Warlord.Enums.WARLORD);
        BaseMod.addPotion(GushPotion.class, Color.CLEAR.cpy(), Color.SCARLET.cpy(), null, GushPotion.POTION_ID, Warlord.Enums.WARLORD);

        logger.info("Registering powers for dev console");
        registerPowersInDevConsole();

        // =============== EVENTS =================

        // =============== /EVENTS/ =================
    }

    // =============== / POST-INITIALIZE/ =================


    // ================ ADD RELICS ===================

    @Override
    public void receiveEditRelics() {
        logger.info("Adding relics");

        List<CustomWarlordModRelic> relics = ReflectionUtils.instantiateAllConcreteWarlordModSubclasses(CustomWarlordModRelic.class);
        for (CustomWarlordModRelic relicInstance : relics) {
            logger.info("Adding relic: " + relicInstance.relicId);
            if (relicInstance.relicColor.equals(AbstractCard.CardColor.COLORLESS)) {
                BaseMod.addRelic(relicInstance, RelicType.SHARED);
            } else {
                BaseMod.addRelicToCustomPool(relicInstance, relicInstance.relicColor);
            }
            UnlockTracker.markRelicAsSeen(relicInstance.relicId);
        }

        logger.info("Done adding relics!");
    }

    // ================ /ADD RELICS/ ===================


    // ================ ADD CARDS ===================

    @Override
    public void receiveEditCards() {
        logger.info("Adding cards");

        BaseMod.addDynamicVariable(new BaseBlockNumber());
        BaseMod.addDynamicVariable(new BaseDamageNumber());
        BaseMod.addDynamicVariable(new UrMagicNumber());
        BaseMod.addDynamicVariable(new MetaMagicNumber());
        BaseMod.addDynamicVariable(new InvertForce());


//        new AutoAdd(WarlordMod.MOD_ID).packageFilter("cards").setDefaultSeen(true).cards();

        List<CustomWarlordModCard> cards = ReflectionUtils.instantiateAllConcreteWarlordModSubclasses(CustomWarlordModCard.class);
        for (CustomWarlordModCard cardInstance : cards) {
            logger.info("Adding card: " + cardInstance.cardID);
            BaseMod.addCard(cardInstance);
            UnlockTracker.unlockCard(cardInstance.cardID);
        }

        logger.info("Done adding cards!");
    }

    // ================ /ADD CARDS/ ===================


    // ================ LOAD THE TEXT ===================

    private void loadStrings(Class<?> stringType, String stringsFileName) {
        // We load english first as a fallback for yet-to-be-translated things, then load the "true" language
        BaseMod.loadCustomStringsFile(stringType, makeLocalizedStringsPath(Settings.GameLanguage.ENG, stringsFileName));
        BaseMod.loadCustomStringsFile(stringType, makeLocalizedStringsPath(Settings.language, stringsFileName));
    }
    
    @Override
    public void receiveEditStrings() {
        logger.info("Beginning to edit strings for mod with ID: " + MOD_ID);

        loadStrings(CardStrings.class, "WarlordMod-Card-Strings.json");
        loadStrings(CharacterStrings.class, "WarlordMod-Character-Strings.json");
        loadStrings(EventStrings.class, "WarlordMod-Event-Strings.json");
        loadStrings(PotionStrings.class, "WarlordMod-Potion-Strings.json");
        loadStrings(PowerStrings.class, "WarlordMod-Power-Strings.json");
        loadStrings(RelicStrings.class, "WarlordMod-Relic-Strings.json");
        loadStrings(UIStrings.class, "WarlordMod-UI-Strings.json");

        logger.info("Done editing strings");
    }

    // ================ /LOAD THE TEXT/ ===================

    // ================ LOAD THE KEYWORDS ===================

    @Override
    public void receiveEditKeywords() {
        // Keywords on cards are supposed to be Capitalized, while in Keyword-String.json they're lowercase
        //
        // Multiword keywords on cards are done With_Underscores
        //
        // If you're using multiword keywords, the first element in your NAMES array in your keywords-strings.json has to be the same as the PROPER_NAME.
        // That is, in Card-Strings.json you would have #yA_Long_Keyword (#y highlights the keyword in yellow).
        // In Keyword-Strings.json you would have PROPER_NAME as A Long Keyword and the first element in NAMES be a long keyword, and the second element be a_long_keyword

        Gson gson = new Gson();
        String json = Gdx.files.internal(makeLocalizedStringsPath(Settings.language, "WarlordMod-Keyword-Strings.json")).readString(String.valueOf(StandardCharsets.UTF_8));
        com.evacipated.cardcrawl.mod.stslib.Keyword[] keywords = gson.fromJson(json, com.evacipated.cardcrawl.mod.stslib.Keyword[].class);

        if (keywords != null) {
            for (Keyword keyword : keywords) {
                BaseMod.addKeyword(MOD_ID.toLowerCase(), keyword.PROPER_NAME, keyword.NAMES, keyword.DESCRIPTION);
            }
        }
    }

    // ================ /LOAD THE KEYWORDS/ ===================

    // this adds "ModName:" before the ID of any card/relic/power etc.
    // in order to avoid conflicts if any other mod uses the same ID.
    public static String makeID(String idText) {
        return MOD_ID + ":" + idText;
    }

    public static String makeID(Class idClass) {
        return makeID(idClass.getSimpleName());
    }

    @Override
    public void receivePowersModified() {
        if (AbstractDungeon.getCurrRoom().phase == AbstractRoom.RoomPhase.COMBAT &&
                !AbstractDungeon.getMonsters().areMonstersBasicallyDead()) {
            for (AbstractPower p : AbstractDungeon.player.powers) {
                if (p instanceof OnPowersModifiedSubscriber) {
                    ((OnPowersModifiedSubscriber) p).receivePowersModified();
                }
            }
        }
    }

    @Override
    public void receiveStartAct() {
    }

    @Override
    public void receiveStartGame() {
    }
}
