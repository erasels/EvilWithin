package sneckomod.cards;


import basemod.abstracts.CustomCard;
import com.badlogic.gdx.graphics.Color;
import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.actions.common.*;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.cards.status.Slimed;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.localization.CardStrings;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import sneckomod.SneckoMod;
import sneckomod.actions.MuddleAction;
import sneckomod.actions.MuddleHandAction;
import sneckomod.actions.MuddleRandomCardAction;


public class Bewildered extends CustomCard {
    public static final String ID = AbstractSneckoCard.makeID("Bewildered");
    public static final String NAME;
    public static final String DESCRIPTION;
    private static final CardType TYPE = CardType.CURSE;
    private static final CardRarity RARITY = CardRarity.CURSE;
    private static final CardTarget TARGET = CardTarget.NONE;
    private static final CardStrings cardStrings;
    private static final int COST = 2;
    public static String UPGRADED_DESCRIPTION;

    private boolean activateThisTurn = false;

    static {
        cardStrings = CardCrawlGame.languagePack.getCardStrings(ID);
        NAME = cardStrings.NAME;
        DESCRIPTION = cardStrings.DESCRIPTION;
        UPGRADED_DESCRIPTION = cardStrings.UPGRADE_DESCRIPTION;
    }

    public Bewildered() {
        super(ID, NAME, SneckoMod.makeCardPath("bewildered.png"), COST, DESCRIPTION, TYPE, CardColor.CURSE, RARITY, TARGET);

        this.magicNumber = this.baseMagicNumber = 1;
        this.exhaust = true;
    }

    public void use(AbstractPlayer p, AbstractMonster m) {
        
    }

    public void triggerOnOtherCardPlayed(AbstractCard c) {
        AbstractDungeon.actionManager.addToBottom(new MuddleHandAction());
        this.flash();
        AbstractDungeon.actionManager.addToBottom(new DiscardSpecificCardAction(this));
    }

    public AbstractCard makeCopy() {
        return new Bewildered();
    }

    public void upgrade() {
    }
}


