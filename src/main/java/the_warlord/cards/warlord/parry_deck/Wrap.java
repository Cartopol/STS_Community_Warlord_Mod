package the_warlord.cards.warlord.parry_deck;

import com.megacrit.cardcrawl.actions.common.ApplyPowerAction;
import com.megacrit.cardcrawl.actions.common.GainBlockAction;
import com.megacrit.cardcrawl.actions.common.ReducePowerAction;
import com.megacrit.cardcrawl.actions.common.RemoveSpecificPowerAction;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.powers.PlatedArmorPower;
import the_warlord.WarlordMod;
import the_warlord.characters.Warlord;
import the_warlord.powers.TenseUpPower;
import the_warlord.powers.TensionPower;

public class Wrap extends CustomParryCard {
    public static final String ID = WarlordMod.makeID(Wrap.class);

    private static final CardRarity RARITY = CardRarity.BASIC;
    private static final CardTarget TARGET = CardTarget.SELF;
    private static final CardType TYPE = CardType.SKILL;
    public static final CardColor COLOR = Warlord.Enums.WARLORD_CARD_COLOR;

    private static final int COST = COST_UNPLAYABLE;

    private static final int BLOCK = 5;
    private static final int UPGRADE_PLUS_BLOCK = 3;
    private static final int TENSION_LOSS = 1;

    public Wrap() {
        super(ID, COST, TYPE, COLOR, RARITY, TARGET);
        block = baseBlock = BLOCK;
        magicNumber = baseMagicNumber = TENSION_LOSS;
    }

    @Override
    public void useParry() {
        AbstractPlayer p = AbstractDungeon.player;
        addToBot(new GainBlockAction(p, this.block));
        if(p.hasPower(TensionPower.POWER_ID)){
            if(p.getPower(TensionPower.POWER_ID).amount != this.magicNumber){ addToBot(new ApplyPowerAction(p, p, new TensionPower(p, -magicNumber), -magicNumber)); }
            else { addToBot(new RemoveSpecificPowerAction(p, p, TensionPower.POWER_ID)); }
        }
    }

    @Override
    public void upgrade() {
        if (!upgraded) {
            upgradeBlock(UPGRADE_PLUS_BLOCK);
            upgradeName();
            upgradeDescription();
        }
    }
}
