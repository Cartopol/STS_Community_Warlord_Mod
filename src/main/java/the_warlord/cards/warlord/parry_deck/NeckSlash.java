package the_warlord.cards.warlord.parry_deck;

import com.megacrit.cardcrawl.actions.common.ApplyPowerAction;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import the_warlord.WarlordMod;
import the_warlord.characters.Warlord;
import the_warlord.powers.BleedPower;
import the_warlord.powers.GushPower;

public class NeckSlash extends CustomParryCard {
    public static final String ID = WarlordMod.makeID(NeckSlash.class.getSimpleName());

    private static final CardRarity RARITY = CardRarity.BASIC;
    private static final CardTarget TARGET = CardTarget.ENEMY;
    private static final CardType TYPE = CardType.SKILL;
    public static final CardColor COLOR = Warlord.Enums.WARLORD_CARD_COLOR;

    private static final int COST = COST_UNPLAYABLE;

    private static final int BLEED = 5;
    private static final int UPGRADE_PLUS_BLEED = 2;


    public NeckSlash() {
        super(ID, COST, TYPE, COLOR, RARITY, TARGET);
        magicNumber = baseMagicNumber = BLEED;

    }

    @Override
    public void onChoseThisOption() {
        AbstractMonster m = AbstractDungeon.getMonsters().getRandomMonster(null, true, AbstractDungeon.cardRandomRng);
        AbstractPlayer p = AbstractDungeon.player;
        addToBot(new ApplyPowerAction(m, p, new BleedPower(m, magicNumber)));
        addToBot(new ApplyPowerAction(m, p, new GushPower(m, -1)));
    }

    @Override
    public void upgrade() {
        if (!upgraded) {
            upgradeMagicNumber(UPGRADE_PLUS_BLEED);

            upgradeName();
            upgradeDescription();
        }
    }
}
