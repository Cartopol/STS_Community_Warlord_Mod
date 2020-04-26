package the_warlord.cards.warlord;

import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.actions.common.ApplyPowerAction;
import com.megacrit.cardcrawl.actions.common.DamageAction;
import com.megacrit.cardcrawl.cards.DamageInfo;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import com.megacrit.cardcrawl.powers.StrengthPower;
import the_warlord.WarlordMod;
import the_warlord.cards.CustomWarlordModCard;
import the_warlord.characters.Warlord;

public class StunNeedle extends CustomWarlordModCard {
    public static final String ID = WarlordMod.makeID(StunNeedle.class.getSimpleName());

    private static final CardRarity RARITY = CardRarity.UNCOMMON;
    private static final CardTarget TARGET = CardTarget.ENEMY;
    private static final CardType TYPE = CardType.ATTACK;
    public static final CardColor COLOR = Warlord.Enums.WARLORD_CARD_COLOR;

    private static final int COST = 0;
    private static final int DAMAGE = 4;
    private static final int STRENGTH_LOSS = 1;
    private static final int UPGRADE_PLUS_DAMAGE = 3;

    public StunNeedle() {
        super(ID, COST, TYPE, COLOR, RARITY, TARGET);
        baseDamage = DAMAGE;
        magicNumber = baseMagicNumber = STRENGTH_LOSS;
        this.exhaust = true;
    }

    @Override
    public void use(AbstractPlayer p, AbstractMonster m) {

        addToBot(new DamageAction(m, new DamageInfo(p, damage), AbstractGameAction.AttackEffect.SLASH_HORIZONTAL));
        this.addToBot(new ApplyPowerAction(m, p, new StrengthPower(m, -this.magicNumber), -this.magicNumber));


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