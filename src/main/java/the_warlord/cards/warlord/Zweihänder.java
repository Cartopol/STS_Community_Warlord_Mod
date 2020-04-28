package the_warlord.cards.warlord;

import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.actions.common.DamageAction;
import com.megacrit.cardcrawl.actions.common.RemoveSpecificPowerAction;
import com.megacrit.cardcrawl.cards.DamageInfo;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import the_warlord.WarlordMod;
import the_warlord.cards.CustomWarlordModCard;
import the_warlord.characters.Warlord;
import the_warlord.powers.ReactionTimePower;

public class Zweihänder extends CustomWarlordModCard {
    public static final String ID = WarlordMod.makeID(Zweihänder.class);

    private static final CardRarity RARITY = CardRarity.COMMON;
    private static final CardTarget TARGET = CardTarget.ENEMY;
    private static final CardType TYPE = CardType.ATTACK;
    public static final CardColor COLOR = Warlord.Enums.WARLORD_CARD_COLOR;

    private static final int COST = 2;
    private static final int DAMAGE = 16;
    private static final int UPGRADE_PLUS_DAMAGE = 6;

    public Zweihänder() {
        super(ID, COST, TYPE, COLOR, RARITY, TARGET);
        baseDamage = DAMAGE;
    }

    @Override
    public void use(AbstractPlayer p, AbstractMonster m) {
        addToBot(new DamageAction(m, new DamageInfo(p, damage), AbstractGameAction.AttackEffect.SMASH));
        if (p.hasPower(ReactionTimePower.POWER_ID)) {
            addToBot(new RemoveSpecificPowerAction(p, p, ReactionTimePower.POWER_ID));
        }
    }

    @Override
    public void upgrade() {
        if (!upgraded) {
            upgradeDamage(UPGRADE_PLUS_DAMAGE);
            upgradeName();
            upgradeDescription();
        }
    }
}