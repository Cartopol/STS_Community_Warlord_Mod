package the_warlord.cards.warlord.parry_deck;

import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.actions.common.ApplyPowerAction;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.powers.DexterityPower;
import com.megacrit.cardcrawl.powers.StrengthPower;
import the_warlord.WarlordMod;
import the_warlord.characters.Warlord;

public class HighReward extends CustomParryCard {
    public static final String ID = WarlordMod.makeID(HighReward.class);

    private static final CardRarity RARITY = CardRarity.SPECIAL;
    private static final CardTarget TARGET = CardTarget.SELF;
    private static final CardType TYPE = CardType.SKILL;
    public static final CardColor COLOR = Warlord.Enums.WARLORD_CARD_COLOR;

    private static final int COST = COST_UNPLAYABLE;

    private static final int STR_DEX = 3;
    private static final int UPGRADE_PLUS_STR_DEX = 1;

    public HighReward() {
        super(ID, COST, TYPE, COLOR, RARITY, TARGET);
        this.magicNumber = this.baseMagicNumber = STR_DEX;
    }

    @Override
    public void useParry() {
        AbstractPlayer p = AbstractDungeon.player;
        addToBot(new ApplyPowerAction(p, p, new StrengthPower(p, this.magicNumber), this.magicNumber, true, AbstractGameAction.AttackEffect.NONE));
        addToBot(new ApplyPowerAction(p, p, new DexterityPower(p, this.magicNumber), this.magicNumber, true, AbstractGameAction.AttackEffect.NONE));
        removeFromMasterParryDeck(this);
    }

    @Override
    public void upgrade() {
        if (!upgraded) {
            upgradeName();
            upgradeMagicNumber(UPGRADE_PLUS_STR_DEX);
            upgradeDescription();
        }
    }
}