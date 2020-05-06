package the_warlord.cards.warlord.parry_deck;

import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.actions.common.AttackDamageRandomEnemyAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import the_warlord.WarlordMod;
import the_warlord.actions.AddToParryDeckAction;
import the_warlord.characters.Warlord;

public class BetaStrike extends CustomParryCard {
    public static final String ID = WarlordMod.makeID(BetaStrike.class);

    private static final CardRarity RARITY = CardRarity.SPECIAL;
    private static final CardTarget TARGET = CardTarget.ALL_ENEMY;
    private static final CardType TYPE = CardType.ATTACK;
    public static final CardColor COLOR = Warlord.Enums.WARLORD_CARD_COLOR;

    private static final int COST = COST_UNPLAYABLE;

    private static final int DAMAGE = 30;


    public BetaStrike() {
        super(ID, COST, TYPE, COLOR, RARITY, TARGET);
        baseDamage = DAMAGE;
        AbstractCard c = new OmegaStrike();
        if (upgraded) {
            c.upgrade();
        }
        this.cardsToPreview = c;

        tags.add(CardTags.STRIKE);
    }

    @Override
    public void onChoseThisOption() {
        this.addToBot(new AttackDamageRandomEnemyAction(this, AbstractGameAction.AttackEffect.SLASH_HORIZONTAL));

        AbstractCard c = new OmegaStrike();
        if (upgraded) {
            c.upgrade();
        }
        addToBot(new AddToParryDeckAction(c, false));
        removeFromMasterParryDeck(this);

    }

    @Override
    public void upgrade() {
        if (!upgraded) {

            upgradeName();
            upgradeDescription();
        }
    }
}
