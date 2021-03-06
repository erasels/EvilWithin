package champ.cards;

import champ.ChampMod;
import charbosses.bosses.AbstractCharBoss;
import com.evacipated.cardcrawl.mod.stslib.actions.common.StunMonsterAction;
import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import com.megacrit.cardcrawl.stances.NeutralStance;

public class CheapShot extends AbstractChampCard {

    public final static String ID = makeID("CheapShot");

    //stupid intellij stuff attack, all_enemy, common

    private static final int DAMAGE = 5;

    public CheapShot() {
        super(ID, 2, CardType.ATTACK, CardRarity.RARE, CardTarget.ENEMY);
        baseDamage = DAMAGE;
        tags.add(ChampMod.FINISHER);
        exhaust = true;
    }

    public void use(AbstractPlayer p, AbstractMonster m) {
        dmg(m, AbstractGameAction.AttackEffect.BLUNT_LIGHT);
        atb(new StunMonsterAction(m, p));
        finisher();
    }

    public void upp() {
        upgradeBaseCost(1);
    }
}