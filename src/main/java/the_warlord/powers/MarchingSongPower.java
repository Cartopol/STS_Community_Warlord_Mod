package the_warlord.powers;

import com.megacrit.cardcrawl.actions.common.ApplyPowerAction;
import com.megacrit.cardcrawl.actions.common.DrawCardAction;
import com.megacrit.cardcrawl.actions.common.RemoveSpecificPowerAction;
import com.megacrit.cardcrawl.actions.utility.UseCardAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.cards.CardQueueItem;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.core.AbstractCreature;
import com.megacrit.cardcrawl.core.Settings;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import com.megacrit.cardcrawl.powers.AbstractPower;

public class MarchingSongPower extends CustomWarlordModPower {
    public static final StaticPowerInfo STATIC = StaticPowerInfo.Load(MarchingSongPower.class);
    public static final String POWER_ID = STATIC.ID;

    public MarchingSongPower(AbstractCreature owner, int amount) {
        super(STATIC);

        this.type = PowerType.BUFF;

        this.owner = owner;
        this.amount = amount;

        //Todo: add proper art
        loadRegion("dexterity");

        updateDescription();
    }

    @Override
    public void updateDescription() {
        if(this.amount == 1){ this.description = DESCRIPTIONS[0]; }
        else{ this.description = String.format(DESCRIPTIONS[1], this.amount); }
    }

    public void onUseCard(AbstractCard card, UseCardAction action) {
        if(card.costForTurn > 0){
            AbstractPlayer p = AbstractDungeon.player;
            addToBot(new DrawCardAction(p, amount)); }
    }

    @Override
    public AbstractPower makeCopy() {
        return new MarchingSongPower(owner, amount);
    }
}