package the_warlord.cards.warlord;

import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.actions.common.DamageAction;
import com.megacrit.cardcrawl.cards.DamageInfo;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import the_warlord.WarlordMod;
import the_warlord.cards.CustomWarlordModCard;
import the_warlord.characters.Warlord;

public class SwordDance extends CustomWarlordModCard {
    public static final String ID = WarlordMod.makeID(SwordDance.class);

    private static final CardRarity RARITY = CardRarity.RARE;
    private static final CardTarget TARGET = CardTarget.ENEMY;
    private static final CardType TYPE = CardType.ATTACK;
    public static final CardColor COLOR = Warlord.Enums.WARLORD_CARD_COLOR;

    private static final int COST = 1;
    private static final int DAMAGE = 4;
    private static final int UPGRADE_PLUS_DAMAGE = 1;

    public SwordDance() {
        super(ID, COST, TYPE, COLOR, RARITY, TARGET);
        baseDamage = DAMAGE;
        magicNumber = baseMagicNumber = 0;
    }

    @Override
    public int calculateBonusMagicNumber() {
        return AbstractDungeon.actionManager.cardsPlayedThisTurn.size();
    }

    @Override
    public void use(AbstractPlayer p, AbstractMonster m) {
        for (int i = 0; i < magicNumber - 1; ++i) {
            addToBot(new DamageAction(m, new DamageInfo(p, damage), AbstractGameAction.AttackEffect.SLASH_DIAGONAL));
        }
    }

    @Override
    public String getRawDynamicDescriptionSuffix() {
        if (magicNumber == 1) {
            return EXTENDED_DESCRIPTION[0];
        } else if (magicNumber > 1) {
            return EXTENDED_DESCRIPTION[1];
        }
        return "";
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