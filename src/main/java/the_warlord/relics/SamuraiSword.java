package the_warlord.relics;

import the_warlord.WarlordMod;

import static the_warlord.characters.Warlord.Enums.WARLORD_CARD_COLOR;

public class SamuraiSword extends CustomWarlordModRelic {
    public static final String ID = WarlordMod.makeID(SamuraiSword.class);
    public SamuraiSword() {
        super(ID, WARLORD_CARD_COLOR, RelicTier.BOSS, LandingSound.MAGICAL);
    }
}