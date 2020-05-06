package the_warlord.cards.warlord.parry_deck;

import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.actions.common.DamageAllEnemiesAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import the_warlord.WarlordMod;
import the_warlord.actions.AddToParryDeckAction;
import the_warlord.characters.Warlord;

public class AlphaStrike extends CustomParryCard {
    public static final String ID = WarlordMod.makeID(AlphaStrike.class);

    private static final CardRarity RARITY = CardRarity.RARE;
    private static final CardTarget TARGET = CardTarget.ALL_ENEMY;
    private static final CardType TYPE = CardType.ATTACK;
    public static final CardColor COLOR = Warlord.Enums.WARLORD_CARD_COLOR;

    private static final int COST = COST_UNPLAYABLE;

    private static final int DAMAGE = 13;


    public AlphaStrike() {
        super(ID, COST, TYPE, COLOR, RARITY, TARGET);
        baseDamage = DAMAGE;
        AbstractCard c = new BetaStrike();
        if (upgraded) {
            c.upgrade();
        }
        this.cardsToPreview = c;
        this.isMultiDamage = true;
        tags.add(CardTags.STRIKE);
    }

    @Override
    public void onChoseThisOption() {
        for (AbstractMonster m : AbstractDungeon.getCurrRoom().monsters.monsters) {
            calculateCardDamage(m);
        }
        AbstractDungeon.actionManager.addToBottom(new DamageAllEnemiesAction(AbstractDungeon.player, this.multiDamage, this.damageTypeForTurn, AbstractGameAction.AttackEffect.SLASH_HEAVY));
        AbstractCard c = new BetaStrike();
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
