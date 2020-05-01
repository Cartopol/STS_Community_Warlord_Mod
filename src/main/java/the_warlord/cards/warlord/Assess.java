package the_warlord.cards.warlord;

import basemod.ReflectionHacks;
import com.megacrit.cardcrawl.actions.common.GainBlockAction;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import the_warlord.WarlordMod;
import the_warlord.cards.CustomWarlordModCard;
import the_warlord.characters.Warlord;
import the_warlord.util.IntentUtils;

public class Assess extends CustomWarlordModCard {
    public static final String ID = WarlordMod.makeID(Assess.class);

    private static final CardRarity RARITY = CardRarity.UNCOMMON;
    private static final CardTarget TARGET = CardTarget.ENEMY;
    private static final CardType TYPE = CardType.SKILL;
    public static final CardColor COLOR = Warlord.Enums.WARLORD_CARD_COLOR;

    private static final int COST = 1;

    private static final int BLOCK = 0;


    public Assess() {
        super(ID, COST, TYPE, COLOR, RARITY, TARGET);
        block = baseBlock = BLOCK;
        exhaust = true;
    }

    @Override
    public int calculateBonusBaseBlock(AbstractMonster m) {
        return (getEnemyDamage(m) + 1) / 2;
    }


    public int getEnemyDamage(AbstractMonster m) {
        int damage = 0;
        if (IntentUtils.isAttackIntent(m.intent)) {
            damage = (int) ReflectionHacks.getPrivate(m, AbstractMonster.class, "intentDmg");
        }

        if (IntentUtils.isAttackIntent(m.intent)) {
            if ((boolean) ReflectionHacks.getPrivate(m, AbstractMonster.class, "isMultiDmg")) {
                damage *= (int) ReflectionHacks.getPrivate(m, AbstractMonster.class, "intentMultiAmt");
            }
        }
        return damage;
    }

    @Override
    public String getRawDynamicDescriptionSuffix() {
        if (block > 0 && IntentUtils.playerCanSeeThatAnyEnemyIntentMatches(IntentUtils::isAttackIntent)) {
            return EXTENDED_DESCRIPTION[0];
        }
        return "";
    }

    @Override
    public void use(AbstractPlayer p, AbstractMonster m) {
        if (block > 0) {
            addToBot(new GainBlockAction(p, p, block));
        }
    }

    @Override
    public void upgrade() {
        if (!upgraded) {
            this.selfRetain = true;
            upgradeName();
            upgradeDescription();
        }
    }
}
