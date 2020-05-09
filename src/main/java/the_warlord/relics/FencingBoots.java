package the_warlord.relics;

import com.megacrit.cardcrawl.actions.common.ApplyPowerAction;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import the_warlord.WarlordMod;
import the_warlord.powers.ReactionTimePower;

import static the_warlord.characters.Warlord.Enums.WARLORD_CARD_COLOR;

public class FencingBoots extends CustomWarlordModRelic {
    public static final String ID = WarlordMod.makeID(FencingBoots.class);
    private static final int REACTION_TIME = 2;

    public FencingBoots() {
        super(ID, WARLORD_CARD_COLOR, RelicTier.STARTER, LandingSound.FLAT);
    }

    @Override
    public void atBattleStart() {
        AbstractPlayer p = AbstractDungeon.player;
        addToBot(new ApplyPowerAction(p, p, new ReactionTimePower(p, REACTION_TIME)));
        this.flash();
    }
}
