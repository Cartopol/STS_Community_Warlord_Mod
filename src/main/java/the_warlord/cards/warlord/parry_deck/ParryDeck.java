package the_warlord.cards.warlord.parry_deck;

import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.cards.CardGroup;
import com.megacrit.cardcrawl.cards.CardGroup.CardGroupType;

import java.util.ArrayList;

public class ParryDeck {
    public static int playedThisCombatCount = 0;

    public static final CardGroup masterParryDeck = new CardGroup(CardGroupType.UNSPECIFIED);
    public static final ArrayList<AbstractCard> parryOptions = new ArrayList<>();

    public static void reset() {
        masterParryDeck.clear();
        playedThisCombatCount = 0;
    }

    public static ArrayList getParryOptions() {
        parryOptions.clear();
        int nbOptions = 3;
        if (masterParryDeck.size() < 3) {
            nbOptions = masterParryDeck.size();
        }
        masterParryDeck.shuffle();
        for (int i = 0; i < nbOptions; ++i) {
            AbstractCard c = masterParryDeck.getNCardFromTop(i);
            c.applyPowers();
            parryOptions.add(c.makeStatEquivalentCopy());
        }

        return parryOptions;
    }
}
