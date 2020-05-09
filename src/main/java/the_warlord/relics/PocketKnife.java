package the_warlord.relics;

import the_warlord.WarlordMod;

import static the_warlord.characters.Warlord.Enums.WARLORD_CARD_COLOR;

public class PocketKnife extends CustomWarlordModRelic {
    public static final String ID = WarlordMod.makeID(PocketKnife.class);
    public PocketKnife() {
        super(ID, WARLORD_CARD_COLOR, RelicTier.UNCOMMON, LandingSound.CLINK);
    }
}
