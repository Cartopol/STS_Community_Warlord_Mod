package the_warlord.cards.warlord.parry_deck;

import com.megacrit.cardcrawl.actions.unique.GamblingChipAction;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import the_warlord.WarlordMod;
import the_warlord.characters.Warlord;

public class HighStakes extends CustomParryCard {
    public static final String ID = WarlordMod.makeID(HighStakes.class);

    private static final CardRarity RARITY = CardRarity.UNCOMMON;
    private static final CardTarget TARGET = CardTarget.SELF;
    private static final CardType TYPE = CardType.SKILL;
    public static final CardColor COLOR = Warlord.Enums.WARLORD_CARD_COLOR;

    private static final int COST = COST_UNPLAYABLE;
    private static final int RETAIN_TURNS = 1;
    private static final int UPGRADE_PLUS_RETAIN_TURNS = 1;


    public HighStakes() {
        super(ID, COST, TYPE, COLOR, RARITY, TARGET);
        magicNumber = baseMagicNumber = RETAIN_TURNS;
    }

    @Override
    public void onChoseThisOption() {
        AbstractPlayer p = AbstractDungeon.player;
        addToBot(new GamblingChipAction(p, true));
        if (!this.upgraded) {
            removeFromMasterParryDeck(this);
        }
    }

    @Override
    public void upgrade() {
        if (!upgraded) {
            upgradeMagicNumber(UPGRADE_PLUS_RETAIN_TURNS);

            upgradeName();
            upgradeDescription();
        }
    }
}
