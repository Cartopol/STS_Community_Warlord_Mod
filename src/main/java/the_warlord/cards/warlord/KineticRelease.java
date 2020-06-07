package the_warlord.cards.warlord;

import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.actions.animations.VFXAction;
import com.megacrit.cardcrawl.actions.common.DamageAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.cards.DamageInfo;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import com.megacrit.cardcrawl.vfx.combat.SearingBlowEffect;
import the_warlord.WarlordMod;
import the_warlord.cards.CustomWarlordModCard;
import the_warlord.characters.Warlord;

public class KineticRelease extends CustomWarlordModCard {
    public static final String ID = WarlordMod.makeID(KineticRelease.class);

    private static final CardRarity RARITY = CardRarity.SPECIAL;
    private static final CardTarget TARGET = CardTarget.ENEMY;
    private static final CardType TYPE = CardType.ATTACK;
    public static final CardColor COLOR = Warlord.Enums.WARLORD_CARD_COLOR;

    private static final int COST = 0;

    private static final int DAMAGE = 0;

    public KineticRelease() {
        super(ID, COST, TYPE, COLOR, RARITY, TARGET);
        baseDamage = DAMAGE;
        exhaust = true;
    }

    public void setDamage(int amount) {
        this.damage = amount;
        this.baseDamage = this.damage;
        this.initializeDescription();
    }

    @Override
    public void use(AbstractPlayer p, AbstractMonster m) {

        addToBot(new VFXAction(new SearingBlowEffect(m.hb.cX, m.hb.cY, this.baseDamage/8), 0.2F));
        addToBot(new DamageAction(m, new DamageInfo(p, damage), AbstractGameAction.AttackEffect.NONE));
    }

    @Override
    public AbstractCard makeStatEquivalentCopy() {
        AbstractCard card = super.makeStatEquivalentCopy();
        card.baseDamage = this.baseDamage;
        card.damage = this.damage;
        return card;
    }

    @Override
    public String getRawDynamicDescriptionSuffix() {
        if (baseDamage > 0) {
            return EXTENDED_DESCRIPTION[0];
        } else return "";
    }

    @Override
    public void upgrade() {
        if (!upgraded) {

            exhaust = false;
            upgradeName();
            upgradeDescription();
        }
    }




}