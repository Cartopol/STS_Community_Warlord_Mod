package the_warlord.cards.warlord;

import com.evacipated.cardcrawl.mod.stslib.fields.cards.AbstractCard.AutoplayField;
import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.actions.animations.VFXAction;
import com.megacrit.cardcrawl.actions.common.DamageAllEnemiesAction;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import com.megacrit.cardcrawl.vfx.combat.DaggerSprayEffect;
import the_warlord.WarlordMod;
import the_warlord.cards.CustomWarlordModCard;
import the_warlord.characters.Warlord;

public class Ambush extends CustomWarlordModCard {
    public static final String ID = WarlordMod.makeID(Ambush.class);

    private static final CardRarity RARITY = CardRarity.UNCOMMON;
    private static final CardTarget TARGET = CardTarget.ALL_ENEMY;
    private static final CardType TYPE = CardType.ATTACK;
    public static final CardColor COLOR = Warlord.Enums.WARLORD_CARD_COLOR;

    private static final int COST = COST_UNPLAYABLE;
    private static final int DAMAGE = 7;
    private static final int UPGRADE_PLUS_DAMAGE = 3;

    public Ambush() {
        super(ID, COST, TYPE, COLOR, RARITY, TARGET);
        baseDamage = DAMAGE;
        AutoplayField.autoplay.set(this, true);
        this.exhaust = true;
        this.isMultiDamage = true;
    }

//    @Override
//    public void triggerWhenDrawn() {
//        addToBot(new VFXAction(new DaggerSprayEffect(AbstractDungeon.getMonsters().shouldFlipVfx()), 0.0F));
//        for (AbstractMonster m : AbstractDungeon.getCurrRoom().monsters.monsters) {
//            if (!m.isDead && !m.isDying) {
//                calculateCardDamage(m);
//                addToBot(new DamageAction(m, new DamageInfo(AbstractDungeon.player, damage), AbstractGameAction.AttackEffect.SLASH_HORIZONTAL));
//            }
//        }
////        addToBot(new DamageAllEnemiesAction(AbstractDungeon.player, this.multiDamage, this.damageTypeForTurn, AbstractGameAction.AttackEffect.NONE));
//        addToBot(new ExhaustSpecificCardAction(this, AbstractDungeon.player.hand ));
//    }

//    @Override
//    public boolean canUse(AbstractPlayer p, AbstractMonster m) {
//        this.cantUseMessage = cardStrings.EXTENDED_DESCRIPTION[0];
//        return false;
//    }

    @Override
    public void use(AbstractPlayer p, AbstractMonster m) {
        addToBot(new VFXAction(new DaggerSprayEffect(AbstractDungeon.getMonsters().shouldFlipVfx()), 0.0F));
        addToBot(new DamageAllEnemiesAction(AbstractDungeon.player, this.multiDamage, this.damageTypeForTurn, AbstractGameAction.AttackEffect.NONE));
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