package the_warlord.powers;

import com.megacrit.cardcrawl.cards.DamageInfo;

public interface OnDamagedPreBlockSubscriber {
    void onDamagedPreBlock(DamageInfo damageInfo);
}
