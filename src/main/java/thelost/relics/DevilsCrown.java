package thelost.relics;

import com.badlogic.gdx.math.MathUtils;
import com.megacrit.cardcrawl.actions.common.ApplyPowerAction;
import com.megacrit.cardcrawl.actions.common.RelicAboveCreatureAction;
import com.megacrit.cardcrawl.core.AbstractCreature;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.helpers.input.InputHelper;
import thelost.character.TheLost;
import thelost.powers.EvilPower;

import static thelost.BasicMod.makeID;

public class DevilsCrown extends BaseRelic{
    private static final String NAME = "DevilsCrown";
    public static final String ID = makeID(NAME);

    public DevilsCrown() {
        super(ID, NAME, TheLost.Enums.CARD_COLOR, RelicTier.COMMON, LandingSound.FLAT);
    }

    @Override
    public String getUpdatedDescription() {
        return DESCRIPTIONS[0] + 1 + DESCRIPTIONS[1];
    }

    public void atTurnStart() {
        this.flash();
        this.addToBot(new RelicAboveCreatureAction(AbstractDungeon.player, this));
        this.addToBot(new ApplyPowerAction(AbstractDungeon.player, (AbstractCreature)null, new EvilPower(AbstractDungeon.player, 1), 1));
    }

}
