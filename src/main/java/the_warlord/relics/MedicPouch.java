package the_warlord.relics;

import com.megacrit.cardcrawl.actions.common.MakeTempCardInHandAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import the_warlord.WarlordMod;
import the_warlord.cards.warlord.Syrette;

import static the_warlord.characters.Warlord.Enums.WARLORD_CARD_COLOR;

public class MedicPouch extends CustomWarlordModRelic {
    public static final String ID = WarlordMod.makeID(MedicPouch.class);
    private static final int SYRETTES = 2;
    public MedicPouch() {
        super(ID, WARLORD_CARD_COLOR, RelicTier.COMMON, LandingSound.HEAVY);
    }
    @Override
    public void atBattleStart() {
        this.flash();
        AbstractPlayer p = AbstractDungeon.player;
        AbstractCard c = new Syrette();
        addToBot(new MakeTempCardInHandAction(c.makeCopy(), SYRETTES));
    }
}