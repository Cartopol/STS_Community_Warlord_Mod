package the_warlord.cards.warlord;

import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.actions.common.DamageAction;
import com.megacrit.cardcrawl.cards.DamageInfo;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import the_warlord.WarlordMod;
import the_warlord.cards.CustomWarlordModCard;
import the_warlord.cards.warlord.parry_deck.ParryDeck;
import the_warlord.characters.Warlord;
import the_warlord.powers.ParryPower;
import the_warlord.util.CardMetaUtils;


public class Punishment extends CustomWarlordModCard {
    public static final String ID = WarlordMod.makeID(Punishment.class.getSimpleName());

    private static final CardRarity RARITY = CardRarity.RARE;
    private static final CardTarget TARGET = CardTarget.ENEMY;
    private static final CardType TYPE = CardType.ATTACK;
    public static final CardColor COLOR = Warlord.Enums.WARLORD_CARD_COLOR;

    private static final int COST = 1;
    private static final int DAMAGE = 12;
    private static final int UPGRADE_PLUS_DAMAGE = 5;
    private static final int PLAY_COUNT = 1;

    public Punishment() {
        super(ID, COST, TYPE, COLOR, RARITY, TARGET);
        baseDamage = DAMAGE;
        magicNumber = baseMagicNumber = PLAY_COUNT;
    }

    @Override
    public void use(AbstractPlayer p, AbstractMonster m) {
        addToBot(new DamageAction(m, new DamageInfo(p, damage), AbstractGameAction.AttackEffect.SLASH_HORIZONTAL));
        if (!this.purgeOnUse) {
            if (p.hasPower(ParryPower.POWER_ID) && ParryDeck.hasParried) {
                    CardMetaUtils.playCardAdditionalTime(this, m);
            }
        }
    }

    @Override
    public boolean shouldGlowGold() {
        return ParryDeck.hasParried;
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