package the_warlord.relics;

import com.megacrit.cardcrawl.actions.common.ApplyPowerAction;
import com.megacrit.cardcrawl.actions.common.RelicAboveCreatureAction;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import the_warlord.WarlordMod;
import the_warlord.powers.PosturePower;

import static the_warlord.characters.Warlord.Enums.WARLORD_CARD_COLOR;

public class FencingGloves extends CustomWarlordModRelic implements RelicParrySubscriber {
    public static final String ID = WarlordMod.makeID(FencingGloves.class);
    public FencingGloves() {
        super(ID, WARLORD_CARD_COLOR, RelicTier.UNCOMMON, LandingSound.FLAT);
    }

    @Override
    public void onParry(boolean fullParry) {
        AbstractPlayer p = AbstractDungeon.player;
        flash();
        addToBot(new RelicAboveCreatureAction(AbstractDungeon.player, this));
        addToBot(new ApplyPowerAction(p, p, new PosturePower(p, 2)));
    }
}