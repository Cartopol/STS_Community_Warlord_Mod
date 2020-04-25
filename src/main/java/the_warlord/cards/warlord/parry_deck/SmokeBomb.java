package the_warlord.cards.warlord.parry_deck;

import com.megacrit.cardcrawl.actions.common.ApplyPowerAction;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import com.megacrit.cardcrawl.powers.WeakPower;
import the_warlord.WarlordMod;
import the_warlord.characters.Warlord;

public class SmokeBomb extends CustomParryCard {
    public static final String ID = WarlordMod.makeID(SmokeBomb.class.getSimpleName());

    private static final CardRarity RARITY = CardRarity.BASIC;
    private static final CardTarget TARGET = CardTarget.ALL_ENEMY;
    private static final CardType TYPE = CardType.SKILL;
    public static final CardColor COLOR = Warlord.Enums.WARLORD_CARD_COLOR;

    private static final int COST = COST_UNPLAYABLE;

    private static final int WEAK = 1;
    private static final int UPGRADE_PLUS_WEAK = 1;


    public SmokeBomb() {
        super(ID, COST, TYPE, COLOR, RARITY, TARGET);
        magicNumber = baseMagicNumber = WEAK;

    }

    @Override
    public void onChoseThisOption() {
        for (AbstractMonster monster : AbstractDungeon.getCurrRoom().monsters.monsters) {
            if (!monster.isDead && !monster.isDying) {
                addToBot(new ApplyPowerAction(monster, AbstractDungeon.player, new WeakPower(monster, magicNumber, false)));
            }
        }
    }

    @Override
    public void upgrade() {
        if (!upgraded) {
            upgradeMagicNumber(UPGRADE_PLUS_WEAK);

            upgradeName();
            upgradeDescription();
        }
    }
}
