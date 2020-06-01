package the_warlord.relics;

import the_warlord.WarlordMod;

import static the_warlord.characters.Warlord.Enums.WARLORD_CARD_COLOR;

public class ParryingDagger extends CustomWarlordModRelic {
    public static final String ID = WarlordMod.makeID(ParryingDagger.class);
    public ParryingDagger() {
        super(ID, WARLORD_CARD_COLOR, RelicTier.RARE, LandingSound.CLINK);
    }
}
