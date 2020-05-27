package the_warlord.cards.warlord;

import basemod.ReflectionHacks;
import com.megacrit.cardcrawl.actions.common.GainBlockAction;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import the_warlord.WarlordMod;
import the_warlord.cards.CustomWarlordModCard;
import the_warlord.characters.Warlord;
import the_warlord.util.IntentUtils;

public class PerfectAnswer extends CustomWarlordModCard {
    public static final String ID = WarlordMod.makeID(PerfectAnswer.class);

    private static final CardRarity RARITY = CardRarity.RARE;
    private static final CardTarget TARGET = CardTarget.SELF;
    private static final CardType TYPE = CardType.SKILL;
    public static final CardColor COLOR = Warlord.Enums.WARLORD_CARD_COLOR;

    private static final int COST = 4;
    private static final int UPGRADED_COST = 3;

    public PerfectAnswer() {
        super(ID, COST, TYPE, COLOR, RARITY, TARGET);
        magicNumber = baseMagicNumber = 0;
        this.exhaust = true;
    }

    @Override
    public int calculateBonusMagicNumber() {
        int bonusBlock = 0;
        for (AbstractMonster m : AbstractDungeon.getCurrRoom().monsters.monsters) {
            if (!m.isDead && !m.isDying) {
                if (IntentUtils.isAttackIntent(m.intent)) {
                    bonusBlock += getEnemyDamage(m);
                }
            }
        }
        return bonusBlock;
    }

    public int getEnemyDamage(AbstractMonster m) {
        int damage = 0;
            damage = (int) ReflectionHacks.getPrivate(m, AbstractMonster.class, "intentDmg");

            if ((boolean) ReflectionHacks.getPrivate(m, AbstractMonster.class, "isMultiDmg")) {
                damage *= (int) ReflectionHacks.getPrivate(m, AbstractMonster.class, "intentMultiAmt");
        }
        return damage;
    }

    @Override
    public String getRawDynamicDescriptionSuffix() {
        if (magicNumber > 0 && IntentUtils.playerCanSeeThatAnyEnemyIntentMatches(IntentUtils::isAttackIntent)) {
            return EXTENDED_DESCRIPTION[0];
        }
        return "";
    }

    @Override
    public void use(AbstractPlayer p, AbstractMonster m) {
        if (magicNumber > 0) {
            addToBot(new GainBlockAction(p, magicNumber));
        }
    }

    @Override
    public void upgrade() {
        if (!upgraded) {
            upgradeName();
            upgradeBaseCost(UPGRADED_COST);
            upgradeDescription();
        }
    }
}
