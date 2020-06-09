package the_warlord.variables;

import basemod.abstracts.DynamicVariable;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import the_warlord.patches.SqueenyInvertForceField;

import static the_warlord.WarlordMod.makeID;

public class InvertForce extends DynamicVariable {
    @Override
    public String key() {
        return makeID("IF");
    }

    @Override
    public int baseValue(AbstractCard card) {
        return AbstractDungeon.player != null ? SqueenyInvertForceField.parriedDamageThisTurn.get(AbstractDungeon.player) : 0;
    }

    @Override
    public int value(AbstractCard card) {
        return baseValue(card);
    }

    @Override
    public boolean isModified(AbstractCard card) {
        return baseValue(card) != 0 ? true : false;
    }

    @Override
    public void setIsModified(AbstractCard card, boolean v) {
        v = isModified(card);
        card.isDamageModified = v;
    }

    @Override
    public boolean upgraded(AbstractCard card) {
        return card.upgraded;
    }
}
