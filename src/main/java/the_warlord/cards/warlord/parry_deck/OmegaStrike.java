package the_warlord.cards.warlord.parry_deck;

import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.actions.common.ApplyPowerAction;
import com.megacrit.cardcrawl.actions.common.DamageAllEnemiesAction;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import the_warlord.WarlordMod;
import the_warlord.characters.Warlord;
import the_warlord.powers.BleedPower;
import the_warlord.powers.GushPower;

public class OmegaStrike extends CustomParryCard {
    public static final String ID = WarlordMod.makeID(OmegaStrike.class);

    private static final CardRarity RARITY = CardRarity.SPECIAL;
    private static final CardTarget TARGET = CardTarget.ALL_ENEMY;
    private static final CardType TYPE = CardType.ATTACK;
    public static final CardColor COLOR = Warlord.Enums.WARLORD_CARD_COLOR;

    private static final int COST = COST_UNPLAYABLE;
    private static final int DAMAGE = 1;

    private static final int BLEED = 99;
    private static final int GUSH = 1;

    public OmegaStrike() {
        super(ID, COST, TYPE, COLOR, RARITY, TARGET);
        damage = baseDamage = DAMAGE;
        magicNumber = baseMagicNumber = BLEED;
        urMagicNumber = baseUrMagicNumber = GUSH;
        isMultiDamage = true;

        tags.add(CardTags.STRIKE);
    }

    @Override
    public void useParry() {
        for (AbstractMonster m : AbstractDungeon.getCurrRoom().monsters.monsters) {
            calculateCardDamage(m);
        }
        AbstractDungeon.actionManager.addToBottom(new DamageAllEnemiesAction(AbstractDungeon.player, this.multiDamage, this.damageTypeForTurn, AbstractGameAction.AttackEffect.SLASH_HEAVY));

        for (AbstractMonster m : AbstractDungeon.getCurrRoom().monsters.monsters) {
            if (!m.isDead && !m.isDying) {
                if (upgraded) {
                    addToBot(new ApplyPowerAction(m, AbstractDungeon.player, new GushPower(m, urMagicNumber)));
                }
                addToBot(new ApplyPowerAction(m, AbstractDungeon.player, new BleedPower(m, magicNumber)));
            }
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
