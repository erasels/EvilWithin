package sneckomod.actions;

import com.badlogic.gdx.math.MathUtils;
import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.actions.utility.WaitAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.cards.DamageInfo;
import com.megacrit.cardcrawl.core.AbstractCreature;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.vfx.combat.FlashAtkImgEffect;
import sneckomod.cards.AbstractSneckoCard;

public class NoApplyRandomDamageAction extends AbstractGameAction {

    private static final float DURATION = 0.01F;
    private DamageInfo info;
    private int numTimes;
    private int min;
    private int max;
    private AbstractSneckoCard source;
    private int dmg;

    public NoApplyRandomDamageAction(AbstractCreature target, int min, int max, int numTimes, AttackEffect fx, AbstractSneckoCard source) {
        if (min > max) {
            this.min = max;
            this.max = min;
        } else {
            this.min = min;
            this.max = max;
        }

        if (this.max != this.min) {
            dmg = AbstractDungeon.miscRng.random(min, max);
        } else {
            dmg = this.max;
        }
        this.source = source;
        this.info = new DamageInfo(AbstractDungeon.player, dmg);
        this.target = target;
        this.actionType = ActionType.DAMAGE;
        this.attackEffect = fx;
        this.duration = DURATION;
        this.numTimes = numTimes;
    }

    public void update() {
        if (this.target == null) {
            this.isDone = true;
            return;
        }
        if (AbstractDungeon.getCurrRoom().monsters.areMonstersBasicallyDead()) {
            AbstractDungeon.actionManager.clearPostCombatActions();
            this.isDone = true;
            return;
        }
        if (this.target.currentHealth > 0) {
            AbstractDungeon.effectList.add(
                    new FlashAtkImgEffect(
                            this.target.hb.cX, this.target.hb.cY, this.attackEffect
                    )
            );

            float tmp = info.output;
            /*
            for (AbstractPower p : target.powers) {
                tmp = p.atDamageReceive(tmp, info.type);
                if (info.base != (int) tmp) {
                    info.isModified = true;
                }
            }
            for (AbstractPower p : target.powers) {
                tmp = p.atDamageFinalReceive(tmp, info.type);
                if (info.base != (int) tmp) {
                    info.isModified = true;
                }
            }
            */
            info.output = MathUtils.floor(tmp);
            if (info.output < 0) {
                info.output = 0;
            }

            this.target.damage(this.info);
      /*
      AbstractDungeon.actionManager.addToBottom(
          new DamageAction(
              this.target,
              this.info,
              this.attackEffect
          )
      );
      */
            if ((this.numTimes > 1) && (!AbstractDungeon.getMonsters().areMonstersBasicallyDead())) {
                this.numTimes--;
                AbstractDungeon.actionManager.addToTop(
                        new NoApplyRandomDamageAction(
                                AbstractDungeon.getMonsters().getRandomMonster(true),
                                min,
                                max,
                                this.numTimes,
                                attackEffect,
                                source
                        )
                );
            }
            AbstractDungeon.actionManager.addToTop(new WaitAction(0.2F));
        }
        this.isDone = true;
    }
}