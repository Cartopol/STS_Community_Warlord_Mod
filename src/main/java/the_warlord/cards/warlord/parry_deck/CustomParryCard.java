package the_warlord.cards.warlord.parry_deck;

import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import the_warlord.WarlordMod;
import the_warlord.cards.CustomWarlordModCard;

import static com.megacrit.cardcrawl.cards.AbstractCard.CardType.SKILL;
import static the_warlord.WarlordMod.makeCharPath;

public abstract class CustomParryCard extends CustomWarlordModCard {

    public CustomParryCard(String id, int cost, AbstractCard.CardType type, AbstractCard.CardColor color, AbstractCard.CardRarity rarity, AbstractCard.CardTarget target) {
        super(id, cost, type, color, rarity, target);

        if (type.equals(CardType.ATTACK)) {
            setBackgroundTexture(makeCharPath("warlord/card_bgs/card_bg_parry_attack_512.png"), makeCharPath("warlord/card_bgs/card_bg_parry_attack_1024.png"));
        }
        if (type.equals(SKILL)) {
            setBackgroundTexture(makeCharPath("warlord/card_bgs/card_bg_parry_skill_512.png"), makeCharPath("warlord/card_bgs/card_bg_parry_skill_1024.png"));
        }
        if (type.equals(CardType.POWER)) {
            setBackgroundTexture(makeCharPath("warlord/card_bgs/card_bg_parry_power_512.png"), makeCharPath("warlord/card_bgs/card_bg_parry_power_1024.png"));
        }

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

    @Override
    public void setBackgroundTexture(String backgroundSmallImg, String backgroundLargeImg) {
        super.setBackgroundTexture(backgroundSmallImg, backgroundLargeImg);
    }



}
