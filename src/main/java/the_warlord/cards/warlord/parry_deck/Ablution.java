package the_warlord.cards.warlord.parry_deck;

import com.megacrit.cardcrawl.actions.unique.RemoveDebuffsAction;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import the_warlord.WarlordMod;
import the_warlord.characters.Warlord;

public class Ablution extends CustomParryCard {
    public static final String ID = WarlordMod.makeID(Ablution.class);

    private static final CardRarity RARITY = CardRarity.RARE;
    private static final CardTarget TARGET = CardTarget.SELF;
    private static final CardType TYPE = CardType.SKILL;
    public static final CardColor COLOR = Warlord.Enums.WARLORD_CARD_COLOR;

    private static final int COST = COST_UNPLAYABLE;

    public Ablution() {
        super(ID, COST, TYPE, COLOR, RARITY, TARGET);
    }

    @Override
    public void onChoseThisOption() {
        addToBot(new RemoveDebuffsAction(AbstractDungeon.player));

        if (!this.upgraded) {
            removeFromMasterParryDeck(this);
        }
    }

    @Override
    public void upgrade() {
        if (!upgraded) {

            upgradeName();
            upgradeDescription();
        }
    }
}
