package the_warlord.relics;

import the_warlord.WarlordMod;

import static the_warlord.characters.Warlord.Enums.WARLORD_CARD_COLOR;

public class PaperScorpion extends CustomWarlordModRelic {
    public static final String ID = WarlordMod.makeID(PaperScorpion.class);
    public PaperScorpion() {
        super(ID, WARLORD_CARD_COLOR, RelicTier.RARE, LandingSound.CLINK);
    }
}
