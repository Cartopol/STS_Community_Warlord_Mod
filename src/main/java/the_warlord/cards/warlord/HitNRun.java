package the_warlord.cards.warlord;

import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.actions.common.ApplyPowerAction;
import com.megacrit.cardcrawl.actions.common.DamageAction;
import com.megacrit.cardcrawl.cards.DamageInfo;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import com.megacrit.cardcrawl.powers.NextTurnBlockPower;
import the_warlord.WarlordMod;
import the_warlord.cards.CustomWarlordModCard;
import the_warlord.characters.Warlord;

public class HitNRun extends CustomWarlordModCard {
    public static final String ID = WarlordMod.makeID(HitNRun.class.getSimpleName());

    private static final CardRarity RARITY = CardRarity.UNCOMMON;
    private static final CardTarget TARGET = CardTarget.ENEMY;
    private static final CardType TYPE = CardType.ATTACK;
    public static final CardColor COLOR = Warlord.Enums.WARLORD_CARD_COLOR;

    private static final int COST = 2;
    private static final int BLOCK = 5;
    private static final int DAMAGE = 15;
    private static final int UPGRADE_PLUS_BLOCK = 2;
    private static final int UPGRADE_PLUS_DAMAGE = 3;

    public HitNRun() {
        super(ID, COST, TYPE, COLOR, RARITY, TARGET);
        baseBlock = BLOCK;
        baseDamage = DAMAGE;
    }

    @Override
    public void use(AbstractPlayer p, AbstractMonster m) {

        addToBot(new DamageAction(m, new DamageInfo(p, damage), AbstractGameAction.AttackEffect.BLUNT_HEAVY));
        if (block > 0) {
            this.addToBot(new ApplyPowerAction(p, p, new NextTurnBlockPower(p, this.block), this.block));
        }


    }

    @Override
    public void upgrade() {
        if (!upgraded) {
            upgradeBlock(UPGRADE_PLUS_BLOCK);
            upgradeDamage(UPGRADE_PLUS_DAMAGE);
            upgradeName();
            upgradeDescription();
        }
    }
}