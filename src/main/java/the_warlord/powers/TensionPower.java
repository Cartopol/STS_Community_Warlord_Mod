package the_warlord.powers;

import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.megacrit.cardcrawl.cards.DamageInfo;
import com.megacrit.cardcrawl.core.AbstractCreature;
import com.megacrit.cardcrawl.core.Settings;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.helpers.FontHelper;
import com.megacrit.cardcrawl.powers.AbstractPower;

public class TensionPower extends CustomWarlordModPower {
    public static final StaticPowerInfo STATIC = StaticPowerInfo.Load(TensionPower.class);
    public static final String POWER_ID = STATIC.ID;
    private boolean justApplied = false;


    public TensionPower(AbstractCreature owner, int amount) {
        super(STATIC);

        this.type = PowerType.DEBUFF;

        this.owner = owner;
        this.amount = amount;

        this.isTurnBased = true;

        if (AbstractDungeon.actionManager.turnHasEnded) {
            this.justApplied = true;
        }


        updateDescription();
    }

    @Override
    public void updateDescription() {
        description = String.format(DESCRIPTIONS[0], amount);
    }

//    @Override
//    public void atStartOfTurn() {
//        if (!justApplied) {
//            addToBot(new ReducePowerAction(owner, owner, this, 1));
//        } else justApplied = false;
//    }

    @Override
    public float atDamageReceive(float damage, DamageInfo.DamageType type) {
        return type == DamageInfo.DamageType.NORMAL ? damage + amount : damage;
    }

//    @Override
//    public float modifyBlock(float blockAmount) {
//        return blockAmount + amount;
//    }
//

    @Override
    public AbstractPower makeCopy() {
        return new TensionPower(owner, amount);
    }

    @Override
    public void renderIcons(SpriteBatch sb, float x, float y, Color c) {
        super.renderIcons(sb, x, y, c);

        sb.setColor(c);
        float xPos = owner.dialogX;
        float yPos = owner.dialogY+ 80;
        float xOffset = -200.0F;
        sb.draw(STATIC.TEXTURE_84, xPos + xOffset - 25, yPos - 12.0F, 16.0F, 16.0F, 50.0F, 50.0F, Settings.scale * 1.5F, Settings.scale * 1.5F, 0.0F, 0, 0, 84, 84, false, false);
    }

    @Override
    public void renderAmount(SpriteBatch sb, float x, float y, Color c) {
        super.renderAmount(sb, x, y, c);
        float xPos = owner.dialogX;
        float yPos = owner.dialogY;
        float xOffset = -200.0F;
        FontHelper.renderFontRightTopAligned(sb, FontHelper.cardEnergyFont_L, Integer.toString(this.amount), xPos + xOffset, yPos, 0.7F, c);
    }
}
