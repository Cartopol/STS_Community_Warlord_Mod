package the_warlord.relics;

import com.megacrit.cardcrawl.actions.common.ApplyPowerAction;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import the_warlord.WarlordMod;
import the_warlord.powers.ReactionTimePower;

import static the_warlord.characters.Warlord.Enums.WARLORD_CARD_COLOR;

public class PocketKnife extends CustomWarlordModRelic {
    public static final String ID = WarlordMod.makeID(PocketKnife.class);
    public PocketKnife() {
        super(ID, WARLORD_CARD_COLOR, RelicTier.UNCOMMON, LandingSound.MAGICAL);
    }
}
