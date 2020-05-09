package the_warlord.relics;

import com.megacrit.cardcrawl.actions.common.GainEnergyAction;
import com.megacrit.cardcrawl.actions.common.RelicAboveCreatureAction;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import the_warlord.WarlordMod;

import static the_warlord.characters.Warlord.Enums.WARLORD_CARD_COLOR;

public class SamuraiSword extends CustomWarlordModRelic implements RelicParrySubscriber {
    public static final String ID = WarlordMod.makeID(SamuraiSword.class);
    public SamuraiSword() {
        super(ID, WARLORD_CARD_COLOR, RelicTier.BOSS, LandingSound.CLINK);
    }
    @Override
    public void onParry(boolean fullParry) {
        flash();
        addToBot(new RelicAboveCreatureAction(AbstractDungeon.player, this));
        addToBot(new GainEnergyAction(1));
    }
}