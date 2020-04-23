package the_warlord.variables;

import basemod.abstracts.DynamicVariable;
import com.megacrit.cardcrawl.cards.AbstractCard;
import the_warlord.cards.CustomWarlordModCard;

import static the_warlord.WarlordMod.makeID;

// A second "magic" number for highlighting and display on cards, with possibly different upgraded values.
public class MetaMagicNumber extends DynamicVariable {

    // Reference as "!the_warlord:MetaMagic!" in card strings to actually display the number.
    @Override
    public String key() {
        return makeID("MetaMagic");
    }

    @Override
    public boolean isModified(AbstractCard card) {
        return ((CustomWarlordModCard) card).isMetaMagicNumberModified;
    }

    @Override
    public int value(AbstractCard card) {
        return ((CustomWarlordModCard) card).metaMagicNumber;
    }

    @Override
    public int baseValue(AbstractCard card) {
        return ((CustomWarlordModCard) card).baseMetaMagicNumber;
    }

    @Override
    public boolean upgraded(AbstractCard card) {
        return ((CustomWarlordModCard) card).upgradedMetaMagicNumber;
    }
}
