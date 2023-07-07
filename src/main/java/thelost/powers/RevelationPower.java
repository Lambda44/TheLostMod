package thelost.powers;

import basemod.interfaces.CloneablePowerInterface;
import com.megacrit.cardcrawl.actions.common.ApplyPowerAction;
import com.megacrit.cardcrawl.actions.defect.ChannelAction;
import com.megacrit.cardcrawl.core.AbstractCreature;
import com.megacrit.cardcrawl.powers.AbstractPower;
import thelost.orbs.Purity;

import static thelost.BasicMod.makeID;

public class RevelationPower extends BasePower implements CloneablePowerInterface {
    public static final String POWER_ID = makeID("Revelation");
    private static final PowerType TYPE = PowerType.BUFF;
    private static final boolean TURN_BASED = false;

    public RevelationPower(AbstractCreature owner, int amount) {
        super(POWER_ID, TYPE, TURN_BASED, owner, amount);
    }

    //public void playApplyPowerSfx() {
    //    CardCrawlGame.sound.play("POWER_METALLICIZE", 0.05F);
    //}

    public void atStartOfTurnPostDraw() {
        this.flash();
        int i;
        for(i = 0; i < this.amount; ++i)
        {
            this.addToBot(new ChannelAction(new Purity()));
        }
    }

    public void stackPower(int stackAmount) {
        if (this.amount == -1) {
            //do nothing, i guess?
        } else {
            this.fontScale = 8.0F;
            this.amount += stackAmount;
            this.updateDescription();
        }
    }

    public void updateDescription() {
        this.description = DESCRIPTIONS[0] + this.amount + DESCRIPTIONS[1];
    }

    @Override
    public AbstractPower makeCopy() {
        return new RevelationPower(owner, amount);
    }
}
