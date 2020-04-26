package the_warlord.cards.warlord.parry_deck;

import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import the_warlord.WarlordMod;
import the_warlord.cards.CustomWarlordModCard;

public abstract class CustomParryCard extends CustomWarlordModCard {

    public CustomParryCard(String id, int cost, AbstractCard.CardType type, AbstractCard.CardColor color, AbstractCard.CardRarity rarity, AbstractCard.CardTarget target) {
        super(id, cost, type, color, rarity, target);
    }

    @Override
    public final void use(AbstractPlayer p, AbstractMonster m) {
        ParryDeck.playedThisCombatCount++;
        onChoseThisOption();
    }

    public static AbstractCard getMasterParryDeckEquivalent(AbstractCard playingCard) {
        for (AbstractCard c : ParryDeck.masterParryDeck.group) {
            if (c.uuid.equals(playingCard.uuid)) {
                return c;
            }
        }
        return null;
    }

    public static void removeFromMasterParryDeck(AbstractCard playingCard) {
        AbstractCard c = getMasterParryDeckEquivalent(playingCard);
        if (c != null) {
            ParryDeck.masterParryDeck.removeCard(c);
            WarlordMod.logger.info(c + " removed from Master Parry Deck");
            WarlordMod.logger.info("Master Parry Deck contains: " + ParryDeck.masterParryDeck);

        }
    }


}
