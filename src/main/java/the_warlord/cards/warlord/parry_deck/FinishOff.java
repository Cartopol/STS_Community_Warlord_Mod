package the_warlord.cards.warlord.parry_deck;

import com.megacrit.cardcrawl.actions.common.ApplyPowerAction;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import com.megacrit.cardcrawl.powers.DoubleDamagePower;
import com.megacrit.cardcrawl.powers.VulnerablePower;
import the_warlord.WarlordMod;
import the_warlord.characters.Warlord;

public class FinishOff extends CustomParryCard {
    public static final String ID = WarlordMod.makeID(FinishOff.class);

    private static final CardRarity RARITY = CardRarity.RARE;
    private static final CardTarget TARGET = CardTarget.SELF;
    private static final CardType TYPE = CardType.SKILL;
    public static final CardColor COLOR = Warlord.Enums.WARLORD_CARD_COLOR;

    private static final int COST = COST_UNPLAYABLE;
    private static final int VULN_AMOUNT = 1;
    private static final int DOUBLE_DAMAGE_AMOUNT = 1;

    public FinishOff() {
        super(ID, COST, TYPE, COLOR, RARITY, TARGET);
        magicNumber = baseMagicNumber = VULN_AMOUNT;
        urMagicNumber = baseUrMagicNumber = DOUBLE_DAMAGE_AMOUNT;

    }

    @Override
    public void onChoseThisOption() {
        if (this.upgraded) {
            for (AbstractMonster monster : AbstractDungeon.getCurrRoom().monsters.monsters) {
                if (!monster.isDead && !monster.isDying) {
                    addToBot(new ApplyPowerAction(monster, AbstractDungeon.player, new VulnerablePower(monster, magicNumber, false)));
                }
            }
        }
        AbstractPlayer p = AbstractDungeon.player;
        addToBot(new ApplyPowerAction(p, p, new DoubleDamagePower(p, urMagicNumber, false)));

        removeFromMasterParryDeck(this);
    }

    @Override
    public void upgrade() {
        if (!upgraded) {

            upgradeName();
            upgradeDescription();
        }
    }
}
