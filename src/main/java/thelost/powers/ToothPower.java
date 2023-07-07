package thelost.powers;

import basemod.interfaces.CloneablePowerInterface;
import com.megacrit.cardcrawl.actions.common.ApplyPowerAction;
import com.megacrit.cardcrawl.actions.common.GainEnergyAction;
import com.megacrit.cardcrawl.core.AbstractCreature;
import com.megacrit.cardcrawl.powers.AbstractPower;
import com.megacrit.cardcrawl.powers.StrengthPower;
import com.megacrit.cardcrawl.powers.ThornsPower;

import static thelost.BasicMod.makeID;

public class ToothPower extends BasePower implements CloneablePowerInterface {
    public static final String POWER_ID = makeID("ToothAndNail");
    private static final PowerType TYPE = PowerType.BUFF;
    private static final boolean TURN_BASED = false;
    //private static final PowerStrings powerStrings = ;

    public ToothPower(AbstractCreature owner, int amount) {
        super(POWER_ID, TYPE, TURN_BASED, owner, amount);
    }

    //public void playApplyPowerSfx() {
    //    CardCrawlGame.sound.play("POWER_METALLICIZE", 0.05F);
    //}

    public void atStartOfTurnPostDraw() {
        this.flash();
        this.addToBot(new ApplyPowerAction(this.owner, this.owner, new ThornsPower(this.owner, this.amount), this.amount));
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
        return new ToothPower(owner, amount);
    }
}
