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
import thelost.powers.TearsPower;

import static thelost.BasicMod.makeID;

public class FlatStone extends BaseRelic{
    private static final String NAME = "FlatStone";
    public static final String ID = makeID(NAME);

    public FlatStone() {
        super(ID, NAME, TheLost.Enums.CARD_COLOR, RelicTier.UNCOMMON, LandingSound.FLAT);
    }

    @Override
    public String getUpdatedDescription() {
        return DESCRIPTIONS[0] + 3 + DESCRIPTIONS[1];
    }

    public void atTurnStart() {
        this.flash();
        this.addToBot(new RelicAboveCreatureAction(AbstractDungeon.player, this));
        this.addToBot(new ApplyPowerAction(AbstractDungeon.player, (AbstractCreature)null, new TearsPower(AbstractDungeon.player, 3), 3));
    }

}
