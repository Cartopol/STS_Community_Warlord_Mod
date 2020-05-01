package the_warlord.cards.warlord;

import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.actions.common.ApplyPowerAction;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import the_warlord.WarlordMod;
import the_warlord.actions.GushAction;
import the_warlord.cards.CustomWarlordModCard;
import the_warlord.characters.Warlord;
import the_warlord.powers.BleedPower;

public class TrappingPit extends CustomWarlordModCard {
    public static final String ID = WarlordMod.makeID(TrappingPit.class);

    private static final CardRarity RARITY = CardRarity.UNCOMMON;
    private static final CardTarget TARGET = CardTarget.ALL_ENEMY;
    private static final CardType TYPE = CardType.SKILL;
    public static final CardColor COLOR = Warlord.Enums.WARLORD_CARD_COLOR;

    private static final int COST = 1;
    private static final int BLEED = 9;
    private static final int UPGRADE_PLUS_BLEED = 2;

    public TrappingPit() {
        super(ID, COST, TYPE, COLOR, RARITY, TARGET);
        magicNumber = baseMagicNumber = BLEED;
    }

    @Override
    public void use(AbstractPlayer p, AbstractMonster m) {

        for (AbstractMonster mo: AbstractDungeon.getCurrRoom().monsters.monsters) {
            if (mo.intent == AbstractMonster.Intent.ATTACK ||
                    mo.intent == AbstractMonster.Intent.ATTACK_BUFF ||
                    mo.intent == AbstractMonster.Intent.ATTACK_DEBUFF ||
                    mo.intent == AbstractMonster.Intent.ATTACK_DEFEND) {
                AbstractDungeon.actionManager.addToBottom(new ApplyPowerAction(mo, p, new BleedPower(mo, this.magicNumber), this.magicNumber, true, AbstractGameAction.AttackEffect.NONE));
            }
        }

    }

    @Override
    public void upgrade() {
        if (!upgraded) {
            upgradeName();
            upgradeMagicNumber(UPGRADE_PLUS_BLEED);
            upgradeDescription();
        }
    }
}